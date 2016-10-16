package net.zenconsult.android;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import net.zenconsult.android.crypto.Crypto;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class StoreData {
	public static long store(Crypto crypto, ContactsDb db, Contact contact) {
		// Prepare values
		ContentValues values = new ContentValues();
		try {
			values.put("FIRSTNAME", crypto.armorEncrypt(contact.getFirstName()
					.getBytes()));
			values.put("LASTNAME", crypto.armorEncrypt(contact.getLastName()
					.getBytes()));
			values.put("EMAIL", crypto.armorEncrypt(contact.getEmail()
					.getBytes()));
			values.put("PHONE", crypto.armorEncrypt(contact.getPhone()
					.getBytes()));
			values.put("ADDRESS1", contact.getAddress1());
			values.put("ADDRESS2", contact.getAddress2());
		} catch (InvalidKeyException e) {
			Log.e("SE3", "Exception in StoreData: " + e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			Log.e("SE3", "Exception in StoreData: " + e.getMessage());
		} catch (NoSuchPaddingException e) {
			Log.e("SE3", "Exception in StoreData: " + e.getMessage());
		} catch (IllegalBlockSizeException e) {
			Log.e("SE3", "Exception in StoreData: " + e.getMessage());
		} catch (BadPaddingException e) {
			Log.e("SE3", "Exception in StoreData: " + e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			Log.e("SE3", "Exception in StoreData: " + e.getMessage());
		}
		SQLiteDatabase wdb = db.getWritableDatabase();
		return wdb.insert(ContactsDb.tblName, null, values);
	}
}
