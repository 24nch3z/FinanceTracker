package homework.smd.ru.financetracker.screens.walletReport.domain;

import java.util.List;

import homework.smd.ru.financetracker.datalayer.OperationRepository;
import homework.smd.ru.financetracker.datalayer.repositories.OperationRepositoryDataBase;
import homework.smd.ru.financetracker.models.Operation;
import io.reactivex.Flowable;

public class WalletReportInteractorImpl implements WalletReportInteractor {

	private final OperationRepository operationRepository = new OperationRepositoryDataBase();

	@Override
	public Flowable<List<Operation>> getOperations(int expenseId) {
		return operationRepository
			.getOperations(expenseId);
	}
}
