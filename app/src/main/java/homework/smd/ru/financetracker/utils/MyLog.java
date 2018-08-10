package homework.smd.ru.financetracker.utils;

// Timber'ы там всякие и другие либы для слабаков
public class MyLog {

	private static final String TAG = "sssss";

	// TODO: Попробовать Timber
	public static void l(Object... objects) {
		StringBuilder message = new StringBuilder();
		for (Object obj : objects) {
			message.append(obj.toString());
			message.append(" ");
		}
		android.util.Log.d(TAG, message.toString().trim());
	}
}
