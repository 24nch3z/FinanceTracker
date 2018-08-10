package homework.smd.ru.financetracker.screens.information.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import homework.smd.ru.financetracker.R;

public class InfoView extends Fragment {

	@NonNull
	public static InfoView newAboutInstance() {
		return new InfoView();
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container,
	                         Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_about, container, false);
		setToolbarText();
		return view;
	}

	private void setToolbarText() {
		((AppCompatActivity) getActivity()).getSupportActionBar()
			.setTitle(getString(R.string.toolbar_about));
	}
}
