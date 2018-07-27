package homework.smd.ru.financetracker;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AbstractMainActivity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}
}
