package homework.smd.ru.financetracker.screens.detail.presentation;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import homework.smd.ru.financetracker.screens.detail.presentation.pager.TabPageAdapter;
import homework.smd.ru.financetracker.screens.detail.presentation.tabs.OperationRecyclerAdapter;

public interface DetailContract {

	interface ViewPager {
		TabPageAdapter getAdapter();
		TabLayout getLayout();
		android.support.v4.view.ViewPager getPager();
	}

	abstract class ViewTab extends Fragment {}

	interface Presenter {
		void attachView(ViewPager pager);
		void detachView();
		void setOpenTabPosition(int tabPosition);
	}
}
