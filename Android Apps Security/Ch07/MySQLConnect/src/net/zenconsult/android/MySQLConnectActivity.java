package net.zenconsult.android;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MySQLConnectActivity extends ListActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Connection conn = null;
		String host = "192.168.3.105";
		int port = 3306;
		String db = "android";

		String user = "sheran";
		String pass = "P@ssw0rd";

		String url = "jdbc:mysql://" + host + ":" + port + "/" + db + "?user="
				+ user + "&password=" + pass;
		String sql = "SELECT * FROM apress";

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url);

			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			Hashtable<String, String> details = new Hashtable<String, String>();
			while (rs.next()) {
				details.put(rs.getString("name"), rs.getString("email"));
			}
			String[] names = new String[details.keySet().size()];
			int x = 0;
			for (Enumeration<String> e = details.keys(); e.hasMoreElements();) {
				names[x] = e.nextElement();
				x++;
			}
			conn.close();
			this.setListAdapter(new ArrayAdapter<String>(this,
					R.layout.list_item, names));

			ListView lv = getListView();
			lv.setTextFilterEnabled(true);

			lv.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Toast.makeText(getApplicationContext(),
							((TextView) view).getText(), Toast.LENGTH_SHORT)
							.show();
				}
			});

		} catch (ClassNotFoundException e) {
			Log.e("MYSQL", "Class not found!");
		} catch (SQLException e) {
			Log.e("MYSQL", "SQL Exception " + e.getMessage());
		} catch (InstantiationException e) {
			Log.e("MYSQL", "Instantiation error " + e.getMessage());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}