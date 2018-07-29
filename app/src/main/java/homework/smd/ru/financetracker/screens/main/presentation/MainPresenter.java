package homework.smd.ru.financetracker.screens.main.presentation;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.models.Currency;
import homework.smd.ru.financetracker.models.CurrencyRate;
import homework.smd.ru.financetracker.screens.main.domain.BalanceModel;
import homework.smd.ru.financetracker.screens.main.domain.MainInteractorStub;

public class MainPresenter implements MainContract.Presenter {

	@Inject Handler mainHandler;

	@Nullable private MainContract.View view;
	@NonNull private final List<BalanceModel> dataset = new ArrayList<>();
	@NonNull private BalanceAdapter adapter = new BalanceAdapter(dataset);
	@NonNull private MainInteractorStub interactor = new MainInteractorStub();
	@NonNull private Currency currency;
	private float rate;

	MainPresenter() {
		App.getComponent().inject(this);

		adapter.setOnCardClickListener(new OnHolderClick());
		adapter.setOnImageClickListener(new OnChangeVisibility());
		interactor.setCallback(new MainCallback());

		currency = interactor.getSystemCurrency();
		rate = interactor.getLastRate();
	}

	@Override
	public void attachView(@NonNull MainContract.View view) {
		this.view = view;
		this.view.setAdapter(adapter);
		interactor.updateCurrencyRates();
		interactor.getUserBalance();
	}

	@Override
	public void detachView() {
		this.view = null;
	}

	private void processBalance(@NonNull final BalanceModel model) {

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
			model.setStringSum(String.format(
				Locale.getDefault(), "%,.2f " + moneySign, sum
			));
		} else {
			// Hide balance
			model.setStringSum("* * * * * *");
		}
	}


	private class MainCallback implements MainInteractorStub.Callback {

		@Override
		public void onSuccessUserBalance(final List<BalanceModel> newDataset) {
			for (final BalanceModel balance : newDataset) processBalance(balance);
			mainHandler.post(() -> {
				dataset.clear();
				dataset.addAll(newDataset);
				adapter.notifyDataSetChanged();
			});
			if (view != null) view.hideProgress();
		}

		@Override
		public void onUpdateCurrencyRates(List<CurrencyRate> rates) {
			if (view == null) return;

			for (final CurrencyRate rate : rates) {

				if (MainPresenter.this.currency == rate.getCurrency()) {
					MainPresenter.this.rate = rate.getRate();
				}

				if (rate.getCurrency() == Currency.EUR) {
					view.updateRateEUR(String.format(
						Locale.getDefault(), "%.2f", rate.getRate()
					));
				}

				if (rate.getCurrency() == Currency.USD) {
					view.updateRateUSD(String.format(
						Locale.getDefault(), "%.2f", rate.getRate()
					));
				}
			}
		}
	}

	private class OnChangeVisibility implements BalanceAdapter.OnContentClick {
		@Override
		public void onClick(int position) {
			final BalanceModel balance = dataset.get(position);
			balance.changeVisibility();
			processBalance(balance);
			adapter.notifyItemChanged(position);
			interactor.updateBalance(balance);
		}
	}

	private class OnHolderClick implements BalanceAdapter.OnContentClick {
		@Override
		public void onClick(int position) {

		}
	}
}
