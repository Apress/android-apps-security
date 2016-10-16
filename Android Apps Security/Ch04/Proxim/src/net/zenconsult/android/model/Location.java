package net.zenconsult.android.model;

public class Location {
	private String identifier;
	private double latitude;
	private double longitude;

	public Location() {

	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
	}

	public String toString() {
		StringBuilder ret = new StringBuilder();
		ret.append(getIdentifier());
		ret.append(String.valueOf(getLatitude()));
		ret.append(String.valueOf(getLongitude()));
		return ret.toString();
	}

	public byte[] getBytes() {
		return toString().getBytes();
	}

}
