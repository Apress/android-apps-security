package net.zenconsult.android;

import net.zenconsult.android.controller.SaveController;
import net.zenconsult.android.model.Contact;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ProximActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final Contact contact = new Contact();
		contact.setFirstName("Sheran");
		contact.setLastName("Gunasekera");
		contact.setAddress1("");
		contact.setAddress2("");
		contact.setEmail("sheran@zenconsult.net");
		contact.setPhone("12120031337");
		final Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
		    public void onClick(View v) {
		        SaveController.saveContact(getApplicationContext(), contact);
		    }
		});
		
	}
}