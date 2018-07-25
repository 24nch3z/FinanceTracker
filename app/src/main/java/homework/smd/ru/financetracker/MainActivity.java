package homework.smd.ru.financetracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
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

public class MainActivity extends AppCompatActivity
	implements SettingsFragment.NavigationController,
	NavigationView.OnNavigationItemSelectedListener {

	@Inject public Configuration configuration;

	private Unbinder unbinder;
	@BindView(R.id.nav_view) NavigationView navigationView;
	@BindView(R.id.drawer_layout) DrawerLayout drawer;
	@BindView(R.id.toolbar) Toolbar toolbar;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		App.getComponent().inject(this);
		unbinder = ButterKnife.bind(this);

		setSupportActionBar(toolbar);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();

		navigationView.setNavigationItemSelectedListener(this);

		if (configuration.isFirstTime()) {
			updateUserInfo(configuration.getName(), configuration.getEmail());
			onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_main_page));
		}
	}

	@Override
	public void onBackPressed() {
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbinder.unbind();
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {

		drawer.closeDrawer(GravityCompat.START);
		final Fragment fragment;
		final int titleID;

		switch (item.getItemId()) {
			case R.id.nav_main_page:
				fragment = MainFragment.newInstance();
				titleID = R.string.app_full_name;
				break;

			case R.id.nav_settings:
				fragment = SettingsFragment.newInstance();
				titleID = R.string.nav_settings;
				break;

			case R.id.nav_about:
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

	@Override
	public void updateUserInfo(@NonNull String name, @NonNull String email) {
		((TextView) navigationView.getHeaderView(0)
			.findViewById(R.id.nav_header_name)).setText(name);
		((TextView) navigationView.getHeaderView(0)
			.findViewById(R.id.nav_header_email)).setText(email);
		drawer.openDrawer(GravityCompat.START);
	}

}
