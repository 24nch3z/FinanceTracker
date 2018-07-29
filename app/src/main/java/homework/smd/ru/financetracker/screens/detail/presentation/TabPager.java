package homework.smd.ru.financetracker.screens.detail.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import homework.smd.ru.financetracker.R;

public class TabPager extends Fragment implements DetailContract.ViewTabPager {

	public TabPager() { }

	private Unbinder unbinder;
	private DetailContract.Presenter presenter;
	public static Fragment newDetailInstance() {
		return new TabPager();
	}

	@BindView(R.id.tab_layout) TabLayout tabLayout;
	@BindView(R.id.tab_pager) ViewPager pager;


	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container,
	                         Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.fragment_detail_tabsholder, container, false);
		unbinder = ButterKnife.bind(this, view);



//		presenter = new SettingsPresenter();
		presenter.attachPager(this);
		return view;
	}


	@Override
	public void onDestroyView() {
		presenter.detachPager();
		unbinder.unbind();
		super.onDestroyView();
	}


	@Override
	public TabLayout getLayout() {
		return tabLayout;
	}

	@Override
	public void setupAdapter(@NonNull FragmentPagerAdapter adapter) {
		pager.setAdapter(adapter);
		tabLayout.setupWithViewPager(pager);
	}
}
