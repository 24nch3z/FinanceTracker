package homework.smd.ru.financetracker.screens.wallet.domain;

import java.util.List;

import homework.smd.ru.financetracker.models.Operation;
import io.reactivex.Flowable;

public interface WalletInteractor {

	Flowable<List<Operation>> getOperations(int expenseId);

}
