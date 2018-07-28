package homework.smd.ru.financetracker.screens.main.presentation;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.screens.main.domain.BalanceModel;
import homework.smd.ru.financetracker.screens.main.domain.MainInteractor;

public class MainPresenter implements MainContract.Presenter {

	@Inject Handler mainHandler;

	@Nullable private MainContract.View view;
	@NonNull private final List<BalanceModel> dataset = new ArrayList<>();
	@NonNull private BalanceAdapter adapter = new BalanceAdapter(dataset);
	@NonNull private MainInteractor interactor = new MainInteractor();

//	@Override
//	public MainView attachView(@NonNull LayoutInflater inflater,
//	                         ViewGroup container,
//	                         Bundle savedInstanceState) {
//		final MainView view = inflater.inflate(R.layout.fragment_main, container, false);
//
//		App.getComponent().inject(this);
//		unbinder = ButterKnife.bind(this, view);
//		updateBalance();
//
//		// Set dollar rate
//		dollarRateView.setText(String.format(
//			Locale.getDefault(),
//			getString(R.string.dollar_rate) + " %10.2f",
//			configuration.getDollarRatio()
//		));
//		if (!configuration.isBalanceVisible()) imageSwitcher.showNext();
//
//		return view;
//	}
//
//	@OnCheckedChanged(R.id.currency_switcher)
//	public void onCurrencyChange(boolean isRuble) {
//		configuration.setRuble(isRuble);
//		updateBalance();
//	}
//
//	@OnClick(R.id.visibility_switcher)
//	public void onVisibilityChange() {
//		boolean isVisible = configuration.isBalanceVisible();
//		configuration.setBalanceVisible(!isVisible);
//		imageSwitcher.showNext();
//		updateBalance();
//	}
//
//	private void updateBalance() {
//
//		final boolean isVisible = configuration.isBalanceVisible();
//		final boolean isRuble = configuration.isRuble();
//		final float dollarRate = configuration.getDollarRatio();
//
//		// Determine the currency (₽ or $)
//		final float balance;
//		final String moneySign;
//		currencySwitcher.setChecked(isRuble);
//		if (isRuble) {
//			balance = configuration.getRubleBalance();
//			moneySign = getString(R.string.ruble_sign);
//		} else {
//			moneySign = getString(R.string.dollar_sign);
//			balance = configuration.getRubleBalance() / dollarRate;
//		}
//
//		if (isVisible) {
//			// Show balance in the correct currency
//			balanceView.setText(String.format(
//				Locale.getDefault(), "%,.2f " + moneySign, balance));
//		} else {
//			// Hide balance
//			balanceView.setText("* * * * * ");
//		}
//	}


	MainPresenter() {
		App.getComponent().inject(this);
		interactor.setCallback(new MainCallback());
	}

	@Override
	public void attachView(@NonNull MainContract.View view) {
		this.view = view;
		this.view.setAdapter(adapter);
		interactor.getUserBalance();
	}

	@Override
	public void detachView() {
		this.view = null;
	}

	private class MainCallback implements MainInteractor.Callback {

		@Override
		public void onSuccessUserBalance(List<BalanceModel> newDataset) {
			for (final BalanceModel balance : newDataset) {
				if (balance.isVisible()) {

				} else {
					// Hide balance
					balance.setStringSum("* * * * * ");
				}
			}
			mainHandler.post(() -> {
				dataset.clear();
				dataset.addAll(newDataset);
				adapter.notifyDataSetChanged();
				if (view != null) {
					view.hideProgress();
				}
			});
		}
	}
}
