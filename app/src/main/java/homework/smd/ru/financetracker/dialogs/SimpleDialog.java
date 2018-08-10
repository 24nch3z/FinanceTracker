package homework.smd.ru.financetracker.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class SimpleDialog extends DialogFragment {

	public static final String EXTRA_ANSWER = "EXTRA_ANSWER";
	public static final String EXTRA_DATA = "EXTRA_DATA";

	public static final String ARG_TITLE = "ARG_TITLE";
	public static final String ARG_MESSAGE = "ARG_MESSAGE";
	public static final String ARG_DATA = "ARG_DATA";

	public static SimpleDialog newInstance(@StringRes int title, @StringRes int message, Bundle bundle) {
		SimpleDialog dialog = new SimpleDialog();
		Bundle args = new Bundle();
		args.putInt(ARG_TITLE, title);
		args.putInt(ARG_MESSAGE, message);
		args.putBundle(ARG_DATA, bundle);
		dialog.setArguments(args);
		return dialog;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new AlertDialog.Builder(getActivity())
			.setTitle(getArguments().getInt(ARG_TITLE))
			.setMessage(getArguments().getInt(ARG_MESSAGE))
			.setNeutralButton(android.R.string.no, null)
			.setPositiveButton(android.R.string.yes, this::sendResult)
			.create();
	}

	private void sendResult(DialogInterface dialogInterface, int i) {
		if (getTargetFragment() == null) {
			return;
		}

		boolean answer = true;
		int resultCode = Activity.RESULT_OK;
		Intent intent = new Intent();
		intent.putExtra(EXTRA_ANSWER, answer);
		intent.putExtra(EXTRA_DATA, getArguments().getBundle(ARG_DATA));

		getTargetFragment()
			.onActivityResult(getTargetRequestCode(), resultCode, intent);
	}
}
