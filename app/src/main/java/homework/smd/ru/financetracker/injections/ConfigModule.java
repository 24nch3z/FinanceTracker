package homework.smd.ru.financetracker.injections;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import homework.smd.ru.financetracker.datalayer.data.sharedpreferences.Configuration;

@Module
public class ConfigModule {

	private final Configuration configuration;

	public ConfigModule(@NonNull Context context) {
		configuration = new Configuration(context.getApplicationContext());
	}

	@Provides
	@Singleton
	public Configuration getConfiguration() {
		return configuration;
	}
}
