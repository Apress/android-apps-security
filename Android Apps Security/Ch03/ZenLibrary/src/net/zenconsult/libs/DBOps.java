package net.zenconsult.libs;

import android.util.Log;

public class DBOps {
	private static final String TAG = "DBOps";
	
	public DBOps(){
		
	}
	
	public static void purgeDatabase(){
		Log.v(TAG,"Purge DB called");
	}
}
