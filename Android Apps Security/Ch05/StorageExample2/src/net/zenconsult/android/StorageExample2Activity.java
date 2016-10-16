package net.zenconsult.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;

public class StorageExample2Activity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Context ctx = getApplicationContext();

		// Store data
		Contact contact = new Contact();
		contact.setFirstName("Sheran");
		contact.setLastName("Gunasekera");
		contact.setEmail("sheran@zenconsult.net");
		contact.setPhone("+12120031337");

		StoreData.storeData(contact.getBytes(), ctx);

		// Retrieve data

		EditText ed = (EditText) findViewById(R.id.editText1);
		ed.setText(new String(RetrieveData.get(ctx)));

	}
}