package homework.smd.ru.financetracker.screens.addoperation.presentation;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.models.Currency;
import homework.smd.ru.financetracker.models.Expense;
import homework.smd.ru.financetracker.models.Operation;
import homework.smd.ru.financetracker.screens.addoperation.domain.OperationInteractor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class OperationPresenter implements OperationContract.Presenter {

	@Nullable private OperationContract.View view;
	private final Context context;
	private final OperationInteractor interactor;
	private CompositeDisposable cd = new CompositeDisposable();

	List<Expense> expenseList = new ArrayList<>();

	public OperationPresenter(Context context, OperationInteractor interactor) {
		this.context = context;
		this.interactor = interactor;
	}

	@Override
	public void attachView(OperationContract.View view) {
		this.view = view;
		initCategory();
		initExpense();
	}

	private void initExpense() {
		// TODO: спиннер пересоздаётся
		Disposable disposable = interactor.getUserExpenses()
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(expense -> {
				expenseList.add(expense);
				view.setExpense(expenseList);
			});
		cd.add(disposable);
	}

	private void initCategory() {
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

		final String category = view.getCategory();
		final Expense expense = view.getExpense();
		float sum = view.getSum();
		if (sum == 0 || category == null || expense == null) return;

		int checkedRadioButtonTypeId = view.getCheckedRadioButtonId();
		if (checkedRadioButtonTypeId == R.id.radio_button_cost) {
			sum *= -1;
		}

		final Operation operation = new Operation(sum, Currency.RUB, category, expense.getId());
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
