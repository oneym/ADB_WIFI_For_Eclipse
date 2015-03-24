package com.oneym.adb_wifi_for_eclipse;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.oneym.adb_wifi_for_eclipse.R.string;

public class onToggleButtonClickListening implements OnClickListener{
	
	private Context context = null;
	ToggleButton startOrStop = null;
	private static onToggleButtonClickListening instance = null;
	
	private onToggleButtonClickListening(Context context) {
		this.context = context;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (startOrStop.isChecked()) {
			Toast.makeText(context, string.start_but, Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(context, string.stop_but, Toast.LENGTH_LONG).show();
		}
		
	}
	
	public static onToggleButtonClickListening getInstance(Context context) {
		if (instance == null)
			instance = new onToggleButtonClickListening(context);
		return instance;
	}
	
}
