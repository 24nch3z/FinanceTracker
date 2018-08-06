package homework.smd.ru.financetracker.screens.wallet.presentation;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.dialogs.WalletCreatorDialog;
import homework.smd.ru.financetracker.models.Wallet;
import homework.smd.ru.financetracker.screens.Screens;
import homework.smd.ru.financetracker.screens.wallet.domain.WalletInteractor;
import homework.smd.ru.financetracker.screens.wallet.domain.WalletInteractorStub;

public class WalletView extends Fragment implements WalletContract.View {

	private final static int SPAN_COUNT = 2;
	private final static String ARG_WALLET = "ARG_WALLET";
	private final static String DIALOG_TAG = "WALLET_DIALOG";

	private Wallet wallet;
	private Unbinder unbinder;
	private WalletViewModel viewModel;
	private WalletPresenter presenter;
	private WalletInteractor interactor;

	@BindView(R.id.recycler_view) RecyclerView recycler;
	@BindView(R.id.title) TextView title;
	@BindView(R.id.change_wallet) Button buttonChangeWallet;
	@BindView(R.id.create_operation) Button buttonCreateOperation;
	@BindView(R.id.remove_wallet) Button buttonRemoveWallet;

	public static WalletView getInstance(Object data) {
		WalletView fragment = new WalletView();
		Bundle bundle = new Bundle();
		if (data != null) {
			bundle.putSerializable(ARG_WALLET, (Wallet) data);
		}
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		wallet = (Wallet) getArguments().getSerializable(ARG_WALLET);
	}

	// TODO: Добавить прокрутку длинных имён для кошелька
	// TODO: На главный экран укарасивать длину имени
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

		buttonChangeWallet.setOnClickListener(view -> {
			// TODO: Возможно тут тоже нужен childFM
			FragmentManager manager = getFragmentManager();
			WalletCreatorDialog dialog = WalletCreatorDialog.newInstance(wallet);
			dialog.show(manager, DIALOG_TAG);
		});

		buttonCreateOperation.setOnClickListener(view -> {
			App.instance.getRouter().navigateTo(Screens.SCREEN_ADD_OPERATION, wallet);
		});

		buttonRemoveWallet.setOnClickListener(view -> {

		});
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
