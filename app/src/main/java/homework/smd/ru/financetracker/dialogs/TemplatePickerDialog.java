package homework.smd.ru.financetracker.dialogs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.models.OperationTemplate;

public class TemplatePickerDialog extends DialogFragment {

	public static final String EXTRA_TEMPLATE = "EXTRA_TEMPLATE";

	private List<String> adapterList = new ArrayList<>();
	private List<OperationTemplate> templates = new ArrayList<>();
	private ArrayAdapter<String> adapter;

	@NonNull
	@Override
	@SuppressLint("CheckResult")
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		adapter = new ArrayAdapter<>(getActivity(),
			android.R.layout.simple_list_item_1, adapterList);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		App.instance.getDatabase().operationTemplateDao()
			.getAll()
			.subscribe(templates -> {

				this.templates.clear();
				adapterList.clear();
				this.templates.addAll(templates);

				for (OperationTemplate template : templates) {
					adapterList.add(template.title);
				}

				adapter.notifyDataSetChanged();
			});

		return new AlertDialog.Builder(getActivity())
			.setTitle("Выберите шаблон") // TODO: Вынести в res
			.setNeutralButton(android.R.string.cancel, null)
			.setAdapter(adapter, this::sendResult)
			.create();
	}

	private void sendResult(DialogInterface dialogInterface, int i) {
		if (getTargetFragment() == null) {
			return;
		}

		OperationTemplate template = templates.get(i);
		Intent intent = new Intent();
		intent.putExtra(EXTRA_TEMPLATE, template);
		int resultCode = Activity.RESULT_OK;

		getTargetFragment()
			.onActivityResult(getTargetRequestCode(), resultCode, intent);
	}
}
