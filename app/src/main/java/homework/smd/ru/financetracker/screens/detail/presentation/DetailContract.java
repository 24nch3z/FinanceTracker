package homework.smd.ru.financetracker.screens.detail.presentation;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import homework.smd.ru.financetracker.models.Costs;
import homework.smd.ru.financetracker.screens.detail.presentation.pager.TabPageAdapter;

public interface DetailContract {

	interface ViewPager {
		TabPageAdapter getAdapter();
		TabLayout getLayout();
	}

	interface PresenterPager {
		void attachView(ViewPager pager);
		void detachView();
	}


	interface PresenterTab {
		void attachView(ViewTab tab);
		void detachView();
	}

	abstract class ViewTab extends Fragment {
		public abstract void setAdapter(RecyclerView.Adapter adapter);
		public abstract Costs getCosts();
	}
}
