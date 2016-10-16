package net.zenconsult.android;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class AuthActivity extends Activity {
	private BasicNameValuePair clientId = new BasicNameValuePair("client_id",
			"200744748489.apps.googleusercontent.com");
	private BasicNameValuePair clientSecret = new BasicNameValuePair(
			"client_secret", "edxCTl_L8_SFl1rz2klZ4DbB");
	private BasicNameValuePair redirectURI = new BasicNameValuePair(
			"redirect_uri", "urn:ietf:wg:oauth:2.0:oob");
	private String scope = "scope=https://picasaweb.google.com/data/";
	private String oAuth = "https://accounts.google.com/o/oauth2/auth?";
	private String httpReqPost = "https://accounts.google.com/o/oauth2/token";
	private final String FILENAME = ".oauth_settings";
	private URI uri;
	private WebView wv;
	private Context ctx;
	private Token token;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.auth);
		doAuth();
	}

	public void doAuth() {
		try {
			uri = new URI(oAuth + clientId + "&" + redirectURI + "&" + scope
					+ "&response_type=code");
			wv = (WebView) findViewById(R.id.webview);
			wv.setWebChromeClient(new ClientHandler(this));
			wv.setWebViewClient(new MWebClient());
			wv.getSettings().setJavaScriptEnabled(true);
			wv.loadUrl(uri.toASCIIString());
			Log.v("OAUTH", "Calling " + uri.toASCIIString());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
