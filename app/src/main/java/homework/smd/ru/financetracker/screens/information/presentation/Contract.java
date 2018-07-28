package homework.smd.ru.financetracker.screens.information.presentation;

import android.support.annotation.StringRes;

class Contract {

	interface View {
		void setHtmlContent(final String text);
		void setError(@StringRes long stringID);
	}

	interface Presenter {
		void onCreateView(final View view);
		void onDestroyView();
	}
}
