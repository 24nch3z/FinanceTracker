package homework.smd.ru.financetracker.screens.addoperation.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
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
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.Unbinder;
import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.models.Expense;

public class OperationView extends Fragment implements OperationContract.View {

	@Inject OperationContract.Presenter presenter;
	private Unbinder unbinder;

	@BindView(R.id.edit_sum) TextInputEditText editSum;
	@BindView(R.id.category_spinner) Spinner spinnerCategory;
	@BindView(R.id.expense_spinner) Spinner spinnerExpense;
	@BindView(R.id.edit_category) TextInputEditText editCategory;
	@BindView(R.id.category_input) TextInputLayout inputCategory;
	@BindView(R.id.radio_group_type) RadioGroup radioGroupType;
	@BindView(R.id.period_checkbox) CheckBox checkBoxPeriod;
	@BindView(R.id.period_form) TextInputLayout formPeriod;
	@BindView(R.id.edittext_period) TextInputEditText editTextPeriod;

	public static OperationView newInstance() {
		return new OperationView();
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container,
	                         Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.add_operation, container, false);
		App.getComponent().inject(this);
		unbinder = ButterKnife.bind(this, view);

		presenter.attachView(this);
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
	public void setCategories(List<String> categories) {
		if (getContext() == null) return;
		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
			getContext(), android.R.layout.simple_list_item_1, categories);
		spinnerCategory.setAdapter(arrayAdapter);
	}

	@Override
	public float getSum() {
		final Editable editable = editSum.getText();
		if (editable == null) return 0f;
		try {
			return Float.valueOf(editable.toString());
		} catch (NumberFormatException e) {
			return 0f;
		}
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
	public void setExpense(List<Expense> expenses) {
		if (getContext() == null) return;
		final ExpenseAdapter arrayAdapter = new ExpenseAdapter(
			getContext(), android.R.layout.simple_list_item_1, expenses);
		spinnerExpense.setAdapter(arrayAdapter);
	}

	@Override
	public Expense getExpense() {
		return (Expense) spinnerExpense.getSelectedItem();
	}

	@Override
	public void back() {
		Toast.makeText(getContext(), R.string.created_op, Toast.LENGTH_SHORT).show();

		if (getActivity() != null) {
			final BottomNavigationView navigation = getActivity().findViewById(R.id.navigation);
			if (navigation != null) {
				navigation.setSelectedItemId(navigation.getMenu().getItem(1).getItemId());
			}
		}
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

	public void showHidePeriodForm(boolean isVisible) {
		formPeriod.setVisibility(isVisible ? View.VISIBLE : View.GONE);
	}

	@OnClick(R.id.buttonSave)
	void onClickSave(View v) {
		presenter.createOperation();
	}

	@OnCheckedChanged(R.id.period_checkbox)
	void onCheckedChangedPeriod(CompoundButton compoundButton, boolean b) {
		showHidePeriodForm(b);
	}
}
