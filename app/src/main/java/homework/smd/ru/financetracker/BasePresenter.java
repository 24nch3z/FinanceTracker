package homework.smd.ru.financetracker;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<T> {

	protected T view;
	protected CompositeDisposable cd = new CompositeDisposable();

	public void attachView(T view) {
		this.view = view;
	}

	public void detachView() {
		this.view = null;
		cd.clear();
	}

	public boolean hasView() {
		return view != null;
	}
}
