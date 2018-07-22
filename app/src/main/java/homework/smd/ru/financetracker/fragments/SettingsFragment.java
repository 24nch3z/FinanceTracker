package homework.smd.ru.financetracker.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.modules.Configuration;

public class SettingsFragment extends Fragment {

	public static final int MIN_LENGTH = 3;

	@Inject public Configuration configuration;

	@BindView(R.id.settings_name) TextInputEditText editName;
	@BindView(R.id.settings_email) TextInputEditText editEmail;
	@Nullable private NavigationController navigationController;
	private View view;

	public SettingsFragment() { }

	public static Fragment newInstance() {
		return new SettingsFragment();
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (context instanceof NavigationController) {
			navigationController = (NavigationController) context;
		}
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container,
	                         Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_settings, container, false);
		App.getComponent().inject(this);
		ButterKnife.bind(this, view);

		// Set current value of user configuration
		editName.setText(configuration.getName());
		editEmail.setText(configuration.getEmail());
		editName.setSelection(configuration.getName().length());

		return view;
	}

	@OnClick(R.id.settings_apply_button)
	public void onApplyClick() {
		final Editable nameEditable = editName.getText();
		final Editable emailEditable = editEmail.getText();

		// Validation
		if (emailEditable == null || nameEditable == null) return;
		final String name = nameEditable.toString().trim();
		final String email = emailEditable.toString().trim();

		if (email.length() < MIN_LENGTH) {
			Toast.makeText(getContext(), R.string.too_short_email, Toast.LENGTH_SHORT).show();
			return;
		}

		if (name.length() < MIN_LENGTH) {
			Toast.makeText(getContext(), R.string.too_short_name, Toast.LENGTH_SHORT).show();
			return;
		}


		// Hide keyboard
		final InputMethodManager imm = (InputMethodManager)
				view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm != null) {
			imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}


		// Update user info in preference
		configuration.setName(name);
		configuration.setEmail(email);

		if (navigationController != null) {
			navigationController.updateUserInfo(name, email);
		}
	}


	@Override
	public void onDetach() {
		super.onDetach();
		navigationController = null;
	}

	public interface NavigationController {
		void updateUserInfo(@NonNull String name, @NonNull String email);
	}
}
