package homework.smd.ru.financetracker.database;

import java.util.Date;

import homework.smd.ru.financetracker.models.Currency;
import homework.smd.ru.financetracker.models.Wallet;
import homework.smd.ru.financetracker.models.Operation;

public class InitialData {

	public void run(AppDatabase database) {
		database.walletDao().insert(
			new Wallet(1, "Заначка", true, 500)
		);
		database.walletDao().insert(
			new Wallet(2, "Карта Сбербанка", false, 5000)
		);
		database.walletDao().insert(
			new Wallet(3, "Любовница", true, -40455)
		);

		Date date = new Date();
		database.operationDao().insert(
			new Operation(1000, Currency.RUB, "Зарплата", 1, date)
		);
		database.operationDao().insert(
			new Operation(-500, Currency.RUB, "Водочка", 1, date)
		);
		database.operationDao().insert(
			new Operation(6000, Currency.RUB, "Рэкет", 2, date)
		);
		database.operationDao().insert(
			new Operation(-1000, Currency.RUB, "Кино", 2, date)
		);
		database.operationDao().insert(
			new Operation(-27999, Currency.RUB, "Шуба", 3, date)
		);
		database.operationDao().insert(
			new Operation(-12456, Currency.RUB, "Ресторан", 3, date)
		);
	}
}
