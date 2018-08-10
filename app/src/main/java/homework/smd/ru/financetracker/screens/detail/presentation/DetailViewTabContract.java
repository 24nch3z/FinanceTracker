package homework.smd.ru.financetracker.screens.detail.presentation;

import homework.smd.ru.financetracker.screens.detail.presentation.tabs.OperationRecyclerAdapter;

public interface DetailViewTabContract {

	interface View {
		void setAdapter(OperationRecyclerAdapter adapter);
	}
}
