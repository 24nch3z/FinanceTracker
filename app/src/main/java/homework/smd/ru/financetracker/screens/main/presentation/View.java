package homework.smd.ru.financetracker.screens.main.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.modules.Configuration;

public class View extends Fragment {

	@Inject Configuration configuration;

	private Unbinder unbinder;
	public View() { }

	public static Fragment newMainInstance() {
		return new View();
	}

	@Override
	public android.view.View onCreateView(@NonNull LayoutInflater inflater,
	                                      ViewGroup container,
	                                      Bundle savedInstanceState) {
		final android.view.View view = inflater.inflate(R.layout.fragment_main, container, false);

		App.getComponent().inject(this);
		unbinder = ButterKnife.bind(this, view);

		return view;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		unbinder.unbind();
	}
}
