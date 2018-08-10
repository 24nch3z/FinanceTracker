package homework.smd.ru.financetracker.screens.wallet.presentation;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.models.Operation;
import homework.smd.ru.financetracker.models.UtilsKt;

public class OperationRecyclerAdapter extends
	RecyclerView.Adapter<OperationRecyclerAdapter.OperationHolder> {

	@NonNull private List<Operation> dataset;
	private CallbackOnClick callbackOnClick;

	static class OperationHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		@IdRes static int red = 0;
		@IdRes static int green = 0;
		@NonNull private final TextView operationName;
		@NonNull private final TextView operationSum;
		private Operation operation;
		private CallbackOnClick callbackOnClick;

		OperationHolder(@NonNull View itemView) {
			super(itemView);
			itemView.setOnClickListener(this);
			operationName = itemView.findViewById(R.id.operation_name);
			operationSum = itemView.findViewById(R.id.operation_sum);
			if (red == 0 || green == 0) {
				red = itemView.getResources().getColor(R.color.red);
				green = itemView.getResources().getColor(R.color.green);
			}
		}

		private void updateContent(@NonNull Operation operation, CallbackOnClick callbackOnClick) {
			this.operation = operation;
			this.callbackOnClick = callbackOnClick;

			if (operation.sum < 0) {
				operationSum.setTextColor(red);
			} else {
				operationSum.setTextColor(green);
			}
			operationSum.setText(UtilsKt.moneyFormat(operation.sum));
			operationName.setText(operation.category);
		}

		@Override
		public void onClick(View view) {
			this.callbackOnClick.onClickOperation(operation);
		}
	}

	public OperationRecyclerAdapter() {
		this.dataset = Collections.emptyList();
	}

	public void setCallbackOnClick(CallbackOnClick callbackOnClick) {
		this.callbackOnClick = callbackOnClick;
	}

	@NonNull
	@Override
	public OperationHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
		final CardView cardView = (CardView) LayoutInflater.from(viewGroup.getContext())
			.inflate(R.layout.holder_operation, viewGroup, false);
		return new OperationHolder(cardView);
	}

	@Override
	public void onBindViewHolder(@NonNull OperationHolder holder, int position) {
		holder.updateContent(dataset.get(position), callbackOnClick);
	}

	@Override
	public int getItemCount() {
		return dataset.size();
	}

	public void updateDataset(@NonNull final List<Operation> dataset) {
		this.dataset = dataset;
		notifyDataSetChanged();
	}
}
