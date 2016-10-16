package net.zenconsult.android;

import net.zenconsult.android.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CommercialAppActivity extends Activity implements CommsEvent {
	private Activity activity;
	private TextView view;
	private CommsEvent event;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		activity = this;
		event = this;
		view = (TextView) findViewById(R.id.editText1);

		// Verify license on first run

		// Click Button
		final Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				view.setText("Fetching fact...");
				CommsNotifier c = new CommsNotifier(event);
				c.start();
			}
		});

	}

	@Override
	public void onTextReceived(final String text) {
		runOnUiThread(new Runnable() {
			public void run() {
				view.setText(text);
			}
		});
	}
}