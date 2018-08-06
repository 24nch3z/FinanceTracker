package homework.smd.ru.financetracker.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import homework.smd.ru.financetracker.database.Converters;

@Entity
@TypeConverters(Converters.class)
public class Operation {

	@PrimaryKey(autoGenerate = true)
	public int id;

	public double sum;

	public String category;

	public int expenseId;

	public Currency currency;

	public Operation(double sum, Currency currency, String category, int expenseId) {
		this.sum = sum;
		this.category = category;
		this.expenseId = expenseId;
		this.currency = currency;
	}
}
