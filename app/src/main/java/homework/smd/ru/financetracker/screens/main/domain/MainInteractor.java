package homework.smd.ru.financetracker.screens.main.domain;

import android.support.annotation.NonNull;

import homework.smd.ru.financetracker.models.Balance;
import homework.smd.ru.financetracker.models.CurrencyRate;
import io.reactivex.Flowable;

public interface MainInteractor {

	@NonNull Flowable<Balance> getUserBalances();

	@NonNull Flowable<CurrencyRate> getCurrencyRates();

	@NonNull Flowable<CurrencyRate> getSystemCurrencyRate();


	void updateBalance(final Balance balance);
}
