package homework.smd.ru.financetracker.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Configuration {

	private final static String USER_SETTINGS = "user_settings";
	private final static String USER_NAME = "user_name";
	private final static String USER_EMAIL = "user_email";

	private final SharedPreferences userPreferences;

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

	@NonNull
	public String getName() {
		return userPreferences.getString(USER_NAME, "Noname");
	}

	@NonNull
	public String getEmail() {
		return userPreferences.getString(USER_EMAIL, "");
	}


}
