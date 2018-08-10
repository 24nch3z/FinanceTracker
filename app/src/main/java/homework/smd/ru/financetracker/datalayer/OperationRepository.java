package homework.smd.ru.financetracker.datalayer;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import homework.smd.ru.financetracker.models.Operation;
import homework.smd.ru.financetracker.models.Period;
import io.reactivex.Flowable;

public interface OperationRepository {

	@NonNull
	Flowable<List<Operation>> getOperations(int expanseId);

	void addOperation(Operation operation, @Nullable Period period);

	void removeOperation(Operation operation);
}
