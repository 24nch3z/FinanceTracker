package homework.smd.ru.financetracker.injections;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import homework.smd.ru.financetracker.datalayer.data.network.CurrencyAPI;
import homework.smd.ru.financetracker.datalayer.data.network.CurrencyService;

@Module
public class NetworkCurrencyModule {
	private final CurrencyAPI currencyAPI;

	public NetworkCurrencyModule() {
		currencyAPI = new CurrencyAPI();
	}

	@Provides
	@Singleton
	CurrencyService getService() {
		return currencyAPI.getCurrencyService();
	}
}
