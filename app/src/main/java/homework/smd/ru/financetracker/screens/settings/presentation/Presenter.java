package homework.smd.ru.financetracker.screens.settings.presentation;

import android.support.annotation.Nullable;

public class Presenter implements Contract.Presenter {

	@Nullable private Contract.View view;

	@Override
	public void onCreateView(Contract.View view) {
		this.view = view;
		view.changeCurrency(true);
	}

	@Override
	public void onDestroyView() {
		this.view = null;
	}

	@Override
	public void onChangeCurrency(boolean isRub) {
		if (view == null) return;
		view.changeCurrency(isRub);
	}
}
