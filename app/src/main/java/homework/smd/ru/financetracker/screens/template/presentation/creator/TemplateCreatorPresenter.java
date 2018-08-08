package homework.smd.ru.financetracker.screens.template.presentation.creator;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import homework.smd.ru.financetracker.BasePresenter;
import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.models.Currency;
import homework.smd.ru.financetracker.models.OperationTemplate;
import homework.smd.ru.financetracker.screens.addoperation.presentation.OperationPresenter;
import homework.smd.ru.financetracker.screens.template.domain.TemplateInteractor;

public class TemplateCreatorPresenter extends BasePresenter<TemplateCreatorContract.View> {

	private TemplateInteractor interactor;
	private TemplateCreatorViewModel viewModel;

	public TemplateCreatorPresenter(TemplateInteractor interactor) {
		this.interactor = interactor;
	}

	public void setViewModel(TemplateCreatorViewModel viewModel) {
		this.viewModel = viewModel;
	}

	public void attachView(TemplateCreatorContract.View view, Context context) {
		super.attachView(view);
		initCategory(context);
		initCurrencies();
		view.setTitle(viewModel.title);
		view.setSum(viewModel.sum != 0.0 ? String.valueOf(viewModel.sum) : "");
	}

	private void initCategory(Context context) {
		final List<String> categories = Arrays.asList(
			context.getResources().getStringArray(R.array.default_categories));
		view.setCategories(categories, 0);
	}

	private void initCurrencies() {
		List<Currency> currencyList = Arrays.asList(Currency.values());
		List<String> spinnerList = new ArrayList<>();
		for (Currency currency : currencyList) spinnerList.add(currency.name());
		int defaultPosition = 1;
		view.setCurrencies(spinnerList, defaultPosition);
	}

	public void save() {
		if (validate()) {
			OperationTemplate template = new OperationTemplate();
			template.category = "Категория";
			template.currency = Currency.RUB;
			template.sum = viewModel.sum;
			template.title = viewModel.title;

			// TODO: Пока тут только метод insert
			interactor.insert(template);
			view.back();
		}
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

		return flagAllOk;
	}

	public void setSum(String s) {
		double sum = 0.0;
		try {
			sum = Double.parseDouble(s);
		} catch (NumberFormatException e) {}
		viewModel.sum = sum;
	}

	public void setTitle(String s) {
		viewModel.title = s;
	}
}
