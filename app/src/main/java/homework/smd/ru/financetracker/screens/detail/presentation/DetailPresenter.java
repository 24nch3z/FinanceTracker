package homework.smd.ru.financetracker.screens.detail.presentation;

import android.support.annotation.Nullable;

import homework.smd.ru.financetracker.screens.detail.domain.DetailInteractor;

public class DetailPresenter implements DetailContract.Presenter {

	DetailInteractor interactor;
	@Nullable DetailContract.ViewTabPager viewPager;


	@Override
	public void attachPager(DetailContract.ViewTabPager pager) {
		this.viewPager = pager;

	}

	@Override
	public void detachPager() {
		viewPager = null;
		interactor.getCosts();
	}

	@Override
	public void attachTab(int position) {

	}

	@Override
	public void detachTab(int position) {

	}
}
