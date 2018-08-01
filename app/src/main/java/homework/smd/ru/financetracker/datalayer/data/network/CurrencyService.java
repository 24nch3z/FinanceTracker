package homework.smd.ru.financetracker.datalayer.data.network;

import homework.smd.ru.financetracker.models.CurrencyModelAPI;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrencyService {

	@GET("convert")
	Flowable<CurrencyModelAPI> updateCurrency(
		@Query("q") String currencyRatio
	);
}
