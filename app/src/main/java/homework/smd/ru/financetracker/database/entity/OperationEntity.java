package homework.smd.ru.financetracker.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "operation")
public class OperationEntity {

	@PrimaryKey(autoGenerate = true)
	public int id;

	public double sum;

	public String category;

//	@ForeignKey(entity = ExpenseEntity.class, parentColumns = "id", childColumns = , onDelete = ForeignKey.CASCADE)
	public int expenseId;
}
