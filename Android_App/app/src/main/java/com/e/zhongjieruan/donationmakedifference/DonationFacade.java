package com.e.zhongjieruan.donationmakedifference;

public class DonationFacade {
    private int amountDonate;
    private int donationRequired;
    Payment payment;
    Donation donation;
    User user;


    public DonationFacade(String cardName, int cardNumber, String cardType, int amountDonate, int donationAmount, User user) {
        this.amountDonate = amountDonate;
        this.donationRequired = donationAmount;
        this.user=user;
        payment = new Payment(cardName,cardType,cardNumber);
        donation = new DonationSpecialUser(donationAmount);
    }

    public String donate(){
        if (payment.validateCardInfo(user)){
            if(donation.whetherAmountReached(donationRequired,amountDonate)){
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
        return donationRequired -amountDonate;
    }
}
