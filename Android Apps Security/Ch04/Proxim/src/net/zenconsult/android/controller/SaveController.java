package net.zenconsult.android.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import net.zenconsult.android.crypto.Crypto;
import net.zenconsult.android.model.Contact;
import net.zenconsult.android.model.Location;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class SaveController {
	private static final String TAG = "SaveController";
	
	public static void saveContact(Context context, Contact contact) {
		if (isReadWrite()) {
			try {
				File outputFile = new File(context.getExternalFilesDir(null),contact.getFirstName());
				FileOutputStream outputStream = new FileOutputStream(outputFile);
				byte[] key = Crypto.generateKey("randomtext".getBytes());
				outputStream.write(encrypt(key,contact.getBytes()));
				outputStream.close();

			} catch (FileNotFoundException e) {
				Log.e(TAG,"File not found");
			} catch (IOException e) {
				Log.e(TAG,"IO Exception");
			}

		} else {
			Log.e(TAG,"Error opening media card in read/write mode!");
		}
	}

	public static void saveLocation(Context context, Location location) {
		if (isReadWrite()) {
			try {
				File outputFile = new File(context.getExternalFilesDir(null),location.getIdentifier());
				FileOutputStream outputStream = new FileOutputStream(outputFile);
				byte[] key = Crypto.generateKey("randomtext".getBytes());
				outputStream.write(encrypt(key,location.getBytes()));
				outputStream.close();

			} catch (FileNotFoundException e) {
				Log.e(TAG,"File not found");
			} catch (IOException e) {
				Log.e(TAG,"IO Exception");
			}

		} else {
			Log.e(TAG,"Error opening media card in read/write mode!");
		}
	}

	private static boolean isReadOnly() {
		Log.e(TAG,Environment
				.getExternalStorageState());
		return Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment
				.getExternalStorageState());
	}

	private static boolean isReadWrite() {
		Log.e(TAG,Environment
				.getExternalStorageState());
		
		return Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState());
	}
	
	private static byte[] encrypt(byte[] key, byte[] data){
		SecretKeySpec sKeySpec = new SecretKeySpec(key,"AES");
		Cipher cipher;
		byte[] ciphertext = null;
		try {
			cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, sKeySpec);
			ciphertext = cipher.doFinal(data);
		} catch (NoSuchAlgorithmException e) {
			Log.e(TAG,"NoSuchAlgorithmException");
		} catch (NoSuchPaddingException e) {
			Log.e(TAG,"NoSuchPaddingException");
		} catch (IllegalBlockSizeException e) {
			Log.e(TAG,"IllegalBlockSizeException");
		} catch (BadPaddingException e) {
			Log.e(TAG,"BadPaddingException");
		} catch (InvalidKeyException e) {
			Log.e(TAG,"InvalidKeyException");
		}
		return ciphertext;
		
	}
}
