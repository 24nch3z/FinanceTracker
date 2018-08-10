package homework.smd.ru.financetracker.screens.detail.presentation.pager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.screens.detail.presentation.DetailContract;

public class DetailViewPager extends Fragment implements DetailContract.ViewPager {

	public static final String TAB_POSITION = "TAB_POSITION";

	public static Fragment newInstance() {
		return new DetailViewPager();
	}

	public static Fragment newInstance(final Object tabPosition) {
		final Fragment fragment = new DetailViewPager();
		final Bundle bundle = new Bundle();

		if (tabPosition != null) {
			bundle.putInt(TAB_POSITION, (int) tabPosition);
		}
		fragment.setArguments(bundle);

		return fragment;
	}

	private Unbinder unbinder;
	private TabPageAdapter adapter;

	@Inject public DetailContract.Presenter presenter;

	@BindView(R.id.tab_pager) ViewPager pager;
	@BindView(R.id.tab_layout) TabLayout tabLayout;


	public DetailViewPager() { }

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.fragment_detail_tablayout, container, false);
		App.getComponent().inject(this);
		unbinder = ButterKnife.bind(this, view);

		adapter = new TabPageAdapter(getChildFragmentManager());
		pager.setAdapter(adapter);
		tabLayout.setupWithViewPager(pager);

		initPresenter();
		return view;
	}

	private void initPresenter() {
		presenter.attachView(this);

		if (getArguments() != null) {
			int tabPosition = getArguments().getInt(TAB_POSITION, 0);
			presenter.setOpenTabPosition(tabPosition);
		}
	}


	@Override
	public void onDestroyView() {
		presenter.detachView();
		unbinder.unbind();
		super.onDestroyView();
	}

	@Override
	public TabPageAdapter getAdapter() {
		return adapter;
	}

	@Override
	public ViewPager getPager() {
		return pager;
	}

	@Override
	public TabLayout getLayout() {
		return tabLayout;
	}
}
