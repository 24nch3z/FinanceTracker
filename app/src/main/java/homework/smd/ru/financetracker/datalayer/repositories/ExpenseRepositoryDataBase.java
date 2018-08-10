package homework.smd.ru.financetracker.datalayer.repositories;

import android.support.annotation.NonNull;

import java.util.List;

import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.database.AppDatabase;
import homework.smd.ru.financetracker.datalayer.ExpenseRepository;
import homework.smd.ru.financetracker.models.Expense;
import io.reactivex.Flowable;

public class ExpenseRepositoryDataBase implements ExpenseRepository {

	private AppDatabase db;

	public ExpenseRepositoryDataBase() {
		db = App.instance.getDatabase();
	}

	@NonNull
	@Override
	public Flowable<List<Expense>> getExpenses() {
		return db.expenseDao()
			.getExpenses();
	}

	@Override
	public void updateExpense(Expense expense) {
		db.expenseDao().update(expense);
	}
}
