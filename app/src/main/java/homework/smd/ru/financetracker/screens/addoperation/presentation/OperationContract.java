package homework.smd.ru.financetracker.screens.addoperation.presentation;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.AdapterView;

import java.util.List;

import homework.smd.ru.financetracker.models.Wallet;

public interface OperationContract {

	interface View {
		void hideCategory();

		void showCategory();

		void setCategories(List<String> categories);

		void setOnCategoriesClickListener(AdapterView.OnItemSelectedListener listener);

		float getSum();

		@Nullable String getCategory();

		int getCheckedRadioButtonId();

		void back();

		boolean getIsPeriod();

		int getPeriodDays();

		void showHideSumError(boolean flag);
	}

	interface Presenter {
		void attachView(View view, Context context);

		void detachView();

		void createOperation();

		void setWallet(Wallet wallet);
	}
}
