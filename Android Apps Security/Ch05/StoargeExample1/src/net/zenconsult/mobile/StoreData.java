package net.zenconsult.mobile;

import java.util.Hashtable;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class StoreData {

	public static boolean storeData(Hashtable data, Context ctx) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(ctx);
		String hostname = (String) data.get("hostname");
		int port = (Integer) data.get("port");
		boolean useSSL = (Boolean) data.get("ssl");
		Editor ed = prefs.edit();
		ed.putString("hostname", hostname);
		ed.putInt("port", port);
		ed.putBoolean("ssl", useSSL);
		return ed.commit();
	}
}
