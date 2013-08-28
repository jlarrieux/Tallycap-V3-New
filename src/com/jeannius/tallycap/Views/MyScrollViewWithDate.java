package com.jeannius.tallycap.Views;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.animation.LayoutTransition;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.actionbarsherlock.ActionBarSherlock.OnOptionsItemSelectedListener;
import com.actionbarsherlock.internal.nineoldandroids.animation.AnimatorSet;
import com.actionbarsherlock.internal.nineoldandroids.animation.ObjectAnimator;
import com.actionbarsherlock.view.MenuItem;
import com.jeannius.tallycap.R;
import com.jeannius.tallycap.util.Global;
import com.jeannius.tallycap.util.MyUneditableDateEditText;

public abstract class MyScrollViewWithDate extends ScrollView implements OnClickListener, OnDateSetListener, OnOptionsItemSelectedListener{
	
	
	private MyUneditableDateEditText dateText;
	protected DecimalFormat nf;
	private Context context;
	private PopupWindow pop;
	public Global g;
	protected int fifty;
	
	
	public MyScrollViewWithDate(Context context) {
		super(context);
		this.context = context;
		init();
	}

	public MyScrollViewWithDate(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}

	public MyScrollViewWithDate(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		init();
	}

	
	
	
	private void init(){
		
		fifty = getResources().getInteger(R.integer.fifty);
		dateText = new MyUneditableDateEditText(context);
		dateText.setOnClickListener(this);
		g= new Global(context);
		nf = (DecimalFormat) NumberFormat.getInstance(getResources().getConfiguration().locale);
		LayoutTransition l = new LayoutTransition();
		this.setLayoutTransition(l);
		pop = new PopupWindow(context);
//		TempPopUpAnimatoOption t = new TempPopUpAnimatoOption(context);
//		pop.setContentView(t);
		pop.setWidth(600);
		pop.setHeight(400);
		
		
	}
	
	public MyUneditableDateEditText getMyUneditableDateEditText(){
		return dateText;
	}
	
	
	public void setMyUneditableDateEditText(MyUneditableDateEditText m){
		dateText = m;		
	}
	
	@Override
	public void onClick(View v) {
		
		Resources res =getResources();
		Button b = (Button) v;
		if(b.getText().toString().equals(res.getString(R.string.calculate)))calculate();
			
		
		else if(b.getText().toString().equals(res.getString(R.string.what_if))) whatif();
		
//		g.toaster(b.getText().toString(), true);
		
	}
	
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		
		
	}
	
	
	
	/**
	 * This next function animates all the results for calculator
	 */
	
	private void resultAnimator(View v){
		
//		g.toaster(String.format("Position: %d time:%d ms", TempInterpolatorDialogFragment.i, TempInterpolatorDialogFragment.j), true);
		int duration = 200;
		ObjectAnimator animAlphaTrans =ObjectAnimator.ofFloat(v, "alpha",  0f);
		animAlphaTrans.setDuration(200);
		
		ObjectAnimator animXScale = ObjectAnimator.ofFloat(v, "scaleX", 1f, g.scaleFactorBig);
		animXScale.setInterpolator(new AccelerateDecelerateInterpolator());
		animXScale.setDuration(duration);
		
		
		ObjectAnimator animYScale = ObjectAnimator.ofFloat(v, "scaleX", 1f, g.scaleFactorBig);
		animYScale.setInterpolator(new AccelerateDecelerateInterpolator());
		animYScale.setDuration(duration);
		
		ObjectAnimator animAlpha =ObjectAnimator.ofFloat(v, "alpha", 1f);
		animAlpha.setInterpolator(new AccelerateDecelerateInterpolator());
		animAlpha.setDuration(duration);
		
		ObjectAnimator animYScaleReset = ObjectAnimator.ofFloat(v, "scaleX", 1f);
		animYScaleReset.setInterpolator(new AccelerateDecelerateInterpolator());
		animYScaleReset.setDuration(duration);
		
		ObjectAnimator animXScaleReset = ObjectAnimator.ofFloat(v, "scaleX", 1f);
		animXScaleReset.setInterpolator(new AccelerateDecelerateInterpolator());
		animXScaleReset.setDuration(duration);
		
		AnimatorSet set = new AnimatorSet();
		set.play(animAlphaTrans).before(animXScale);
		set.play(animXScale).with(animYScale);
		set.play(animXScale).with(animAlpha);
		set.play(animXScaleReset).after(animXScale);
		set.play(animYScaleReset).with(animXScaleReset);
		
		set.start();
		
	}
	
	
	
	@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			
			g.toaster(item.toString(), true);
			boolean b =false;
					if(item.getItemId()== R.id.TempShow){
						
						pop.showAtLocation(this, Gravity.CENTER, 0, 0);
						b =true;
					}
		
			return b;
		}
	
	

	public void setResult(TextView result, double value){
		
		result.setText(String.format(context.getResources().getString(R.string.monthly_payments_format),nf.format(value)));
		resultAnimator(result);
		
	}
	
	
	
	
	public abstract void calculate();
	protected abstract void whatif();
	
	
	
	
	

}
