package homework.smd.ru.financetracker.screens.walletreport.presentation;

import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import homework.smd.ru.financetracker.BasePresenter;
import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.dialogs.DatePickerDialog;
import homework.smd.ru.financetracker.models.CategoryChart;
import homework.smd.ru.financetracker.models.Operation;
import homework.smd.ru.financetracker.models.Wallet;
import homework.smd.ru.financetracker.screens.walletreport.domain.WalletReportInteractor;
import homework.smd.ru.financetracker.utils.Utils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import static homework.smd.ru.financetracker.screens.walletreport.presentation.WalletReportView.REQUEST_DATE_FROM;
import static homework.smd.ru.financetracker.screens.walletreport.presentation.WalletReportView.REQUEST_DATE_TO;

public class WalletReportPresenter extends BasePresenter<WalletReportContract.View> {

	private Wallet wallet;
	private WalletReportViewModel viewModel;
	private WalletReportInteractor interactor;

	private List<Operation> operations;

	public WalletReportPresenter(WalletReportInteractor interactor) {
		this.interactor = interactor;
		operations = new ArrayList<>();
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public void setViewModel(WalletReportViewModel viewModel) {
		this.viewModel = viewModel;
	}

	@Override
	public void attachView(WalletReportContract.View view) {
		super.attachView(view);

		final Disposable disposable = interactor
			.getOperations(wallet.getId())
			.onBackpressureBuffer()
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(operations -> {
				this.operations.clear();
				this.operations.addAll(operations);

				showReport(operations);
			});
		cd.add(disposable);

		initViews();
	}

	private void showReport(List<Operation> list) {
		if (list == null || list.size() == 0) {
			view.setNoDataForReport();
			return;
		}

		StringBuilder report = new StringBuilder();
		float incomes = 0, costs = 0;
		int incomeCount = 0, costCount = 0;
		Map<String, Float> categories = new HashMap<>();

		for (Operation operation : list) {
			float sum = (float) operation.sum;

			if (sum > 0) {
				incomes += sum;
				incomeCount++;
			} else {
				costs += sum;
				costCount++;

				String category = operation.category;
				if (categories.containsKey(category)) {
					categories.put(category, categories.get(categories) + sum);
				} else {
					categories.put(category, sum);
				}
			}
		}

		view.setDataForTextReport(incomes, costs, incomeCount, costCount);

		List<CategoryChart> chartData = getCategoryChartList(categories, costs);
		if (chartData != null && chartData.size() > 0) {
			view.setDataForChart(getCategoryChartList(categories, costs));
			view.showHideChart(true);
		} else {
			view.showHideChart(false);
		}
	}

	private List<CategoryChart> getCategoryChartList(Map<String, Float> categories, float total) {
		List<CategoryChart> list = new ArrayList<>();

		Set<Map.Entry<String, Float>> entry = categories.entrySet();
		Iterator<Map.Entry<String, Float>> iterator = entry.iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Float> next = iterator.next();
			list.add(new CategoryChart(next.getValue() / total, next.getKey()));
		}

		if (list.size() < 5) {
			return list;
		}

		Collections.sort(list, (t1, t2) -> {
			if (t1.percent > t2.percent) return -1;
			else if (t1.percent < t2.percent) return 1;
			return 0;
		});

		List<CategoryChart> output = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			output.add(list.get(i));
		}
		float sum = 0;
		for (int i = 3; i < list.size(); i++) sum += list.get(i).percent;
		output.add(new CategoryChart(sum, "Другое"));

		return output;
	}

	private void initViews() {
		showDateFrom();
		showDateTo();
		view.showHideChart(false);
	}

	private void showDateFrom() {
		view.setDateFrom(Utils.fromDateToString(viewModel.dateFrom));
	}

	private void showDateTo() {
		view.setDateTo(Utils.fromDateToString(viewModel.dateTo));
	}

	public void setDateFrom(Date d) {
		if (d.after(viewModel.dateTo)) {
			view.showErrorToast(R.string.template_report_error_to_less_from);
			return;
		}
		viewModel.dateFrom = d;
		showDateFrom();
	}

	public void setDateTo(Date d) {
		if (d.before(viewModel.dateFrom)) {
			view.showErrorToast(R.string.template_report_error_from_more_to);
			return;
		}
		viewModel.dateTo = d;
		showDateTo();
	}

	public Date getDateFrom() {
		return viewModel.dateFrom;
	}

	public Date getDateTo() {
		return viewModel.dateTo;
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != Activity.RESULT_OK) return;

		switch (requestCode) {
			case REQUEST_DATE_FROM:
				Date dateFrom = (Date) data.getSerializableExtra(DatePickerDialog.EXTRA_DATE);
				setDateFrom(dateFrom);
				break;

			case REQUEST_DATE_TO:
				Date dateTo = (Date) data.getSerializableExtra(DatePickerDialog.EXTRA_DATE);
				setDateTo(dateTo);
				break;
		}
	}
}
