package homework.smd.ru.financetracker.fragments;


import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.text.HtmlCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;
import homework.smd.ru.financetracker.R;

public class AboutFragment extends Fragment {

	private static final String TEXT_CONTENT = "about.txt";

	@BindView(R.id.content_about) TextView contentView;

	public AboutFragment() { }

	@NonNull
	public static AboutFragment newInstance() {
		return new AboutFragment();
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container,
	                         Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_about, container, false);
		ButterKnife.bind(this, view);

		final String content = loadContentFromAssets(view.getContext().getAssets());
		contentView.setText(HtmlCompat.fromHtml(content, HtmlCompat.FROM_HTML_MODE_COMPACT));
		contentView.setTextSize(getResources().getDimension(R.dimen.text_size));

		return view;
	}

	private String loadContentFromAssets(@NonNull AssetManager manager) {

		final StringBuilder builder = new StringBuilder();

		try(final InputStream inputStream = manager.open(TEXT_CONTENT)) {
			final BufferedReader reader =
					new BufferedReader(new InputStreamReader(inputStream));

			while (true) {
				final String line = reader.readLine();
				if (line == null) break;
				builder.append(line);
			}

		} catch (IOException e) {
			builder.append(getString(R.string.content_unavailable));
		}

		return builder.toString();
	}

}
