package homework.smd.ru.financetracker.screens.addoperation.presentation;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import homework.smd.ru.financetracker.BasePresenter;
import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.database.Converters;
import homework.smd.ru.financetracker.models.Currency;
import homework.smd.ru.financetracker.models.Operation;
import homework.smd.ru.financetracker.models.OperationTemplate;
import homework.smd.ru.financetracker.models.Period;
import homework.smd.ru.financetracker.models.Wallet;
import homework.smd.ru.financetracker.screens.addoperation.domain.OperationInteractor;
import io.reactivex.disposables.CompositeDisposable;

public class OperationPresenter extends BasePresenter<OperationContract.View> {

	private Wallet wallet;
	private final OperationInteractor interactor;
	private OperationViewModel viewModel;
	private CompositeDisposable cd = new CompositeDisposable();

	private List<Currency> currencies;
	private List<String> categories;

	public OperationPresenter(OperationInteractor interactor) {
		this.interactor = interactor;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public void setViewModel(OperationViewModel viewModel) {
		this.viewModel = viewModel;
	}

	public void initViews(Context context) {
		view.setSum(viewModel.sum == 0 ? "" : String.valueOf(viewModel.sum));
		view.setDate(new Converters().fromDateToString(viewModel.operationDate)); // TODO: Вынести
		initCategories(context);
		initCurrencies();
		view.showHidePeriodForm(viewModel.isPeriod);
		view.setPeriodDays(viewModel.isPeriod && viewModel.periodDays > 0 ?
			String.valueOf(viewModel.periodDays) : "");
		view.setType(viewModel.isIncome);
		view.showHideOtherCategory(viewModel.isOtherCategory);
		if (viewModel.isOtherCategory) {
			view.setCategoryInput(viewModel.otherCategory);
		}
	}

	public void setSum(String s) {
		double sum = 0;
		if (s.trim().length() != 0) {
			try {
				sum = Double.parseDouble(s);
			} catch (NumberFormatException e) { }
		}
		viewModel.sum = sum;
		view.showHideSumError(false);
	}

	public void setDate(Date date) {
		viewModel.operationDate = date;
		view.setDate(new Converters().fromDateToString(viewModel.operationDate));
	}

	private void initCategories(Context context) {
		categories = Arrays.asList(context.getResources()
			.getStringArray(R.array.default_categories));
		int selection = viewModel.categoryPosition;
		view.setCategories(categories, selection);
	}

	private void initCurrencies() {
		currencies = Arrays.asList(Currency.values());
		List<String> spinnerList = new ArrayList<>();
		for (Currency currency : currencies) spinnerList.add(currency.name());
		int defaultPosition = viewModel.currencyPosition;
		view.setCurrencies(spinnerList, defaultPosition);
	}

	@Override
	public void detachView() {
		this.view = null;
		cd.clear();
	}

	public void createOperation() {
		if (view == null) return;

		if (validate()) {
			Operation operation = new Operation();

			if (viewModel.isOtherCategory) {
				operation.category = viewModel.otherCategory;
			} else {
				operation.category = categories.get(viewModel.categoryPosition);
			}

			operation.currency = currencies.get(viewModel.currencyPosition);
			operation.expenseId = wallet.id;
			operation.operationDate = viewModel.operationDate;
			operation.sum = viewModel.sum;

			if (!viewModel.isIncome) {
				operation.sum *= -1;
			}

			boolean isPeriod = viewModel.isPeriod;
			Period period = null;
			if (isPeriod) {
				period = new Period();
				period.days = viewModel.periodDays;
				period.lastOperationDate = new Date();
			}

			view.back();
			interactor.addOperation(operation, period);
		}
	}

	private boolean validate() {
		boolean flagAllOk = true;

		if (viewModel.sum == 0) {
			view.showHideSumError(true);
			flagAllOk = false;
		}

		if (viewModel.isPeriod && viewModel.periodDays == 0) {
			view.showHidePeriodError(true);
			flagAllOk = false;
		}

		if (viewModel.isOtherCategory && viewModel.otherCategory.trim().length() == 0) {
			view.showHideOtherCategoryError(true);
			flagAllOk = false;
		}

		return flagAllOk;
	}

	public void setTemplate(OperationTemplate template, Context context) {
		viewModel.sum = template.sum;

		Currency currency = template.currency;
		for (int i = 0; i < currencies.size(); i++) {
			if (currency == currencies.get(i)) {
				viewModel.currencyPosition = i;
				break;
			}
		}

		String category = template.category;
		int categoryPosition = -1;
		for (int i = 0; i < categories.size(); i++) {
			if (category.equals(categories.get(i))) {
				categoryPosition = i;
				break;
			}
		}
		if (categoryPosition == -1) {
			viewModel.categoryPosition = categories.size() - 1; // Other
			viewModel.isOtherCategory = true;
			viewModel.otherCategory = category;
		} else {
			viewModel.categoryPosition = categoryPosition;
			viewModel.isOtherCategory = false;
			viewModel.otherCategory = "";
		}

		viewModel.isIncome = template.isIncome;

		initViews(context);
	}

	public void setCategoryPosition(int position) {
		int categoriesLength = categories.size();
		viewModel.categoryPosition = position;

		if (position + 1 == categoriesLength) {
			view.showHideOtherCategory(true);
			viewModel.isOtherCategory = true;
		} else {
			view.showHideOtherCategory(false);
			viewModel.isOtherCategory = false;
			viewModel.otherCategory = "";
			view.setCategoryInput("");
		}

		view.showHideOtherCategoryError(false);
	}

	public void setOtherCategory(String s) {
		viewModel.otherCategory = s;
		view.showHideOtherCategoryError(false);
	}

	public void setCurrencyPosition(int position) {
		viewModel.currencyPosition = position;
	}

	public void setType(boolean isIncome) {
		viewModel.isIncome = isIncome;
	}

	public void setPeriodFlag(boolean flag) {
		viewModel.isPeriod = flag;
		view.showHidePeriodForm(flag);
		view.showHidePeriodError(false);
	}

	public void setPeriodDays(String s) {
		view.showHidePeriodError(false);
		int periodDays = 0;
		if (s.trim().length() != 0) {
			try {
				periodDays = Integer.parseInt(s);
			} catch (NumberFormatException e) { }
		}
		viewModel.periodDays = periodDays;
	}
}
