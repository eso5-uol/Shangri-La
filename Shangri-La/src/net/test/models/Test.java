package net.test.models;

public class Test {
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getTtn() {
		return ttn;
	}
	public void setTtn(String ttn) {
		this.ttn = ttn;
	}
	public String getTestResult() {
		return testResult;
	}
	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}
	
	private String email;
	private String fullName;
	private int age;
	private String gender;
	private String address;
	private String postcode;
	private String ttn;
	private String testResult;
	
	public Test(String email, String fullName, int age, String gender, String address, String postcode, String ttn, String testResult) {
		super();
		this.address = address;
		this.age = age;
		this.email = email;
		this.fullName = fullName;
		this.postcode = postcode;
		this.ttn = ttn;
		this.testResult = testResult;
		
	}
	
	public Test() {}
	@Override
	public String toString() {
		return String
				.format("Test [email=%s, fullName=%s, age=%s, gender=%s, address=%s, postcode=%s, ttn=%s, testResult=%s]",
						email, fullName, age, gender, address, postcode, ttn, testResult);
	}

}
