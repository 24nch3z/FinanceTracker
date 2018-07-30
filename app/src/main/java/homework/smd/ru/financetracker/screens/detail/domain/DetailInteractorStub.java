package homework.smd.ru.financetracker.screens.detail.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import homework.smd.ru.financetracker.models.Costs;
import homework.smd.ru.financetracker.models.Currency;
import homework.smd.ru.financetracker.models.Operation;
import io.reactivex.Flowable;

public class DetailInteractorStub implements DetailInteractor {

	@Override
	public Flowable<Costs> getCosts() {
		return Flowable.fromIterable(Arrays.asList(
			new Costs("First", generateOperations()),
			new Costs("Second", generateOperations()),
			new Costs("Third", generateOperations()),
			new Costs("Second", generateOperations()),
			new Costs("Third", generateOperations()),
			new Costs("Second", generateOperations()),
			new Costs("Third", generateOperations())
		));
	}

	private static final String[] types = { "Еда", "Коммунальные платежи", "Транспорт" };

	private List<Operation> generateOperations() {
		final Random random = new Random(System.currentTimeMillis());
		final List<Operation> list = new ArrayList<>();

		for (int i = 0; i < random.nextInt(15) + 5; ++i) {
			list.add(new Operation(
				random.nextDouble() * 10_000 - 5_000,
				Currency.USD, types[random.nextInt(types.length)]
			));
		}
		return list;
	}
}
