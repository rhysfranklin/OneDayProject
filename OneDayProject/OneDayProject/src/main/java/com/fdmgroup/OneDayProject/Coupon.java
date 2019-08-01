package com.fdmgroup.OneDayProject;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;


@Entity
@SequenceGenerator(name = "my_seq_2", sequenceName = "MY_SEQ_2", allocationSize = 1)
public class Coupon implements Serializable{
	
	@GeneratedValue(generator = "my_seq_2", strategy = GenerationType.AUTO)
	@Id
	private long couponID;
	private String currency;
	private double value;
	private int quantity;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fk_userID")
    private UserAccount userAcount;


	public Coupon(String currency, double value, int quantity, UserAccount userAcount) {
		super();
		this.value = value;
		this.quantity = quantity;
		this.userAcount = userAcount;
		this.currency = currency;
	}

	public Coupon() {
		
	}

	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public long getCouponID() {
		return couponID;
	}

	public void setCouponID(long couponID) {
		this.couponID = couponID;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Coupon [couponID=" + couponID + ", currency=" + currency + ", value=" + value + ", quantity=" + quantity
				+ ", userAcount=" + userAcount + "]";
	}






	
	
		

}
