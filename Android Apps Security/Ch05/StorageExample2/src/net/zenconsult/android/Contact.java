package net.zenconsult.android;

public class Contact {
	private String firstName;
	private String lastName;
	private String address1;
	private String address2;
	private String email;
	private String phone;

	public Contact() {

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String toString() {
		StringBuilder ret = new StringBuilder();
		ret.append(getFirstName() + "|");
		ret.append(getLastName() + "|");
		ret.append(getAddress1() + "|");
		ret.append(getAddress2() + "|");
		ret.append(getEmail() + "|");
		ret.append(getPhone() + "|");
		return ret.toString();
	}

	public byte[] getBytes() {
		return toString().getBytes();
	}
}
