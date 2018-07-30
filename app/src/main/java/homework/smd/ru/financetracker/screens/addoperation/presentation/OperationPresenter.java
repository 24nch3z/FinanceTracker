package homework.smd.ru.financetracker.screens.addoperation.presentation;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

import homework.smd.ru.financetracker.models.Currency;
import homework.smd.ru.financetracker.models.Operation;
import homework.smd.ru.financetracker.screens.addoperation.domain.OperationInteractor;
import homework.smd.ru.financetracker.screens.addoperation.domain.OperationInteractorStub;

public class OperationPresenter implements OperationContract.Presenter {

	private final OperationInteractor interactor = new OperationInteractorStub();
	@Nullable private OperationContract.View view;

	@Override
	public void attachView(OperationContract.View view) {
		this.view = view;
		view.hideCategory();
		final List<String> categories = view.getDefaultCategories();
		view.setCategories(categories);
		view.setOnCategoriesClickListener(new OnCategoryClickListener());
	}

	@Override
	public void detachView() {
		this.view = null;
	}

	@Override
	public void createOperation(boolean isPlus) {
		if (view == null) return;

		final String category = view.getCategory();
		float sum = view.getSum();
		if (sum == 0 || category == null) return;
		if (!isPlus) sum *= -1;

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
