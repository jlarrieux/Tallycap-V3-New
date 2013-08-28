package com.jeannius.tallycap.util;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.jeannius.tallycap.R;

public class MySpinnerContainer extends LinearLayout {
	
	public Spinner lengthSpinner;
	private TextView label;
	private Context context;
	private Global g;	
	private int resId;
	private int preferredSelection;
	
	public MySpinnerContainer(Context context) {
		super(context);
//		this.resId = ResId;		
		this.context = context;
//		this.preferredSelection = preferredSelection;
		if(!isInEditMode())init();
	}

	public MySpinnerContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MySpinnerContainer);
		resId = 		a.getResourceId(R.styleable.MySpinnerContainer_res_id, 0);
		preferredSelection = a.getInt(R.styleable.MySpinnerContainer_preferred_selection, 0);
		
		a.recycle();
		
		if(!isInEditMode())init();
	}

	

	
	
	private void init(){
		setMinimumHeight(48);
		g = new Global(context);
		Resources res = context.getResources();
		float textSize = res.getDimension(R.dimen.text_size_medium)/res.getDisplayMetrics().density;
		
		setOrientation(LinearLayout.HORIZONTAL);
		label = new TextView(context);label.setText(String.valueOf(getTag()));
		 label.setTextColor(g.textRegularColor);
		 label.setTextSize(textSize);
		 label.setPadding(0, 0, 25, 0);
		 
		lengthSpinner= new Spinner(context);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, resId, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		lengthSpinner.setAdapter(adapter);
		lengthSpinner.setSelection(preferredSelection);
		
		LayoutParams lay = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		 this.setMinimumWidth(400);
		 
//		 lay.gravity
		 
		 addView(label, lay);
		 addView(lengthSpinner,lay);
	}
	
	
	
	//get the selected child
	public Object getSelecObject(){
		return lengthSpinner.getSelectedItem();
	}
	
	//get selected child position
	public int getSelectChildPosition(){
		return lengthSpinner.getSelectedItemPosition();
	}
	
	
	
	
	
	
	
	
	
	
}
