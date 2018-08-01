package homework.smd.ru.financetracker.injections;

import javax.inject.Singleton;

import dagger.Component;
import homework.smd.ru.financetracker.screens.ContainerActivity;
import homework.smd.ru.financetracker.screens.addoperation.presentation.OperationView;
import homework.smd.ru.financetracker.screens.detail.presentation.pager.DetailViewPager;
import homework.smd.ru.financetracker.screens.information.presentation.InfoView;
import homework.smd.ru.financetracker.screens.main.presentation.MainView;
import homework.smd.ru.financetracker.screens.settings.presentation.SettingsView;

@Component(modules = {
	AppModule.class,
	ConfigModule.class,
	StubsModule.class,
	PresentersModule.class
})
@Singleton
public interface AppComponent {

	void inject(SettingsView settingsFragment);

	void inject(ContainerActivity activity);

	void inject(MainView view);

	void inject(OperationView view);

	void inject(DetailViewPager view);

	void inject(InfoView view);
}
