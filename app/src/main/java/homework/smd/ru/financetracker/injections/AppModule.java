package homework.smd.ru.financetracker.injections;

import android.content.Context;
import android.os.Handler;
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
	public Context getContext() {
		return context;
	}

	@Provides
	@Singleton
	public Handler getMainHandler() {
		return new Handler(context.getMainLooper());
	}
}
