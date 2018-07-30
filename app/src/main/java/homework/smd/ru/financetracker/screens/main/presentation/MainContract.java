package homework.smd.ru.financetracker.screens.main.presentation;

import android.support.v7.widget.RecyclerView;


interface MainContract {
	interface View {
		void updateRateUSD(final String rateUSD);
		void updateRateEUR(final String rateEUR);
		void setAdapter(RecyclerView.Adapter adapter);
		void hideProgress();
		void hideCurrencies();
		void showCurrencies();
	}

	interface Presenter {
		void attachView(View view);
		void detachView();
	}
}
