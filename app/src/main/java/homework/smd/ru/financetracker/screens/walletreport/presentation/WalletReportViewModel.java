package homework.smd.ru.financetracker.screens.walletreport.presentation;

import android.arch.lifecycle.ViewModel;

import java.util.Calendar;
import java.util.Date;

public class WalletReportViewModel extends ViewModel {

	Date dateFrom;
	Date dateTo;

	public WalletReportViewModel() {
		dateTo = new Date();

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		dateFrom = calendar.getTime();
	}
}
