package com.fdmgroup.OneDayProject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;



@Entity
@SequenceGenerator(name = "my_seq_1", sequenceName = "MY_SEQ_1", allocationSize = 1)
public class UserAccount implements Serializable {

	@GeneratedValue(generator = "my_seq_1", strategy = GenerationType.AUTO)
	@Id
	private long userID;
	private String firstName;
	private String lastName;

	@OneToMany(mappedBy = "userAcount",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<Coupon> listOfCoupons = new ArrayList<Coupon>();
	

	public List<Coupon> addCoupon(Coupon coupon) {
		//add coupon to the list
		listOfCoupons.add(coupon);
		return listOfCoupons;
	}
	
	//constructors
	public UserAccount() {
		super();
	}

	public UserAccount(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	//setters and getters
	public List<Coupon> getListOfCoupons() {
		return listOfCoupons;
	}

	public void setListOfCoupons(ArrayList<Coupon> listOfCoupons) {
		this.listOfCoupons = listOfCoupons;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
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
	
	

}
