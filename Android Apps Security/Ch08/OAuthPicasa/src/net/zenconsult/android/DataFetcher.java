package net.zenconsult.android;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class DataFetcher {
	private HttpClient httpClient;
	private Token token;

	public DataFetcher(Token t) {
		token = t;
		httpClient = new DefaultHttpClient();

	}

	public void fetchAlbums(String userId) {
		String url = "https://picasaweb.google.com/data/feed/api/user/"
				+ userId;
		try {
			HttpResponse resp = httpClient.execute(buildGet(
					token.getAccessToken(), url));
			if (resp.getStatusLine().getStatusCode() == 200) {
				HttpEntity httpEntity = resp.getEntity();
				String line = EntityUtils.toString(httpEntity);
				// Do your XML Parsing here

			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HttpGet buildGet(String accessToken, String url) {
		HttpGet get = new HttpGet(url);
		get.addHeader("Authorization", "Bearer " + accessToken);
		return get;
	}
}
