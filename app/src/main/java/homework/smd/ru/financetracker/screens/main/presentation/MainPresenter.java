package homework.smd.ru.financetracker.screens.main.presentation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import homework.smd.ru.financetracker.models.Currency;
import homework.smd.ru.financetracker.models.CurrencyRate;
import homework.smd.ru.financetracker.models.Expense;
import homework.smd.ru.financetracker.models.UtilsKt;
import homework.smd.ru.financetracker.screens.main.domain.MainInteractor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainContract.Presenter {

	@Nullable private MainContract.View view;
	@NonNull private final List<Expense> dataset = new ArrayList<>();
	@NonNull private BalanceRecycleAdapter adapter = new BalanceRecycleAdapter(dataset);

	private final MainInteractor interactor;
	private Currency currency;
	private float rate;
	private CompositeDisposable cd = new CompositeDisposable();

	public MainPresenter(MainInteractor interactor) {
		this.interactor = interactor;

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
			.getUserExpenses()
			.observeOn(Schedulers.computation())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(expenses -> {
				dataset.clear();
				dataset.addAll(expenses);
				adapter.notifyDataSetChanged();
				if (this.view != null) this.view.hideProgress();
			});

		cd.add(disposable);

		disposable = interactor
			.getCurrencyRates()
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(
				new OnUpdateCurrencyRates(),
				err -> { if (this.view != null) this.view.hideCurrencies(); }
			);
		cd.add(disposable);
	}

	@Override
	public void detachView() {
		this.view = null;
		cd.clear();
	}

	private class OnUpdateCurrencyRates implements Consumer<CurrencyRate> {
		@Override
		public void accept(CurrencyRate rate) {
			if (MainPresenter.this.view == null) return;

			if (MainPresenter.this.currency == rate.getCurrency()) {
				MainPresenter.this.rate = rate.getRate();
			}
			if (MainPresenter.this.view == null) return;
			if (rate.getCurrency() == Currency.EUR) {
				MainPresenter.this.view.updateRateEUR(UtilsKt.moneyFormat(rate.getRate()));
			}
			if (rate.getCurrency() == Currency.USD) {
				MainPresenter.this.view.updateRateUSD(UtilsKt.moneyFormat(rate.getRate()));
			}
			MainPresenter.this.view.showCurrencies();
		}
	}

	private class OnChangeVisibility implements BalanceRecycleAdapter.OnContentClick {
		@Override
		public void onClick(int position) {
			final Expense expense = dataset.get(position);
			expense.isVisible = !expense.isVisible;
			interactor.updateExpense(expense);
			adapter.notifyItemChanged(position);
		}
	}

	private class OnHolderClick implements BalanceRecycleAdapter.OnContentClick {
		@Override
		public void onClick(int position) {
			if (view != null) {
				view.navigationToDetail(position);
			}
		}
	}
}
