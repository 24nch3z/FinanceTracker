package homework.smd.ru.financetracker.datalayer;

import android.support.annotation.NonNull;

import java.util.List;

import homework.smd.ru.financetracker.models.Expense;
import homework.smd.ru.financetracker.models.Operation;
import io.reactivex.Flowable;

public interface ExpenseRepository {

	@NonNull Flowable<List<Expense>> getExpenses();

	// TODO: Удалить
	void addOperation(@NonNull final Operation operation, final int expanseId);
}
