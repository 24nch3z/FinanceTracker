package homework.smd.ru.financetracker.screens.detail.domain;

import java.util.List;

import homework.smd.ru.financetracker.models.Wallet;
import homework.smd.ru.financetracker.models.Operation;
import io.reactivex.Flowable;

public interface DetailInteractor {

	Flowable<List<Wallet>> getCosts();

	Flowable<List<Operation>> getOperations(int expenseId);
}
