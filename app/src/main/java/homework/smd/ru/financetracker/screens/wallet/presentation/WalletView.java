package homework.smd.ru.financetracker.screens.wallet.presentation;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.screens.wallet.domain.WalletInteractor;
import homework.smd.ru.financetracker.screens.wallet.domain.WalletInteractorStub;

public class WalletView extends Fragment implements WalletContract.View {

	private final static int SPAN_COUNT = 2;
	private final static String EXPENSE_ID = "EXPENSE_ID";

	private WalletPresenter presenter;
	private int expenseId;
	private Unbinder unbinder;
	private WalletViewModel viewModel;
	private WalletInteractor interactor;
	@BindView(R.id.recycler_view) RecyclerView recycler;

	public static WalletView getInstance(Object data) {
		WalletView fragment = new WalletView();
		Bundle bundle = new Bundle();

		if (data != null) {
			bundle.putInt(EXPENSE_ID, (int) data);
		}

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

		final View view = inflater.inflate(R.layout.fragment_wallet, container, false);
		unbinder = ButterKnife.bind(this, view);

		viewModel = ViewModelProviders.of(this).get(WalletViewModel.class);
		interactor = new WalletInteractorStub();
		presenter = new WalletPresenter(interactor, expenseId);
		initViews();
		presenter.setViewModel(viewModel);
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
