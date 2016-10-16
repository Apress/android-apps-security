package net.zenconsult.android;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import net.zenconsult.android.crypto.Crypto;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class RetrieveData {
	public static Contact get(Crypto crypto, ContactsDb db) {
		SQLiteDatabase rdb = db.getReadableDatabase();
		String[] cols = { "FIRSTNAME", "LASTNAME", "EMAIL", "PHONE" };
		Cursor results = rdb.query(ContactsDb.tblName, cols, "", null, "", "",
				"");

		Contact c = new Contact();
		results.moveToLast();

		try {
			c.setFirstName(crypto.armorDecrypt(results.getString(0)));
			c.setLastName(crypto.armorDecrypt(results.getString(1)));
			c.setEmail(crypto.armorDecrypt(results.getString(2)));
			c.setPhone(crypto.armorDecrypt(results.getString(3)));
		} catch (InvalidKeyException e) {
			Log.e("SE3", "Exception in RetrieveData: " + e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			Log.e("SE3", "Exception in RetrieveData: " + e.getMessage());
		} catch (NoSuchPaddingException e) {
			Log.e("SE3", "Exception in RetrieveData: " + e.getMessage());
		} catch (IllegalBlockSizeException e) {
			Log.e("SE3", "Exception in RetrieveData: " + e.getMessage());
		} catch (BadPaddingException e) {
			Log.e("SE3", "Exception in RetrieveData: " + e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			Log.e("SE3", "Exception in RetrieveData: " + e.getMessage());
		}

		return c;
	}
}
