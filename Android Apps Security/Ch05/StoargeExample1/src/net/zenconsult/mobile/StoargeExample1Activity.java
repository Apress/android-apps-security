package net.zenconsult.mobile;

import java.util.Hashtable;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class StoargeExample1Activity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Context cntxt = getApplicationContext();

		Hashtable data = new Hashtable();
		data.put("hostname", "smtp.gmail.com");
		data.put("port", 587);
		data.put("ssl", true);

		if (StoreData.storeData(data, cntxt))
			Log.i("SE", "Successfully wrote data");
		else
			Log.e("SE", "Failed to write data to Shared Prefs");

		EditText ed = (EditText) findViewById(R.id.editText1);
		ed.setText(RetrieveData.get(cntxt).toString());
	}
}