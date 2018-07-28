package homework.smd.ru.financetracker.screens.main.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.modules.Configuration;

public class MainView extends Fragment implements MainContract.View {

	@Inject Configuration configuration;

	private MainContract.Presenter presenter;

	private Unbinder unbinder;
	@BindView(R.id.usd_rate) TextView rateUSD;
	@BindView(R.id.eur_rate) TextView rateEUR;
	@BindView(R.id.main_progress) ProgressBar progress;
	@BindView(R.id.recycler_main_view) RecyclerView recycler;
	public MainView() { }

	public static Fragment newMainInstance() {
		return new MainView();
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                                      ViewGroup container,
	                                      Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_main, container, false);

		App.getComponent().inject(this);
		unbinder = ButterKnife.bind(this, view);

		recycler.setLayoutManager(new LinearLayoutManager(
			getContext(), LinearLayoutManager.VERTICAL, false));

		presenter = new MainPresenter();
		presenter.attachView(this);
		return view;
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
}
