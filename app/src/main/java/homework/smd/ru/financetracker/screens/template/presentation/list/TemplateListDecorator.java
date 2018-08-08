package homework.smd.ru.financetracker.screens.template.presentation.list;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class TemplateListDecorator extends RecyclerView.ItemDecoration {
	private int margin;

	public TemplateListDecorator(int margin) {
		this.margin = margin;
	}

	@Override
	public void getItemOffsets(Rect outRect, View view,
	                           RecyclerView parent, RecyclerView.State state) {
		super.getItemOffsets(outRect, view, parent, state);

		if (parent != null && view != null) {
			int itemPosition = parent.getChildAdapterPosition(view);
			int totalCount = parent.getAdapter().getItemCount();

			if (itemPosition >= 0 && itemPosition < totalCount - 1) {
				outRect.bottom = margin;
			}
		}
	}
}
