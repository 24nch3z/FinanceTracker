package homework.smd.ru.financetracker.screens.detail.presentation.pager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.screens.detail.presentation.DetailContract;

public class DetailViewPager extends Fragment implements DetailContract.ViewPager {

	public DetailViewPager() { }

	private Unbinder unbinder;
	private DetailContract.PresenterPager presenter;
	public static Fragment newDetailInstance() {
		return new DetailViewPager();
	}


	private TabPageAdapter adapter;
	@BindView(R.id.tab_pager)
	android.support.v4.view.ViewPager pager;
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

		presenter = new DetailPresenter();
		presenter.attachView(this);
		return view;
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
	public TabLayout getLayout() {
		return tabLayout;
	}
}
