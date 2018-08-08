package homework.smd.ru.financetracker.screens.template.presentation.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.models.OperationTemplate;

public class TemplateListHolder extends RecyclerView.ViewHolder {

	private TextView textViewTitle;

	public TemplateListHolder(@NonNull View itemView) {
		super(itemView);
		textViewTitle = itemView.findViewById(R.id.title);
	}

	public void bind(OperationTemplate template) {
		textViewTitle.setText(template.title);
	}
}
