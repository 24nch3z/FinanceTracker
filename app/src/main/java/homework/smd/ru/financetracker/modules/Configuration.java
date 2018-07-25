package homework.smd.ru.financetracker.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.concurrent.atomic.AtomicBoolean;

public class Configuration {

	private final static String USER_SETTINGS = "user_settings";
	private final static String USER_NAME = "user_name";
	private final static String USER_EMAIL = "user_email";
	private final static String USER_BALANCE = "user_balance";
	private final static String USD_RATE = "usd_rate";
	private final static String IS_RUB = "is_rub";
	private final static String IS_VISIBLE = "is_visible";

	private final SharedPreferences userPreferences;
	private final AtomicBoolean isFirstTime = new AtomicBoolean(true);

	public Configuration(Context context) {
		userPreferences = context.getSharedPreferences(USER_SETTINGS, Context.MODE_PRIVATE);
	}


	public void setName(@Nullable final String name) {
		userPreferences
				.edit()
				.putString(USER_NAME, name)
				.apply();
	}

	public void setEmail(@Nullable final String email) {
		userPreferences
				.edit()
				.putString(USER_EMAIL, email)
				.apply();
	}

	public void setRuble(final boolean isRUB) {
		userPreferences
				.edit()
				.putBoolean(IS_RUB, isRUB)
				.apply();
	}

	public void setBalanceVisible(final boolean isVisible) {
		userPreferences
				.edit()
				.putBoolean(IS_VISIBLE, isVisible)
				.apply();
	}

	@NonNull
	public String getName() {
		return userPreferences.getString(USER_NAME, "Noname");
	}

	@NonNull
	public String getEmail() {
		return userPreferences.getString(USER_EMAIL, "");
	}

	public float getRubleBalance() {
		return userPreferences.getFloat(USER_BALANCE, 42_000f);
	}

	public float getDollarRatio() {
		return userPreferences.getFloat(USD_RATE, 63.52f);
	}

	public boolean isBalanceVisible() {
		return userPreferences.getBoolean(IS_VISIBLE, false);
	}

	public boolean isRuble() {
		return userPreferences.getBoolean(IS_RUB, true);
	}


	public boolean isFirstTime() {
		return isFirstTime.getAndSet(false);
	}
}
