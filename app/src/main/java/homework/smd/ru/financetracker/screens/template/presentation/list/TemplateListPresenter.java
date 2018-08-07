package homework.smd.ru.financetracker.screens.template.presentation.list;

import homework.smd.ru.financetracker.BasePresenter;
import homework.smd.ru.financetracker.screens.template.domain.TemplateInteractor;

public class TemplateListPresenter extends BasePresenter<TemplateListContract.View> {

	private TemplateInteractor interactor;

	public TemplateListPresenter(TemplateInteractor interactor) {
		this.interactor = interactor;
	}
}
