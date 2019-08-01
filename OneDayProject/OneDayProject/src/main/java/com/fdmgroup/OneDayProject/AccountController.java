package com.fdmgroup.OneDayProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AccountController {

	@Autowired
	AccountRepo accountRepo;

	@Autowired
	CouponRepo couponRepo;

	public void populateDatabase() {
		
		//create user accounts in database
		UserAccount account1 = new UserAccount("Rhys", "Franklin");
		accountRepo.save(account1);
		UserAccount account2 = new UserAccount("Benjamin", "Franklin");
		accountRepo.save(account2);
		

		//create two coupons with the user assigned
		Coupon c1 = new Coupon("GBP", 10.00, 3, account1);
		Coupon c2 = new Coupon("GBP", 5.00, 3, account2);
		
		//add the coupons to the user list
		account1.addCoupon(c1);
		account2.addCoupon(c2);
		
		//save user and coupons to the database
		couponRepo.save(c1);
		couponRepo.save(c2);
		accountRepo.save(account1);
		accountRepo.save(account2);
	}

	@GetMapping("/")
	public String homePage() {
		
		return "index";
	}

	@PostMapping("/retrieve")
	public String findAccount(HttpSession session, Model model, @RequestParam("user") long userid, @RequestParam("coupon") long couponID) {

		if(session.isNew()) {
		//populate database with users and coupons
		populateDatabase();
		
		}
		
		// check whether the account id is in the database
		Boolean findUser = accountRepo.existsById(userid);
		Boolean findCoupon = couponRepo.existsById(couponID);

		// if account with that ID does exist search database for account
		if (findUser && findCoupon) {

			Optional<Coupon> coupon = couponRepo.findById(couponID);
			Coupon foundCoupon = coupon.get();
			
			Optional<UserAccount> userAccount = accountRepo.findById(userid);
			UserAccount foundAccount = userAccount.get();
			
				if (foundCoupon.getQuantity() > 1) {

					//remove one quantity from coupon
					int oldQuant = foundCoupon.getQuantity();
					int newQuant = oldQuant - 1;
					foundCoupon.setQuantity(newQuant);
					couponRepo.save(foundCoupon);
					
					//display both list of coupons and the redeemed coupon
					List<Coupon> listOfFoundCoupons = foundAccount.getListOfCoupons();
					model.addAttribute("showCoupon", foundCoupon);
					model.addAttribute("coupons", listOfFoundCoupons);
					
					return "couponSuccessful";
					
				} else {
					// if quantity is 0 remove coupon from database
					couponRepo.delete(foundCoupon);
					
					//display that the coupon has been fully redeemed and display the details of that coupon
					model.addAttribute("lastOne", "You have used your last coupon");
					model.addAttribute("showCoupon", foundCoupon);
					return "couponSuccessful";
				}
		} else {
			return "Invalid";
		}
	}

}
