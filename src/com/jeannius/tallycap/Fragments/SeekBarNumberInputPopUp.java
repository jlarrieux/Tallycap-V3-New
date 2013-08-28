package com.jeannius.tallycap.Fragments;



import android.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.jeannius.tallycap.Views.SeekBarNumberInputPopUpView;
import com.jeannius.tallycap.util.Global;
import com.jeannius.tallycap.util.MySeekBarWidget;

public class SeekBarNumberInputPopUp extends DialogFragment implements	DialogInterface.OnClickListener {

	

	private Global g;
	private SeekBarNumberInputPopUpView view1;
	private static MySeekBarWidget m;
	
	
	public static SeekBarNumberInputPopUp newInstance(int max, int id){
		SeekBarNumberInputPopUp frag = new  SeekBarNumberInputPopUp();
		
		Bundle args = new Bundle();
		args.putInt("max", max);
		args.putInt("id", id);
		frag.setArguments(args);
		
		return frag;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		int max = getArguments().getInt("max");
		
		m = (MySeekBarWidget) getActivity().findViewById(getArguments().getInt("id"));
		Dialog s1 = new Dialog(getActivity());
		g = new Global(getActivity());
				
		view1 = new SeekBarNumberInputPopUpView(getActivity());
		view1.mMaxAbsolute = max;
		view1.range.setText( "("+g.formatNumber(0) +" - " +g.formatNumber(max)+")");
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setPositiveButton(R.string.ok, this)
			   .setNegativeButton(R.string.cancel, this)
		       .setView(view1);
		s1 =builder.create();	
			
			

		return s1;
		
	}
	




	
	/**
	 * This function sets the value of the mySeekBarWidget amount text to the value chosen in the dialogFragment
	 */
	@Override
	public void onClick(DialogInterface dialog, int which) {
		
		
		if(which == DialogInterface.BUTTON_POSITIVE){
//			m.setMyText(value);
			if(view1.editText.getText().toString().length()>0){
				double value =Double.valueOf(g.purgerAny(view1.editText.getText().toString()));
				if(m.getType()==2 || (m.getType()==5 & m.mSpinner.getSelectedItemPosition()==0)) value*=m.scaleFactor;
				m.progressSetter((int) value, false);
			}
			
		}
		
	}
	
	
	
	

	
	
	
	

	
	
	
	
	
	
	
	
	
	
	

}
