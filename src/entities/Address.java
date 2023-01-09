package entities;

public class Address {
	
	private String zipCode;
	private String street;
	private String district;
	private String city;
	private Integer number;
	
	public Address(String zipCode, String street, String district, String city, Integer number) {
		this.zipCode = zipCode;
		this.street = street;
		this.district = district;
		this.city = city;
		this.number = number;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
}
