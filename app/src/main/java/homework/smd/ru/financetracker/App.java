package homework.smd.ru.financetracker;

import android.app.Application;

import homework.smd.ru.financetracker.injections.AppComponent;
import homework.smd.ru.financetracker.injections.ConfigModule;
import homework.smd.ru.financetracker.injections.DaggerAppComponent;

public class App extends Application {

	private static AppComponent component;

	public static AppComponent getComponent() {
		return component;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		// DI by Dagger
		component = DaggerAppComponent.builder()
			.configModule(new ConfigModule(this))
			.build();
	}
}
