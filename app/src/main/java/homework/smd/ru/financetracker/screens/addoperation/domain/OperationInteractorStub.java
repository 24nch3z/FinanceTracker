package homework.smd.ru.financetracker.screens.addoperation.domain;

import android.support.annotation.NonNull;

import homework.smd.ru.financetracker.datalayer.ExpenseRepository;
import homework.smd.ru.financetracker.datalayer.OperationRepository;
import homework.smd.ru.financetracker.datalayer.repositories.ExpenseRepositoryDataBase;
import homework.smd.ru.financetracker.datalayer.repositories.OperationRepositoryDataBase;
import homework.smd.ru.financetracker.models.Expense;
import homework.smd.ru.financetracker.models.Operation;
import io.reactivex.Flowable;

public class OperationInteractorStub implements OperationInteractor {

	private final ExpenseRepository expenseRepository = new ExpenseRepositoryDataBase();
	private final OperationRepository operationRepository = new OperationRepositoryDataBase();

	@Override
	public void addOperation(@NonNull Operation operation) {
		operationRepository.addOperation(operation);
	}

	@NonNull
	@Override
	public Flowable<Expense> getUserExpenses() {
		return expenseRepository
			.getExpenses()
			.flatMap(Flowable::fromIterable);
	}
}
