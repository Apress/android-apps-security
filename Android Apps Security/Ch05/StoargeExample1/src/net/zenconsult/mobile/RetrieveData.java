package net.zenconsult.mobile;

import java.util.Hashtable;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class RetrieveData {
	public static Hashtable get(Context ctx) {
		String hostname = "hostname";
		String port = "port";
		String ssl = "ssl";

		Hashtable data = new Hashtable();
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(ctx);
		data.put(hostname, prefs.getString(hostname, null));
		data.put(port, prefs.getInt(port, 0));
		data.put(ssl, prefs.getBoolean(ssl, true));
		return data;
	}
}
