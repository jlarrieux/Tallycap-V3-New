package com.jeannius.tallycap.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;

import com.jeannius.tallycap.Views.TempPopUpAnimatoOption;

public class TempInterpolatorDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
	
	
	private TempPopUpAnimatoOption view1;
	private Dialog s1;
	public static int position=-1;
	public static int duration=100;

	
	
	
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		if(s1==null) s1 = new Dialog(getActivity());
		
//		view1 = new TempPopUpAnimatoOption(getActivity());
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setView(view1);
		builder.setPositiveButton("Dismis", this);
		s1 =builder.create();
		if(position !=-1) view1.getSpinnerInterpolator().setSelection(position);
		view1.getMySeekBarWidget().mSpinner.setVisibility(View.GONE);
		view1.getMySeekBarWidget().progressSetter(duration, false);
		return s1;
	}







	@Override
	public void onClick(DialogInterface dialog, int which) {
		
		position=view1.getSpinnerInterpolator().getSelectedItemPosition();
		duration=view1.getMySeekBarWidget().seekBar.getProgress();
		dialog.cancel();
		
	}
	
	
	
	
	
	

}
