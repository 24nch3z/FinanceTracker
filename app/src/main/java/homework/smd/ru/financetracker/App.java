package homework.smd.ru.financetracker;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.support.v7.app.AppCompatDelegate;

import com.crashlytics.android.Crashlytics;

import java.util.concurrent.Executors;

import homework.smd.ru.financetracker.database.AppDatabase;
import homework.smd.ru.financetracker.database.converter.ExpenseConverter;
import homework.smd.ru.financetracker.injections.AppComponent;
import homework.smd.ru.financetracker.injections.AppModule;
import homework.smd.ru.financetracker.injections.ConfigModule;
import homework.smd.ru.financetracker.injections.DaggerAppComponent;
import homework.smd.ru.financetracker.models.Currency;
import homework.smd.ru.financetracker.models.Expense;
import homework.smd.ru.financetracker.models.Operation;
import io.fabric.sdk.android.Fabric;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

public class App extends Application {

	public static App instance;

	private static AppComponent component;
	private Cicerone<Router> cicerone;
	private AppDatabase database;

	public static AppComponent getComponent() {
		return component;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;

		// For using drawable resource at old API
		AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

		// Crash reports
		Fabric.with(this, new Crashlytics());

		// DI by Dagger
		component = DaggerAppComponent.builder()
			.appModule(new AppModule(this))
			.configModule(new ConfigModule(this))
			.build();

		// Cicerone
		cicerone = Cicerone.create();

		// Room database
		database = Room.databaseBuilder(getApplicationContext(),
			AppDatabase.class, "database")
			.allowMainThreadQueries()
			.addCallback(rdc)
			.build();
	}

	RoomDatabase.Callback rdc = new RoomDatabase.Callback(){
		public void onCreate (SupportSQLiteDatabase db) {
			super.onCreate(db);
			Executors.newSingleThreadScheduledExecutor().execute(() -> {
				// TODO: Вынести отсюда

				instance.database.expenseDao().insert(
					new Expense(1, "Наличка", true, 1000)
				);
				instance.database.expenseDao().insert(
					new Expense(2, "Карта сберыча", false, 5000)
				);
				instance.database.expenseDao().insert(
					new Expense(3, "Любовница", true, 500000)
				);

				instance.database.operationDao().insert(
					new Operation(-199, Currency.USD, "Рыбка", 1)
				);
				instance.database.operationDao().insert(
					new Operation(5000, Currency.USD, "Рекит", 2)
				);
				instance.database.operationDao().insert(
					new Operation(300, Currency.USD, "Кино", 2)
				);
				instance.database.operationDao().insert(
					new Operation(50000, Currency.USD, "Алмазы", 3)
				);
			});
		}
	};

	public NavigatorHolder getNavigatorHolder() {
		return cicerone.getNavigatorHolder();
	}

	public Router getRouter() {
		return cicerone.getRouter();
	}

	public AppDatabase getDatabase() {
		return database;
	}
}
