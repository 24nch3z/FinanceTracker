package homework.smd.ru.financetracker.screens.addoperation.presentation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import homework.smd.ru.financetracker.models.Wallet;

public class ExpenseAdapter extends ArrayAdapter<Wallet> {

	public ExpenseAdapter(@NonNull Context context, int resource, List<Wallet> wallet) {
		super(context, resource, wallet);
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		View view = super.getView(position, convertView, parent);
		setTitle(position, view);
		return view;
	}

	@Override
	public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		View view = super.getDropDownView(position, convertView, parent);
		setTitle(position, view);
		return view;
	}

	private void setTitle(int position, View view) {
		TextView textView = view.findViewById(android.R.id.text1);
		Wallet wallet = getItem(position);
		textView.setText(wallet.getTitle());
	}
}
