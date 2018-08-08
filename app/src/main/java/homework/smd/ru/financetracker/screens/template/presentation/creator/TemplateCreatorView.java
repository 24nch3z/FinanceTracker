package homework.smd.ru.financetracker.screens.template.presentation.creator;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.OnTextChanged;
import butterknife.Unbinder;
import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.models.OperationTemplate;
import homework.smd.ru.financetracker.screens.Screens;
import homework.smd.ru.financetracker.utils.MyToast;
import homework.smd.ru.financetracker.utils.SpinnerHelper;

public class TemplateCreatorView extends Fragment implements TemplateCreatorContract.View {

	private static final String ARG_TEMPLATE = "ARG_TEMPLATE";

	private Unbinder unbinder;
	@Inject TemplateCreatorPresenter presenter;
	private TemplateCreatorViewModel viewModel;

	@BindView(R.id.edit_title) TextInputEditText editTextTitle;
	@BindView(R.id.title_error) TextView textViewTitleError;
	@BindView(R.id.edit_sum) TextInputEditText editTextSum;
	@BindView(R.id.sum_error) TextView textViewSumError;
	@BindView(R.id.currency_spinner) Spinner spinnerCurrency;
	@BindView(R.id.category_spinner) Spinner spinnerCategory;
	@BindView(R.id.category_input) TextInputLayout otherCategoryForm;
	@BindView(R.id.edit_category) TextInputEditText editTextOtherCategory;
	@BindView(R.id.category_error) TextView textViewCategoryError;

	@BindView(R.id.radio_group_type) RadioGroup radioGroupType;

	public static TemplateCreatorView newInstance(Object template) {
		TemplateCreatorView fragment = new TemplateCreatorView();
		Bundle args = new Bundle();
		if (template != null) {
			args.putSerializable(ARG_TEMPLATE, (OperationTemplate) template);
		}
		fragment.setArguments(args);
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		final View view = inflater
			.inflate(R.layout.fragment_template_creator, container, false);
		unbinder = ButterKnife.bind(this, view);
		App.getComponent().inject(this);

		radioGroupType.setOnCheckedChangeListener((RadioGroup radioGroup, int i) -> {
			presenter.setType(i == R.id.radio_button_income);
		});

		viewModel = ViewModelProviders.of(this).get(TemplateCreatorViewModel.class);
		presenter.setViewModel(viewModel);
		presenter.attachView(this, getContext());

		return view;
	}

	@Override
	public void onDestroyView() {
		presenter.detachView();
		unbinder.unbind();
		super.onDestroyView();
	}

	@Override
	public void setCategories(List<String> list, int selection) {
		SpinnerHelper.initSimpleSpinner(list, selection, spinnerCategory, getContext());
	}

	@Override
	public void setCategoryInput(String s) {
		editTextOtherCategory.setText(s);
	}

	@Override
	public void setCurrencies(List<String> list, int selection) {
		SpinnerHelper.initSimpleSpinner(list, selection, spinnerCurrency, getContext());
	}

	@Override
	public void setSum(String s) {
		editTextSum.setText(s);
	}

	@Override
	public void setType(boolean isIncome) {
		((RadioButton) radioGroupType
			.findViewById(R.id.radio_button_income)).setChecked(isIncome);
	}

	@Override
	public void setTitle(String s) {
		editTextTitle.setText(s);
	}

	@Override
	public void showHideSumError(boolean flag) {
		textViewSumError.setVisibility(flag ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showHideTitleError(boolean flag) {
		textViewTitleError.setVisibility(flag ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showHideOtherCategoryError(boolean flag) {
		textViewCategoryError.setVisibility(flag ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showHideOtherCategory(boolean flag) {
		otherCategoryForm.setVisibility(flag ? View.VISIBLE : View.GONE);
	}

	@Override
	public void back() {
		MyToast.get(getContext()).show("Шаблон успешно создан"); // TODO: res
		App.instance.getRouter().backTo(Screens.SCREEN_TEMPLATES);
	}

	@OnClick(R.id.buttonSave)
	void onClickSave(View v) {
		presenter.save();
	}

	@OnTextChanged(R.id.edit_title)
	void onTextChangedTitle(CharSequence s, int start, int before, int count) {
		showHideTitleError(false);
		presenter.setTitle(s.toString());
	}

	@OnTextChanged(R.id.edit_sum)
	void onTextChangedSum(CharSequence s, int start, int before, int count) {
		showHideSumError(false);
		presenter.setSum(s.toString());
	}

	@OnItemSelected(R.id.currency_spinner)
	void onItemSelectedCurrency(AdapterView<?> adapterView, View view, int i, long l) {
		presenter.setCurrencyPosition(i);
	}

	@OnItemSelected(R.id.category_spinner)
	void onItemSelectedCategory(AdapterView<?> adapterView, View view, int i, long l) {
		presenter.setCategoryPosition(i);
		showHideOtherCategoryError(false);
	}

	@OnTextChanged(R.id.edit_category)
	void onTextChangedOtherCategory(CharSequence s, int start, int before, int count) {
		showHideOtherCategoryError(false);
		presenter.setOtherCategory(s.toString());
	}
}
