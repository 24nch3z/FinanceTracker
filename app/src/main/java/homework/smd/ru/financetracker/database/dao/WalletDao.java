package homework.smd.ru.financetracker.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import homework.smd.ru.financetracker.models.Wallet;
import io.reactivex.Flowable;

@Dao
public interface WalletDao {

	@Query("SELECT * FROM Wallet")
	Flowable<List<Wallet>> getExpenses();

	@Query("UPDATE Wallet SET sum = :balance WHERE id = :expenseId")
	void updateBalance(int expenseId, float balance);

	@Insert
	void insert(Wallet wallet);

	@Update
	void update(Wallet wallet);

	@Delete
	void delete(Wallet wallet);
}
