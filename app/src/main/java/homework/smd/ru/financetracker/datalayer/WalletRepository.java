package homework.smd.ru.financetracker.datalayer;

import android.support.annotation.NonNull;

import java.util.List;

import homework.smd.ru.financetracker.models.Wallet;
import io.reactivex.Flowable;

public interface WalletRepository {

	@NonNull
	Flowable<List<Wallet>> getExpens();

	void updateExpense(Wallet wallet);
}
