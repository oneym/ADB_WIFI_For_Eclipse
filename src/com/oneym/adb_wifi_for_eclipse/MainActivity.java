/*
 * Copyright (C) 2015 oneym
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.oneym.adb_wifi_for_eclipse;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.oneym.adb_wifi_for_eclipse.R.string;

public class MainActivity extends Activity {
	
	TextView hint = null;
	ToggleButton startOrStop = null;
	String info_hint = "请在电脑cmd中输入\nadb connect ";
	WifiInfo wifiInfo = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		hint = (TextView) findViewById(R.id.textView1);
		startOrStop = (ToggleButton) findViewById(R.id.startOrStop);
		startOrStop.setChecked(true);
		startOrStop.setOnClickListener(new onToggleButtonClick());
		ADBFun.getInstance().getConnectWithEclipse();
		WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		wifiInfo = wifiManager.getConnectionInfo();
		hint.setText(info_hint+Formatter.formatIpAddress(wifiInfo.getIpAddress()));
	}
	
	class onToggleButtonClick implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (startOrStop.isChecked()) {
				ADBFun.getInstance().getConnectWithEclipse();
				hint.setText(info_hint+Formatter.formatIpAddress(wifiInfo.getIpAddress()));
				Toast.makeText(MainActivity.this, string.start_but, Toast.LENGTH_LONG).show();
			} else {
				ADBFun.getInstance().stopConnectWithEclipse();
				hint.setText(string.hello_world);
				Toast.makeText(MainActivity.this, string.stop_but, Toast.LENGTH_LONG).show();
			}
		}
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
