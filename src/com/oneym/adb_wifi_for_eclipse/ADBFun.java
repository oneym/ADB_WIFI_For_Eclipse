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

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Vector;

import android.util.Log;

public class ADBFun {
	
	private static ADBFun instance = null;
	String adb_setprop = "setprop service.adb.tcp.port 5555\n";
	String adb_setprop_stop = "setprop service.adb.tcp.port -1\n";
	String adb_stop = "stop adbd\n";
	String adb_start = "start adbd\n";
	String Shell_exit = "exit\n";
	Runtime runtime = Runtime.getRuntime();
	Process p = null;
	
	private ADBFun() {}
	
	public void getConnectWithEclipse() {
		
		try {
			p = runtime.exec("su");
			DataOutputStream os = new DataOutputStream(p.getOutputStream());
			os.writeBytes(adb_setprop);
			os.writeBytes(adb_stop);
			os.writeBytes(adb_start);
			os.writeBytes(Shell_exit);
			os.flush();
			p.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("abc", e.getMessage().toString()+"\n"+e.getStackTrace().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("abc", e.getMessage()+"\n"+e.getStackTrace());
		}
	}
	
	public void stopConnectWithEclipse() {
		try {
			p = runtime.exec("su");
			DataOutputStream os = new DataOutputStream(p.getOutputStream());
			os.writeBytes(adb_setprop_stop);
			os.writeBytes(adb_stop);
			os.writeBytes(adb_start);
			os.writeBytes(Shell_exit);
			os.flush();
			p.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("abc", e.getMessage().toString()+"\n"+e.getStackTrace().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("abc", e.getMessage().toString()+"\n"+e.getStackTrace().toString());
		}
	}
	
	private Vector<String> getInet4Address() {
		Vector<String> ipv4All = new Vector<String>();
		InetAddress ipv4 = null;
		try {
			Enumeration<NetworkInterface> enumeration = null;
			enumeration = NetworkInterface.getNetworkInterfaces();
			while(enumeration.hasMoreElements()) {
				NetworkInterface ni = enumeration.nextElement();
				Enumeration<InetAddress> nii = ni.getInetAddresses();
				while(nii.hasMoreElements()) {
					ipv4 = nii.nextElement();
					if (ipv4 != null && ipv4 instanceof Inet4Address) {
						if (ipv4.isSiteLocalAddress())
							ipv4All.add(ipv4.getHostAddress());
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return ipv4All;
	}
	
	public static ADBFun getInstance() {
		if (instance == null)
			instance = new ADBFun();
		return instance;
	}
}
