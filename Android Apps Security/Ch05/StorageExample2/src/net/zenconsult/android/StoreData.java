package net.zenconsult.android;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.util.Log;

public class StoreData {
	public static final String file = "contacts";

	public static void storeData(byte[] data, Context ctx) {

		try {
			FileOutputStream fos = ctx.openFileOutput(file, ctx.MODE_PRIVATE);
			fos.write(data);
			fos.close();
		} catch (FileNotFoundException e) {
			Log.e("SE2", "Exception: " + e.getMessage());
		} catch (IOException e) {
			Log.e("SE2", "Exception: " + e.getMessage());
		}
	}
}
