package homework.smd.ru.financetracker.screens.template.presentation.creator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.models.OperationTemplate;

public class TemplateCreatorView extends Fragment implements TemplateCreatorContract.View {

	private static final String ARG_TEMPLATE = "ARG_TEMPLATE";

	private Unbinder unbinder;
	@Inject TemplateCreatorPresenter presenter;

	public static TemplateCreatorView newInstance(Object template) {
		TemplateCreatorView fragment = new TemplateCreatorView();
		Bundle args = new Bundle();
		if (template != null) {
			args.putSerializable(ARG_TEMPLATE, (OperationTemplate) template);
		}
		fragment.setArguments(args);
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_template_creator, container, false);
		unbinder = ButterKnife.bind(this, view);
		App.getComponent().inject(this);

		presenter.attachView(this);

		return view;
	}
}
