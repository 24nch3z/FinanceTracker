package homework.smd.ru.financetracker.screens.template.presentation.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.models.OperationTemplate;

public class TemplateListAdapter extends RecyclerView.Adapter<TemplateListHolder> {

	private List<OperationTemplate> items;

	public TemplateListAdapter(List<OperationTemplate> items) {
		this.items = items;
	}

	public void updateItems(List<OperationTemplate> items) {
		this.items = items;
	}

	@Override
	public TemplateListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View view = inflater.inflate(R.layout.item_template_operation, parent, false);
		return new TemplateListHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull TemplateListHolder holder, int i) {
		OperationTemplate template = items.get(i);
		holder.bind(template);
	}

	@Override
	public int getItemCount() {
		return items.size();
	}
}
