package homework.smd.ru.financetracker.injections;

import javax.inject.Singleton;

import dagger.Component;
import homework.smd.ru.financetracker.screens.ContainerActivity;
import homework.smd.ru.financetracker.screens.main.presentation.View;

@Component(modules = { AppModule.class, ConfigModule.class })
@Singleton
public interface AppComponent {

	void inject(homework.smd.ru.financetracker.screens.settings.presentation.View settingsFragment);

	void inject(ContainerActivity activity);

	void inject(View mainFragment);
}
