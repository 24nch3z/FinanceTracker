package homework.smd.ru.financetracker.database;

import java.util.Date;

import homework.smd.ru.financetracker.models.Currency;
import homework.smd.ru.financetracker.models.Wallet;
import homework.smd.ru.financetracker.models.Operation;

public class InitialData {

	public void run(AppDatabase database) {
		database.walletDao().insert(
			new Wallet(1, "Наличка", true, 1000)
		);
		database.walletDao().insert(
			new Wallet(2, "Карта Сбербанка", false, 5000)
		);
		database.walletDao().insert(
			new Wallet(3, "Любовница", true, -165394)
		);

		Date date = new Date();
		database.operationDao().insert(
			new Operation(1000, Currency.RUB, "Рыбка", 1, date)
		);
		database.operationDao().insert(
			new Operation(6000, Currency.RUB, "Рэкет", 2, date)
		);
		database.operationDao().insert(
			new Operation(-1000, Currency.RUB, "Кино", 2, date)
		);
		database.operationDao().insert(
			new Operation(-165394, Currency.RUB, "Алмазы", 3, date)
		);
	}
}
