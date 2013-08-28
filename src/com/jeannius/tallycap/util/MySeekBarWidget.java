package com.jeannius.tallycap.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

import com.jeannius.tallycap.R;

public class MySeekBarWidget extends LinearLayout implements OnSeekBarChangeListener, View.OnTouchListener, AnimatorListener, OnItemClickListener, SubjectToObservers, ObserverOfSubject {
	
	

	public MySeekBar seekBar;
	private Context context;
	public TextView seekBarAmountTextView, nameTextView;
	private int relativeMax;
	private int maxAbsolute;	
	public int maxAbsoluteFake;
	private int minAbsolute;
	public int minAbsoluteFake;
	private int increment;
	private DecimalFormat nf;
	private int type=1, preferredSpinnerSelection=0;
	private Global g;
	private ArrayList<Integer> dynamicScaleArray;	
	private String range;
	public Spinner mSpinner;
	private int oldProgress=0, originalProgress, padding;
//	private long timeDelay=0;
	private boolean stopBit, noSwitch=false;
	private int milliseconds=1100;
	public int scaleFactor =100;
	private double currentValue;
	private ArrayList<MySeekBarWidget> observers;
	

	public MySeekBarWidget(Context context) {
		super(context);
		this.context = context;
		
		init();
		
	}

	public MySeekBarWidget(Context contexts, AttributeSet attr) {
		super(contexts, attr);
		context = contexts;
		TypedArray a =context.obtainStyledAttributes(attr, R.styleable.MySeekBarWidget);
		maxAbsolute = a.getInt(R.styleable.MySeekBarWidget_max, 0);
		minAbsolute =a.getInt(R.styleable.MySeekBarWidget_min, 0);
		preferredSpinnerSelection= a.getInt(R.styleable.MySeekBarWidget_preferred_spinner_selection,0);
		if(!this.isInEditMode())type =Integer.valueOf(a.getString(R.styleable.MySeekBarWidget_type));
		
		
		a.recycle();		
		
		init();
	}
	
	
	

	
	private void init(){
		
		setMinimumHeight(48);
		observers = new ArrayList<MySeekBarWidget>();
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.my_seekbar_widget, this, true);
		setClipChildren(false);
		setClipToPadding(false);
		int marginer= 5;
		setPadding(marginer, marginer, marginer,marginer);
		
//		if(type==null) type ="1";
		LinearLayout l2 = new LinearLayout(context);
		l2.setOrientation(LinearLayout.HORIZONTAL);
	
		g =new Global(context);
		this.setOrientation(LinearLayout.VERTICAL);
		seekBar = (MySeekBar) findViewById(R.id.MySeekBarWidgetSeekBar);
		
		seekBarAmountTextView= (TextView) findViewById(R.id.MySeekBarWidgetAmountTextView);
		mSpinner = (Spinner) findViewById(R.id.MySeekBarWidgetTimeLengthSpinner);
		
		
		nameTextView = (TextView) findViewById(R.id.MySeekBarWidgetNameTextView);
		
		
		if(!this.isInEditMode()){
			seekBar.setMin(minAbsolute);
			setSeekBarLayout();
		
			nameTextView.setText(String.valueOf(getTag()));
	
		
		nameTextView.setTextColor(g.textRegularColor);

	    
		seekBarAmountTextView.setTextColor(g.textRegularColor);
		nf = (DecimalFormat) NumberFormat.getInstance(getResources().getConfiguration().locale);
		

		
			int[] arr= getResources().getIntArray(R.array.dynamic_max);
			if(type==2) arr =getResources().getIntArray(R.array.dynamic_max_interest);
			dynamicScaleArray  = g.arrayToArrayList(arr);
			seekBar.setMax(dynamicScaleArray.get(0));
			absoluteToRelativeMax();
		
		
						
			
		
		
		
		
		
		this.setMinimumWidth(300);
		this.setMinimumHeight(20);
	
		
		seekBar.setMinimumWidth(250);
		seekBarAmountTextView.setMinHeight(35);
		
		LayoutParams lay = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,1);
		seekBarAmountTextView.setGravity(Gravity.CENTER);
		

