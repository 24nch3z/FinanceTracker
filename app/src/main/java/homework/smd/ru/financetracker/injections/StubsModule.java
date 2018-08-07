package homework.smd.ru.financetracker.injections;

import dagger.Module;
import dagger.Provides;
import homework.smd.ru.financetracker.screens.addoperation.domain.OperationInteractor;
import homework.smd.ru.financetracker.screens.addoperation.domain.OperationInteractorStub;
import homework.smd.ru.financetracker.screens.detail.domain.DetailInteractor;
import homework.smd.ru.financetracker.screens.detail.domain.DetailInteractorStub;
import homework.smd.ru.financetracker.screens.main.domain.MainInteractor;
import homework.smd.ru.financetracker.screens.main.domain.MainInteractorImpl;
import homework.smd.ru.financetracker.screens.template.domain.TemplateInteractor;
import homework.smd.ru.financetracker.screens.template.domain.TemplateInteractorImpl;
import homework.smd.ru.financetracker.screens.wallet.domain.WalletInteractor;
import homework.smd.ru.financetracker.screens.wallet.domain.WalletInteractorImpl;

@Module
public class StubsModule {

	@Provides
	OperationInteractor provideOperationInteractor() {
		return new OperationInteractorStub();
	}

	@Provides
	DetailInteractor provideDetailInteractor() {
		return new DetailInteractorStub();
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
}
