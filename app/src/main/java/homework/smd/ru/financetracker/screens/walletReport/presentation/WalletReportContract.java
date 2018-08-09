package homework.smd.ru.financetracker.screens.walletReport.presentation;

import android.support.annotation.StringRes;

public interface WalletReportContract {
	interface View {
		void setDateFrom(String s);
		void setDateTo(String s);
		void showErrorToast(@StringRes int message);
	}
}
