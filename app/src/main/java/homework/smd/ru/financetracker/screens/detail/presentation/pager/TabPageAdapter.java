package homework.smd.ru.financetracker.screens.detail.presentation.pager;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Collections;
import java.util.List;

import homework.smd.ru.financetracker.screens.detail.presentation.DetailContract;

public class TabPageAdapter extends FragmentPagerAdapter {

	private List<DetailContract.ViewTab> fragments = Collections.emptyList();
	private List<String> titles = Collections.emptyList();

	TabPageAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	@NonNull
	@Override
	public CharSequence getPageTitle(int position) {
		return titles.get(position);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	public void updateTabs(List<DetailContract.ViewTab> fragments) {
		this.fragments = fragments;
	}

	public void updateTitles(List<String> titles) {
		this.titles = titles;
	}
}
