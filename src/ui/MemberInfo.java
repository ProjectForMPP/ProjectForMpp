package ui;

import javafx.beans.property.SimpleStringProperty;

public class MemberInfo {
	private SimpleStringProperty Id = new SimpleStringProperty();
	private SimpleStringProperty firstName = new SimpleStringProperty();
	private SimpleStringProperty lastName = new SimpleStringProperty();
	private SimpleStringProperty street = new SimpleStringProperty();
	private SimpleStringProperty city = new SimpleStringProperty();
	private SimpleStringProperty state = new SimpleStringProperty();
	private SimpleStringProperty phoneNum = new SimpleStringProperty();
	
	MemberInfo(String Id, String firstName,String lastName, String street,
			String city, String state,String phoneNum){
		this.Id = new SimpleStringProperty(Id);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.street = new SimpleStringProperty(street);
		this.city = new SimpleStringProperty(city);
		this.state = new SimpleStringProperty(state);
		this.phoneNum = new SimpleStringProperty(phoneNum);
	}
	
	
	public String getId() {
		return Id.get();
	}
	public String getFirstName() {
		return firstName.get();
	}
	public String getLastName() {
		return lastName.get();
	}
	public String getStreet() {
		return street.get();
	}
	public String getCity() {
		return city.get();
	}
	public String getState() {
		return state.get();
	}
	public String getPhoneNum() {
		return phoneNum.get();
	}
	
	//Set
	public void setId(String Id) {
		this.Id.set(Id);
	}
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	public void setStreet(String street) {
		this.street.set(street);
	}
	public void setCity(String city) {
		this.city.set(city);
	}
	public void setState(String state) {
		this.state.set(state);
	}
	public void setPhoneNum(String phoneNum) {
		
		this.phoneNum.set(phoneNum);
	}
}
