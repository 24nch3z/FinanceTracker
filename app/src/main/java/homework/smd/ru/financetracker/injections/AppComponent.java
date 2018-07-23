package homework.smd.ru.financetracker.injections;

import javax.inject.Singleton;

import dagger.Component;
import homework.smd.ru.financetracker.MainActivity;
import homework.smd.ru.financetracker.fragments.MainFragment;
import homework.smd.ru.financetracker.fragments.SettingsFragment;

@Component(modules = { AppModule.class, ConfigModule.class })
@Singleton
public interface AppComponent {

	void inject(SettingsFragment settingsFragment);

	void inject(MainActivity mainActivity);

	void inject(MainFragment mainFragment);
}
