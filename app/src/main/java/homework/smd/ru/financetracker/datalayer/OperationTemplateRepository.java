package homework.smd.ru.financetracker.datalayer;

import java.util.List;

import homework.smd.ru.financetracker.models.OperationTemplate;
import io.reactivex.Flowable;

public interface OperationTemplateRepository {

	Flowable<List<OperationTemplate>> getList();

	long insert(OperationTemplate template);

	void update(OperationTemplate template);

	void remove(OperationTemplate template);
}
