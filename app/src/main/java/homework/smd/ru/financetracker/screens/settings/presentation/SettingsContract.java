package homework.smd.ru.financetracker.screens.settings.presentation;

class SettingsContract {

	interface View {
		void changeCurrency(boolean isRub);
	}

	interface Presenter {
		void attachView(View view);
		void detachView();
		void onChangeCurrency(boolean isRub);
	}
}
