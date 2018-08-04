package homework.smd.ru.financetracker.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import homework.smd.ru.financetracker.models.Expense;
import io.reactivex.Flowable;

@Dao
public interface ExpenseDao {

	@Query("SELECT * FROM expense")
	Flowable<List<Expense>> getExpenses();

	@Insert
	void insert(Expense expense);

	@Update
	void update(Expense expense);

	@Delete
	void delete(Expense expense);
}
