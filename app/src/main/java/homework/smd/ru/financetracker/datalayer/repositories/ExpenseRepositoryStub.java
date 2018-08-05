package homework.smd.ru.financetracker.datalayer.repositories;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import homework.smd.ru.financetracker.datalayer.ExpenseRepository;
import homework.smd.ru.financetracker.models.Currency;
import homework.smd.ru.financetracker.models.Expense;
import homework.smd.ru.financetracker.models.Operation;
import io.reactivex.Flowable;

public class ExpenseRepositoryStub implements ExpenseRepository {

	private List<Expense> expenses = new ArrayList<>();

	public ExpenseRepositoryStub() {
		Expense expense;
		String title;

//		title = "Наличные";
//		expense = new Expense(
//			title,
//			generateOperations(),
//			new Balance(title, true, 42_000f),
//			1
//		);
//		expenses.add(expense);
//
//		title = "Карточка";
//		expense = new Expense(
//			title,
//			generateOperations(),
//			new Balance(title, true, -1_000f),
//			2
//		);
//		expenses.add(expense);
//
//		title = "Оффшоры в Гонконге";
//		expense = new Expense(
//			title,
//			generateOperations(),
//			new Balance(title, false, 100_500f),
//			3
//		);
//		expenses.add(expense);
	}

	@NonNull
	@Override
	public Flowable<List<Expense>> getExpenses() {
		return Flowable.just(expenses);
	}

	@Override
	public void updateExpense(Expense expense) {

	}

	private final static String[] types = { "Еда", "Коммунальные платежи", "Транспорт" };
	private final static Random random = new Random(System.currentTimeMillis());
	private List<Operation> generateOperations() {
		final List<Operation> list = new ArrayList<>();

//		for (int i = 0; i < random.nextInt(15) + 5; ++i) {
//			list.add(new Operation(
//				random.nextDouble() * 10_000 - 5_000,
//				Currency.USD, types[random.nextInt(types.length)]
//			));
//		}
		return list;
	}
}