package com.e.zhongjieruan.donationmakedifference;


/**
 * This class implement the Facade design pattern.
 * When user click the donate button on the app
 * DonationFacade object will be created which will
 * call three separate methods from class Payment, Donation, and User.
 */
public class DonationFacade {
    private int amountDonate;
    private int donationRequired;
    Payment payment;
    Donation donation;
    User user;

    /**
     * This constructor Initialize the amount of money user donated and the total
     * amount of money the donation required,and initialize payment, donation, user objects
     * @param cardName Name appear on credit card
     * @param cardNumber credit card number
     * @param cardType credit card type
     * @param amountDonate amount of money user donated
     * @param donationAmount amount of money a donation required
     * @param user information of the user who donate
     */
    public DonationFacade(String cardName, int cardNumber, String cardType, int amountDonate, int donationAmount, User user) {
        this.amountDonate = amountDonate;
        this.donationRequired = donationAmount;
        this.user=user;
        payment = new Payment(cardName,cardType,cardNumber);
        donation = new DonationSpecialUser(donationAmount);
    }

    /**
     * This method is called when user hit the donation button
     * it checks whether the credit card information is valid and
     * whether the amount of money user donated exceed the amount of
     * money a donation required
     * @return This returns a string indicates whether donation is success
     */
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

    /**
     * This method will update the newer amount of money a donation
     * needed after receive a donation.
     * @return Returns how much money need to be received in the future
     */
    public int updateNewAmount(){
        return donationRequired -amountDonate;
    }
}
