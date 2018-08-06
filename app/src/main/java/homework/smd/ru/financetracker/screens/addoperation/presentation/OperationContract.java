package homework.smd.ru.financetracker.screens.addoperation.presentation;

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

		void setExpense(List<Wallet> expens);

		Wallet getExpense();

		float getSum();

		@Nullable String getCategory();

		int getCheckedRadioButtonId();

		void back();

		boolean getIsPeriod();

		int getPeriodDays();
	}

	interface Presenter {
		void attachView(View view);

		void detachView();

		void createOperation();
	}
}
