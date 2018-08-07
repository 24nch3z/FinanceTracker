package homework.smd.ru.financetracker.screens.wallet.presentation;

import java.util.ArrayList;
import java.util.List;

import homework.smd.ru.financetracker.BasePresenter;
import homework.smd.ru.financetracker.models.Wallet;
import homework.smd.ru.financetracker.models.Operation;
import homework.smd.ru.financetracker.screens.wallet.domain.WalletInteractor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class WalletPresenter extends BasePresenter<WalletContract.View> {

	private WalletInteractor interactor;
	private Wallet wallet;

	private List<Operation> operations;
	private OperationRecyclerAdapter adapter;
	private final CompositeDisposable cd = new CompositeDisposable();

	public WalletPresenter(WalletInteractor interactor, Wallet wallet) {
		this.interactor = interactor;
		this.wallet = wallet;

		operations = new ArrayList<>();
		adapter = new OperationRecyclerAdapter();
		adapter.updateDataset(operations);
	}

	@Override
	public void attachView(WalletContract.View view) {
		super.attachView(view);

		view.setAdapter(adapter);

		final Disposable disposable = interactor
			.getOperations(wallet.getId())
			.onBackpressureBuffer()
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe((operations) -> {
				this.operations.clear();
				this.operations.addAll(operations);
				adapter.updateDataset(this.operations);
			});
		cd.add(disposable);
	}

	@Override
	public void detachView() {
		super.detachView();
		cd.clear();
	}
}
