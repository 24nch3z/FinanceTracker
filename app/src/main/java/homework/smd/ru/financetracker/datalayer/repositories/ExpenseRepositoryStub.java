package homework.smd.ru.financetracker.datalayer.repositories;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import homework.smd.ru.financetracker.datalayer.ExpenseRepository;
import homework.smd.ru.financetracker.models.Wallet;
import homework.smd.ru.financetracker.models.Operation;
import io.reactivex.Flowable;

public class ExpenseRepositoryStub implements ExpenseRepository {

	private List<Wallet> expens = new ArrayList<>();

	public ExpenseRepositoryStub() {
		Wallet wallet;
		String title;

//		title = "Наличные";
//		wallet = new Wallet(
//			title,
//			generateOperations(),
//			new Balance(title, true, 42_000f),
//			1
//		);
//		expens.add(wallet);
//
//		title = "Карточка";
//		wallet = new Wallet(
//			title,
//			generateOperations(),
//			new Balance(title, true, -1_000f),
//			2
//		);
//		expens.add(wallet);
//
//		title = "Оффшоры в Гонконге";
//		wallet = new Wallet(
//			title,
//			generateOperations(),
//			new Balance(title, false, 100_500f),
//			3
//		);
//		expens.add(wallet);
	}

	@NonNull
	@Override
	public Flowable<List<Wallet>> getExpens() {
		return Flowable.just(expens);
	}

	@Override
	public void updateExpense(Wallet wallet) {

	}

	private final static String[] types = { "Еда", "Коммунальные платежи", "Транспорт" };
	private final static Random random = new Random(System.currentTimeMillis());
	private List<Operation> generateOperations() {
		final List<Operation> list = new ArrayList<>();

//		for (int i = 0; i < random.nextInt(15) + 5; ++i) {
//			list.add(new Operation(
//				random.nextDouble() * 10_000 - 5_000,
//				Currency.USD, types[random.nextInt(types.length)]
//			));
//		}
		return list;
	}
}