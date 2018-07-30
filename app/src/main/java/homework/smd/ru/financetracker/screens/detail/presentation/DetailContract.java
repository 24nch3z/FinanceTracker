package homework.smd.ru.financetracker.screens.detail.presentation;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import homework.smd.ru.financetracker.models.Operation;
import homework.smd.ru.financetracker.screens.detail.presentation.pager.TabPageAdapter;
import homework.smd.ru.financetracker.screens.detail.presentation.tabs.OperationRecyclerAdapter;

public interface DetailContract {

	interface ViewPager {
		TabPageAdapter getAdapter();
		TabLayout getLayout();
	}

	abstract class ViewTab extends Fragment {
		public abstract void setAdapter(RecyclerView.Adapter adapter);
		public abstract OperationRecyclerAdapter getRecycler();
	}

	interface Presenter {
		void attachView(ViewPager pager);
		void detachView();
		void onTabChanged(int position);

		void addNewOperation(final Operation operation);
	}
}
