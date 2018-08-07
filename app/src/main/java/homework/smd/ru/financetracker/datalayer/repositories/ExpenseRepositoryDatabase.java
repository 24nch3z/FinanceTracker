package homework.smd.ru.financetracker.datalayer.repositories;

import android.support.annotation.NonNull;

import java.util.List;

import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.database.AppDatabase;
import homework.smd.ru.financetracker.datalayer.ExpenseRepository;
import homework.smd.ru.financetracker.models.Wallet;
import io.reactivex.Flowable;

public class ExpenseRepositoryDatabase implements ExpenseRepository {

	private AppDatabase db;

	public ExpenseRepositoryDatabase() {
		db = App.instance.getDatabase();
	}

	@NonNull
	@Override
	public Flowable<List<Wallet>> getExpens() {
		return db.walletDao()
			.getExpenses();
	}

	@Override
	public void updateExpense(Wallet wallet) {
		db.walletDao().update(wallet);
	}
}