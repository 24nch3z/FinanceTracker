package homework.smd.ru.financetracker.database.converter;

import homework.smd.ru.financetracker.database.entity.OperationEntity;
import homework.smd.ru.financetracker.models.Currency;
import homework.smd.ru.financetracker.models.Operation;

public class OperationConverter {

	// TODO: Сохранять валюту
	public static Operation fromEntity(OperationEntity operationEntity) {
//		return new Operation(operationEntity.sum,
//			Currency.EUR, operationEntity.category);
		return null;
	}

	public static OperationEntity toEntity(Operation operation, int expenseId) {
		OperationEntity operationEntity = new OperationEntity();
		operationEntity.sum = operation.getSum();
		operationEntity.category = operation.getCategory();
		operationEntity.expenseId = expenseId;
		return operationEntity;
	}
}
