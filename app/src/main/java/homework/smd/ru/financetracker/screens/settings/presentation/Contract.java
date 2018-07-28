package homework.smd.ru.financetracker.screens.settings.presentation;

class Contract {

	interface View {
		void changeCurrency(boolean isRub);
	}

	interface Presenter {
		void onCreateView(View view);
		void onDestroyView();
		void onChangeCurrency(boolean isRub);
	}
}
