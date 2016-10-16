package net.zenconsult.android;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class OAuthPicasaActivity extends ListActivity {
	OAuthPicasaActivity act;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		act = this;
		OAuth o = new OAuth(this);
		Token t = o.getToken();

		if (!t.isValidForReq()) {
			Intent intent = new Intent(this, AuthActivity.class);
			this.startActivity(intent);
		}
		if (t.isExpired()) {
			o.getRequestToken();
		}

		DataFetcher df = new DataFetcher(t);
		df.fetchAlbums("sheranapress");
		String[] names = new String[] {}; // Add bridge code here to parse XML
											// from DataFetcher and populate
											// your List

		this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,
				names));

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(),
						((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		});

	}

}