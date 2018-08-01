package homework.smd.ru.financetracker.injections;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import homework.smd.ru.financetracker.screens.addoperation.domain.OperationInteractor;
import homework.smd.ru.financetracker.screens.addoperation.presentation.OperationContract;
import homework.smd.ru.financetracker.screens.addoperation.presentation.OperationPresenter;
import homework.smd.ru.financetracker.screens.detail.domain.DetailInteractor;
import homework.smd.ru.financetracker.screens.detail.presentation.DetailContract;
import homework.smd.ru.financetracker.screens.detail.presentation.DetailPresenter;
import homework.smd.ru.financetracker.screens.information.presentation.InfoContract;
import homework.smd.ru.financetracker.screens.information.presentation.InfoPresenter;
import homework.smd.ru.financetracker.screens.main.domain.MainInteractor;
import homework.smd.ru.financetracker.screens.main.presentation.MainContract;
import homework.smd.ru.financetracker.screens.main.presentation.MainPresenter;

@Module
class PresentersModule {

	@Provides
	InfoContract.Presenter provideInfoPresenter(Context context) {
		return new InfoPresenter(context);
	}

	@Provides
	DetailContract.Presenter provideDetailPresenter(DetailInteractor interactor) {
		return new DetailPresenter(interactor);
	}

	@Provides
	OperationContract.Presenter provideOperationPresenter(Context context, OperationInteractor interactor) {
		return new OperationPresenter(context, interactor);
	}

	@Provides
	MainContract.Presenter provideMainPresenter(MainInteractor interactor) {
		return new MainPresenter(interactor);
	}
}
