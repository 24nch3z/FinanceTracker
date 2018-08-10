package homework.smd.ru.financetracker.screens.walletreport.presentation;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.dialogs.DatePickerDialog;
import homework.smd.ru.financetracker.models.CategoryChart;
import homework.smd.ru.financetracker.models.Wallet;
import homework.smd.ru.financetracker.utils.MyToast;

public class WalletReportView extends Fragment implements WalletReportContract.View {

	private static final String DIALOG_DATE_FROM = "DIALOG_REPORT_DATE_FROM";
	private static final String DIALOG_DATE_TO = "DIALOG_REPORT_DATE_TO";

	public static final int REQUEST_DATE_FROM = 1;
	public static final int REQUEST_DATE_TO = 2;

	private final static String ARG_WALLET = "ARG_WALLET";

	private Unbinder unbinder;
	@Inject WalletReportPresenter presenter;

	@BindView(R.id.text_view_date_from) TextView textViewDateFrom;
	@BindView(R.id.text_view_date_to) TextView textViewDateTo;
	@BindView(R.id.report) TextView textViewReport;
	@BindView(R.id.chart) PieChart chart;

	public static WalletReportView newInstance(Object data) {
		WalletReportView fragment = new WalletReportView();
		Bundle args = new Bundle();
		if (data != null) {
			args.putSerializable(ARG_WALLET, (Wallet) data);
		}
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.fragment_wallet_report, container, false);
		Wallet wallet = (Wallet) getArguments().getSerializable(ARG_WALLET);
		unbinder = ButterKnife.bind(this, view);
		App.getComponent().inject(this);
		WalletReportViewModel viewModel = ViewModelProviders
			.of(this).get(WalletReportViewModel.class);

		presenter.setWallet(wallet);
		presenter.setViewModel(viewModel);
		presenter.attachView(this);
		setToolbarText();

		return view;
	}

	private void setToolbarText() {
		((AppCompatActivity) getActivity()).getSupportActionBar()
			.setTitle(getString(R.string.toolbar_wallet_report));
	}

	@Override
	public void setDateFrom(String s) {
		textViewDateFrom.setText(s);
	}

	@Override
	public void setDateTo(String s) {
		textViewDateTo.setText(s);
	}

	@Override
	public void showErrorToast(int message) {
		MyToast.get(getContext()).show(getString(message));
	}

	public void showReportText(String s) {
		textViewReport.setText(s);
	}

	@Override
	public void showHideChart(boolean flag) {
		chart.setVisibility(flag ? View.VISIBLE : View.GONE);
	}

	@Override
	public void setDataForChart(List<CategoryChart> categories) {
		List<PieEntry> values = new ArrayList<>();

		for (CategoryChart categoryChart : categories) {
			values.add(new PieEntry(categoryChart.percent, categoryChart.title));
		}

		PieDataSet dataSet = new PieDataSet(values, "");
		dataSet.setSliceSpace(3f);
		dataSet.setSelectionShift(5f);
		dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

		PieData data = new PieData(dataSet);
		data.setValueFormatter(new PercentFormatter());
		data.setValueTextSize(11f);
		data.setValueTextColor(Color.WHITE);
		chart.setData(data);
		chart.getDescription().setText("");

		chart.invalidate();
	}

	@Override
	public void setNoDataForReport() {
		showHideChart(false);
		showReportText(getString(R.string.template_report_no_data));
	}

	@Override
	public void setDataForTextReport(float incomes, float costs, int incomeCount, int costCount) {
		StringBuilder report = new StringBuilder();

		report.append(getString(R.string.template_report_text_balance)).append(" ")
			.append(incomes + costs).append("\n");
		report.append(getString(R.string.template_report_text_total_count)).append(" ")
			.append(incomeCount + costCount).append("\n\n");
		report.append(getString(R.string.template_report_text_cost_count)).append(" ")
			.append(costCount).append("\n");
		report.append(getString(R.string.template_report_text_income_count)).append(" ")
			.append(incomeCount).append("\n\n");
		report.append(getString(R.string.template_report_text_income)).append(" ")
			.append(incomes).append("\n");
		report.append(getString(R.string.template_report_text_cost)).append(" ")
			.append(-costs);

		showReportText(report.toString().trim());
	}

	@OnClick(R.id.text_view_date_from)
	void onClickDateFrom(View v) {
		FragmentManager manager = getFragmentManager();
		DatePickerDialog dialog = DatePickerDialog.newInstance(presenter.getDateFrom(),
			R.string.dialog_select_date);
		dialog.setTargetFragment(WalletReportView.this, REQUEST_DATE_FROM);
		dialog.show(manager, DIALOG_DATE_FROM);
	}

	@OnClick(R.id.text_view_date_to)
	void onClickDateTo(View v) {
		FragmentManager manager = getFragmentManager();
		DatePickerDialog dialog = DatePickerDialog.newInstance(presenter.getDateTo(),
			R.string.dialog_select_date);
		dialog.setTargetFragment(WalletReportView.this, REQUEST_DATE_TO);
		dialog.show(manager, DIALOG_DATE_TO);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		presenter.onActivityResult(requestCode, resultCode, data);
	}
}
