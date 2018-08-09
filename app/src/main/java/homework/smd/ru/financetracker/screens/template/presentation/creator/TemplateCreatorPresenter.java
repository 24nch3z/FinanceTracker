package homework.smd.ru.financetracker.screens.template.presentation.creator;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import homework.smd.ru.financetracker.BasePresenter;
import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.models.Currency;
import homework.smd.ru.financetracker.models.OperationTemplate;
import homework.smd.ru.financetracker.screens.template.domain.TemplateInteractor;
import homework.smd.ru.financetracker.utils.MyLog;

public class TemplateCreatorPresenter extends BasePresenter<TemplateCreatorContract.View> {

	private final TemplateInteractor interactor;
	private TemplateCreatorViewModel viewModel;
	private List<Currency> currencies;
	private List<String> categories;

	public TemplateCreatorPresenter(TemplateInteractor interactor) {
		this.interactor = interactor;
	}

	public void setModel(TemplateCreatorViewModel viewModel, OperationTemplate template) {
		this.viewModel = viewModel;
		if (template != null && this.viewModel.isNew) {
			MyLog.l("CHECK");
			this.viewModel.isNew = false;
			this.viewModel.id = template.id;
			this.viewModel.sum = template.sum;
			this.viewModel.title = template.title;
			this.viewModel.isIncome = template.isIncome;
			// TODO: Доделать остальные значения
		}
	}

	public void attachView(TemplateCreatorContract.View view, Context context) {
		super.attachView(view);
		initForm(context);
	}

	private void initForm(Context context) {
		initCategory(context);
		initCurrencies();
		view.setTitle(viewModel.title);
		view.setSum(viewModel.sum != 0 ? String.valueOf(viewModel.sum) : "");
		view.setCategoryInput(viewModel.otherCategory);
		view.showHideOtherCategory(viewModel.isOtherCategory);
		view.setType(viewModel.isIncome);
		view.showHideRemoveButton(!viewModel.isNew);
	}

	private void initCategory(Context context) {
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

	public void save() {
		if (validate()) {
			OperationTemplate template = new OperationTemplate();

			if (viewModel.isOtherCategory) {
				template.category = viewModel.otherCategory;
			} else {
				template.category = categories.get(viewModel.categoryPosition);
			}

			template.currency = currencies.get(viewModel.currencyPosition);
			template.sum = viewModel.sum;
			template.title = viewModel.title;
			template.isIncome = viewModel.isIncome;

			String message = "";
			if (viewModel.isNew) {
				interactor.insert(template);
				message = "Шаблон успешно создан";
			} else {
				template.id = viewModel.id;
				interactor.update(template);
				message = "Шаблон обновлен";
			}

			view.back(message);
		}
	}

	public void remove() {
		OperationTemplate template = new OperationTemplate();
		template.id = viewModel.id;
		interactor.remove(template);
		view.back("Шаблон удален");
	}

	private boolean validate() {
		boolean flagAllOk = true;

		if (viewModel.sum == 0) {
			view.showHideSumError(true);
			flagAllOk = false;
		}

		if (viewModel.title.trim().length() == 0) {
			view.showHideTitleError(true);
			flagAllOk = false;
		}

		if (viewModel.isOtherCategory && viewModel.otherCategory.trim().length() == 0) {
			view.showHideOtherCategoryError(true);
			flagAllOk = false;
		}

		return flagAllOk;
	}

	public void setSum(String s) {
		double sum = 0.0;
		try {
			sum = Double.parseDouble(s);
		} catch (NumberFormatException e) { }
		viewModel.sum = sum;
		view.showHideSumError(false);
	}

	public void setTitle(String s) {
		viewModel.title = s;
		view.showHideTitleError(false);
	}

	public void setCurrencyPosition(int position) {
		viewModel.currencyPosition = position;
	}

	public void setCategoryPosition(int position) {
		view.showHideOtherCategoryError(false);
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
	}

	public void setOtherCategory(String s) {
		viewModel.otherCategory = s;
		view.showHideOtherCategoryError(false);
	}

	public void setType(boolean isIncome) {
		viewModel.isIncome = isIncome;
	}
}
