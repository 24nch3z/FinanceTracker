package homework.smd.ru.financetracker.datalayer.repositories;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.database.AppDatabase;
import homework.smd.ru.financetracker.models.Period;
import homework.smd.ru.financetracker.datalayer.OperationRepository;
import homework.smd.ru.financetracker.models.Operation;
import io.reactivex.Flowable;

public class OperationRepositoryDataBase implements OperationRepository {

	private AppDatabase db;

	public OperationRepositoryDataBase() {
		db = App.instance.getDatabase();
	}

	@NonNull
	@Override
	public Flowable<List<Operation>> getOperations(int expanseId) {
		return db.operationDao()
			.getOperationsByExpense(expanseId);
	}

	@SuppressLint("CheckResult")
	@Override
	public void addOperation(Operation operation, @Nullable Period period) {
		long id = db.operationDao().insert(operation);

		// При добавлении новой операции нужно обновить баланс кошелька
		int expenseId = operation.getExpenseId();
		db.operationDao().getOperationsByExpense(expenseId)
			.subscribe(operations -> {
				float sum = 0;
				for (Operation o : operations) {
					sum += o.getSum();
				}
				db.expenseDao().updateBalance(expenseId, sum);
			});

		// При добавлении новой периодичной операции, нужно создавать ещё запись в таблицу
		// переодичных записей
		if (period != null) {
			period.operationId = id;
			db.periodDao().insert(period);
		}
	}
}
