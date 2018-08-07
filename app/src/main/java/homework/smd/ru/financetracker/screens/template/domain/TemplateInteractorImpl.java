package homework.smd.ru.financetracker.screens.template.domain;

import java.util.List;

import homework.smd.ru.financetracker.datalayer.OperationTemplateRepository;
import homework.smd.ru.financetracker.datalayer.repositories.OperationTemplateRepositoryDatabase;
import homework.smd.ru.financetracker.models.OperationTemplate;
import io.reactivex.Flowable;

public class TemplateInteractorImpl implements TemplateInteractor {

	private final OperationTemplateRepository operationTemplateRepository
		= new OperationTemplateRepositoryDatabase();

	@Override
	public Flowable<List<OperationTemplate>> getAll() {
		return operationTemplateRepository
			.getList();
	}

	@Override
	public long insert(OperationTemplate template) {
		return operationTemplateRepository
			.insert(template);
	}

	@Override
	public void update(OperationTemplate template) {
		operationTemplateRepository.update(template);
	}
}