		seekBar.setProgress(0);
		
		relativeMax = seekBar.getMax();
		this.setLayoutParams(lay);
		spinnerSetUp();
		
		
		seekBarAmountTextView.setLayoutParams(lay);

		
		
		
		seekBar.setOnSeekBarChangeListener(this);
		seekBar.setOnTouchListener(this);
		setMyText(seekBar.getProgress());
		
		
		}
		
		
	}
	
	
	
	
	
	private void setSeekBarLayout(){
		
		padding =(int) (g.screenWidth *0.025);
		
		int seekWidth = g.screenWidth -2*padding;
		int realwidth=seekWidth;
		
		if(seekWidth> 600) realwidth=600;
		 
		LayoutParams lay2 = new LayoutParams(realwidth, LayoutParams.WRAP_CONTENT);	
		
		
		lay2.setMargins(padding, 0, padding, 0);
		
		seekBar.setLayoutParams(lay2);
		
	}
	
	
	public void setMyText(double value){
		
		currentValue = value;
		
	
		
		String tempval="";
		if(type==2 || (type==5 & mSpinner.getSelectedItemPosition()==0)){
			tempval=	nf.format(descale(value))+"%";
			currentValue = descale(value);
		}
		else if(type==1 || type==4 || (type==5 & mSpinner.getSelectedItemPosition()==1) || type==6) tempval = String.format(getResources().getString(R.string.money), nf.format(value));
		else tempval =nf.format(value);
		
		seekBarAmountTextView.setText(range+tempval);
		
			

	}
	
	
	
	
	
	
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {

		if(noSwitch) seekBar.setProgress(originalProgress);// if noswitch is in effect, don't do anything aka animation is going
		else{			
		
			if(progress<minAbsolute){
				seekBar.setProgress(minAbsolute);
				progress= minAbsolute;
			}
			else if(progress>maxAbsolute){
				seekBar.setProgress(maxAbsolute);
				progress = maxAbsolute;
			}
			else if(fromUser){
				
			
					increment=numberIncrement(progress, fromUser);
				
					progress = progress/increment;		
					progress = progress*increment;
					
					oldProgress =progress;
					originalProgress = progress;
//					g.logCat(String.format("Progress raw: %d, scale: %d",  progress, seekBar.getMax()));
				
			}
			setMyText(progress);
			notifyObserver();
		}
	}
	
	public void progressSetter(int progress, boolean fromUser){
		scaleChooser(progress, fromUser);
		seekBar.setProgress(progress);
		oldProgress = progress;
		originalProgress = progress;
		setMyText(progress);
//		g.logCat(String.format("Progress: %d  fromuser: %b", progress, fromUser));
		
	}

	
	
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		stopBit =false;
		originalProgress= seekBar.getProgress();
//		g.logCat("TOUCH! stopbit:" +stopBit , classs, "OnStartTrackingTouch");
		
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		
		stopBit = true;
//		g.logCat("LET DOWN!  stopbit:" + stopBit, classs, "OnStopTrackingTouch");
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		stopBit = false;
		
