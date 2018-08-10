package homework.smd.ru.financetracker.screens.detail.presentation.tabs;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.models.Operation;
import homework.smd.ru.financetracker.screens.detail.domain.DetailInteractor;
import homework.smd.ru.financetracker.screens.detail.domain.DetailInteractorStub;
import homework.smd.ru.financetracker.screens.detail.presentation.DetailContract;
import homework.smd.ru.financetracker.screens.detail.presentation.DetailViewTabContract;
import homework.smd.ru.financetracker.screens.detail.presentation.DetailViewTabPresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class DetailViewTab extends DetailContract.ViewTab implements DetailViewTabContract.View {

	private final static int SPAN_COUNT = 2;
	private final static String EXPENSE_ID = "EXPENSE_ID";

	private int expenseId;
	private Unbinder unbinder;
	private DetailViewTabPresenter presenter;
	private DetailInteractor interactor = new DetailInteractorStub();
	@BindView(R.id.recycler_tab_page) RecyclerView recycler;

	public static DetailContract.ViewTab getDetailPageInstance(int expenseId) {
		DetailContract.ViewTab fragment = new DetailViewTab();
		Bundle bundle = new Bundle();
		bundle.putInt(EXPENSE_ID, expenseId);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		expenseId = getArguments().getInt(EXPENSE_ID);
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_detail_tabpage, container, false);
		unbinder = ButterKnife.bind(this, view);

		presenter = new DetailViewTabPresenter(interactor, expenseId);
		initViews();
		presenter.attachView(this);

		return view;
	}

	private void initViews() {
		recycler.setLayoutManager(new StaggeredGridLayoutManager(
			SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL));
	}

	@Override
	public void onDestroyView() {
		presenter.detachView();
		unbinder.unbind();
		super.onDestroyView();
	}

	@Override
	public void setAdapter(OperationRecyclerAdapter adapter) {
		recycler.setAdapter(adapter);
	}
}
