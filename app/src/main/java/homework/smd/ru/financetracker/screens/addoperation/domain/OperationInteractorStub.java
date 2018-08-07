package homework.smd.ru.financetracker.screens.addoperation.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import homework.smd.ru.financetracker.datalayer.OperationRepository;
import homework.smd.ru.financetracker.datalayer.repositories.OperationRepositoryDataBase;
import homework.smd.ru.financetracker.models.Operation;
import homework.smd.ru.financetracker.models.Period;

public class OperationInteractorStub implements OperationInteractor {

	private final OperationRepository operationRepository = new OperationRepositoryDataBase();

	@Override
	public void addOperation(@NonNull Operation operation, @Nullable Period period) {
		operationRepository.addOperation(operation, period);
	}
}
