package net.zenconsult.android.chucknorris;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Comms {
	private final String url = "http://www.chucknorrisfacts.com/";

	private DefaultHttpClient client;

	public Comms() {

		client = new DefaultHttpClient();
	}

	public String get() {
		InputStream pageStream = doGetAsInputStream(url);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		Document doc = null;
		String pageText = "";
		Vector<String> quotes = new Vector<String>();
		try {
			db = dbFactory.newDocumentBuilder();
			doc = db.parse(pageStream);
			NodeList nl = doc.getElementsByTagName("div");
			for (int x = 0; x < nl.getLength(); ++x) {
				Node node = nl.item(x);
				NamedNodeMap attributes = node.getAttributes();
				for (int y = 0; y < attributes.getLength(); ++y) {
					if (attributes.getNamedItem("class") != null) {
						Node attribute = attributes.getNamedItem("class");
						if (attribute.getNodeValue()
								.equals("views-field-title")) {
							NodeList children = node.getChildNodes();
							for (int z = 0; z < children.getLength(); ++z) {
								Node child = children.item(z);
								if (child.getNodeName()
										.equalsIgnoreCase("span"))
									quotes.add(child.getTextContent());
							}
						}
					}

				}
			}
			Random r = new Random();
			pageText = quotes.get(r.nextInt(quotes.size() - 1));
			pageStream.close();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pageText;
	}

	public String doGetAsString(String url) {
		HttpGet request = new HttpGet(url);
		String result = "";
		try {
			HttpResponse response = client.execute(request);
			int code = response.getStatusLine().getStatusCode();
			if (code == 200) {
				result = EntityUtils.toString(response.getEntity());
			} else {
				Log.e("CN", "Non 200 Status Code " + code);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	public InputStream doGetAsInputStream(String url) {
		HttpGet request = new HttpGet(url);
		InputStream result = null;
		try {
			HttpResponse response = client.execute(request);
			int code = response.getStatusLine().getStatusCode();
			if (code == 200) {
				result = response.getEntity().getContent();
			} else {
				Log.e("CN", "Non 200 Status Code " + code);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}
}
