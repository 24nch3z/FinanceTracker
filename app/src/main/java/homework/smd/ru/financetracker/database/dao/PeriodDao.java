package homework.smd.ru.financetracker.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import homework.smd.ru.financetracker.models.Period;
import io.reactivex.Single;

@Dao
public interface PeriodDao {

	@Query("SELECT * FROM period")
	Single<List<Period>> getAll();

	@Query("SELECT * FROM period WHERE id = :id")
	Single<List<Period>> getById(long id);

	@Query("SELECT * FROM period WHERE operationId = :id")
	Single<List<Period>> getByOperationId(long id);

	@Insert
	long insert(Period period);

	@Update
	void update(Period period);

	@Delete
	void delete(Period period);
}
