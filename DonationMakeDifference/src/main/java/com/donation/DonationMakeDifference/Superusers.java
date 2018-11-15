package com.donation.DonationMakeDifference;

public abstract class Superusers extends User {
	public Boolean postDonation(Donation donationInfo) {
		return true;
	}
	public abstract Boolean deletePost(Donation donationInfo);
	public abstract void editDonation(Donation donation);
}
