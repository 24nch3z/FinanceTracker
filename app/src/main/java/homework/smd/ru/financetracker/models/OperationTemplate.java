package homework.smd.ru.financetracker.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.io.Serializable;

import homework.smd.ru.financetracker.database.Converters;

@TypeConverters(Converters.class)
@Entity(tableName = "operationTemplate")
public class OperationTemplate implements Serializable {

	@PrimaryKey(autoGenerate = true)
	public int id;

	public double sum;

	public String category;

	public Currency currency;
	
	public String title;

	public boolean isIncome;

	public OperationTemplate() { }

	public OperationTemplate(double sum, Currency currency,
	                         String category, String title, boolean isIncome) {

		this.sum = sum;
		this.category = category;
		this.currency = currency;
		this.title = title;
		this.isIncome = isIncome;
	}
}
