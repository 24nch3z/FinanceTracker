package homework.smd.ru.financetracker.screens.template.presentation.creator;

import android.arch.lifecycle.ViewModel;

public class TemplateCreatorViewModel extends ViewModel {

	double sum = 0;
	boolean isIncome = true;
	String title = "";
	int currencyPosition = 1;
	int categoryPosition = 0;
	boolean isOtherCategory = false;
	String otherCategory = "";
}