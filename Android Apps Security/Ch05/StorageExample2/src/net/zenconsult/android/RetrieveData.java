package net.zenconsult.android;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.content.Context;
import android.util.Log;

public class RetrieveData {
	public static final String file = "contacts";

	public static byte[] get(Context ctx) {
		byte[] data = null;
		try {
			int bytesRead = 0;
			FileInputStream fis = ctx.openFileInput(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			while ((bytesRead = fis.read(b)) != -1) {
				bos.write(b, 0, bytesRead);
			}
			data = bos.toByteArray();

		} catch (FileNotFoundException e) {
			Log.e("SE2", "Exception: " + e.getMessage());
		} catch (IOException e) {
			Log.e("SE2", "Exception: " + e.getMessage());
		}
		return data;
	}
}
