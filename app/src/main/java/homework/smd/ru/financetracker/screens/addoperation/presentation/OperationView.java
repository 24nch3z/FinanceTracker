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
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import homework.smd.ru.financetracker.R;

public class OperationView extends Fragment implements OperationContract.View {

	private OperationContract.Presenter presenter;
	private Unbinder unbinder;

	@BindView(R.id.button_plus_sum) Button buttonPlus;
	@BindView(R.id.edit_sum) TextInputEditText editSum;
	@BindView(R.id.category_spinner) Spinner spinnerCategory;
	@BindView(R.id.edit_category) TextInputEditText editCategory;
	@BindView(R.id.category_input) TextInputLayout inputCategory;

	public static OperationView newInstance() {
		return new OperationView();
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container,
	                         Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.add_operation, container, false);
		unbinder = ButterKnife.bind(this, view);

		presenter = new OperationPresenter();
		presenter.attachView(this);
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
			getContext(), android.R.layout.simple_spinner_item, categories);
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
	public List<String> getDefaultCategories() {
		return Arrays.asList(getResources().getStringArray(R.array.default_categories));
	}

	@Override
	public void setOnCategoriesClickListener(AdapterView.OnItemSelectedListener listener) {
		spinnerCategory.setOnItemSelectedListener(listener);
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

	@OnClick(R.id.button_plus_sum)
	void onPlusButtonClick() {
		presenter.createOperation(true);
	}

	@OnClick(R.id.button_minus_sum)
	void onMinusButtonClick() {
		presenter.createOperation(false);
	}
}
