package homework.smd.ru.financetracker.screens.settings.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Switch;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.Unbinder;
import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.R;

public class View extends Fragment implements Contract.View {

	private Contract.Presenter presenter;
	private Unbinder unbinder;

	@BindView(R.id.currency_switcher) Switch currencySwitcher;


	public View() { }

	public static Fragment newSettingInstance() {
		// TODO injections via Dagger?
		return new View();
	}


	@Override
	public android.view.View onCreateView(@NonNull LayoutInflater inflater,
	                                      ViewGroup container,
	                                      Bundle savedInstanceState) {

		final android.view.View view = inflater.inflate(R.layout.fragment_settings, container, false);
		App.getComponent().inject(this);
		unbinder = ButterKnife.bind(this, view);

		presenter = new Presenter();
		presenter.onCreateView(this);
		return view;
	}

	@Override
	public void onDestroyView() {
		presenter.onDestroyView();
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
