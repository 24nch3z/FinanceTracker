package homework.smd.ru.financetracker.screens.addoperation.presentation;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;
import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.dialogs.DatePickerDialog;
import homework.smd.ru.financetracker.models.Wallet;
import homework.smd.ru.financetracker.screens.Screens;
import homework.smd.ru.financetracker.utils.SpinnerHelper;

public class OperationView extends Fragment implements OperationContract.View {

	private final static String ARG_WALLET = "ARG_WALLET";
	private static final String DIALOG_DATE = "DIALOG_DATE";
	private static final int REQUEST_DATE = 0;

	@Inject OperationPresenter presenter;
	private OperationViewModel viewModel;
	private Unbinder unbinder;
	private Wallet wallet;

	@BindView(R.id.edit_sum) TextInputEditText editSum;
	@BindView(R.id.category_spinner) Spinner spinnerCategory;
	@BindView(R.id.currency_spinner) Spinner spinnerCurrency;
	@BindView(R.id.edit_category) TextInputEditText editCategory;
	@BindView(R.id.category_input) TextInputLayout inputCategory;
	@BindView(R.id.radio_group_type) RadioGroup radioGroupType;
	@BindView(R.id.period_checkbox) CheckBox checkBoxPeriod;
	@BindView(R.id.period_form) TextInputLayout formPeriod;
	@BindView(R.id.edittext_period) TextInputEditText editTextPeriod;
	@BindView(R.id.sum_error) TextView textViewSumError;
	@BindView(R.id.text_view_date) TextView textViewDate;
	@BindView(R.id.period_error) TextView textViewPeriodError;

	public static OperationView newInstance(Object data) {
		OperationView fragment = new OperationView();
		Bundle bundle = new Bundle();
		if (data != null) {
			bundle.putSerializable(ARG_WALLET, (Wallet) data);
		}
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container,
	                         Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.fragment_add_operation, container, false);
		App.getComponent().inject(this);
		unbinder = ButterKnife.bind(this, view);
		wallet = (Wallet) getArguments().getSerializable(ARG_WALLET);

		viewModel = ViewModelProviders.of(this).get(OperationViewModel.class);

		presenter.setViewModel(viewModel);
		presenter.setWallet(wallet);
		presenter.attachView(this);
		presenter.init(getContext());
		showHidePeriodForm(checkBoxPeriod.isChecked());
		return view;
	}

	@Override
	public void onDestroyView() {
		unbinder.unbind();
		presenter.detachView();
		super.onDestroyView();
	}

	@Override
	public void hideCategory() {
		inputCategory.setVisibility(View.GONE);
	}

	@Override
	public void showCategory() {
		inputCategory.setVisibility(View.VISIBLE);
	}

	@Override
	public void setCategories(List<String> categories, int positions) {
		SpinnerHelper.initSimpleSpinner(categories, positions, spinnerCategory, getContext());
	}

	@Override
	public void setCurrencies(List<String> categories, int position) {
		SpinnerHelper.initSimpleSpinner(categories, position, spinnerCurrency, getContext());
	}

	@Override
	@Nullable
	public String getCategory() {
		final Object category = spinnerCategory.getSelectedItem();
		final int size = spinnerCategory.getCount();

		if (category.equals(spinnerCategory.getItemAtPosition(size - 1))) {
			final Editable editable = editCategory.getEditableText();
			if (editable == null) return null;
			return editable.toString();
		} else {
			return (String) category;
		}
	}

	@Override
	public int getCheckedRadioButtonId() {
		return radioGroupType.getCheckedRadioButtonId();
	}

	@Override
	public void setOnCategoriesClickListener(AdapterView.OnItemSelectedListener listener) {
		spinnerCategory.setOnItemSelectedListener(listener);
	}

	@Override
	public void back() {
		Toast.makeText(getContext(), R.string.created_op, Toast.LENGTH_SHORT).show();
		App.instance.getRouter().backTo(Screens.SCREEN_WALLET);
	}

	@Override
	public boolean getIsPeriod() {
		return checkBoxPeriod.isChecked();
	}

	@Override
	public int getPeriodDays() {
		String str = editTextPeriod.getText().toString();

		try {
			int days = Integer.parseInt(str);
			return days;
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	@Override
	public void showHideSumError(boolean flag) {
		textViewSumError.setVisibility(flag ? View.VISIBLE : View.GONE);
	}

	@Override
	public void setSum(String s) {
		editSum.setText(s);
	}

	@Override
	public void setDate(String s) {
		textViewDate.setText(s);
	}

	@Override
	public void showHidePeriodError(boolean flag) {
		textViewPeriodError.setVisibility(flag ? View.VISIBLE : View.GONE);
	}

	public void showHidePeriodForm(boolean isVisible) {
		formPeriod.setVisibility(isVisible ? View.VISIBLE : View.GONE);
	}

	@OnClick(R.id.buttonSave)
	void onClickSave(View v) {
		presenter.createOperation();
	}

	@OnTextChanged(R.id.edit_sum)
	void onTextChangedSum(CharSequence s, int start, int before, int count) {
		showHideSumError(false);
		presenter.setSum(s.toString());
	}

	@OnTextChanged(R.id.edittext_period)
	void onTextChangedPeriod(CharSequence s, int start, int before, int count) {
		showHidePeriodError(false);
	}

	@OnCheckedChanged(R.id.period_checkbox)
	void onCheckedChangedPeriod(CompoundButton compoundButton, boolean b) {
		showHidePeriodForm(b);
		showHidePeriodError(false);
	}

	@OnClick(R.id.text_view_date)
	void onClickDate(View v) {
		FragmentManager manager = getFragmentManager();
		DatePickerDialog dialog = DatePickerDialog.newInstance(viewModel.operationDate, R.string.dialog_select_date);
		dialog.setTargetFragment(OperationView.this, REQUEST_DATE);
		dialog.show(manager, DIALOG_DATE);
	}

	@OnClick(R.id.button_select_template)
	void onClickSelectTemplate(View v) {

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != Activity.RESULT_OK) {
			return;
		}

		if (requestCode == REQUEST_DATE) {
			Date date = (Date) data.getSerializableExtra(DatePickerDialog.EXTRA_DATE);
			presenter.setDate(date);
		}
	}
}
