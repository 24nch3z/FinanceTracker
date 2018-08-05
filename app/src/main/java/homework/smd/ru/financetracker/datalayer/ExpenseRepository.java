package homework.smd.ru.financetracker.datalayer;

import android.support.annotation.NonNull;

import java.util.List;

import homework.smd.ru.financetracker.models.Expense;
import io.reactivex.Flowable;

public interface ExpenseRepository {

	@NonNull
	Flowable<List<Expense>> getExpenses();

	void updateExpense(Expense expense);
}
