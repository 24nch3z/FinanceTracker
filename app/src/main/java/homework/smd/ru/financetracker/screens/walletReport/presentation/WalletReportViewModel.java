package homework.smd.ru.financetracker.screens.walletReport.presentation;

import android.arch.lifecycle.ViewModel;

import java.util.Date;

public class WalletReportViewModel extends ViewModel {

	Date dateFrom;
	Date dateTo;

	public WalletReportViewModel() {
		dateFrom = new Date();
		dateTo = new Date();
	}
}
