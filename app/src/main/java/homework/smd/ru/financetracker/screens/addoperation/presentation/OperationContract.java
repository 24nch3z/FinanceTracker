package homework.smd.ru.financetracker.screens.addoperation.presentation;

import java.util.List;

public interface OperationContract {

	interface View {
		void setSum(String s);
		void setCategories(List<String> list, int selection);
		void setCategoryInput(String s);
		void setCurrencies(List<String> list, int selection);
		void showHideSumError(boolean flag);
		void showHideOtherCategoryError(boolean flag);
		void showHideOtherCategory(boolean flag);
		void setDate(String s);
		void setType(boolean isIncome);
		void back();
		void showHidePeriodError(boolean flag);
		void showHidePeriodForm(boolean isVisible);
		void setPeriodDays(String s);
	}
}
