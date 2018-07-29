package homework.smd.ru.financetracker.screens.main.domain;

import android.support.annotation.NonNull;

import homework.smd.ru.financetracker.models.Balance;
import homework.smd.ru.financetracker.models.CurrencyRate;
import homework.smd.ru.financetracker.screens.main.data.MainRepositoryStub;
import io.reactivex.Flowable;

public class MainInteractorImpl implements MainInteractor {

	private final MainRepository repository = new MainRepositoryStub();

	@NonNull
	@Override
	public Flowable<Balance> getUserBalances() {
		return repository
			.getUserBalances()
			.flatMap(Flowable::fromIterable);
	}

	@NonNull
	@Override
	public Flowable<CurrencyRate> getCurrencyRates() {
		return repository
			.getCurrencyRates()
			.flatMap(Flowable::fromIterable);
	}

	@NonNull
	@Override
	public Flowable<CurrencyRate> getSystemCurrencyRate() {
		return repository.getSystemCurrencyRate();
	}

	@Override
	public void updateBalance(Balance balance) {

	}
}
