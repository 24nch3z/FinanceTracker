package homework.smd.ru.financetracker.datalayer;

import android.support.annotation.NonNull;

import java.util.List;

import homework.smd.ru.financetracker.models.Balance;
import homework.smd.ru.financetracker.models.Expense;
import homework.smd.ru.financetracker.models.Operation;
import io.reactivex.Flowable;

public interface ExpenseRepository {

	@NonNull Flowable<List<Expense>> getExpenses();

	@NonNull Flowable<List<Balance>> getBalances();

	void addOperation(@NonNull final Operation operation, @NonNull final Expense expense);
}
