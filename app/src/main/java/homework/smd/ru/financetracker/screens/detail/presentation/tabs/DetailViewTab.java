package homework.smd.ru.financetracker.screens.detail.presentation.tabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.models.Costs;
import homework.smd.ru.financetracker.screens.detail.presentation.DetailContract;

public class DetailViewTab extends DetailContract.ViewTab {

	private final static String COSTS = "costs";
	private final static int SPAN_COUNT = 2;

	private Costs costs;
	private Unbinder unbinder;
	private DetailContract.PresenterTab presenter;

	@BindView(R.id.recycler_tab_page) RecyclerView recycler;

	public static DetailContract.ViewTab getDetailPageInstance(@NonNull final Costs costs) {

		final DetailContract.ViewTab instance = new DetailViewTab();
		final Bundle arguments = new Bundle();

		arguments.putParcelable(COSTS, costs);
		instance.setArguments(arguments);

		return instance;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_detail_tabpage, container, false);
		unbinder = ButterKnife.bind(this, view);

		costs = null;
		if (getArguments() != null) {
			costs = getArguments().getParcelable(COSTS);
		} else if (savedInstanceState != null) {
			costs = savedInstanceState.getParcelable(COSTS);
		}

		recycler.setLayoutManager(new StaggeredGridLayoutManager(
			SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL));

		presenter = new DetailPresenterTab();
		presenter.attachView(this);
		return view;
	}

	@Override
	public void onSaveInstanceState(@NonNull Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putParcelable(COSTS, costs);
	}

	@Override
	public void onDestroyView() {
		unbinder.unbind();
		presenter.detachView();
		super.onDestroyView();
	}

	
	@Override
	public Costs getCosts() {
		return costs;
	}

	@Override
	public void setAdapter(RecyclerView.Adapter adapter) {
		recycler.setAdapter(adapter);
	}
}
