package homework.smd.ru.financetracker.injections;

import dagger.Module;
import dagger.Provides;
import homework.smd.ru.financetracker.screens.addoperation.domain.OperationInteractor;
import homework.smd.ru.financetracker.screens.addoperation.domain.OperationInteractorImpl;
import homework.smd.ru.financetracker.screens.main.domain.MainInteractor;
import homework.smd.ru.financetracker.screens.main.domain.MainInteractorImpl;
import homework.smd.ru.financetracker.screens.template.domain.TemplateInteractor;
import homework.smd.ru.financetracker.screens.template.domain.TemplateInteractorImpl;
import homework.smd.ru.financetracker.screens.wallet.domain.WalletInteractor;
import homework.smd.ru.financetracker.screens.wallet.domain.WalletInteractorImpl;
import homework.smd.ru.financetracker.screens.walletreport.domain.WalletReportInteractor;
import homework.smd.ru.financetracker.screens.walletreport.domain.WalletReportInteractorImpl;

@Module
public class StubsModule {

	@Provides
	OperationInteractor provideOperationInteractor() {
		return new OperationInteractorImpl();
	}

	@Provides
	MainInteractor provideMainInteractor() {
		return new MainInteractorImpl();
	}

	@Provides
	WalletInteractor provideWalletInteractor() {
		return new WalletInteractorImpl();
	}

	@Provides
	TemplateInteractor provideTemplateInteractor() {
		return new TemplateInteractorImpl();
	}

	@Provides
	WalletReportInteractor provideWalletReportInteractor() {
		return new WalletReportInteractorImpl();
	}
}
