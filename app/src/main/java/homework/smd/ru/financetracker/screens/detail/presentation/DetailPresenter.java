package homework.smd.ru.financetracker.screens.detail.presentation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import homework.smd.ru.financetracker.models.Expense;
import homework.smd.ru.financetracker.models.Operation;
import homework.smd.ru.financetracker.screens.detail.domain.DetailInteractor;
import homework.smd.ru.financetracker.screens.detail.domain.DetailInteractorStub;
import homework.smd.ru.financetracker.screens.detail.presentation.DetailContract;
import homework.smd.ru.financetracker.screens.detail.presentation.pager.TabPageAdapter;
import homework.smd.ru.financetracker.screens.detail.presentation.tabs.DetailViewTab;
import homework.smd.ru.financetracker.screens.detail.presentation.tabs.OperationRecyclerAdapter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class DetailPresenter implements DetailContract.Presenter {

	private final static int MAX_FIXED_TABS = 4;

	private CompositeDisposable cd = new CompositeDisposable();
	private DetailInteractor interactor = new DetailInteractorStub();

	private final List<DetailContract.ViewTab> viewTabs = new ArrayList<>();
	private final List<Expense> costs = new ArrayList<>();

	@Nullable private DetailContract.ViewPager viewPager;
	@Nullable private TabPageAdapter pageAdapter;
	private int currentTabPosition = 0;
	private String currentTabName = "";


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
				(cost) -> {
					costs.add(cost);
					viewTabs.add(DetailViewTab.getDetailPageInstance());
					titles.add(cost.getTitle());
				},
				(err) -> Log.e(getClass().getName(), err.getMessage(), err),
				() -> {
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
				}
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
	public void onTabChanged(int position) {
		final OperationRecyclerAdapter recycler = viewTabs.get(position).getRecycler();
		final List<Operation> dataset = costs.get(position).getOperations();
		recycler.updateDataset(dataset);
		recycler.notifyDataSetChanged();
		currentTabPosition = position;
	}

	@Override
	public void addNewOperation(Operation operation) {

	}

	@Override
	public void setOpenTabPosition(int tabPosition) {
		currentTabPosition = tabPosition;
	}
}
