package homework.smd.ru.financetracker.screens.detail.presentation.tabs;

import android.support.annotation.Nullable;

import java.util.List;

import homework.smd.ru.financetracker.models.Operation;
import homework.smd.ru.financetracker.screens.detail.presentation.DetailContract;

public class DetailPresenterTab implements DetailContract.PresenterTab {

	@Nullable private DetailContract.ViewTab view;
	private OperationRecyclerAdapter adapter;
	private List<Operation> dataset;

	@Override
	public void attachView(DetailContract.ViewTab view) {
		this.view = view;
		dataset = view.getCosts().getOperations();
		adapter = new OperationRecyclerAdapter(dataset);
		view.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	@Override
	public void detachView() {
		this.view = null;
	}
}
