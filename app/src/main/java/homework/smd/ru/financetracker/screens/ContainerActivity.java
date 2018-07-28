package homework.smd.ru.financetracker.screens;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.modules.Configuration;

public class ContainerActivity extends AppCompatActivity
	implements BottomNavigationView.OnNavigationItemSelectedListener {

	@Inject public Configuration configuration;

	private Unbinder unbinder;
	@BindView(R.id.navigation) BottomNavigationView bottomNavigation;
	@BindView(R.id.toolbar) Toolbar toolbar;


	@Override
	@CallSuper
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_abstract_main);

		App.getComponent().inject(this);
		unbinder = ButterKnife.bind(this);

		setSupportActionBar(toolbar);
		bottomNavigation.setOnNavigationItemSelectedListener(this);
	}

	@Override
	@CallSuper
	protected void onDestroy() {
		super.onDestroy();
		unbinder.unbind();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		final Fragment fragment;
		final int titleID;

		switch (item.getItemId()) {
			case R.id.opt_settings:
				fragment = homework.smd.ru.financetracker.screens.settings.presentation.View.newSettingInstance();
				titleID = R.string.opt_settings;
				break;

			case R.id.opt_about:
				fragment = homework.smd.ru.financetracker.screens.information.presentation.View.newAboutInstance();
				titleID = R.string.opt_about;
				break;

			default:
				return super.onOptionsItemSelected(item);
		}

		final FragmentManager fragmentManager = getSupportFragmentManager();
		final String fragmentTag = getResources().getString(titleID);

		fragmentManager
			.beginTransaction()
			.replace(R.id.container, fragment, fragmentTag)
			.addToBackStack(fragmentTag)
			.commit();

		if (getSupportActionBar() != null) {
			getSupportActionBar().setTitle(titleID);
		}

		return true;
	}


	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {

		if (bottomNavigation.getSelectedItemId() == item.getItemId()) return true;

		final Fragment fragment;
		final int titleID;

		switch (item.getItemId()) {
			case R.id.navigation_home:
				fragment = homework.smd.ru.financetracker.screens.main.presentation.View.newMainInstance();
				titleID = R.string.app_full_name;
				break;

			case R.id.navigation_dashboard:
				fragment = homework.smd.ru.financetracker.screens.settings.presentation.View.newSettingInstance();
				titleID = R.string.opt_settings;
				break;

			case R.id.navigation_notifications:
				fragment = homework.smd.ru.financetracker.screens.information.presentation.View.newAboutInstance();
				titleID = R.string.opt_about;
				break;

			default:
				return false;
		}

		final FragmentManager manager = getSupportFragmentManager();
		manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
		manager
			.beginTransaction()
			.replace(R.id.container, fragment)
			.addToBackStack(null)
			.commit();

		if (getSupportActionBar() != null) {
			getSupportActionBar().setTitle(titleID);
		}

		return true;
	}
}
