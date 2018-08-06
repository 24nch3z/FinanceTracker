package homework.smd.ru.financetracker.screens.main.presentation;

import android.support.v7.widget.RecyclerView;

import homework.smd.ru.financetracker.models.Expense;


public interface MainContract {
	interface View {
		void updateRateUSD(final String rateUSD);
		void updateRateEUR(final String rateEUR);
		void setAdapter(RecyclerView.Adapter adapter);

		void hideProgress();
		void hideCurrencies();
		void showCurrencies();

		void navigationToWalletScreen(final Expense expense);
	}

	interface Presenter {
		void attachView(View view);
		void detachView();
	}
}
