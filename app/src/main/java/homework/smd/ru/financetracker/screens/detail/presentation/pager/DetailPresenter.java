package homework.smd.ru.financetracker.screens.detail.presentation.pager;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import homework.smd.ru.financetracker.screens.detail.domain.DetailInteractor;
import homework.smd.ru.financetracker.screens.detail.domain.DetailInteractorStub;
import homework.smd.ru.financetracker.screens.detail.presentation.DetailContract;
import homework.smd.ru.financetracker.screens.detail.presentation.tabs.DetailViewTab;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class DetailPresenter implements DetailContract.PresenterPager {

	private final static int MAX_FIXED_TABS = 4;
	private CompositeDisposable cd = new CompositeDisposable();
	private DetailInteractor interactor = new DetailInteractorStub();
	private final List<DetailContract.ViewTab> viewTabs = new ArrayList<>();
	private final List<String> titles = new ArrayList<>();

	@Nullable private DetailContract.ViewPager viewPager;
	@Nullable private TabPageAdapter adapter;


	@Override
	public void attachView(DetailContract.ViewPager pager) {
		this.viewPager = pager;
		this.adapter = viewPager.getAdapter();
		final Disposable disposable = interactor
			.getCosts()
			.subscribe(
				(costs) -> {
					viewTabs.add(DetailViewTab.getDetailPageInstance(costs));
					titles.add(costs.getTitle());
				},
				(err) -> Log.e(getClass().getName(), err.getMessage(), err),
				() -> {
					if (adapter != null) {
						adapter.updateTabs(viewTabs);
						adapter.updateTitles(titles);
						adapter.notifyDataSetChanged();

						if (viewTabs.size() > MAX_FIXED_TABS) {
							this.viewPager.getLayout().setTabMode(TabLayout.MODE_SCROLLABLE);
						} else {
							this.viewPager.getLayout().setTabMode(TabLayout.MODE_FIXED);
						}
					}
				}
			);
		cd.add(disposable);
	}

	@Override
	public void detachView() {
		viewPager = null;
		viewTabs.clear();
		adapter = null;
		cd.clear();
	}
}
