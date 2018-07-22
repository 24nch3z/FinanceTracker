package homework.smd.ru.financetracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import homework.smd.ru.financetracker.fragments.AboutFragment;

public class MainActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener {

	@BindView(R.id.nav_view) NavigationView navigationView;
	@BindView(R.id.drawer_layout) DrawerLayout drawer;
	@BindView(R.id.toolbar) Toolbar toolbar;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		setSupportActionBar(toolbar);

		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();

		navigationView.setNavigationItemSelectedListener(this);

		if (getSupportActionBar() != null) {
			getSupportActionBar().setTitle(R.string.app_full_name);
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
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {

		drawer.closeDrawer(GravityCompat.START);
		final Fragment fragment;

		switch (item.getItemId()) {
			case R.id.nav_about:
				fragment = AboutFragment.newInstance();
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

		return true;
	}
}
