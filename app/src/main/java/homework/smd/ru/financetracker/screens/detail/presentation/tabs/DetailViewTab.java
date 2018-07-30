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
import homework.smd.ru.financetracker.screens.detail.presentation.DetailContract;

public class DetailViewTab extends DetailContract.ViewTab {

	private final static int SPAN_COUNT = 2;

	private Unbinder unbinder;
	private OperationRecyclerAdapter adapter = new OperationRecyclerAdapter();

	@BindView(R.id.recycler_tab_page) RecyclerView recycler;

	public static DetailContract.ViewTab getDetailPageInstance() {
		return new DetailViewTab();
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_detail_tabpage, container, false);
		unbinder = ButterKnife.bind(this, view);

		recycler.setLayoutManager(new StaggeredGridLayoutManager(
			SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL));
		recycler.setAdapter(adapter);

		return view;
	}

	@Override
	public void onDestroyView() {
		unbinder.unbind();
		super.onDestroyView();
	}

	@Override
	public void setAdapter(RecyclerView.Adapter adapter) {
		recycler.setAdapter(adapter);
	}

	@Override
	public OperationRecyclerAdapter getRecycler() {
		return adapter;
	}
}
