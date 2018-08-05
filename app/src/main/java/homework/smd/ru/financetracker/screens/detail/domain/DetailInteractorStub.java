package homework.smd.ru.financetracker.screens.detail.domain;

import java.util.List;

import homework.smd.ru.financetracker.datalayer.ExpenseRepository;
import homework.smd.ru.financetracker.datalayer.OperationRepository;
import homework.smd.ru.financetracker.datalayer.repositories.ExpenseRepositoryDataBase;
import homework.smd.ru.financetracker.datalayer.repositories.OperationRepositoryDataBase;
import homework.smd.ru.financetracker.models.Expense;
import homework.smd.ru.financetracker.models.Operation;
import io.reactivex.Flowable;

public class DetailInteractorStub implements DetailInteractor {

	private final ExpenseRepository expenseRepository = new ExpenseRepositoryDataBase();
	private final OperationRepository operationRepository = new OperationRepositoryDataBase();

	@Override
	public Flowable<List<Expense>> getCosts() {
		return expenseRepository
			.getExpenses();
	}

	@Override
	public Flowable<List<Operation>> getOperations(int expenseId) {
		return operationRepository
			.getOperations(expenseId);
	}
}
