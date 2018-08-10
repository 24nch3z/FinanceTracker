package homework.smd.ru.financetracker.screens.template.presentation.creator;

import android.arch.lifecycle.ViewModel;

public class TemplateCreatorViewModel extends ViewModel {

	boolean isNew = true;
	int id = -1;

	double sum = 0;
	String title = "";
	boolean isIncome = true;
	int currencyPosition = 1;
	int categoryPosition = 0;
	String otherCategory = "";
	boolean isOtherCategory = false;
}
