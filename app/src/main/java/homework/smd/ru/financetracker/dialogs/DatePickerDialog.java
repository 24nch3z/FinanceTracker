package homework.smd.ru.financetracker.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import homework.smd.ru.financetracker.R;

public class DatePickerDialog extends DialogFragment {

	public static final String EXTRA_DATE = "EXTRA_DATE";

	private static final String ARG_DATE = "ARG_DATE";
	private static final String ARG_TITLE = "ARG_TITLE";

	private DatePicker datePicker;

	public static DatePickerDialog newInstance(Date date, @StringRes int title) {
		Bundle args = new Bundle();
		args.putSerializable(ARG_DATE, date);
		args.putInt(ARG_TITLE, title);

		DatePickerDialog fragment = new DatePickerDialog();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Date date = (Date) getArguments().getSerializable(ARG_DATE);
		int titleRes = getArguments().getInt(ARG_TITLE);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		View v = LayoutInflater.from(getActivity())
			.inflate(R.layout.dialog_date_picker, null);

		datePicker = v.findViewById(R.id.date_picker);
		datePicker.init(year, month, day, null);

		return new AlertDialog.Builder(getActivity())
			.setView(v)
			.setTitle(titleRes)
			.setNeutralButton(android.R.string.cancel, null)
			.setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
				int year1 = datePicker.getYear();
				int month1 = datePicker.getMonth();
				int day1 = datePicker.getDayOfMonth();

				Date date1 = new GregorianCalendar(year1, month1, day1).getTime();
				sendResult(date1);
			})
			.create();
	}

	private void sendResult(Date date) {
		if (getTargetFragment() == null) {
			return;
		}

		int resultCode = Activity.RESULT_OK;
		Intent intent = new Intent();
		intent.putExtra(EXTRA_DATE, date);

		getTargetFragment()
			.onActivityResult(getTargetRequestCode(), resultCode, intent);
	}
}
