package homework.smd.ru.financetracker.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import homework.smd.ru.financetracker.database.dao.ExpenseDao;
import homework.smd.ru.financetracker.database.dao.OperationDao;
import homework.smd.ru.financetracker.database.dao.PeriodDao;
import homework.smd.ru.financetracker.models.Period;
import homework.smd.ru.financetracker.models.Wallet;
import homework.smd.ru.financetracker.models.Operation;

@Database(entities = {
	Operation.class,
	Wallet.class,
	Period.class
}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
	public abstract OperationDao operationDao();
	public abstract ExpenseDao expenseDao();
	public abstract PeriodDao periodDao();
}