package homework.smd.ru.financetracker.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

import homework.smd.ru.financetracker.database.Converters;

// Таблица с данными переодических доходов
@Entity(tableName = "period")
@TypeConverters(Converters.class)
public class Period {

	@PrimaryKey(autoGenerate = true)
	public long id;

	public long operationId;

	public Date lastOperationDate;

	public int days;
}
