package homework.smd.ru.financetracker.screens.wallet.presentation;

import java.util.ArrayList;
import java.util.List;

import homework.smd.ru.financetracker.BasePresenter;
import homework.smd.ru.financetracker.models.Operation;
import homework.smd.ru.financetracker.screens.wallet.domain.WalletInteractor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class WalletPresenter extends BasePresenter<WalletContract.View> {

	private WalletViewModel viewModel;
	private WalletInteractor interactor;
	private int expenseId;

	private List<Operation> operations;
	private OperationRecyclerAdapter adapter;
	private final CompositeDisposable cd = new CompositeDisposable();


	public WalletPresenter(WalletInteractor interactor, int expenseId) {
		this.interactor = interactor;
		this.expenseId = expenseId;

		operations = new ArrayList<>();
		adapter = new OperationRecyclerAdapter();
		adapter.updateDataset(operations);
	}

	public void setViewModel(WalletViewModel viewModel) {
		this.viewModel = viewModel;
	}

	@Override
	public void attachView(WalletContract.View view) {
		super.attachView(view);

		view.setAdapter(adapter);

		final Disposable disposable = interactor
			.getOperations(expenseId)
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
