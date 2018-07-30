package homework.smd.ru.financetracker.datalayer.data.network;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrencyService {

	@GET("convert")
	Flowable<Object> updateCurrency(
		@Query("q") String currencyRatio,
		@Query("compact") String compactForm
	);
}
