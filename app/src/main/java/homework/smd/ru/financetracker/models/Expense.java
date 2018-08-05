package homework.smd.ru.financetracker.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "expense")
public class Expense {

	@PrimaryKey(autoGenerate = true)
	public int id;

	public String title;

	public boolean isVisible;

	public float sum;

	@Ignore
	public String stringSum;

	public Expense(int id, String title, boolean isVisible, float sum) {
		this.id = id;
		this.title = title;
		this.isVisible = isVisible;
		this.sum = sum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean visible) {
		isVisible = visible;
	}

	public float getSum() {
		return sum;
	}

	public void setSum(float sum) {
		this.sum = sum;
	}

	public String getStringSum() {
		return stringSum;
	}

	public void setStringSum(String stringSum) {
		this.stringSum = stringSum;
	}
}
