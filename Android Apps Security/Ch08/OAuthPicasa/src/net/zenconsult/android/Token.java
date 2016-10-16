package net.zenconsult.android;

import java.io.Serializable;
import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

public class Token implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6534067628631656760L;
	private String refreshToken;
	private String accessToken;
	private Calendar expiryDate;
	private String authCode;
	private String tokenType;
	private String name;

	public Token() {
		setExpiryDate(0);
		setTokenType("");
		setAccessToken("");
		setRefreshToken("");
		setName("");
	}

	public Token(JSONObject response) {
		try {
			setExpiryDate(response.getInt("expires_in"));
		} catch (JSONException e) {
			setExpiryDate(0);
		}
		try {
			setTokenType(response.getString("token_type"));
		} catch (JSONException e) {
			setTokenType("");
		}
		try {
			setAccessToken(response.getString("access_token"));
		} catch (JSONException e) {
			setAccessToken("");
		}
		try {
			setRefreshToken(response.getString("refresh_token"));
		} catch (JSONException e) {
			setRefreshToken("");
		}
	}

	public void buildToken(JSONObject response) {
		try {
			setExpiryDate(response.getInt("expires_in"));
		} catch (JSONException e) {
			setExpiryDate(0);
		}
		try {
			setTokenType(response.getString("token_type"));
		} catch (JSONException e) {
			setTokenType("");
		}
		try {
			setAccessToken(response.getString("access_token"));
		} catch (JSONException e) {
			setAccessToken("");
		}
		try {
			setRefreshToken(response.getString("refresh_token"));
		} catch (JSONException e) {
			setRefreshToken("");
		}
	}

	public boolean isValidForReq() {
		if (getAccessToken() != null && !getAccessToken().equals("")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isExpired() {
		Calendar now = Calendar.getInstance();
		if (now.after(getExpiryDate()))
			return true;
		else
			return false;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		if (refreshToken == null)
			refreshToken = "";
		this.refreshToken = refreshToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		if (accessToken == null)
			accessToken = "";
		this.accessToken = accessToken;
	}

	public Calendar getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(int seconds) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.SECOND, seconds);
		this.expiryDate = now;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		if (authCode == null)
			authCode = "";
		this.authCode = authCode;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		if (tokenType == null)
			tokenType = "";
		this.tokenType = tokenType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
