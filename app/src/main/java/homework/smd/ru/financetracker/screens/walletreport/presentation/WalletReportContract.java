package homework.smd.ru.financetracker.screens.walletreport.presentation;

import android.support.annotation.StringRes;

import java.util.List;

import homework.smd.ru.financetracker.models.CategoryChart;

public interface WalletReportContract {
	interface View {
		void setDateFrom(String s);
		void setDateTo(String s);
		void showErrorToast(@StringRes int message);
		void showHideChart(boolean flag);
		void setDataForChart(List<CategoryChart> categories);
		void setNoDataForReport();
		void setDataForTextReport(float incomes, float costs, int incomeCount, int costCount);
	}
}
