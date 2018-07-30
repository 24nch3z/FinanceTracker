package homework.smd.ru.financetracker.screens.detail.domain;

import homework.smd.ru.financetracker.datalayer.ExpenseRepository;
import homework.smd.ru.financetracker.datalayer.repositories.ExpenseRepositoryStub;
import homework.smd.ru.financetracker.models.Expense;
import io.reactivex.Flowable;

public class DetailInteractorStub implements DetailInteractor {

	private final ExpenseRepository expenseRepository = new ExpenseRepositoryStub();

	@Override
	public Flowable<Expense> getCosts() {
		return expenseRepository
			.getExpenses()
			.flatMap(Flowable::fromIterable);
	}

}
