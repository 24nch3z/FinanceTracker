package homework.smd.ru.financetracker.screens.settings.presentation;

import java.util.List;

interface SettingsContract {

	interface View {
		void initCurrencySpinner(List<String> currencyList, int defaultPosition);
	}
}
