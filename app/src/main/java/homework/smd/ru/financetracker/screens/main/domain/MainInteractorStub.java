package homework.smd.ru.financetracker.screens.main.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import homework.smd.ru.financetracker.models.Currency;
import homework.smd.ru.financetracker.models.CurrencyRate;

public class MainInteractorStub {

	@Nullable private Callback callback;
	private final Executor executor = Executors.newSingleThreadExecutor();

	public void getUserBalance() {
		executor.execute(() -> {
			final ArrayList<BalanceModel> balances = new ArrayList<>();
			balances.add(new BalanceModel("Наличка", true, 42000));
			balances.add(new BalanceModel("Зарплата", false, 100500));

			if (callback != null) {
				callback.onSuccessUserBalance(balances);
			}
		});
	}

	public Currency getSystemCurrency() {
		return Currency.USD;
	}

	public float getLastRate() {
		return 62.80f;
	}

	public void updateCurrencyRates() {
		executor.execute(() -> {
			final List<CurrencyRate> rates = new LinkedList<>();
			rates.add(new CurrencyRate(Currency.USD, 62.80f, true));
			rates.add(new CurrencyRate(Currency.EUR, 73.23f, false));
			if (callback != null) {
				callback.onUpdateCurrencyRates(rates);
			}
		});
	}

	public void updateBalance(@NonNull final BalanceModel balance) {

	}

	public void setCallback(@Nullable Callback callback) {
		this.callback = callback;
	}

	public interface Callback {
		void onSuccessUserBalance(List<BalanceModel> balances);

		void onUpdateCurrencyRates(List<CurrencyRate> rates);
	}
}
