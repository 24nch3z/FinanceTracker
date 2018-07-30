package homework.smd.ru.financetracker.datalayer.data.network;

import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CurrencyAPI {

	private final static String CURRENCY_API_URL = "http://free.currencyconverterapi.com/api/v5/";

	private final CurrencyService currencyService;

	public CurrencyAPI() {

		final RxJava2CallAdapterFactory rxAdapter =
			RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
		final Converter.Factory gsonFactory =
			GsonConverterFactory.create(new GsonBuilder().create());

		final Retrofit retrofit = new Retrofit.Builder()
			.baseUrl(CURRENCY_API_URL)
			.addConverterFactory(gsonFactory)
			.addCallAdapterFactory(rxAdapter)
			.build();

		currencyService = retrofit.create(CurrencyService.class);
	}

	public CurrencyService getCurrencyService() {
		return currencyService;
	}
}
