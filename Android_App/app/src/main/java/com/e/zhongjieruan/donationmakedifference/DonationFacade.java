package com.e.zhongjieruan.donationmakedifference;

import android.provider.ContactsContract;

public class DonationFacade {
    private String cardName;
    private int cardNumber;
    private String cardType;
    private int amountDonate;
    private int donationAmount;
    Payment payment;
    Donation donation;
    Database database;
    User user;


    public DonationFacade(String cardName, int cardNumber, String cardType, int amountDonate, int donationAmount, User user) {
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.amountDonate = amountDonate;
        this.donationAmount = donationAmount;
        this.user=user;
        payment = new Payment(cardName,cardType,cardNumber);
        donation = new DonationSpecialUser(donationAmount);
    }

    public String donate(){
        if (payment.validateCardInfo(user)){
            if(donation.whetherAmountReached(donationAmount,amountDonate)){
                return "Success";
            }
            else{
                return "Donate too much";
            }
        }
        else{
            return "Card not valid/No Card Linked";
        }
    }
    public int updateNewAmount(){
        return donationAmount-amountDonate;
    }
}
