package homework.smd.ru.financetracker;

public class BasePresenter<T> {

	protected T view;

	public void attachView(T view) {
		this.view = view;
	}

	public void detachView() {
		this.view = null;
	}

	public boolean hasView() {
		return view != null ? true : false;
	}
}
