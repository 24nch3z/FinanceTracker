package homework.smd.ru.financetracker.screens.main.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainInteractor {

	@Nullable private Callback callback;
	private final Executor executor = Executors.newSingleThreadExecutor();

	public void getUserBalance() {
		executor.execute(() -> {
			final ArrayList<BalanceModel> balances = new ArrayList<>();
			balances.add(new BalanceModel("Наличка", true, 42000));
			balances.add(new BalanceModel("Зарплата", false, 100500));

			if (callback != null) {
				callback.onSuccessUserBalance(balances);
			}
		});
	}

	public void setCallback(@Nullable Callback callback) {
		this.callback = callback;
	}

	public interface Callback {
		void onSuccessUserBalance(List<BalanceModel> balances);
	}
}
