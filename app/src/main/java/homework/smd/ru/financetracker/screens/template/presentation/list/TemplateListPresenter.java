package homework.smd.ru.financetracker.screens.template.presentation.list;

import java.util.ArrayList;
import java.util.List;

import homework.smd.ru.financetracker.BasePresenter;
import homework.smd.ru.financetracker.models.OperationTemplate;
import homework.smd.ru.financetracker.screens.template.domain.TemplateInteractor;
import homework.smd.ru.financetracker.utils.MyLog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class TemplateListPresenter extends BasePresenter<TemplateListContract.View> {

	private TemplateInteractor interactor;
	private TemplateListAdapter adapter;
	private List<OperationTemplate> templates;

	public TemplateListPresenter(TemplateInteractor interactor) {
		this.interactor = interactor;
		templates = new ArrayList<>();
	}

	public void attachView(TemplateListContract.View view, CallbackUpdateTemplate callback) {
		super.attachView(view);
		adapter = new TemplateListAdapter(templates, callback);
		view.setAdapter(adapter);

		final Disposable disposable = interactor
			.getAll()
			.onBackpressureBuffer()
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(templates -> {
				this.templates.clear();
				this.templates.addAll(templates);
				MyLog.l(templates.size());
				adapter.updateItems(this.templates);
				adapter.notifyDataSetChanged();
			});
		cd.add(disposable);
	}
}
