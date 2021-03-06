package homework.smd.ru.financetracker.database;

import homework.smd.ru.financetracker.models.Currency;
import homework.smd.ru.financetracker.models.Expense;
import homework.smd.ru.financetracker.models.Operation;

public class InitialData {

	public void run(AppDatabase database) {
		database.expenseDao().insert(
			new Expense(1, "Наличка", true, 1000)
		);
		database.expenseDao().insert(
			new Expense(2, "Карта сберыча", false, 5000)
		);
		database.expenseDao().insert(
			new Expense(3, "Любовница", true, 500000)
		);

		database.operationDao().insert(
			new Operation(1000, Currency.USD, "Рыбка", 1)
		);
		database.operationDao().insert(
			new Operation(6000, Currency.USD, "Рекит", 2)
		);
		database.operationDao().insert(
			new Operation(-1000, Currency.USD, "Кино", 2)
		);
		database.operationDao().insert(
			new Operation(500000, Currency.USD, "Алмазы", 3)
		);
	}
}
