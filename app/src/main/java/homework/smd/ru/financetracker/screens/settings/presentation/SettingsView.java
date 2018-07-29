package homework.smd.ru.financetracker.screens.settings.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.Unbinder;
import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.R;

public class SettingsView extends Fragment implements SettingsContract.View {

	private SettingsContract.Presenter presenter;
	private Unbinder unbinder;

	@BindView(R.id.currency_switcher) Switch currencySwitcher;


	public SettingsView() { }

	public static Fragment newSettingInstance() {
		return new SettingsView();
	}


	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container,
	                         Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.fragment_settings, container, false);
		App.getComponent().inject(this);
		unbinder = ButterKnife.bind(this, view);

		presenter = new SettingsPresenter();
		presenter.attachView(this);
		return view;
	}

	@Override
	public void onDestroyView() {
		presenter.detachView();
		unbinder.unbind();
		super.onDestroyView();
	}

	@OnCheckedChanged(R.id.currency_switcher)
	public void onCurrencyChange(boolean isRub) {
		presenter.onChangeCurrency(isRub);
	}

	@Override
	public void changeCurrency(boolean isRub) {
		currencySwitcher.setChecked(isRub);
	}
}
