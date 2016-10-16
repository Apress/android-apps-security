package net.zenconsult.android;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

public class OAuth {
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
	private Activity activity;
	private boolean authenticated;
	private Token token;

	public OAuth(Activity act) {
		ctx = act.getApplicationContext();
		activity = act;
		token = readToken();

	}

	public Token readToken() {
		Token token = null;
		FileInputStream fis;
		try {
			fis = ctx.openFileInput(FILENAME);
			ObjectInputStream in = new ObjectInputStream(
					new BufferedInputStream(fis));
			token = (Token) in.readObject();
			if (token == null) {
				token = new Token();
				writeToken(token);
			}
			in.close();
			fis.close();
		} catch (FileNotFoundException e) {
			writeToken(new Token());
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return token;
	}

	public void writeToken(Token token) {
		try {
			File f = new File(FILENAME);
			if (f.exists()) {
				f.delete();
			}
			FileOutputStream fos = ctx.openFileOutput(FILENAME,
					Context.MODE_PRIVATE);

			ObjectOutputStream out = new ObjectOutputStream(
					new BufferedOutputStream(fos));
			out.writeObject(token);
			out.close();
			fos.close();
		} catch (FileNotFoundException e1) {
			Log.e("OAUTH", "Error creating settings file");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}

	public void getRequestToken() {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(httpReqPost);
		List<NameValuePair> nvPairs = new ArrayList<NameValuePair>();
		nvPairs.add(clientId);
		nvPairs.add(clientSecret);
		nvPairs.add(new BasicNameValuePair("code", token.getAuthCode()));
		nvPairs.add(redirectURI);
		nvPairs.add(new BasicNameValuePair("grant_type", "authorization_code"));
		try {
			post.setEntity(new UrlEncodedFormEntity(nvPairs));
			HttpResponse response = httpClient.execute(post);
			HttpEntity httpEntity = response.getEntity();
			String line = EntityUtils.toString(httpEntity);
			JSONObject jObj = new JSONObject(line);
			token.buildToken(jObj);
			writeToken(token);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			if (e.getMessage().equals("No peer certificate")) {
				Toast toast = Toast.makeText(activity.getApplicationContext(),
						"Possible HTC Error for Android 2.3.3",
						Toast.LENGTH_SHORT);
				toast.show();
			}
			Log.e("OAUTH", "IOException " + e.getMessage());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}
}
