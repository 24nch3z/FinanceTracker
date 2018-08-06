package homework.smd.ru.financetracker.screens.detail.presentation;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import homework.smd.ru.financetracker.models.Wallet;
import homework.smd.ru.financetracker.screens.detail.domain.DetailInteractor;
import homework.smd.ru.financetracker.screens.detail.presentation.pager.TabPageAdapter;
import homework.smd.ru.financetracker.screens.detail.presentation.tabs.DetailViewTab;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class DetailPresenter implements DetailContract.Presenter {

	private final static int MAX_FIXED_TABS = 4;

	private final CompositeDisposable cd = new CompositeDisposable();
	private final List<DetailContract.ViewTab> viewTabs = new ArrayList<>();
	private final List<Wallet> costs = new ArrayList<>();
	private final DetailInteractor interactor;

	@Nullable private DetailContract.ViewPager viewPager;
	@Nullable private TabPageAdapter pageAdapter;
	private int currentTabPosition = 0;


	public DetailPresenter(DetailInteractor interactor) {
		this.interactor = interactor;
	}

	@Override
	public void attachView(DetailContract.ViewPager pager) {
		this.viewPager = pager;
		this.pageAdapter = viewPager.getAdapter();
		final List<String> titles = new ArrayList<>();
		final Disposable disposable = interactor
			.getCosts()
			.onBackpressureBuffer()
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(
				(costs) -> {
					this.costs.clear();
					this.costs.addAll(costs);
					viewTabs.clear();

					for (Wallet expense : costs) {
						viewTabs.add(DetailViewTab.getDetailPageInstance(expense.getId()));
						titles.add(expense.getTitle());
					}

					if (pageAdapter != null) {
						pageAdapter.updateTabs(viewTabs);
						pageAdapter.updateTitles(titles);
						pageAdapter.notifyDataSetChanged();

						if (viewTabs.size() > MAX_FIXED_TABS) {
							this.viewPager.getLayout().setTabMode(TabLayout.MODE_SCROLLABLE);
						} else {
							this.viewPager.getLayout().setTabMode(TabLayout.MODE_FIXED);
						}
					}
					if (this.viewPager != null) {
						this.viewPager.getPager().setCurrentItem(currentTabPosition);
					}
				},
				(err) -> Log.e(getClass().getName(), err.getMessage(), err)
			);
		cd.add(disposable);
	}

	@Override
	public void detachView() {
		viewPager = null;
		viewTabs.clear();
		pageAdapter = null;
		cd.clear();
	}

	@Override
	public void setOpenTabPosition(int tabPosition) {
		currentTabPosition = tabPosition;
	}
}
