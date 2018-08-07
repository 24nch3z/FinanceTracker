package homework.smd.ru.financetracker.utils;

import android.content.Context;
import android.widget.Toast;

public class MyToast {

    private static MyToast instance;

    private Context context;
    private Toast toast;

    private MyToast(Context applicationContext) {
        context = applicationContext.getApplicationContext();
        toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
    }

    public static MyToast get(Context context) {
        if (instance == null) {
            instance = new MyToast(context);
        }
        return instance;
    }

    public void show(Object... messages) {
        StringBuilder text = new StringBuilder();

        for (int i = 0; i < messages.length; i++) {
            text.append(messages[i].toString());
            text.append("\n");
        }

        toast.setText(text.toString().trim());
        toast.show();
    }
}