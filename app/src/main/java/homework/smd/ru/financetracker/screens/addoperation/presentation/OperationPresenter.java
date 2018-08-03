package homework.smd.ru.financetracker.screens.addoperation.presentation;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import java.util.Arrays;
import java.util.List;

import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.models.Currency;
import homework.smd.ru.financetracker.models.Operation;
import homework.smd.ru.financetracker.screens.addoperation.domain.OperationInteractor;

public class OperationPresenter implements OperationContract.Presenter {

	@Nullable private OperationContract.View view;
	private final Context context;
	private final OperationInteractor interactor;

	public OperationPresenter(Context context, OperationInteractor interactor) {
		this.context = context;
		this.interactor = interactor;
	}

	@Override
	public void attachView(OperationContract.View view) {
		this.view = view;
		view.hideCategory();
		final List<String> categories = Arrays.asList(
			context.getResources().getStringArray(R.array.default_categories));
		view.setCategories(categories);
		view.setOnCategoriesClickListener(new OnCategoryClickListener());
	}

	@Override
	public void detachView() {
		this.view = null;
	}

	@Override
	public void createOperation() {
		if (view == null) return;

		final String category = view.getCategory();
		float sum = view.getSum();
		if (sum == 0 || category == null) return;

		int checkedRadioButtonTypeId = view.getCheckedRadioButtonId();
		if (checkedRadioButtonTypeId == R.id.radio_button_cost) {
			sum *= -1;
		}

		final Operation operation = new Operation(sum, Currency.RUB, category);
		interactor.addOperation(operation);
		view.back();
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
		public void onNothingSelected(AdapterView<?> adapterView) { }
	}
}
