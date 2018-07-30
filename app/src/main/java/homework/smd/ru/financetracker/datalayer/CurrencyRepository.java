package homework.smd.ru.financetracker.datalayer;

import android.support.annotation.NonNull;

import java.util.List;

import homework.smd.ru.financetracker.models.CurrencyRate;
import io.reactivex.Flowable;

public interface CurrencyRepository {
	@NonNull Flowable<List<CurrencyRate>> getCurrencyRates();

	@NonNull Flowable<CurrencyRate> getSystemCurrencyRate();
}
