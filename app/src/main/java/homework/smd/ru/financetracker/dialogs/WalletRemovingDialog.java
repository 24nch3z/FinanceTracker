package homework.smd.ru.financetracker.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.models.Wallet;

public class WalletRemovingDialog extends DialogFragment {

	public static final String IS_REMOVING = "IS_REMOVING";
	private static final String WALLET = "WALLET";

	public static WalletRemovingDialog newInstance(Wallet wallet) {
		WalletRemovingDialog fragment = new WalletRemovingDialog();
		Bundle args = new Bundle();
		args.putSerializable(WALLET, wallet);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new AlertDialog.Builder(getActivity())
			.setTitle(getString(R.string.wallet_removing_title))
			.setMessage(getString(R.string.wallet_removing_message))
			.setNeutralButton(android.R.string.cancel, null)
			.setPositiveButton(android.R.string.ok, this::remove)
			.create();
	}

	private void remove(DialogInterface dialogInterface, int i) {
		// TODO: Нужно сносить все операции удалённого кошелька
		// TODO: Проверить, можно ли это сделать одним запросом из БД
		final Wallet wallet = (Wallet) getArguments().getSerializable(WALLET);
		App.instance.getDatabase().walletDao().delete(wallet);
		sendResult();
	}

	private void sendResult() {
		if (getTargetFragment() == null) {
			return;
		}

		boolean isRemovingFlag = true;
		Intent intent = new Intent();
		intent.putExtra(IS_REMOVING, isRemovingFlag);
		int resultCode = Activity.RESULT_OK;

		getTargetFragment()
			.onActivityResult(getTargetRequestCode(), resultCode, intent);
	}
}
