package homework.smd.ru.financetracker.screens.main.presentation;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.models.Expense;

public class BalanceRecycleAdapter extends RecyclerView.Adapter<BalanceRecycleAdapter.BalanceHolder> {

	@NonNull private final List<Expense> dataset;
	@Nullable private OnContentClick onImageClickListener;
	@Nullable private OnContentClick onCardClickListener;

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

		private void updateContent(@NonNull Expense model) {
			balanceName.setText(model.getTitle());
			balanceCount.setText(model.getStringSum());
			if (model.isVisible()) {
				balanceVisibility.setImageDrawable(imageInvisible);
			} else {
				balanceVisibility.setImageDrawable(imageVisible);
			}
		}
	}

	BalanceRecycleAdapter(@NonNull List<Expense> dataset) {
		this.dataset = dataset;
	}

	@NonNull
	@Override
	public BalanceHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
		// Inflate card
		final CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
			.inflate(R.layout.holder_balance, parent, false);
		// Wrap card in holder
		final BalanceHolder holder = new BalanceHolder(cardView);
		holder.itemView.setOnClickListener(view -> {
			if (onCardClickListener != null) {
				onCardClickListener.onClick(holder.getAdapterPosition());
			}
		});
		holder.balanceVisibility.setOnClickListener(view -> {
			if (onImageClickListener != null) {
				onImageClickListener.onClick(holder.getAdapterPosition());
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


	public void setOnImageClickListener(@Nullable OnContentClick listener) {
		this.onImageClickListener = listener;
	}

	public void setOnCardClickListener(@Nullable OnContentClick listener) {
		this.onCardClickListener = listener;
	}

	interface OnContentClick {
		void onClick(final int position);
	}
}
