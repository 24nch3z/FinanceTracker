package homework.smd.ru.financetracker.screens.detail.presentation;

import java.util.Collections;

import homework.smd.ru.financetracker.screens.detail.domain.DetailInteractor;
import homework.smd.ru.financetracker.screens.detail.presentation.tabs.OperationRecyclerAdapter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class DetailViewTabPresenter {

	private DetailViewTabContract.View view;
	private OperationRecyclerAdapter adapter;
	private DetailInteractor interactor;
	private int expenseId;
	private CompositeDisposable cd = new CompositeDisposable();

	public DetailViewTabPresenter(DetailInteractor interactor, int expenseId) {
		this.interactor = interactor;
		this.expenseId = expenseId;
	}

	public void attachView(DetailViewTabContract.View view) {
		this.view = view;
		adapter = new OperationRecyclerAdapter(); // TODO: dagger
		adapter.updateDataset(Collections.emptyList());
		this.view.setAdapter(adapter);

		Disposable disposable = interactor.getOperations(expenseId)
			.onBackpressureBuffer()
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(
				(operations -> {
					adapter.updateDataset(operations);
					adapter.notifyDataSetChanged();
				})
			);
		cd.add(disposable);
	}

	public void detachView() {
		this.view = null;
		cd.clear();
	}
}
