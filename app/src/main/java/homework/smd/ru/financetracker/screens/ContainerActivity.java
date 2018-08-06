package homework.smd.ru.financetracker.screens;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import homework.smd.ru.financetracker.App;
import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.datalayer.data.sharedpreferences.Configuration;
import homework.smd.ru.financetracker.models.PeriodicOperationsUpdater;
import homework.smd.ru.financetracker.screens.addoperation.presentation.OperationView;
import homework.smd.ru.financetracker.screens.detail.presentation.pager.DetailViewPager;
import homework.smd.ru.financetracker.screens.information.presentation.InfoView;
import homework.smd.ru.financetracker.screens.main.presentation.MainView;
import homework.smd.ru.financetracker.screens.settings.presentation.SettingsView;
import homework.smd.ru.financetracker.screens.wallet.presentation.WalletView;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;

public class ContainerActivity extends AppCompatActivity
	implements BottomNavigationView.OnNavigationItemSelectedListener {

	@Inject public Configuration configuration;

	@BindView(R.id.navigation) BottomNavigationView bottomNavigation;
	@BindView(R.id.toolbar) Toolbar toolbar;

	@Override
	@CallSuper
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.container_main);

		App.getComponent().inject(this);
		ButterKnife.bind(this);

		setSupportActionBar(toolbar);
		bottomNavigation.setOnNavigationItemSelectedListener(this);

		if (savedInstanceState == null) {
			App.instance.getRouter().newRootScreen(Screens.SCREEN_MAIN);
		}

		setStatusBarColor();
		new PeriodicOperationsUpdater().run();
	}

	void setStatusBarColor() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(getResources().getColor(R.color.primary_dark));
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		App.instance.getNavigatorHolder().setNavigator(navigator);
	}

	@Override
	protected void onPause() {
		super.onPause();
		App.instance.getNavigatorHolder().removeNavigator();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		final int titleID;

		// TODO: Теперь, когда я в настройках или в эбаут, не могу переключиться на навигэйшн
		// TODO: с navigateTo проблема, если я несколько раз открою настройки или about, то все в стэке останутся
		switch (item.getItemId()) {
			case R.id.opt_settings:
				App.instance.getRouter().navigateTo(Screens.SCREEN_SETTINGS);
				titleID = R.string.opt_settings;
				break;

			case R.id.opt_about:
				App.instance.getRouter().navigateTo(Screens.SCREEN_ABOUT);
				titleID = R.string.opt_about;
				break;

			default:
				return super.onOptionsItemSelected(item);
		}
		setToolbarTitle(titleID);

		return true;
	}

	private Navigator navigator = new SupportFragmentNavigator(getSupportFragmentManager(),
		R.id.container) {
		@Override
		protected Fragment createFragment(String screenKey, Object data) {
			switch (screenKey) {
				case Screens.SCREEN_MAIN:
					return MainView.newInstance();
				case Screens.SCREEN_DETAIL:
					return DetailViewPager.newInstance(data);
				case Screens.SCREEN_ADD_OPERATION:
					return OperationView.newInstance(data);
				case Screens.SCREEN_SETTINGS:
					return SettingsView.newSettingInstance();
				case Screens.SCREEN_ABOUT:
					return InfoView.newAboutInstance();
				case Screens.SCREEN_WALLET:
					return WalletView.getInstance(data);
			}
			return MainView.newInstance();
		}

		@Override
		protected void showSystemMessage(String message) {
			Toast.makeText(ContainerActivity.this, message,
				Toast.LENGTH_SHORT).show();
		}

		@Override
		protected void exit() {
			finish();
		}
	};

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		if (bottomNavigation.getSelectedItemId() == item.getItemId()) return true;

		final int titleID;

		switch (item.getItemId()) {
			case R.id.navigation_main:
				App.instance.getRouter().newRootScreen(Screens.SCREEN_MAIN);
				titleID = R.string.app_full_name;
				break;

			case R.id.navigation_detail:
				App.instance.getRouter().newRootScreen(Screens.SCREEN_DETAIL);
				titleID = R.string.nav_detail;
				break;

			case R.id.temp_add:
				App.instance.getRouter().newRootScreen(Screens.SCREEN_ADD_OPERATION);
				titleID = R.string.add_op;
				break;

			default:
				return false;
		}

		setToolbarTitle(titleID);
		return true;
	}

	private void setToolbarTitle(int titleId) {
		toolbar.setTitle(titleId);
	}
}
