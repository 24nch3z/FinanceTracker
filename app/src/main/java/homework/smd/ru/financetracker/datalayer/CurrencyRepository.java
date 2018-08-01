package homework.smd.ru.financetracker.datalayer;

import android.support.annotation.NonNull;

import homework.smd.ru.financetracker.models.CurrencyModelAPI;
import homework.smd.ru.financetracker.models.CurrencyRate;
import io.reactivex.Flowable;

public interface CurrencyRepository {

	@NonNull Flowable<CurrencyModelAPI> getCurrencyRates();

	@NonNull Flowable<CurrencyRate> getSystemCurrencyRate();
}
