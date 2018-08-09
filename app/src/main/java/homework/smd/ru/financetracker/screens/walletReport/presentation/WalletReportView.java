package homework.smd.ru.financetracker.screens.walletReport.presentation;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.dialogs.DatePickerDialog;
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
