package homework.smd.ru.financetracker.screens.addoperation.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import homework.smd.ru.financetracker.datalayer.ExpenseRepository;
import homework.smd.ru.financetracker.datalayer.OperationRepository;
import homework.smd.ru.financetracker.datalayer.repositories.ExpenseRepositoryDataBase;
import homework.smd.ru.financetracker.datalayer.repositories.OperationRepositoryDataBase;
import homework.smd.ru.financetracker.models.Wallet;
import homework.smd.ru.financetracker.models.Operation;
import homework.smd.ru.financetracker.models.Period;
import io.reactivex.Flowable;

public class OperationInteractorStub implements OperationInteractor {

	private final ExpenseRepository expenseRepository = new ExpenseRepositoryDataBase();
	private final OperationRepository operationRepository = new OperationRepositoryDataBase();

	@Override
	public void addOperation(@NonNull Operation operation, @Nullable Period period) {
		operationRepository.addOperation(operation, period);
	}

	@NonNull
	@Override
	public Flowable<Wallet> getUserExpenses() {
		return expenseRepository
			.getExpens()
			.flatMap(Flowable::fromIterable);
	}
}
