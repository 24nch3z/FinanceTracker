package homework.smd.ru.financetracker.screens.settings.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import homework.smd.ru.financetracker.R;

public class SettingsView extends Fragment implements SettingsContract.View {

	private SettingsPresenter presenter;
	private Unbinder unbinder;

	@BindView(R.id.currency_spinner)
	Spinner spinnerCurrency;

	public static Fragment newSettingInstance() {
		return new SettingsView();
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container,
	                         Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.fragment_settings, container, false);
		unbinder = ButterKnife.bind(this, view);

		presenter = new SettingsPresenter();
		presenter.attachView(this, getContext());
		return view;
	}

	@Override
	public void onDestroyView() {
		presenter.detachView();
		unbinder.unbind();
		super.onDestroyView();
	}

	@Override
	public void initCurrencySpinner(List<String> currencyList, int defaultPosition) {
		initSpinner(currencyList, defaultPosition, spinnerCurrency);
	}

	private void initSpinner(List<String> list, int selection, Spinner spinner) {
		ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
			android.R.layout.simple_spinner_item, list);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setSelection(selection);
	}
}
