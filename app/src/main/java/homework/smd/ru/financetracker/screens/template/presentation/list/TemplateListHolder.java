package homework.smd.ru.financetracker.screens.template.presentation.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.models.OperationTemplate;

public class TemplateListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

	private TextView textViewTitle;
	private OperationTemplate template;
	private CallbackUpdateTemplate callback;

	public TemplateListHolder(@NonNull View itemView) {
		super(itemView);
		itemView.setOnClickListener(this);
		textViewTitle = itemView.findViewById(R.id.title);
	}

	public void bind(OperationTemplate template, CallbackUpdateTemplate callback) {
		this.template = template;
		this.callback = callback;
		textViewTitle.setText(template.title);
	}

	@Override
	public void onClick(View view) {
		callback.updateTemplate(template);
	}
}
