package homework.smd.ru.financetracker.screens.addoperation.presentation;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import homework.smd.ru.financetracker.BasePresenter;
import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.database.Converters;
import homework.smd.ru.financetracker.models.Currency;
import homework.smd.ru.financetracker.models.Operation;
import homework.smd.ru.financetracker.models.Period;
import homework.smd.ru.financetracker.models.Wallet;
import homework.smd.ru.financetracker.screens.addoperation.domain.OperationInteractor;
import io.reactivex.disposables.CompositeDisposable;

public class OperationPresenter extends BasePresenter<OperationContract.View> {

	private Wallet wallet;
	private final OperationInteractor interactor;
	private OperationViewModel viewModel;
	private CompositeDisposable cd = new CompositeDisposable();

	public OperationPresenter(OperationInteractor interactor) {
		this.interactor = interactor;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public void setViewModel(OperationViewModel viewModel) {
		this.viewModel = viewModel;
	}

	public void init(Context context) {
		view.setSum(viewModel.sum == 0 ? "" : String.valueOf(viewModel.sum));
		view.setDate(new Converters().fromDateToString(viewModel.operationDate));

		initCategory(context);
		initCurrency();
	}

	public void setSum(String s) {
		double sum = 0;
		if (s.trim().length() != 0) {
			try {
				sum = Double.parseDouble(s);
			} catch (NumberFormatException e) { }
		}
		viewModel.sum = sum;
	}

	public void setDate(Date date) {
		viewModel.operationDate = date;
		view.setDate(new Converters().fromDateToString(viewModel.operationDate));
	}

	private void initCategory(Context context) {
		view.hideCategory();
		final List<String> categories = Arrays.asList(
			context.getResources().getStringArray(R.array.default_categories));
		view.setCategories(categories, 0);
		view.setOnCategoriesClickListener(new OnCategoryClickListener());
	}

	private void initCurrency() {
		List<Currency> currencyList = Arrays.asList(Currency.values());
		List<String> spinnerList = new ArrayList<>();
		for (Currency currency : currencyList) spinnerList.add(currency.name());
		int defaultPosition = 1;
		view.setCurrencies(spinnerList, defaultPosition);
	}

	@Override
	public void detachView() {
		this.view = null;
		cd.clear();
	}

	public void createOperation() {
		if (view == null) return;

		if (checkForSave()) {
			final String category = view.getCategory();

			double sum = viewModel.sum;

			if (sum == 0 || category == null) return;

			int checkedRadioButtonTypeId = view.getCheckedRadioButtonId();
			if (checkedRadioButtonTypeId == R.id.radio_button_cost) {
				sum *= -1;
			}

			boolean isPeriod = view.getIsPeriod();
			Period period = null;
			if (isPeriod) {
				int days = view.getPeriodDays();
				if (days == 0) return;
				period = new Period();
				period.days = days;
				period.lastOperationDate = new Date();
			}

			int walletId = wallet.getId();
			final Operation operation = new Operation(sum, Currency.RUB, category,
				walletId, viewModel.operationDate);
			interactor.addOperation(operation, period);
			view.back();
		}
	}

	private boolean checkForSave() {
		boolean flagAllOk = true;

		if (viewModel.sum == 0) {
			view.showHideSumError(true);
			flagAllOk = false;
		}

		if (view.getIsPeriod() && view.getPeriodDays() == 0) {
			view.showHidePeriodError(true);
			flagAllOk = false;
		}

		return flagAllOk;
	}

	private class OnCategoryClickListener implements AdapterView.OnItemSelectedListener {
		@Override
		public void onItemSelected(AdapterView<?> adapterView, View v, int position, long l) {
			if (view == null) return;
			if (position == adapterView.getCount() - 1) {
				view.showCategory();
			} else {
				view.hideCategory();
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> adapterView) {}
	}
}
