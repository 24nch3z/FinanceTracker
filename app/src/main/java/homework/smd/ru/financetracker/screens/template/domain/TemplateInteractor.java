package homework.smd.ru.financetracker.screens.template.domain;

import java.util.List;

import homework.smd.ru.financetracker.models.OperationTemplate;
import io.reactivex.Flowable;

public interface TemplateInteractor {

	Flowable<List<OperationTemplate>> getAll();

	long insert(OperationTemplate template);

	void update(OperationTemplate template);

	void remove(OperationTemplate template);
}
