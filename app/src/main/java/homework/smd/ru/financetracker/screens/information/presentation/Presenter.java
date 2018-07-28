package homework.smd.ru.financetracker.screens.information.presentation;

import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import homework.smd.ru.financetracker.R;

public class Presenter implements Contract.Presenter {

	private static final String TEXT_CONTENT = "about.txt";
	@Nullable private Contract.View view;

	@Override
	public void onCreateView(Contract.View view) {
		this.view = view;
		view.setHtmlContent("<h1>Hello, MVP!</h1>");
	}

	@Override
	public void onDestroyView() {
		view = null;
	}

	@Deprecated
	private void loadContentFromAssets(@NonNull AssetManager manager) {

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
			if (view == null) return;
			view.setError(R.string.content_unavailable);
			return;
		}

		if (view == null) return;
		view.setHtmlContent(builder.toString());
	}
}
