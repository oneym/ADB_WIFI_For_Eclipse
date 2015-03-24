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
