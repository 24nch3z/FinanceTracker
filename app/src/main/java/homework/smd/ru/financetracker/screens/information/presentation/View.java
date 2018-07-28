package homework.smd.ru.financetracker.screens.information.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.text.HtmlCompat;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import homework.smd.ru.financetracker.R;

public class View extends Fragment implements Contract.View {

	private Contract.Presenter presenter;

	private Unbinder unbinder;
	@BindView(R.id.content_about) TextView contentView;

	public View() { }

	@NonNull
	public static View newAboutInstance() {
		return new View();
	}

	@Override
	public android.view.View onCreateView(@NonNull LayoutInflater inflater,
	                                      ViewGroup container,
	                                      Bundle savedInstanceState) {
		final android.view.View view = inflater.inflate(R.layout.fragment_about, container, false);
		unbinder = ButterKnife.bind(this, view);
		contentView.setTextSize(getResources().getDimension(R.dimen.text_size));
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

	@Override
	public void setHtmlContent(String content) {
		contentView.setText(HtmlCompat.fromHtml(content, HtmlCompat.FROM_HTML_MODE_COMPACT));
	}

	@Override
	public void setError(@StringRes long stringID) {
		contentView.setText(R.string.content_unavailable);
	}
}
