package homework.smd.ru.financetracker.screens.addoperation.presentation;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.models.Currency;
import homework.smd.ru.financetracker.models.Operation;
import homework.smd.ru.financetracker.models.Period;
import homework.smd.ru.financetracker.models.Wallet;
import homework.smd.ru.financetracker.screens.addoperation.domain.OperationInteractor;
import io.reactivex.disposables.CompositeDisposable;

public class OperationPresenter implements OperationContract.Presenter {

	@Nullable private OperationContract.View view;
	private Wallet wallet;
	private final OperationInteractor interactor;
	private CompositeDisposable cd = new CompositeDisposable();

	public OperationPresenter(OperationInteractor interactor) {
		this.interactor = interactor;
	}

	@Override
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	@Override
	public void attachView(OperationContract.View view, Context context) {
		this.view = view;
		initCategory(context);
	}

	private void initCategory(Context context) {
		view.hideCategory();
		final List<String> categories = Arrays.asList(
			context.getResources().getStringArray(R.array.default_categories));
		view.setCategories(categories);
		view.setOnCategoriesClickListener(new OnCategoryClickListener());
	}

	@Override
	public void detachView() {
		this.view = null;
		cd.clear();
	}

	@Override
	public void createOperation() {
		if (view == null) return;

		if (checkForSave()) {
			final String category = view.getCategory();
			float sum = view.getSum();
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
			final Operation operation = new Operation(sum, Currency.RUB, category, walletId);
			interactor.addOperation(operation, period);
			view.back();
		}
	}

	private boolean checkForSave() {
		float sum = view.getSum();

		if (sum == 0) {
			view.showHideSumError(true);
			return false;
		}

		return true;
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
		public void onNothingSelected(AdapterView<?> adapterView) {
		}
	}
}
