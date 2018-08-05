package homework.smd.ru.financetracker.models;

import android.annotation.SuppressLint;

import java.util.Calendar;
import java.util.Date;

import homework.smd.ru.financetracker.App;

public class PeriodicOperationsUpdater {

	@SuppressLint("CheckResult")
	public void run() {
		App.instance.getDatabase().periodDao().getAll()
			.subscribe(periods -> {
				Date currentDate = new Date();
				for (Period period : periods) {
					App.instance.getDatabase().operationDao()
						.getOperationsById(period.operationId)
						.subscribe(operations -> {
							if (operations != null && operations.size() > 0) {
								Operation operation = operations.get(0);

								Date lastOperationDate = period.lastOperationDate;
								int days = period.days;
								int between = daysBetween(lastOperationDate, currentDate);

								if (between >= days) {
									int countOfRecords = between / days;
									int fullDays = countOfRecords * days;

									while (countOfRecords > 0) {
										operation.setId(0);
										App.instance.getDatabase().operationDao()
											.insert(operation);
										countOfRecords--;
									}

									Calendar calendar = Calendar.getInstance();
									calendar.setTime(new Date());
									calendar.add(Calendar.DATE, fullDays);
									period.lastOperationDate = calendar.getTime();

									App.instance.getDatabase().periodDao()
										.update(period);
								}
							}
						});
				}
			});
	}

	public int daysBetween(Date d1, Date d2) {
		return (int) (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24);
	}
}
