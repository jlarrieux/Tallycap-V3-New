package com.jeannius.tallycap;

import android.os.Bundle;
import android.widget.TextView;

import com.jeannius.tallycap.Views.HomeView1;
import com.jeannius.tallycap.util.MyAbstractActivity;
import com.jeannius.tallycap.util.ObserverOfSubject;

public class HomeActivity extends MyAbstractActivity implements ObserverOfSubject{
	TextView t ;
	HomeView1 homeView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeView = new HomeView1(getApplicationContext());
        setContentView(homeView);

        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        getSupportActionBar().setHomeButtonEnabled(false);

    }

	@Override
	public void update(String s) {
		switchTo(s);		
	}
	
	@Override
	protected void onResume() {		
		super.onResume();
		
		homeView.registerObserver(this);
	}
	
	@Override
	protected void onPause() {		
		super.onPause();
		homeView.removeObserver();
	}


}
