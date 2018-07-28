package homework.smd.ru.financetracker.screens.main.presentation;

public class Presenter {
//	@Inject
//	Configuration configuration;
//
//	private Unbinder unbinder;
//	@BindView(R.id.visibility_switcher)
//	ImageSwitcher imageSwitcher;
//	@BindView(R.id.currency_switcher)
//	Switch currencySwitcher;
//	@BindView(R.id.dollar_rate)
//	TextView dollarRateView;
//	@BindView(R.id.balance) TextView balanceView;
//
//	public View() { }
//
//	public static Fragment newInstance() {
//		return new View();
//	}
//
//	@Override
//	public View onCreateView(@NonNull LayoutInflater inflater,
//	                         ViewGroup container,
//	                         Bundle savedInstanceState) {
//		final View view = inflater.inflate(R.layout.fragment_main, container, false);
//
//		App.getComponent().inject(this);
//		unbinder = ButterKnife.bind(this, view);
//		updateBalance();
//
//		// Set dollar rate
//		dollarRateView.setText(String.format(
//			Locale.getDefault(),
//			getString(R.string.dollar_rate) + " %10.2f",
//			configuration.getDollarRatio()
//		));
//		if (!configuration.isBalanceVisible()) imageSwitcher.showNext();
//
//		return view;
//	}
//
//	@Override
//	public void onDestroyView() {
//		super.onDestroyView();
//		unbinder.unbind();
//	}
//
//
//	@OnCheckedChanged(R.id.currency_switcher)
//	public void onCurrencyChange(boolean isRuble) {
//		configuration.setRuble(isRuble);
//		updateBalance();
//	}
//
//	@OnClick(R.id.visibility_switcher)
//	public void onVisibilityChange() {
//		boolean isVisible = configuration.isBalanceVisible();
//		configuration.setBalanceVisible(!isVisible);
//		imageSwitcher.showNext();
//		updateBalance();
//	}
//
//	private void updateBalance() {
//
//		final boolean isVisible = configuration.isBalanceVisible();
//		final boolean isRuble = configuration.isRuble();
//		final float dollarRate = configuration.getDollarRatio();
//
//		// Determine the currency (₽ or $)
//		final float balance;
//		final String moneySign;
//		currencySwitcher.setChecked(isRuble);
//		if (isRuble) {
//			balance = configuration.getRubleBalance();
//			moneySign = getString(R.string.ruble_sign);
//		} else {
//			moneySign = getString(R.string.dollar_sign);
//			balance = configuration.getRubleBalance() / dollarRate;
//		}
//
//		if (isVisible) {
//			// Show balance in the correct currency
//			balanceView.setText(String.format(
//				Locale.getDefault(), "%,.2f " + moneySign, balance));
//		} else {
//			// Hide balance
//			balanceView.setText("* * * * * ");
//		}
//	}
}
