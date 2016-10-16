package net.zenconsult.android;

import android.app.Activity;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

public class ClientHandler extends WebChromeClient {
	private Activity activity;
	private OAuth oAuth;

	public ClientHandler(Activity act) {
		activity = act;
		oAuth = new OAuth(activity);
	}

	@Override
	public void onReceivedTitle(WebView view, String title) {
		String code = "";
		if (title.contains("Success")) {
			code = title.substring(title.indexOf('=') + 1, title.length());
			setAuthCode(code);
			Log.v("OAUTH", "Code is " + code);
			oAuth.getRequestToken();
			oAuth.writeToken(oAuth.getToken());
			Toast toast = Toast.makeText(activity.getApplicationContext(),
					"Authorization Successful", Toast.LENGTH_SHORT);
			toast.show();
			activity.finish();
		} else if (title.contains("Denied")) {
			code = title.substring(title.indexOf('=') + 1, title.length());
			setAuthCode(code);
			Log.v("OAUTH", "Denied, error was " + code);
			Toast toast = Toast.makeText(activity.getApplicationContext(),
					"Authorization Failed", Toast.LENGTH_SHORT);
			toast.show();
			activity.finish();
		}
	}

	public String getAuthCode() {
		return oAuth.getToken().getAuthCode();
	}

	public void setAuthCode(String authCode) {
		oAuth.getToken().setAuthCode(authCode);
	}

	@Override
	public void onProgressChanged(WebView view, int progress) {

	}
}
