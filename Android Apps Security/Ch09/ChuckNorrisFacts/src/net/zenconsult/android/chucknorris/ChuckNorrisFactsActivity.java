package net.zenconsult.android.chucknorris;

import java.util.UUID;

import com.android.vending.licensing.AESObfuscator;
import com.android.vending.licensing.LicenseChecker;
import com.android.vending.licensing.LicenseCheckerCallback;
import com.android.vending.licensing.ServerManagedPolicy;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ChuckNorrisFactsActivity extends Activity implements CommsEvent {
	private Button button;
	private TextView view;
	private Activity activity;
	private CommsEvent event;
	private LicCallBack lcb;
	private static final String PUB_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlXLnY54Y62odQOcizrYgGuTz1f0OYCnSqv5FUX475uCkLZBCr+9OMZkiW/koxw/ujIpNNyu+AgcP7fTla64ylGKQ2o7IUmzxzJDAitN+/uxdbVqXu6LhvxHjggSDI+g8QYs4LO2lLqyeFddfpS/EkOoFD7aQ0GRZzgyY6eW4dwZ3BML9jXKtj6T37BlgPDv5SjK8chECMOc7IpIh/K6TYX28X9kyyiUK7UWtuaUl99iD9Qyisfwp+8xZlQDNPclWbwZz+SojsNjs9Yh3ISUOFcF/BqxZbiMWhRFj9lLwo+xiTXaNErMspjc4O/vNOuHV9mwAm+ire+c7Fpv6vuSpIwIDAQAB";// Add your Base64 Public
														// key here
	private static final byte[] SALT = new byte[] { -118, -112, 38, 124, 15,
			-121, 59, 93, 35, -55, 14, -15, -52, 67, -53, 54, 111, -28, -87, 12 };

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.main);
		event = this;
		activity = this;
		view = (TextView) findViewById(R.id.editText1);

		// Click Button
		button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Do License Check before allowing click

				// Generate a Unique ID
				String deviceId = Secure.getString(getContentResolver(),
						Secure.ANDROID_ID);
				String serialId = Build.SERIAL;
				UUID uuid = new UUID(deviceId.hashCode(), serialId.hashCode());
				String identity = uuid.toString();
				Context ctx = activity.getApplicationContext();

				// Create an Obfuscator and a Policy
				AESObfuscator obf = new AESObfuscator(SALT, getPackageName(),
						identity);
				ServerManagedPolicy policy = new ServerManagedPolicy(ctx, obf);

				// Create the LicenseChecker
				LicenseChecker lCheck = new LicenseChecker(ctx, policy, PUB_KEY);

				// Do the license check
				lcb = new LicCallBack();
				lCheck.checkAccess(lcb);
			}
		});
	}

	@Override
	public void onTextReceived(final String text) {
		runOnUiThread(new Runnable() {
			public void run() {
				setProgressBarIndeterminateVisibility(false);
				view.setText(text);
				button.setEnabled(true);

			}
		});

	}

	public class LicCallBack implements LicenseCheckerCallback {

		@Override
		public void allow() {
			if (isFinishing()) {
				return;
			}
			Toast toast = Toast.makeText(getApplicationContext(), "Licensed!",
					Toast.LENGTH_LONG);
			toast.show();
			button.setEnabled(false);
			setProgressBarIndeterminateVisibility(true);
			view.setText("Fetching fact...");
			CommsNotifier c = new CommsNotifier(event);
			c.start();
		}

		@Override
		public void dontAllow() {
			if (isFinishing()) {
				return;
			}
			Toast toast = Toast.makeText(getApplicationContext(),
					"Unlicensed!", Toast.LENGTH_LONG);
			toast.show();
		}

		@Override
		public void applicationError(ApplicationErrorCode errorCode) {
			// TODO Auto-generated method stub

		}

	}

}