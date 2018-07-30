package homework.smd.ru.financetracker.screens.addoperation.domain;

import android.support.annotation.NonNull;

import homework.smd.ru.financetracker.models.Operation;

public interface OperationInteractor {

	void addOperation(@NonNull Operation operation);
}
