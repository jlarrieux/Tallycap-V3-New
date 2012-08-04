package com.jeannius.tallycap.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

public class MyEditText extends EditText {
	
	private boolean drawRed= false;
	private Rect rect;
	private Paint paint;
	private boolean Required=false;
	private double min, max;
	private  String name;
	
	public MyEditText(Context context) {
		super(context);
		
		
		init();
	}
	
	public MyEditText(Context context, AttributeSet attrs){
		super(context, attrs);
		init();
		
	}
	
	private void init(){
		rect = new Rect();
		paint = new Paint();
		this.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
		min = 0;
		max =0;
		InputFilter[] f = new InputFilter[1];
		f[0] = new InputFilter.LengthFilter(12);
		this.setFilters(f);
	}
	
	public void setMin(double i){
		min = i;
	}
	
	public double getMind(){
		return min;
	}
	
	public void setMax(double i){
		max = i;
	}
	
	public double getMax(){
		return max;
	}
	public void setName(String s){
		this.name =s;
	}
	
	
	public void setDrawRed(boolean b){
		drawRed = b;
		invalidate();
	}
	
	public void setRequired(boolean b){
		Required = b;
	}
	
	public boolean getRequired(){
		return Required;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {

			super.onDraw(canvas);
			if(drawRed){	        
		        paint.setStyle(Paint.Style.STROKE);
		        paint.setColor(Color.RED);
		        paint.setStrokeWidth(6);
		        getLocalVisibleRect(rect);
		        Log.d("RECT BEFORE", String.format("TOP: %d, Bottom: %d, Left: %d, Right:%d", rect.top, rect.bottom, rect.left, rect.right));
		        rect.set(rect.left, rect.top, rect.right, rect.bottom-(getPaddingBottom()-getPaddingTop()));
		        canvas.drawRect(rect, paint);
	
		        Log.d("Padding top: ", String.valueOf(getPaddingTop()));
		        Log.d("Padding Bottom: ", String.valueOf(getPaddingBottom()));
		        Log.d("Padding left: ", String.valueOf(getPaddingLeft()));
		        Log.d("Padding right: ", String.valueOf(getPaddingRight()));
		        Log.d("RECT After", String.format("TOP: %d, Bottom: %d, Left: %d, Right:%d", rect.top, rect.bottom, rect.left, rect.right));

			}
	}
	
	
	//TODO: CHANGES THE MESSAGE TO STRING RESOURCES
	
	public String validate(){
		//3 steps: required, min, max
		if(name== null) throw new IllegalStateException("Name of MyEditText not set");
		String l="";
		//1-Required
		if(Required) if(this.getText().toString().length()==0) l =name +" is required"+"\n";
		
		//2-min
		if(this.getText().toString().length()>0){
			Double d = Double.valueOf(this.getText().toString());
			if(d<min) l+= name +" is below minimum value"+ "\n";
			if(max>0)if(d> max ) l+= name +" is above maximum value"+"\n";
			
		}
		
		if(l.length()>0) this.setDrawRed(true);
		else setDrawRed(false);
		return l;
	}

}
