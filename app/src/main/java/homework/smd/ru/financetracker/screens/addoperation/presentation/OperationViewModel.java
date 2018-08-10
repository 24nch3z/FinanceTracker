package homework.smd.ru.financetracker.screens.addoperation.presentation;

import android.arch.lifecycle.ViewModel;

import java.util.Date;

public class OperationViewModel extends ViewModel {

	public double sum;
	int categoryPosition = 0;
	boolean isOtherCategory = false;
	String otherCategory = "";
	public Date operationDate = new Date();
	int currencyPosition = 1;
	boolean isIncome = true;
	boolean isPeriod = false;
	int periodDays = 0;
}
