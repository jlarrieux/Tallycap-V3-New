package com.jeannius.tallycap.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.jeannius.tallycap.R;
import com.jeannius.tallycap.util.MySeekBarWidget;

public class TempPopUpAnimatoOption extends RelativeLayout {
	
	
	private Context context;
	private MySeekBarWidget time;
	private Spinner timeInterpolatorChooser;
	
	
	public TempPopUpAnimatoOption(Context context) {
		super(context);
		this.context =context;
		init();
	}

	public TempPopUpAnimatoOption(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context =context;
		init();
	}

	public TempPopUpAnimatoOption(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		this.context =context;
		init();
	}
	
	
	private void init(){
//		LayoutInflater in = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);	
		
//		in.inflate(com.jeannius.tallycap.R.layout.temp_popup_animator_option_layout, this);
		
		time = new MySeekBarWidget(context);
		timeInterpolatorChooser = new Spinner(context);
		
		time =(MySeekBarWidget) findViewById(R.id.tempTimeMySeekBarWidget);
		timeInterpolatorChooser = (Spinner) findViewById(R.id.tempInterpolatorSpinner);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.temp_interpolator_arrays, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		timeInterpolatorChooser.setAdapter(adapter);
		
	}
	
	
	public int getTiming(){
		
		return time.seekBar.getProgress();
	}
	
	
	
	
	
	public Spinner getSpinnerInterpolator(){
		
		return timeInterpolatorChooser;
	}
	
	public MySeekBarWidget getMySeekBarWidget(){
		return time;
	}

}
