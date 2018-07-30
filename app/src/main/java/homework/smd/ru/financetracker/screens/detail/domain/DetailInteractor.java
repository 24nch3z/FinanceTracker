package homework.smd.ru.financetracker.screens.detail.domain;

import homework.smd.ru.financetracker.models.Costs;
import io.reactivex.Flowable;

public interface DetailInteractor {

	Flowable<Costs> getCosts();
}
