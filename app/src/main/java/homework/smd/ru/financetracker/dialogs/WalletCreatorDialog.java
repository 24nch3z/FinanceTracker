package homework.smd.ru.financetracker.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.models.Wallet;

public class WalletCreatorDialog extends DialogFragment {

	private static final String WALLET = "WALLET";

	public static WalletCreatorDialog newInstance(Wallet wallet) {
		WalletCreatorDialog fragment = new WalletCreatorDialog();
		Bundle args = new Bundle();
		args.putSerializable(WALLET, wallet);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Wallet wallet = (Wallet) getArguments().getSerializable(WALLET);

		String title = getString(wallet == null ? R.string.wallet_create_dialog_title_create :
			R.string.wallet_create_dialog_title_change);
		String name = wallet == null ? "" : wallet.title;

		final View dialogView = LayoutInflater.from(getActivity())
			.inflate(R.layout.dialog_wallet_creator, null);
		final TextInputEditText editTextName = dialogView.findViewById(R.id.name);
		final TextView textViewNameError = dialogView.findViewById(R.id.name_error);

		editTextName.setText(name);
		try {
			editTextName.setSelection(name.length());
		} catch (IndexOutOfBoundsException e) {
			editTextName.setSelection(0);
		}

		editTextName.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				textViewNameError.setVisibility(View.GONE);
			}

			@Override
			public void afterTextChanged(Editable editable) {}
		});

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder
			.setTitle(title)
			.setView(dialogView)
			.setNeutralButton(android.R.string.cancel, null)
			.setPositiveButton(android.R.string.ok, null);

		AlertDialog dialog = builder.create();

		dialog.setOnShowListener(dialogInterface -> {
			Button button = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
			button.setOnClickListener(view -> {
				String newName = editTextName.getText().toString().trim();
				if (newName.length() == 0) {
					textViewNameError.setVisibility(View.VISIBLE);
					return;
				}
				createOrUpdateWallet(wallet, newName);
				dialog.dismiss();
			});
		});

		return dialog;
	}

	private void createOrUpdateWallet(Wallet wallet, String name) {
		// TODO: Пока грязно прям буду кидать в базу новый кошель
		if (wallet == null) {
			wallet = new Wallet(0, name, true, 0);
			App.instance.getDatabase().walletDao()
				.insert(wallet);
		} else {
			wallet.setTitle(name);
			App.instance.getDatabase().walletDao()
				.update(wallet);
		}
	}
}