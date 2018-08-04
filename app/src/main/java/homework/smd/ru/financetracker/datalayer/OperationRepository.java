package homework.smd.ru.financetracker.datalayer;

import android.support.annotation.NonNull;

import java.util.List;

import homework.smd.ru.financetracker.models.Operation;
import io.reactivex.Flowable;

public interface OperationRepository {

	@NonNull Flowable<List<Operation>> getOperations(int expanseId);

	void addOperation(Operation operation);
}
