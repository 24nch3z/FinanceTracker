package homework.smd.ru.financetracker.injections;

import dagger.Module;
import dagger.Provides;
import homework.smd.ru.financetracker.screens.addoperation.domain.OperationInteractor;
import homework.smd.ru.financetracker.screens.addoperation.presentation.OperationPresenter;
import homework.smd.ru.financetracker.screens.main.domain.MainInteractor;
import homework.smd.ru.financetracker.screens.main.presentation.MainContract;
import homework.smd.ru.financetracker.screens.main.presentation.MainPresenter;
import homework.smd.ru.financetracker.screens.template.domain.TemplateInteractor;
import homework.smd.ru.financetracker.screens.template.presentation.creator.TemplateCreatorPresenter;
import homework.smd.ru.financetracker.screens.template.presentation.list.TemplateListPresenter;
import homework.smd.ru.financetracker.screens.wallet.domain.WalletInteractor;
import homework.smd.ru.financetracker.screens.wallet.presentation.WalletPresenter;
import homework.smd.ru.financetracker.screens.walletreport.domain.WalletReportInteractor;
import homework.smd.ru.financetracker.screens.walletreport.presentation.WalletReportPresenter;

@Module
class PresentersModule {

	@Provides
	OperationPresenter provideOperationPresenter(OperationInteractor interactor) {
		return new OperationPresenter(interactor);
	}

	@Provides
	MainContract.Presenter provideMainPresenter(MainInteractor interactor) {
		return new MainPresenter(interactor);
	}

	@Provides
	WalletPresenter provideWalletPresenter(WalletInteractor interactor) {
		return new WalletPresenter(interactor);
	}

	@Provides
	TemplateListPresenter provideTemplateListPresenter(TemplateInteractor interactor) {
		return new TemplateListPresenter(interactor);
	}

	@Provides
	TemplateCreatorPresenter provideTemplateCreatorPresenter(TemplateInteractor interactor) {
		return new TemplateCreatorPresenter(interactor);
	}

	@Provides
	WalletReportPresenter provideWalletReportInteractorReport(WalletReportInteractor interactor) {
		return new WalletReportPresenter(interactor);
	}
}