//		g.logCat("REALTOUCH!  stopbit:" + stopBit, classs, "OnTouch");
		new determineLongPress().execute(seekBar);
		return false;
	}
	

	
	
	

	//this function change the increment as well as the scale :)
	
	private int numberIncrement(int progress, boolean fromUser){
				
		int stops = 50 ;
	
		scaleChooser(progress, fromUser);	
		
		
		double f=seekBar.getMax()/stops;
		
		if(f<1)	f=1;

		
		
		return (int) f;
	}
	
	
	/**
	 * this function chooses the correct scale for the seekbar
	 * @param progress
	 */
	public void scaleChooser(int progress, boolean fromUser){
		
//		g.logCat(String.format("BEGIN! Progress: %d  Max: %d, fromUser: %b", progress, seekBar.getMax(), fromUser));
		
		int temp=1;
		
		//this for loop iterate through the array of scales and find the appropriate one based on the value of progress
				for(int i=0; i<dynamicScaleArray.size(); i++){
//					if(type.equals("2") && (i<2 )) continue;
					temp = dynamicScaleArray.get(i);
//					g.logCat(String.format("current scale: %d  i:%d", temp, i));					
					if (progress<temp) { //this makes sure that once the right scale is found, we stop the for loop
						
//						g.logCat(String.format("Temp: %d  maxAbsolute: %d", temp, maxAbsolute));
						if(progress!=maxAbsolute){ //this makes sure that once progress reaches maxAbsolute scale, no scale switching occurs

							seekBar.setMax(temp);
							if(relativeMax!=seekBar.getMax() ){ //this check if we need to change scale
								animator(progress); //this shows the scale change animation

								relativeMax= seekBar.getMax();
							}
						}
						break;
					}
				}				
//				g.logCat(String.format("END! Progress: %d  Max: %d  Absolute Max:%d, ", progress, seekBar.getMax(), maxAbsolute));
		
	}
	
	
	
	
	/**
	 * this function make sure scaling is possible for fractional numbers like %
	 * @param toDeScale the number to transform from a scaled-up int to a decimal (scale factor =100)
	 * @return the real percent value
	 */
	public double descale(double toDeScale){		
		
		return toDeScale/scaleFactor;
	}
	
	
	
	
	/**
	 * 
	 * @param toScale, the number to transform from a decimal to a scaled-up int (scale factor =100)
	 * @return
	 */
	public double scale(double toScale){
		return toScale*scaleFactor;
	}
	
	
	
	private void animator(int progress){
		
		float  xScale=1;
		float yScale = 1;
		long duration =75;
		
		
		if(seekBar.getScaleX()!=1) duration =10;
		
		
		if(oldProgress<progress){
			xScale=g.scaleFactorBig;
			yScale = g.scaleFactorBig;
		}
		else{
			xScale= g.scaleFactorSmall;
			yScale = g.scaleFactorSmall;
		}
//		g.logCat(String.format("Old progress: %d   New Progress: %d", oldProgress, progress));
		
		
		seekBar.animate().setDuration(duration);
		seekBar.animate().setInterpolator(new LinearInterpolator());
		seekBar.animate().setListener(this);
		seekBar.animate().scaleX(xScale);
		seekBar.animate().scaleY(yScale);
		
	}

	@Override
	public void onAnimationStart(Animator animation) {
		noSwitch=true;
		
	}

	@Override
	public void onAnimationEnd(Animator animation) {
		
//		g.toaster(String.format("End Anim Xscale: %f", xScale), classs, "onAnimationEnd", true);
		if(!(seekBar.getScaleX()==1)){
			seekBar.animate().scaleX(1);
			seekBar.animate().scaleY(1);
		}
		else noSwitch =false;
	}

	@Override
	public void onAnimationCancel(Animator animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationRepeat(Animator animation) {
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * this function returns the type (1 for amount, 2 for interest,...)
	 * @return
	 */
	public int getType(){
		return type;
	}
	




	
	
	private class determineLongPress extends AsyncTask<SeekBar, Void, Integer>{
		
		private MySeekBar seek;
		long goal, time;
		@Override
		protected Integer doInBackground(SeekBar... params) {
			
			
			int counter= 1;
			
			goal = SystemClock.elapsedRealtime()+milliseconds;
			time =0;
			
			SeekBar s = params[0];
			int previousProgress =s.getProgress();	
			
			seek = (MySeekBar) s;
			int currentProgress =0;
			
			while(!stopBit){
				
					currentProgress = s.getProgress();					
					time= SystemClock.elapsedRealtime();

					if(time>goal){
						counter=0;
						break;
					}
					if(currentProgress!=previousProgress) break;
				
			}
			

			
			return counter;
		}
		
		
		@Override
		protected void onPostExecute(Integer result) {
			
			if(result==0){
				if(seek.getSeekBarLongPressListener()!=null) seek.getSeekBarLongPressListener().longPressOccured((MySeekBarWidget) seek.getParent().getParent());
				g.logCat(String.format("Total time: %d", time- (goal-milliseconds)));
		}
			time =0;
		
		
	}


		


	

	}






	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		((TextView)view).setHeight(20);
		
	}


	public double getCurrentValue(){
		
		return currentValue;
	}



	public String getFrequency(){
		
		String s="";
		int pos = mSpinner.getSelectedItemPosition();
		
		if(pos==0) s= Global.WEEKLY;
		else if(pos ==1) s= Global.MONTHLY;
		else s =Global.YEARLY;

		return s;
	}
	
	
	
	
	public void setMaxAbsolute(double newMaxAbsolute){
		maxAbsolute =(int) newMaxAbsolute;
		absoluteToRelativeMax();
	}

	@Override
	public void notifyObserver() {
		
		MySeekBarWidget temp;
		double max = getCurrentValue()-getSum()-1;
		if(max<=0) max=0;
		
		for(int i=0; i<observers.size(); i++){
			
			temp=observers.get(i);
			temp.setMaxAbsolute(max+temp.getCurrentValue()); 
			temp.absoluteToRelativeMax();
		}
		
//		g.logCat(String.format("VALUE TO MAX: %f, progress: %d", getCurrentValue(), seekBar.getProgress()));
		
	}
	
	
	
	//this function get the sum of all the observers to determine max value
	private double getSum(){
		double sum=0;
		for(int i=0; i<observers.size(); i++){
			sum += observers.get(i).getCurrentValue();
		}
			
		return sum;
	}

	@Override
	public void registerObserver(ObserverOfSubject obs) {
		observers.add((MySeekBarWidget) obs); 
		notifyObserver();
		
	}

	@Override
	public void removeObserver(ObserverOfSubject obs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeObserver() {
		if(observers.size()>0) 	observers.clear();
//		setMyText(getCurrentValue());
		
	}

	@Override
	public void update(Object o) {
		
		
	}

	@Override
	public void update(String s) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	//this next function convert from absolutemax to relative max
	
	public void absoluteToRelativeMax(){
		if(type==2 || (type==5 & mSpinner.getSelectedItemPosition()==0)){
			maxAbsolute = maxAbsolute*100;
			maxAbsoluteFake = maxAbsolute/100;
			minAbsoluteFake = minAbsolute/100;
			
		}
		else {
			maxAbsoluteFake = maxAbsolute;
			minAbsoluteFake = minAbsolute;			
		}
		
		range= String.format("(%s - %s)\t", nf.format(minAbsoluteFake), nf.format(maxAbsoluteFake));
		setMyText(seekBar.getProgress());
	}
	
	
	
	
	//this next function determines the behavious of the spinner
	private void spinnerSetUp(){
		if(type<3 || type==7) mSpinner.setVisibility(View.GONE);		
		else{
			mSpinner.setVisibility(View.VISIBLE);
			int res=0;
			if(type==3) res =R.array.length_array;
			else if(type==4) res =R.array.frequency_array;
			else if(type==5) res = R.array.fixed_vs_percent;
			else if(type==6) res =R.array.month_long_array;
			ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, res, android.R.layout.simple_spinner_item);
			
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			mSpinner.setAdapter(adapter);
			seekBarAmountTextView.setGravity(Gravity.RIGHT);
			LayoutParams lay2 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lay2.setMargins(padding, 0, 0, 0);
			mSpinner.setLayoutParams(lay2);
			mSpinner.setSelection(1);
			mSpinner.setPadding(0, 0, 0, 0);
			
		}
		mSpinner.setSelection(preferredSpinnerSelection);
	}
	
	
	public int getSpinnerSelectedPosition(){
		return mSpinner.getSelectedItemPosition();
	}
	
	
	
	
	
	
	
	

}
