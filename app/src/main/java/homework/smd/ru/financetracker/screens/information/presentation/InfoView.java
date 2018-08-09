package homework.smd.ru.financetracker.screens.information.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.R;

public class InfoView extends Fragment implements InfoContract.View {

	@Inject InfoContract.Presenter presenter;

	private Unbinder unbinder;
	@BindView(R.id.content_about) TextView contentView;

	public InfoView() { }

	@NonNull
	public static InfoView newAboutInstance() {
		return new InfoView();
	}

	@Override
	public android.view.View onCreateView(@NonNull LayoutInflater inflater,
	                                      ViewGroup container,
	                                      Bundle savedInstanceState) {
		final android.view.View view = inflater.inflate(R.layout.fragment_about, container, false);
		App.getComponent().inject(this);
		unbinder = ButterKnife.bind(this, view);
		presenter.attachView(this);
		setToolbarText();
		return view;
	}

	private void setToolbarText() {
		((AppCompatActivity) getActivity()).getSupportActionBar()
			.setTitle(getString(R.string.toolbar_about));
	}

	@Override
	public void onDestroyView() {
		presenter.detachView();
		unbinder.unbind();
		super.onDestroyView();
	}

	@Override
	public void setHtmlContent(String content) {
		// TODO: Заменить
	}

	@Override
	public void setError(@StringRes long stringID) {
		contentView.setText(R.string.content_unavailable);
	}
}
