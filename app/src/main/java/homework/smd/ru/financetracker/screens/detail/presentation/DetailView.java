package homework.smd.ru.financetracker.screens.detail.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import homework.smd.ru.financetracker.models.Costs;

public class DetailView extends Fragment {

	private final static String ARG_COSTS = "costs";

	public static Fragment getDetailPageInstance(@NonNull final Costs costs) {

		final Fragment instance = new DetailView();
		final Bundle arguments = new Bundle();

		arguments.putParcelable(ARG_COSTS, costs);
		instance.setArguments(arguments);

		return instance;
	}


}
