package moneytransfer.model;

public class Customer {
	private int id;
	private String firstName;
	private String lastName;
	private String address;
	
	public Customer(int id, String firstName, String lastName, String address) {
		this.setAddress(address);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setId(id);
	}
	
	public Customer(){
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
