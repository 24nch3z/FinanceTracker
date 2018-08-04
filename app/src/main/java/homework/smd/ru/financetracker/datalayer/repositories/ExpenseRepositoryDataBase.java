package homework.smd.ru.financetracker.datalayer.repositories;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.database.AppDatabase;
import homework.smd.ru.financetracker.database.converter.OperationConverter;
import homework.smd.ru.financetracker.database.entity.OperationEntity;
import homework.smd.ru.financetracker.datalayer.ExpenseRepository;

import homework.smd.ru.financetracker.models.Expense;
import homework.smd.ru.financetracker.models.Operation;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;

public class ExpenseRepositoryDataBase implements ExpenseRepository {

	private List<Expense> expenses = new ArrayList<>();
	private AppDatabase db;

	@SuppressLint("CheckResult")
	public ExpenseRepositoryDataBase() {
		db = App.instance.getDatabase();

//		expenses.add(new Expense(1, "Наличка",
//			new Balance("Наличка", true, 100)));



//		List<ExpenseEntity> entities = db.expenseDao().getExpenses();
//
//		for (ExpenseEntity entity : entities) {
//			List<OperationEntity> operationEntities = db.operationDao()
//				.getOperationsByExpense(entity.id);
//
//			// TODO: Вынести total куда-то (спросить Диму, реализовал ли он эту штуку)
//			float total = 0;
//
//			List<Operation> operations = new ArrayList<>();
//			for (OperationEntity operationEntity : operationEntities) {
//				operations.add(OperationConverter.fromEntity(operationEntity));
//				total += operationEntity.sum;
//			}
//
//			Expense expense = ExpenseConverter.fromEntity(entity);
//			expense.setOperations(operations);
//			expense.setBalance(new Balance(expense.getTitle(), true, Float.valueOf(total)));
//
//			expenses.add(expense);
//		}

//		db.expenseDao().getExpenses()
//			.subscribe(expenseEntities -> {
//
//				for (ExpenseEntity expenseEntity : expenseEntities) {
//					Expense expense = ExpenseConverter.fromEntity(expenseEntity);
//
//					db.operationDao().getOperationsByExpense(expense.getId())
//						.subscribe(operationEntities -> {
//							List<Operation> operations = new ArrayList<>();
//							float total = 0;
//
//							for (OperationEntity operationEntity : operationEntities) {
//								operations.add(OperationConverter.fromEntity(operationEntity));
//								total += operationEntity.sum;
//							}
//
//							expense.setOperations(operations);
//							expense.setBalance(new Balance(expense.getTitle(), true, Float.valueOf(total)));
//							expenses.add(expense);
//						});
//
//				}
//
////				List<OperationEntity> operationEntities = db.operationDao()
////					.getOperationsByExpense(entity.id);
//			});
	}

	@NonNull
	@Override
	public Flowable<List<Expense>> getExpenses() {
		return db.expenseDao()
			.getExpenses()
			.map(expenses -> {
				for (Expense expense : expenses) {

				}
				return expenses;
			});
	}

//	@NonNull
//	@Override
//	public Flowable<List<Balance>> getBalances() {
//		List<Balance> list = new ArrayList<>();
//		for (Expense expense : expenses) {
//			list.add(expense.getBalance());
//		}
//		return Flowable.just(list);
//	}

	@Override
	public void addOperation(@NonNull Operation operation, final int expanseId) {
		OperationEntity operationEntity = OperationConverter.toEntity(operation, expanseId);
//		db.operationDao().insert(operationEntity);
	}
}
