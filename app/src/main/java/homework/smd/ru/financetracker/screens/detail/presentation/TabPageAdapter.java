package homework.smd.ru.financetracker.screens.detail.presentation;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import homework.smd.ru.financetracker.models.Costs;

public class TabPageAdapter extends FragmentPagerAdapter {

	private final List<Costs> costs;

	TabPageAdapter(FragmentManager fm, List<Costs> costs) {
		super(fm);
		this.costs = costs;
	}

	@Override
	public Fragment getItem(int i) {
		return DetailView.getDetailPageInstance(costs.get(i));
	}

	@NonNull
	@Override
	public CharSequence getPageTitle(int position) {
		return costs.get(position).getTitle();
	}

	@Override
	public int getCount() {
		return costs.size();
	}
}
