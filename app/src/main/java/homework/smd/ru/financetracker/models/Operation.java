package homework.smd.ru.financetracker.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

import homework.smd.ru.financetracker.database.Converters;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@TypeConverters(Converters.class)
@Entity(
	tableName = "operation",
	foreignKeys = @ForeignKey(
		entity = Wallet.class,
		parentColumns = "id",
		childColumns = "expenseId",
		onDelete = CASCADE)
)
public class Operation {

	@PrimaryKey(autoGenerate = true)
	public int id;

	public double sum;

	public String category;

	public int expenseId;

	public Currency currency;

	public Date operationDate;

	public Operation(double sum, Currency currency, String category, int expenseId, Date operationDate) {
		this.sum = sum;
		this.category = category;
		this.expenseId = expenseId;
		this.currency = currency;
		this.operationDate = operationDate;
	}
}
