package homework.smd.ru.financetracker.screens.wallet.presentation;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.dialogs.WalletCreatorDialog;
import homework.smd.ru.financetracker.dialogs.WalletRemovingDialog;
import homework.smd.ru.financetracker.models.Wallet;
import homework.smd.ru.financetracker.screens.Screens;
import homework.smd.ru.financetracker.screens.wallet.domain.WalletInteractor;
import homework.smd.ru.financetracker.screens.wallet.domain.WalletInteractorStub;
import homework.smd.ru.financetracker.utils.MyToast;

public class WalletView extends Fragment implements WalletContract.View {

	private final static int SPAN_COUNT = 2;
	private final static String ARG_WALLET = "ARG_WALLET";

	private final static String CHANGE_DIALOG = "CHANGE_DIALOG";
	private final static String REMOVE_DIALOG = "REMOVE_DIALOG";

	private static final int REQUEST_NEW_WALLET_NAME = 1;
	private static final int REQUEST_IS_REMOVING = 2;

	private Wallet wallet;
	private Unbinder unbinder;
	private WalletViewModel viewModel;
	private WalletPresenter presenter;
	private WalletInteractor interactor;

	@BindView(R.id.recycler_view) RecyclerView recycler;
	@BindView(R.id.title) TextView textViewTitle;
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

	// TODO: Добавить прокрутку длинных имён для кошелька
	// TODO: На главный экран укарачивать длину имени
	// TODO: Удалить старьё
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.fragment_wallet, container, false);
		unbinder = ButterKnife.bind(this, view);
		wallet = (Wallet) getArguments().getSerializable(ARG_WALLET);

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

		textViewTitle.setText(wallet.getTitle());

		buttonChangeWallet.setOnClickListener(view -> {
			FragmentManager manager = getFragmentManager();
			WalletCreatorDialog dialog = WalletCreatorDialog.newInstance(wallet);
			dialog.setTargetFragment(WalletView.this, REQUEST_NEW_WALLET_NAME);
			dialog.show(manager, CHANGE_DIALOG);
		});

		buttonCreateOperation.setOnClickListener(view -> {
			App.instance.getRouter().navigateTo(Screens.SCREEN_ADD_OPERATION, wallet);
		});

		buttonRemoveWallet.setOnClickListener(view -> {
			FragmentManager manager = getFragmentManager();
			WalletRemovingDialog dialog = WalletRemovingDialog.newInstance(wallet);
			dialog.setTargetFragment(WalletView.this, REQUEST_IS_REMOVING);
			dialog.show(manager, REMOVE_DIALOG);
		});
	}

	@Override
	public void onDestroyView() {
		presenter.detachView();
		unbinder.unbind();
		super.onDestroyView();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != Activity.RESULT_OK) {
			return;
		}

		switch (requestCode) {
			case REQUEST_NEW_WALLET_NAME:
				MyToast.get(getContext()).show(getString(R.string.wallet_change_wallet_success_toast));
				String name = data.getStringExtra(WalletCreatorDialog.NEW_WALLET_NAME);
				wallet.setTitle(name);
				textViewTitle.setText(wallet.getTitle());
				break;
			case REQUEST_IS_REMOVING:
				boolean isRemoving = data.getBooleanExtra(WalletRemovingDialog.IS_REMOVING, false);
				if (isRemoving) {
					MyToast.get(getContext()).show(getString(R.string.wallet_remove_wallet_success_toast));
					App.instance.getRouter().backTo(Screens.SCREEN_MAIN);
				}
				break;
		}
	}

	@Override
	public void setAdapter(OperationRecyclerAdapter adapter) {
		recycler.setAdapter(adapter);
	}
}
