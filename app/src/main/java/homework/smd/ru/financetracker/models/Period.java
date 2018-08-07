package homework.smd.ru.financetracker.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

import homework.smd.ru.financetracker.database.Converters;

import static android.arch.persistence.room.ForeignKey.CASCADE;

// Таблица с данными переодических доходов
@TypeConverters(Converters.class)
@Entity(
	tableName = "period",
	foreignKeys = @ForeignKey(
		entity = Operation.class,
		parentColumns = "id",
		childColumns = "operationId",
		onDelete = CASCADE)
)
public class Period {

	@PrimaryKey(autoGenerate = true)
	public long id;

	public long operationId;

	public Date lastOperationDate;

	public int days;
}
