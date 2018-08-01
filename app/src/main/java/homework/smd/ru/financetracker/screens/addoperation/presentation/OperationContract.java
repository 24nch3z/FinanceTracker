package homework.smd.ru.financetracker.screens.addoperation.presentation;

import android.support.annotation.Nullable;
import android.widget.AdapterView;

import java.util.List;

public interface OperationContract {

	interface View {
		void hideCategory();
		void showCategory();

		void setCategories(List<String> categories);
		void setOnCategoriesClickListener(AdapterView.OnItemSelectedListener listener);

		float getSum();
		@Nullable String getCategory();

		void back();
	}

	interface Presenter {
		void attachView(View view);
		void detachView();
		void createOperation(boolean isPlus);
	}
}
