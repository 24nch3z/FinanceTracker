package homework.smd.ru.financetracker.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Locale;

import homework.smd.ru.financetracker.R;
import homework.smd.ru.financetracker.modules.Currency;
import homework.smd.ru.financetracker.modules.Operation;

public class CurrencyView extends View {

	private String currency;
	private String rate;
	private String mExampleString; // TODO: use a default from R.string...

	private TextPaint mTextPaint;
	private float currencyWidth;
	private float textHeight;
	private float rateWidth;

	private int paddingLeft = getPaddingLeft();
	private int paddingTop = getPaddingTop();
	private int paddingRight = getPaddingRight();
	private int paddingBottom = getPaddingBottom();

	public CurrencyView(Context context) {
		super(context);
		init(null, 0);
	}

	public CurrencyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs, 0);
	}

	public CurrencyView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs, defStyle);
	}

	private void init(AttributeSet attrs, int defStyle) {
		// Load attributes
		final TypedArray ta = getContext().obtainStyledAttributes(
			attrs, R.styleable.CurrencyView, defStyle, 0);

		final float rateFloat = ta.getFloat(R.styleable.CurrencyView_rate, 0.0f);
		rate = String.format(Locale.getDefault(), "%,.2f", rateFloat);
		currency = ta.getString(R.styleable.CurrencyView_currency);

		ta.recycle();

		// Set up ta default TextPaint object
		mTextPaint = new TextPaint();
		mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
		mTextPaint.setTextAlign(Paint.Align.LEFT);

		// Update TextPaint and text measurements from attributes
		invalidateTextPaintAndMeasurements();
	}



	private void invalidateTextPaintAndMeasurements() {
		mTextPaint.setTextSize(R.dimen.rate_text_size);
		currencyWidth = mTextPaint.measureText(currency);
		rateWidth = mTextPaint.measureText(rate);

		Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
		textHeight = fontMetrics.bottom;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		int contentWidth = getWidth() - paddingLeft - paddingRight;
		int contentHeight = getHeight() - paddingTop - paddingBottom;

		// Draw the currency (ex. "USD"
		mTextPaint.setColor(getResources().getColor(R.color.divider));
		canvas.drawText(currency,
			paddingLeft + contentWidth / 2,
			paddingTop + (contentHeight + textHeight) / 2,
			mTextPaint
		);

		// Draw the currency (ex. "62.37")
		mTextPaint.setColor(getResources().getColor(R.color.primary));
		canvas.drawText(rate,
			2*paddingLeft + currencyWidth,
			paddingTop + (contentHeight + textHeight) / 2,
			mTextPaint
		);
	}

	public String getExampleString() {
		return mExampleString;
	}

	public void setExampleString(String exampleString) {
		mExampleString = exampleString;
		invalidateTextPaintAndMeasurements();
	}
}
