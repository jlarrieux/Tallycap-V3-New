package com.jeannius.tallycap.util;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.jeannius.tallycap.R;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {
	
	private Context context;
	  // Sample data set.  children[i] contains the children (String[]) for groups[i].
    private String[] groups ;
    private String[] children ;
    Global g;
    

    public MyExpandableListAdapter(Context context, int groupResourceId, int childResourceId){
    	this.context = context;
    	groups = context.getResources().getStringArray(groupResourceId);
    	children = context.getResources().getStringArray(childResourceId);
    	g = new Global(context);
    	
    }
    


    public Object getChild(int groupPosition, int childPosition) {
    	
    	if(groupPosition==0)  return children[childPosition];
    	else return null;
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public int getChildrenCount(int groupPosition) {
    	int z=0;
    	if(groupPosition==0)z = children.length;
    	
        return z;
    }

    public TextView getGenericView() {
    	
    	int minHeight = (int) ((48*g.logicalDensity)+0.5);
        // Layout parameters for the ExpandableListView
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, minHeight);
        

        TextView text = new TextView(context);
        text.setLayoutParams(lp);
        // Center the text vertically
        text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        
        text.setTextSize(14);
       
     
        
        return text;
    }

    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
    	TextView text  = getGenericView();
    	int padding_in_dp =70;
    	final float scale = context.getResources().getDisplayMetrics().density;
    	int paddind_in_px = (int) (padding_in_dp*scale +0.5f);
    	text.setPadding(paddind_in_px, 0, 5, 0);
    	
//    	convertView.setMinimumHeight(100);
    	text.setHeight(150);
    		
    	
        if(groupPosition==0) text.setText(getChild(groupPosition, childPosition).toString());
        
    	
        return text;
    }

    public Object getGroup(int groupPosition) {
        return groups[groupPosition];
    }

    public int getGroupCount() {
        return groups.length;
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,   ViewGroup parent) {
    	
    	int topMargin =8;
    	int bottomMargin=8;
    	LinearLayout lay = new LinearLayout(context);
    	lay.setOrientation(LinearLayout.HORIZONTAL);
    	lay.setPadding(0, 0, 5, 0);
    	
    	TextView text = getGenericView();
        text.setText(getGroup(groupPosition).toString());
        text.setGravity(Gravity.LEFT|Gravity.BOTTOM);
        
        
        
        text.setTextSize(18);
        
        LayoutParams lin = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lin.setMargins(8, topMargin, 12, bottomMargin);
        LayoutParams lin2 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        lin2.setMargins(0, topMargin, 48, bottomMargin);
        LayoutParams lin3 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);       
        lin3.setMargins(0, topMargin, 0, bottomMargin);
        
    	
    	ImageView img = new ImageView(context);
    	img.setPadding(12, 12, 12, 12);
    	
    	LinearLayout lay2 = new LinearLayout(context);
    	lay2.setOrientation(LinearLayout.HORIZONTAL);
    	lay2.setGravity(Gravity.RIGHT);
    	
    	
    	ImageView img2 = new ImageView(context); 
    	img2.setPadding(8, 8, 8, 8);
    	lay2.addView(img2, lin);
    	if(groupPosition==0){
    		if(isExpanded) img2.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.navigation_expand));
    		else img2.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.navigation_collapse));
    	}
    	
    	
    	int r=0;
    	
    	switch(groupPosition){
    		case 0: r= R.drawable.calculator_icon;
    			break;
    		case 1: r= R.drawable.reminder_icon;
    			break;
    		case 2: r= R.drawable.budget_pad_icon;
    			break;
    		case 3: r= R.drawable.account_icon;
    			break;
    		default: r= R.drawable.calculator_icon;
    	}
    	
    	img.setBackgroundDrawable(context.getResources().getDrawable(r));
    	
    	lay.addView(img, 0, lin);
    	lay.addView(text, 1, lin2);    	
    	
    	lay.addView(lay2,2, lin3);
    	
    	
        
        return lay;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public boolean hasStableIds() {
        return true;
    }
    
    

}


