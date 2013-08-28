package com.jeannius.tallycap.util;

import java.text.NumberFormat;
import java.util.ArrayList;

import com.jeannius.tallycap.R;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

public class Global extends View {	


	public int screenWidth, textRegularColor;
	public int screenHeight, line;
	private Context context;
	private NumberFormat nf;
	private String classs, function;
	private TimeInterpolator t;
	public final static String WEEKLY="Weekly";
	public final static String BIWEEKLY="BiWeekly";
	public final static String MONTHLY="Monthly";
	public final static String YEARLY="Yearly";
	public float scaleFactorBig= 1.2f, scaleFactorSmall=.6f, scaleFactorHuge=1.3f, scaleFactorExtreme=1.7f;
	
	public Global(Context context) {
		
		super(context);
		
		this.context = context;
		
		if(!isInEditMode())init();			
	}
	

	
	public void init(){
		
		Display d= ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		Point size = new Point();
		d.getSize(size);
		screenWidth = size.x;
		screenHeight=size.y;
		nf =  NumberFormat.getInstance(getResources().getConfiguration().locale);
		nf.setParseIntegerOnly(false);
		textRegularColor = context.getResources().getColor(R.color.text_regular_color);
	}
	
	
	//this function is used for Toasting during debug
	
	public void toaster(String message,  Boolean LongOne){
		
		int l =Toast.LENGTH_LONG;
		if(!LongOne) l=Toast.LENGTH_SHORT;
		getStackTraceElement();
		
		if(!this.isInEditMode())Toast.makeText(getContext(), message+"\n\n"+"("+classs+", "+ function +")", l).show();
	}
	
	
	//this function is used for logging in the logcat
	
	public void logCat(String message){
		
		
		getStackTraceElement();
		String tag = String.format("Class: %s  Function: %s  line: %d", classs, function, line);
		Log.i(tag, message);
		
	}
	
	
	public String formatNumber(int value){
		
		return nf.format(value);
	}
	
	
	public String formatNumber(long value){
		return nf.format(value);
	}
	
	public String formatNumber(double value){
		return nf.format(value);
	}
	public String formatNumber(short value){
		return nf.format(value);
	}
	public String formatNumber(String value){
		
		return nf.format(Double.valueOf(value));
	}
	//
	
	public ArrayList<Integer> arrayToArrayList(int[] array ){
		
		ArrayList<Integer> a = new ArrayList<Integer>();
		
		for(int i=0; i<array.length; i++){
			
			a.add((Integer)array[i]);
		}
		
		return a;
		
	}
	
	
	
	//purge any formatted number of illegal characters like ","
	public String purgerAny(CharSequence c){
		String e="";
		
		for(int i=0; i<c.length(); i++){
			if(!String.valueOf(c.charAt(i)).equals(","))e+=c.charAt(i);
		}
		
		
		return e;
		
	}
	
	
	private void getStackTraceElement(){
		
		StackTraceElement[] s = Thread.currentThread().getStackTrace();
		StackTraceElement e = s[4];
		
		classs = e.getClassName();
		function = e.getMethodName();
		line = e.getLineNumber();
	}
	
	
	
	public TimeInterpolator getInterpolator(int pos){
		
		switch(pos){
			case 0: t = new AccelerateDecelerateInterpolator(); break;
			case 1: t = new AccelerateInterpolator(); break;
			case 2: t = new AnticipateInterpolator(); break;
			case 3: t = new AnticipateOvershootInterpolator(); break;
			case 4: t = new BounceInterpolator(); break;
			case 5: t = new CycleInterpolator(5); break;
			case 6: t = new DecelerateInterpolator(); break;
			case 7: t = new LinearInterpolator(); break;
			case 8: t = new OvershootInterpolator(); break;
			default: t = new AccelerateInterpolator(); break;
				
		}
		
		return t;
	}
	
	
	
	/**
	 * IMAGE MANIPULATIONS!!!
	 * 
	 * 
	 */
		//CALCULATIONS FOR IMAGE SIZING
	
	public Drawable resizeImage(int imageID){
		
		BitmapDrawable bd = (BitmapDrawable) context.getResources().getDrawable(imageID);
		double imageHeight = bd.getBitmap().getHeight();
		double imageWidth  = bd.getBitmap().getWidth();
		
		double ratio = screenWidth/imageWidth;
		int newImageHeight = (int) (imageHeight * ratio);
		
		Bitmap bmap = BitmapFactory.decodeResource(context.getResources(), imageID);
		Drawable drawable = new BitmapDrawable(context.getResources(), getResizedBitmap(bmap, newImageHeight));
		
		return drawable;
	}



	private Bitmap getResizedBitmap(Bitmap bm, int newImageHeight) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
}
