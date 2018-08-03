package homework.smd.ru.financetracker;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.crashlytics.android.Crashlytics;

import homework.smd.ru.financetracker.injections.AppComponent;
import homework.smd.ru.financetracker.injections.AppModule;
import homework.smd.ru.financetracker.injections.ConfigModule;
import homework.smd.ru.financetracker.injections.DaggerAppComponent;
import io.fabric.sdk.android.Fabric;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

public class App extends Application {

	public static App instance;

	private static AppComponent component;

	private Cicerone<Router> cicerone;

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

		cicerone = Cicerone.create();
	}

	public NavigatorHolder getNavigatorHolder() {
		return cicerone.getNavigatorHolder();
	}

	public Router getRouter() {
		return cicerone.getRouter();
	}
}
