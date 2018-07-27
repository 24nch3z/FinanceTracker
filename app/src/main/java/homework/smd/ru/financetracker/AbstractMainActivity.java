package homework.smd.ru.financetracker;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import homework.smd.ru.financetracker.fragments.AboutFragment;
import homework.smd.ru.financetracker.fragments.MainFragment;
import homework.smd.ru.financetracker.fragments.SettingsFragment;
import homework.smd.ru.financetracker.modules.Configuration;

public abstract class AbstractMainActivity extends AppCompatActivity
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
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {

		if (bottomNavigation.getSelectedItemId() == item.getItemId()) return true;

		final Fragment fragment;
		final int titleID;

		switch (item.getItemId()) {
			case R.id.navigation_home:
				fragment = MainFragment.newInstance();
				titleID = R.string.app_full_name;
				break;

			case R.id.navigation_dashboard:
				fragment = SettingsFragment.newInstance();
				titleID = R.string.nav_settings;
				break;

			case R.id.navigation_notifications:
				fragment = AboutFragment.newInstance();
				titleID = R.string.nav_about;
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
