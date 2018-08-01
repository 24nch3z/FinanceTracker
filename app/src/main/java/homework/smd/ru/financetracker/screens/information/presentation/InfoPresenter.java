package homework.smd.ru.financetracker.screens.information.presentation;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import homework.smd.ru.financetracker.R;

public class InfoPresenter implements InfoContract.Presenter {

	private static final String TEXT_CONTENT = "about.txt";

	@Nullable private InfoContract.View view;
	private final Context context;


	public InfoPresenter(Context context) {
		this.context = context;
	}

	@Override
	public void attachView(InfoContract.View view) {
		this.view = view;
		loadContentFromAssets(context.getAssets());
	}

	@Override
	public void detachView() {
		view = null;
	}

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
