package homework.smd.ru.financetracker.injections;

import javax.inject.Singleton;

import dagger.Component;
import homework.smd.ru.financetracker.datalayer.CurrencyRepository;
import homework.smd.ru.financetracker.screens.ContainerActivity;
import homework.smd.ru.financetracker.screens.main.presentation.MainPresenter;
import homework.smd.ru.financetracker.screens.main.presentation.MainView;
import homework.smd.ru.financetracker.screens.settings.presentation.SettingsView;

@Component(modules = {
	AppModule.class,
	ConfigModule.class,
	NetworkCurrencyModule.class
})
@Singleton
public interface AppComponent {

	void inject(SettingsView settingsFragment);

	void inject(ContainerActivity activity);

	void inject(MainView mainFragment);

	void inject(MainPresenter presenter);

	void inject(CurrencyRepository repository);
}
