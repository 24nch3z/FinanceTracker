package homework.smd.ru.financetracker.injections;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

	private final Context context;

	public AppModule(@NonNull Context context) {
		this.context = context.getApplicationContext();
	}

	@Provides
	@Singleton
	public Context provideContext() {
		return context;
	}
}
