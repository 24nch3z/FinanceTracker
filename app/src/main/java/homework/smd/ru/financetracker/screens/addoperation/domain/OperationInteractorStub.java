package homework.smd.ru.financetracker.screens.addoperation.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import homework.smd.ru.financetracker.datalayer.OperationRepository;
import homework.smd.ru.financetracker.datalayer.OperationTemplateRepository;
import homework.smd.ru.financetracker.datalayer.repositories.OperationRepositoryDataBase;
import homework.smd.ru.financetracker.datalayer.repositories.OperationTemplateRepositoryDatabase;
import homework.smd.ru.financetracker.models.Operation;
import homework.smd.ru.financetracker.models.OperationTemplate;
import homework.smd.ru.financetracker.models.Period;
import io.reactivex.Flowable;

public class OperationInteractorStub implements OperationInteractor {

	private final OperationRepository operationRepository =
		new OperationRepositoryDataBase();

	private final OperationTemplateRepository operationTemplateRepository =
		new OperationTemplateRepositoryDatabase();

	@Override
	public void addOperation(@NonNull Operation operation, @Nullable Period period) {
		operationRepository.addOperation(operation, period);
	}

	@Override
	public Flowable<List<OperationTemplate>> getTemplates() {
		return operationTemplateRepository.getList();
	}
}
