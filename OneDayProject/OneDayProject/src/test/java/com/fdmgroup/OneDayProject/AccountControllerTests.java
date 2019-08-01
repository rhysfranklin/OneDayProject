package com.fdmgroup.OneDayProject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.Test;

public class AccountControllerTests {

	@Test
	public void testThatWhenTheListSizeMethodIsCalledOntheCouponListItReturns1() {

		UserAccount account = new UserAccount("Rhys", "Franklin");
		Coupon coupon = new Coupon("GBP", 10.00, 3, account);
		account.addCoupon(coupon);

		int size = account.getListOfCoupons().size();
		assertEquals(1, size);
	}

	@Test
	public void testThatWhenTheListSizeMethodIsCalledOntheCouponListItReturns2() {

		UserAccount account = new UserAccount("Rhys", "Franklin");
		Coupon coupon = new Coupon("GBP", 10.00, 3, account);
		Coupon coupon2 = new Coupon("GBP", 10.00, 3, account);
		account.addCoupon(coupon);
		account.addCoupon(coupon2);

		int size = account.getListOfCoupons().size();
		assertEquals(2, size);
	}

	@Test
	public void testThatWhenTheCouponIsUsedTheQuantityDecreasesByOne() {

		UserAccount account = new UserAccount("Rhys", "Franklin");
		Coupon coupon = new Coupon("GBP", 10.00, 3, account);
		Coupon coupon2 = new Coupon("GBP",10.00, 3, account);
		account.addCoupon(coupon);
		account.addCoupon(coupon2);

		account.getListOfCoupons().get(1).setQuantity(2);
		int quantity = coupon2.getQuantity();

		assertEquals(2, quantity);
	}

	@Test
	public void testToSeeIfTwoIdenticalCouponsAreNotEqualToEachOther() {

		UserAccount account = new UserAccount("Rhys", "Franklin");
		Coupon coupon = new Coupon("GBP", 10.00, 3, account);
		Coupon coupon2 = new Coupon("GBP", 10.00, 3, account);

		assertNotEquals(coupon, coupon2);
	}
	
	@Test
	public void testToSeeIfTwoIdenticalUsersAreNotEqualToEachOther() {

		UserAccount account = new UserAccount("Rhys", "Franklin");
		Coupon coupon = new Coupon("GBP", 10.00, 3, account);
		account.addCoupon(coupon);
		
		UserAccount account2 = new UserAccount("Rhys", "Franklin");
		Coupon coupon2 = new Coupon("GBP", 10.00, 3, account);
		account2.addCoupon(coupon2);

		assertNotEquals(coupon, coupon2);
	}

}
