package homework.smd.ru.financetracker.screens.detail.presentation;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;

interface DetailContract {

	interface ViewTabPager {
		void setupAdapter(@NonNull final FragmentPagerAdapter adapter);
		TabLayout getLayout();
	}

	interface ViewTab {

	}

	interface Presenter {
		void attachPager(ViewTabPager pager);
		void detachPager();

		void attachTab(int position);
		void detachTab(int position);
	}
}
