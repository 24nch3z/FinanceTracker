package homework.smd.ru.financetracker.screens.information.presentation;

import android.support.annotation.StringRes;

interface InfoContract {

	interface View {
		void setHtmlContent(final String text);
		void setError(@StringRes long stringID);
	}

	interface Presenter {
		void attachView(final View view);
		void detachView();
	}
}
