package homework.smd.ru.financetracker.screens.template.presentation.creator;

import homework.smd.ru.financetracker.BasePresenter;
import homework.smd.ru.financetracker.screens.template.domain.TemplateInteractor;

public class TemplateCreatorPresenter extends BasePresenter<TemplateCreatorContract.View> {

	private TemplateInteractor interactor;

	public TemplateCreatorPresenter(TemplateInteractor interactor) {
		this.interactor = interactor;
	}
}
