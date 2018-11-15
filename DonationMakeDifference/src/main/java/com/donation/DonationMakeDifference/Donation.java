package com.donation.DonationMakeDifference;
import java.util.*;

public class Donation {
	private String donationTitle;
	private String donationDescription;
	private int donationAmount;
	
	public Donation(String donationTitle, String donationDescription, int donationAmount) {
		setDonationTitle(donationTitle);
		setDonationDescription(donationDescription);
		setDonationAmount(donationAmount);
	}
	public String getDonationTitle() {
		return donationTitle;
	}
	public void setDonationTitle(String donationTitle) {
		this.donationTitle = donationTitle;
	}
	public String getDonationDescription() {
		return donationDescription;
	}
	public void setDonationDescription(String donationDescription) {
		this.donationDescription = donationDescription;
	}
	public int getDonationAmount() {
		return donationAmount;
	}
	public void setDonationAmount(int donationAmount) {
		this.donationAmount = donationAmount;
	}
	
	
}
