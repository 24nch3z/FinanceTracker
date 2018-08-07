package homework.smd.ru.financetracker.screens.settings.presentation;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import homework.smd.ru.financetracker.BasePresenter;
import homework.smd.ru.financetracker.models.Currency;

public class SettingsPresenter extends BasePresenter<SettingsContract.View> {

	public void attachView(SettingsContract.View view, Context context) {
		super.attachView(view);
		initCurrencySpinner(context);
	}

	public void initCurrencySpinner(Context context) {
		List<Currency> currencyList = Arrays.asList(Currency.values());
		List<String> spinnerList = new ArrayList<>();
		for (Currency currency : currencyList) spinnerList.add(currency.name());
		int defaultPosition = 1;
		view.initCurrencySpinner(spinnerList, defaultPosition);
	}
}
