package homework.smd.ru.financetracker;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.crashlytics.android.Crashlytics;

import homework.smd.ru.financetracker.injections.AppComponent;
import homework.smd.ru.financetracker.injections.AppModule;
import homework.smd.ru.financetracker.injections.ConfigModule;
import homework.smd.ru.financetracker.injections.DaggerAppComponent;
import io.fabric.sdk.android.Fabric;

public class App extends Application {

	private static AppComponent component;

	public static AppComponent getComponent() {
		return component;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		// For using drawable resource at old API
		AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

		// Crash reports
		Fabric.with(this, new Crashlytics());

		// DI by Dagger
		component = DaggerAppComponent.builder()
			.appModule(new AppModule(this))
			.configModule(new ConfigModule(this))
			.build();
	}
}
