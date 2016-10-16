package net.zenconsult.android;

import net.zenconsult.android.crypto.Crypto;
import net.zenconsult.android.crypto.KeyManager;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class StorageExample3Activity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		String key = "12345678909876543212345678909876";
		String iv = "1234567890987654";

		KeyManager km = new KeyManager(getApplicationContext());
		km.setIv(iv.getBytes());
		km.setId(key.getBytes());

		// Store data
		Contact contact = new Contact();
		contact.setFirstName("Sheran");
		contact.setLastName("Gunasekera");
		contact.setEmail("sheran@zenconsult.net");
		contact.setPhone("+12120031337");

		ContactsDb db = new ContactsDb(getApplicationContext(), "ContactsDb",
				null, 1);
		Log.i("SE3", String.valueOf(StoreData.store(new Crypto(
				getApplicationContext()), db, contact)));

		Contact c = RetrieveData.get(new Crypto(getApplicationContext()), db);

		db.close();

		EditText ed = (EditText) findViewById(R.id.editText1);
		ed.setText(c.toString());

	}
}