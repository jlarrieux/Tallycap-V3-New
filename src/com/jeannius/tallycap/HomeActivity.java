package com.jeannius.tallycap;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;

import com.jeannius.tallycap.Fragments.CalculatorTabFragment;
import com.jeannius.tallycap.util.MyAbstractActivity;
import com.jeannius.tallycap.util.MyExpandableListAdapter;

public class HomeActivity extends MyAbstractActivity implements OnGroupCollapseListener, OnGroupExpandListener, OnChildClickListener, OnGroupClickListener{
	
    // Declare Variable
    DrawerLayout mDrawerLayout;
    ExpandableListView mDrawerList;
    ActionBarDrawerToggle mDrawerToggle;
    MyExpandableListAdapter mMenuAdapter;
    String[] title;
    String[] subtitle;
    int[] icon;
    ExpandableListView mExpandableListView;
   
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_drawer);
        init();
       
 
    }
	
	
	
	
	
	private void init(){
		
		FrameLayout frame = (FrameLayout) findViewById(R.id.content_frame);
		ImageView img = new ImageView(getApplicationContext());
//		img.setBackgroundResource(R.drawable.construction);
		LayoutParams lay = new LayoutParams(new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT));
		
		frame.addView(img, lay);
		 // Generate title
        title = getResources().getStringArray(R.array.fragment_list);       
 
        // Generate icon
        icon = new int[] { R.drawable.test1, R.drawable.reminder,R.drawable.ic_menu_compose, R.drawable.device_access_accounts };
 
        // Locate DrawerLayout in drawer_main.xml
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
 
        // Locate ListView in drawer_main.xml
        mDrawerList = (ExpandableListView) findViewById(R.id.left_drawer);
 
        // Set a custom shadow that overlays the main content when the drawer
        // opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,  GravityCompat.START);
 
        // Pass results to MenuListAdapter Class
        mMenuAdapter = new MyExpandableListAdapter(this, R.array.fragment_list, R.array.calculator_subgroup);
 
        // Set the MenuListAdapter to the ListView
        mDrawerList.setAdapter(mMenuAdapter);
 
//        mExpandableListView = findViewById(R.)
 
        // Enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
 
        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.one,
                R.string.two) {
 
            public void onDrawerClosed(View view) {
                // TODO Auto-generated method stub
                super.onDrawerClosed(view);
            }
 
            public void onDrawerOpened(View drawerView) {
                // TODO Auto-generated method stub
                super.onDrawerOpened(drawerView);
            }
        };
 
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerList.setOnGroupClickListener(this);
		mDrawerList.setOnChildClickListener(this);
		
	}





	@Override
	public void onGroupExpand(int groupPosition) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void onGroupCollapse(int groupPosition) {
		// TODO Auto-generated method stub
		
	}


	


	@Override
	public boolean onChildClick(ExpandableListView parent, View v,	int groupPosition, int childPosition, long id) {
		CalculatorTabFragment t = new CalculatorTabFragment();			
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.content_frame, t);
		ft.commit();
		
//		mDrawerList.setIt
//		g.toaster(String.format("Child position: %d", childPosition), true);
		return false;
	}





	@Override
	public boolean onGroupClick(ExpandableListView parent, View v,	int groupPosition, long id) {
		if(groupPosition==0){
			
			CalculatorTabFragment t = new CalculatorTabFragment();			
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.add(t, "RED");
			ft.commit();	
//			g.toaster(String.format("Group Position: %d", groupPosition), false);
			
		}
			
			
		return false;
	}

	
	
}
