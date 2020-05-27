package my.app.elasticsearch.service;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.micronaut.core.annotation.Introspected;

@Introspected 
@JsonIgnoreProperties(ignoreUnknown = true)
public class ESBuilding{
	private String id;
	private String addressStreet1;
	private String addressStreet2;
	private String city;
	private String zipPostalCode;
	private String state;
	private String bid;//building entity id
	private String location;
	private String buildingName;
	private String buildingId;
	private String siteName;

	public ESBuilding() {
	}

	public ESBuilding(String id, String buildingName, String buildingId, String siteName, String addressStreet1,
			String addressStreet2, String city, String state, String zipPostalCode, String bid, String location) {
		this.id = id;
		this.addressStreet1 = addressStreet1;
		this.addressStreet2 = addressStreet2;
		this.zipPostalCode = zipPostalCode;
		this.state = state;
		this.city = city;
		this.bid = bid;
		this.location = location;
		this.buildingName = buildingName;
		this.buildingId = buildingId;
		this.siteName = siteName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddressStreet1() {
		return addressStreet1;
	}

	public void setAddressStreet1(String addressStreet1) {
		this.addressStreet1 = addressStreet1;
	}

	public String getAddressStreet2() {
		return addressStreet2;
	}

	public void setAddressStreet2(String addressStreet2) {
		this.addressStreet2 = addressStreet2;
	}

	public String getZipPostalCode() {
		return zipPostalCode;
	}

	public void setZipPostalCode(String zipPostalCode) {
		this.zipPostalCode = zipPostalCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
}