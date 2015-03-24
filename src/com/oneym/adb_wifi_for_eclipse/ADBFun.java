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
