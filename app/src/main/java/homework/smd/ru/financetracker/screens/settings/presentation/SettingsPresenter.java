package homework.smd.ru.financetracker.screens.settings.presentation;

import android.support.annotation.Nullable;

public class SettingsPresenter implements SettingsContract.Presenter {

	@Nullable private SettingsContract.View view;

	@Override
	public void attachView(SettingsContract.View view) {
		this.view = view;
		view.changeCurrency(true);
	}

	@Override
	public void detachView() {
		this.view = null;
	}

	@Override
	public void onChangeCurrency(boolean isRub) {
		if (view == null) return;
		view.changeCurrency(isRub);
	}
}
