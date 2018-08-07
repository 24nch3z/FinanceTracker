package homework.smd.ru.financetracker.screens.addoperation.presentation;

import android.support.annotation.Nullable;
import android.widget.AdapterView;

import java.util.List;

public interface OperationContract {

	interface View {
		void hideCategory();

		void showCategory();

		void setCategories(List<String> categories, int position);

		void setCurrencies(List<String> categories, int position);

		void setOnCategoriesClickListener(AdapterView.OnItemSelectedListener listener);

		@Nullable
		String getCategory();

		int getCheckedRadioButtonId();

		void back();

		boolean getIsPeriod();

		int getPeriodDays();

		void showHideSumError(boolean flag);

		void setSum(String s);

		void setDate(String s);
	}
}
