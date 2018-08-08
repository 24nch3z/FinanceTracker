package homework.smd.ru.financetracker.screens.template.presentation.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.screens.Screens;

public class TemplateListView extends Fragment implements TemplateListContract.View {

	private Unbinder unbinder;
	@Inject TemplateListPresenter presenter;

	@BindView(R.id.button_create_template) Button buttonCreateTemplate;
	@BindView(R.id.recycler_view) RecyclerView recyclerView;

	public static TemplateListView newInstance() {
		return new TemplateListView();
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_template_list, container, false);
		unbinder = ButterKnife.bind(this, view);
		App.getComponent().inject(this);

		initViews();
		presenter.attachView(this);

		return view;
	}

	@Override
	public void onDestroyView() {
		presenter.detachView();
		unbinder.unbind();
		super.onDestroyView();
	}

	private void initViews() {
		buttonCreateTemplate.setOnClickListener(view -> {
			App.instance.getRouter().navigateTo(Screens.SCREEN_TEMPLATE_CREATOR);
		});

		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//		recyclerView.addItemDecoration(new TemplateListDecorator((int)
//			getResources().getDimension(R.dimen.template_list_decorator_margin)));
	}

	@Override
	public void setAdapter(TemplateListAdapter adapter) {
		recyclerView.setAdapter(adapter);
	}
}
