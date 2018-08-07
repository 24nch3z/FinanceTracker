package homework.smd.ru.financetracker.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import homework.smd.ru.financetracker.models.OperationTemplate;
import io.reactivex.Flowable;

@Dao
public interface OperationTemplateDao {

	@Query("SELECT * FROM operationTemplate")
	Flowable<List<OperationTemplate>> getAll();

	@Insert
	long insert(OperationTemplate template);

	@Update
	void update(OperationTemplate template);

	@Delete
	void delete(OperationTemplate template);

}
