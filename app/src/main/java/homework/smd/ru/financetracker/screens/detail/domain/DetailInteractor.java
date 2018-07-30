package homework.smd.ru.financetracker.screens.detail.domain;

import homework.smd.ru.financetracker.models.Expense;
import io.reactivex.Flowable;

public interface DetailInteractor {

	Flowable<Expense> getCosts();
}
