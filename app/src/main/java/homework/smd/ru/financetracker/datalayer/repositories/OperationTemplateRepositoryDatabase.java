package homework.smd.ru.financetracker.datalayer.repositories;

import java.util.List;

import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.database.AppDatabase;
import homework.smd.ru.financetracker.datalayer.OperationTemplateRepository;
import homework.smd.ru.financetracker.models.OperationTemplate;
import io.reactivex.Flowable;

public class OperationTemplateRepositoryDatabase implements OperationTemplateRepository {

	private AppDatabase db;

	public OperationTemplateRepositoryDatabase() {
		db = App.instance.getDatabase();
	}

	@Override
	public Flowable<List<OperationTemplate>> getList() {
		return db.operationTemplateDao()
			.getAll();
	}

	@Override
	public long insert(OperationTemplate template) {
		return db.operationTemplateDao().insert(template);
	}

	@Override
	public void update(OperationTemplate template) {
		db.operationTemplateDao().update(template);
	}
}
