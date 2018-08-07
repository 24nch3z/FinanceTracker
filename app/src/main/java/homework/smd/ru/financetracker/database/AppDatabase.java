package homework.smd.ru.financetracker.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import homework.smd.ru.financetracker.database.dao.OperationTemplateDao;
import homework.smd.ru.financetracker.database.dao.WalletDao;
import homework.smd.ru.financetracker.database.dao.OperationDao;
import homework.smd.ru.financetracker.database.dao.PeriodDao;
import homework.smd.ru.financetracker.models.OperationTemplate;
import homework.smd.ru.financetracker.models.Period;
import homework.smd.ru.financetracker.models.Wallet;
import homework.smd.ru.financetracker.models.Operation;

@Database(entities = {
	Operation.class,
	Wallet.class,
	Period.class,
	OperationTemplate.class
}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
	public abstract OperationDao operationDao();
	public abstract WalletDao walletDao();
	public abstract PeriodDao periodDao();
	public abstract OperationTemplateDao operationTemplateDao();
}