package homework.smd.ru.financetracker.screens.main.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.datalayer.data.sharedpreferences.Configuration;
import homework.smd.ru.financetracker.dialogs.WalletCreatorDialog;
import homework.smd.ru.financetracker.models.Wallet;
import homework.smd.ru.financetracker.screens.Screens;
import homework.smd.ru.financetracker.screens.wallet.presentation.WalletView;

public class MainView extends Fragment implements MainContract.View {

	@Inject Configuration configuration;
	@Inject MainContract.Presenter presenter;

	private Unbinder unbinder;
	@BindView(R.id.usd_rate) TextView rateUSD;
	@BindView(R.id.eur_rate) TextView rateEUR;
	@BindView(R.id.main_progress) ProgressBar progress;
	@BindView(R.id.currencies_block) ViewGroup currencies;
	@BindView(R.id.recycler_main_view) RecyclerView recycler;
	@BindView(R.id.button_create_wallet) Button buttonCreateWallet;

	public MainView() { }

	public static Fragment newInstance() {
		return new MainView();
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container,
	                         Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_main, container, false);
		App.getComponent().inject(this);
		unbinder = ButterKnife.bind(this, view);
		initView();
		presenter.attachView(this);
		return view;
	}

	private void initView() {
		recycler.setLayoutManager(new LinearLayoutManager(
			getContext(), LinearLayoutManager.VERTICAL, false));

		buttonCreateWallet.setOnClickListener(view -> {
			FragmentManager manager = getFragmentManager();
			WalletCreatorDialog dialog = WalletCreatorDialog.newInstance(null);
			dialog.show(manager, "");
		});
	}

	@Override
	public void onDestroyView() {
		presenter.detachView();
		unbinder.unbind();
		super.onDestroyView();
	}

	@Override
	public void updateRateUSD(String rateUSD) {
		this.rateUSD.setText(rateUSD);
	}

	@Override
	public void updateRateEUR(String rateEUR) {
		this.rateEUR.setText(rateEUR);
	}

	@Override
	public void hideProgress() {
		progress.setVisibility(View.GONE);
	}

	@Override
	public void setAdapter(RecyclerView.Adapter adapter) {
		recycler.setAdapter(adapter);
		recycler.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideCurrencies() {
		currencies.setVisibility(View.GONE);
	}

	@Override
	public void showCurrencies() {
		currencies.setVisibility(View.VISIBLE);
	}

	@Override
	public void navigationToWalletScreen(Wallet wallet) {
		App.instance.getRouter().navigateTo(Screens.SCREEN_WALLET, wallet);
	}
}
