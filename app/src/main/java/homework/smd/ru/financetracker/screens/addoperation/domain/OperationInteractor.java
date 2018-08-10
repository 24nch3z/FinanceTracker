package homework.smd.ru.financetracker.screens.addoperation.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import homework.smd.ru.financetracker.models.Operation;
import homework.smd.ru.financetracker.models.OperationTemplate;
import homework.smd.ru.financetracker.models.Period;
import io.reactivex.Flowable;

public interface OperationInteractor {
	void addOperation(@NonNull Operation operation, @Nullable Period period);
	Flowable<List<OperationTemplate>> getTemplates();
}