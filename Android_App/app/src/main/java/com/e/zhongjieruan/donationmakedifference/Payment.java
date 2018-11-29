package com.e.zhongjieruan.donationmakedifference;

/**
 * This class contains users' payment information and payment related method
 */
public class Payment {
    private String cardHolderName;
    private String cardType;
    private int cardNumber;

    /**
     * Constructor set payment information to user's credit card information
     * @param cardHolderName Name on the credit card
     * @param cardType credit card type
     * @param cardNumber credit card number
     */
    public Payment(String cardHolderName, String cardType, int cardNumber) {
        this.cardHolderName = cardHolderName;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
    }

    /**
     * Default constructor
     */
    public Payment() {

    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * This method is verify whether the credit card information user inputed
     * is valid or not. Because I cannot verify card info with bank
     * I will assume only specific input is valid
     * Ex(cardname: Raymond Ruan, cardtype: Visa, cardnumber: 1234567890)
     * Other inputs are treated as invalid
     * @param user User object contains user data
     * @return return type is boolean indicate whether the card is valid or not
     */
    public boolean validateCardInfo(User user){
        if(cardHolderName==null && cardType==null){
            return false;
        }
        if(cardHolderName.equals("Raymond Ruan") && cardType.equals("Visa") && cardNumber==1234567890){
            return true;
        }
        else
            return false;
    }

    /**
     * This method link credit card information to User object
     * @param user User object contains user data
     * @return return type is user which will update the old user data
     */
    public User linkCard (User user){
        return user.addPaymentInfo(cardHolderName,cardType,cardNumber);
    }
}
