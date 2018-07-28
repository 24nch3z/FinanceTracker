package homework.smd.ru.financetracker.screens.main.presentation;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.screens.main.domain.BalanceModel;

public class BalanceAdapter extends RecyclerView.Adapter<BalanceAdapter.BalanceHolder> {

	private final List<BalanceModel> dataset;

	static class BalanceHolder extends RecyclerView.ViewHolder {

		private static Drawable imageVisible = null;
		private static Drawable imageInvisible = null;
		private final ImageView balanceVisibility;
		private final TextView balanceCount;
		private final TextView balanceName;

		BalanceHolder(@NonNull View itemView) {
			super(itemView);
			balanceCount = itemView.findViewById(R.id.balance_count);
			balanceName = itemView.findViewById(R.id.balance_name);
			balanceVisibility = itemView.findViewById(R.id.balance_visibility);
			if (imageVisible == null || imageInvisible == null) {
				imageVisible = itemView.getResources().getDrawable(R.drawable.ic_eye_open);
				imageInvisible = itemView.getResources().getDrawable(R.drawable.ic_eye_close);
			}
		}

		private void updateContent(@NonNull BalanceModel model) {
			balanceName.setText(model.getBalanceName());
			balanceCount.setText(model.getStringSum());
			if (model.isVisible()) {
				balanceVisibility.setImageDrawable(imageInvisible);
			} else {
				balanceVisibility.setImageDrawable(imageVisible);
			}
		}
	}

	public BalanceAdapter(@NonNull List<BalanceModel> dataset) {
		this.dataset = dataset;
	}

	@NonNull
	@Override
	public BalanceHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
		// Inflate card
		final CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
			.inflate(R.layout.balance_holder, parent, false);
		// Wrap card in holder
		final BalanceHolder holder = new BalanceHolder(cardView);
		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

			}
		});
		return holder;
	}

	@Override
	public void onBindViewHolder(@NonNull BalanceHolder holder, int position) {
		holder.updateContent(dataset.get(position));
	}

	@Override
	public int getItemCount() {
		return dataset.size();
	}
}
