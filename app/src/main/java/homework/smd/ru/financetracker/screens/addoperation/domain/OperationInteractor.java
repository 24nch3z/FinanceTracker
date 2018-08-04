package homework.smd.ru.financetracker.screens.addoperation.domain;

import android.support.annotation.NonNull;

import homework.smd.ru.financetracker.models.Expense;
import homework.smd.ru.financetracker.models.Operation;
import io.reactivex.Flowable;

public interface OperationInteractor {

	void addOperation(@NonNull Operation operation);

	@NonNull Flowable<Expense> getUserExpenses();
}
