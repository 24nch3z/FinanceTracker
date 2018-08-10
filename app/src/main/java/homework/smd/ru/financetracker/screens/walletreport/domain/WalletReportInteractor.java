package homework.smd.ru.financetracker.screens.walletreport.domain;

import java.util.List;

import homework.smd.ru.financetracker.models.Operation;
import io.reactivex.Flowable;

public interface WalletReportInteractor {

	Flowable<List<Operation>> getOperations(int expenseId);

}
