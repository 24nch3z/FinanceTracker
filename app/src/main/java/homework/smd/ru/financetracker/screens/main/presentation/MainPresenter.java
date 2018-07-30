package homework.smd.ru.financetracker.screens.main.presentation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.models.Balance;
import homework.smd.ru.financetracker.models.Currency;
import homework.smd.ru.financetracker.models.CurrencyRate;
import homework.smd.ru.financetracker.models.UtilsKt;
import homework.smd.ru.financetracker.screens.main.domain.MainInteractorImpl;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainContract.Presenter {

	@Nullable private MainContract.View view;
	@NonNull private final List<Balance> dataset = new ArrayList<>();
	@NonNull private BalanceRecycleAdapter adapter = new BalanceRecycleAdapter(dataset);
	@NonNull private MainInteractorImpl interactor = new MainInteractorImpl();

	private Currency currency;
	private float rate;
	private CompositeDisposable cd = new CompositeDisposable();

	MainPresenter() {
		App.getComponent().inject(this);

		adapter.setOnCardClickListener(new OnHolderClick());
		adapter.setOnImageClickListener(new OnChangeVisibility());

		Disposable disposable = interactor
			.getSystemCurrencyRate()
			.subscribe(currencyRate -> {
				currency = currencyRate.getCurrency();
				rate = currencyRate.getRate();
			});
		cd.add(disposable);
	}

	@Override
	public void attachView(@NonNull MainContract.View view) {

		this.view = view;
		this.view.setAdapter(adapter);

		Disposable disposable = interactor
			.getUserBalances()
			.observeOn(Schedulers.computation())
			.map(this::processBalance)
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(balance -> {
				dataset.add(balance);
				adapter.notifyItemInserted(dataset.size() - 1);
				if (this.view != null) this.view.hideProgress();
			});
		cd.add(disposable);

		disposable = interactor
			.getCurrencyRates()
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new OnUpdateCurrencyRates());
		cd.add(disposable);
	}

	@Override
	public void detachView() {
		this.view = null;
		cd.clear();
	}

	private Balance processBalance(@NonNull final Balance model) {

		float sum = model.getSum();
		String moneySign;
		if (currency == Currency.USD) {
			sum /= rate;
			moneySign = "$";
		} else {
			moneySign = "P";
		}

		if (model.isVisible()) {
			// Show balance in correct currency
			model.setStringSum(UtilsKt.moneyFormat(sum) + " " + moneySign);
		} else {
			// Hide balance
			model.setStringSum("* * * * * *");
		}
		return model;
	}

	private class OnUpdateCurrencyRates implements Consumer<CurrencyRate> {
		@Override
		public void accept(CurrencyRate rate) {
			if (MainPresenter.this.currency == rate.getCurrency()) {
				MainPresenter.this.rate = rate.getRate();
			}
			if (MainPresenter.this.view == null) return;
			if (rate.getCurrency() == Currency.EUR) {
				MainPresenter.this.view.updateRateEUR(String.format(
					Locale.getDefault(), "%.2f", rate.getRate()
				));
			}
			if (rate.getCurrency() == Currency.USD) {
				MainPresenter.this.view.updateRateUSD(String.format(
					Locale.getDefault(), "%.2f", rate.getRate()
				));
			}
		}
	}

	private class OnChangeVisibility implements BalanceRecycleAdapter.OnContentClick {
		@Override
		public void onClick(int position) {
			final Balance balance = dataset.get(position);
			balance.changeVisibility();
			processBalance(balance);
			adapter.notifyItemChanged(position);
			interactor.updateBalance(balance);
		}
	}

	private class OnHolderClick implements BalanceRecycleAdapter.OnContentClick {
		@Override
		public void onClick(int position) {

		}
	}
}
