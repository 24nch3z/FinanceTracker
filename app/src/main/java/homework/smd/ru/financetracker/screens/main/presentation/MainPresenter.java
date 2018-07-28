package homework.smd.ru.financetracker.screens.main.presentation;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.screens.main.domain.BalanceModel;
import homework.smd.ru.financetracker.screens.main.domain.MainInteractor;

public class MainPresenter implements MainContract.Presenter {

	@Inject Handler mainHandler;

	@Nullable private MainContract.View view;
	@NonNull private final List<BalanceModel> dataset = new ArrayList<>();
	@NonNull private BalanceAdapter adapter = new BalanceAdapter(dataset);
	@NonNull private MainInteractor interactor = new MainInteractor();

	MainPresenter() {
		App.getComponent().inject(this);
		interactor.setCallback(new MainCallback());
	}

	@Override
	public void attachView(@NonNull MainContract.View view) {
		this.view = view;
		this.view.setAdapter(adapter);
		interactor.getUserBalance();
	}

	@Override
	public void detachView() {
		this.view = null;
	}

	private class MainCallback implements MainInteractor.Callback {

		@Override
		public void onSuccessUserBalance(final List<BalanceModel> newDataset) {
			for (final BalanceModel balance : newDataset) {
				if (balance.isVisible()) {
					balance.setStringSum(String.format(
						Locale.getDefault(), "%,.2f", balance.getSum()
					));
				} else {
					// Hide balance
					balance.setStringSum("* * * * * ");
				}
			}
			mainHandler.post(() -> {
				dataset.clear();
				dataset.addAll(newDataset);
				adapter.notifyDataSetChanged();
				if (view != null) {
					view.hideProgress();
				}
			});
		}
	}
}
