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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.screens.detail.presentation.DetailContract;
import homework.smd.ru.financetracker.screens.detail.presentation.DetailPresenter;

public class DetailViewPager extends Fragment implements DetailContract.ViewPager {

	public static final String TAB_POSITION = "TAB_POSITION";

	public DetailViewPager() { }

	private Unbinder unbinder;
	private DetailContract.Presenter presenter;

	public static Fragment newDetailInstance() {
		return new DetailViewPager();
	}
	public static Fragment newDetailInstance(final int tabPosition) {
		final Fragment fragment = new DetailViewPager();
		final Bundle bundle = new Bundle();

		bundle.putInt(TAB_POSITION, tabPosition);
		fragment.setArguments(bundle);

		return fragment;
	}


	private TabPageAdapter adapter;
	@BindView(R.id.tab_pager) ViewPager pager;
	@BindView(R.id.tab_layout) TabLayout tabLayout;

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.fragment_detail_tablayout, container, false);
		unbinder = ButterKnife.bind(this, view);

		adapter = new TabPageAdapter(getFragmentManager());
		pager.setAdapter(adapter);
		tabLayout.setupWithViewPager(pager);

		initPresenter();
		return view;
	}

	private void initPresenter() {
		presenter = new DetailPresenter();
		presenter.attachView(this);

		if (getArguments() != null) {
			int tabPosition = getArguments().getInt(TAB_POSITION, 0);
			presenter.setOpenTabPosition(tabPosition);
		}
		pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				presenter.onTabChanged(position);
			}
		});
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
