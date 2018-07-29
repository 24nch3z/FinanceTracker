package homework.smd.ru.financetracker.screens.main.domain;

import android.support.annotation.NonNull;

import java.util.List;

import homework.smd.ru.financetracker.models.Balance;
import homework.smd.ru.financetracker.models.CurrencyRate;
import io.reactivex.Flowable;

public interface MainRepository {

	@NonNull Flowable<List<Balance>> getUserBalances();

	@NonNull Flowable<List<CurrencyRate>> getCurrencyRates();
	
	@NonNull Flowable<CurrencyRate> getSystemCurrencyRate();
}
