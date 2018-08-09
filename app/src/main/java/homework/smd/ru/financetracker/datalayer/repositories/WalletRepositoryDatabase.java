package homework.smd.ru.financetracker.datalayer.repositories;

import android.support.annotation.NonNull;

import java.util.List;

import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.database.AppDatabase;
import homework.smd.ru.financetracker.datalayer.WalletRepository;
import homework.smd.ru.financetracker.models.Wallet;
import io.reactivex.Flowable;

public class WalletRepositoryDatabase implements WalletRepository {

	private AppDatabase db;

	public WalletRepositoryDatabase() {
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
