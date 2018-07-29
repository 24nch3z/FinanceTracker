package homework.smd.ru.financetracker.screens.main.domain;

public class BalanceModel {

	private String stringSum = "";
	private String balanceName;
	private boolean isVisible;
	private float sum;

	BalanceModel(String balanceName, boolean isVisible, float sum) {
		this.balanceName = balanceName;
		this.isVisible = isVisible;
		this.sum = sum;
	}

	public void changeVisibility() {
		isVisible = !isVisible;
	}

	public String getBalanceName() {
		return balanceName;
	}

	public void setBalanceName(String balanceName) {
		this.balanceName = balanceName;
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
