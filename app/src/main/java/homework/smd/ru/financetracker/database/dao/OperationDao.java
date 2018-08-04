package homework.smd.ru.financetracker.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import homework.smd.ru.financetracker.models.Operation;
import io.reactivex.Flowable;

@Dao
public interface OperationDao {

	@Query("SELECT * FROM operation")
	Flowable<List<Operation>> getAll();

	@Query("SELECT * FROM operation WHERE expenseId = :expenseEntityId")
	Flowable<List<Operation>> getOperationsByExpense(int expenseEntityId);

	@Insert
	void insert(Operation operation);

	@Update
	void update(Operation operation);

	@Delete
	void delete(Operation operation);
}
