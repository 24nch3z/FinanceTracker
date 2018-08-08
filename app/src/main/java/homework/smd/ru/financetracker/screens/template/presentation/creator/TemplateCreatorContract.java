package homework.smd.ru.financetracker.screens.template.presentation.creator;

import java.util.List;

public interface TemplateCreatorContract {

	interface View {
		void setCategories(List<String> list, int selection);
		void setCategoryInput(String s);
		void setCurrencies(List<String> list, int selection);
		void setSum(String s);
		void setType(boolean isIncome);
		void setTitle(String s);
		void showHideSumError(boolean flag);
		void showHideTitleError(boolean flag);
		void showHideOtherCategoryError(boolean flag);
		void showHideOtherCategory(boolean flag);
		void back();
	}
}
