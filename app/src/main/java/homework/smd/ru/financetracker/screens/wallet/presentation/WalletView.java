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
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.models.Wallet;
import homework.smd.ru.financetracker.screens.wallet.domain.WalletInteractor;
import homework.smd.ru.financetracker.screens.wallet.domain.WalletInteractorStub;

public class WalletView extends Fragment implements WalletContract.View {

	private final static int SPAN_COUNT = 2;
	private final static String WALLET = "WALLET";

	private Wallet wallet;
	private Unbinder unbinder;
	private WalletViewModel viewModel;
	private WalletPresenter presenter;
	private WalletInteractor interactor;

	@BindView(R.id.recycler_view) RecyclerView recycler;
	@BindView(R.id.title) TextView title;

	public static WalletView getInstance(Object data) {
		WalletView fragment = new WalletView();
		Bundle bundle = new Bundle();

		if (data != null) {
			bundle.putSerializable(WALLET, (Wallet) data);
		}

		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		wallet = (Wallet) getArguments().getSerializable(WALLET);
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.fragment_wallet, container, false);
		unbinder = ButterKnife.bind(this, view);

		initViews();
		viewModel = ViewModelProviders.of(this).get(WalletViewModel.class);
		interactor = new WalletInteractorStub();
		presenter = new WalletPresenter(interactor, wallet);
		presenter.setViewModel(viewModel);
		presenter.attachView(this);

		return view;
	}

	private void initViews() {
		recycler.setLayoutManager(new StaggeredGridLayoutManager(
			SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL));
		title.setText(wallet.getTitle());
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
