package com.e.zhongjieruan.donationmakedifference;

public class Payment {
    private String cardHolderName;
    private String cardType;
    private int cardNumber;

    public Payment(String cardHolderName, String cardType, int cardNumber) {
        this.cardHolderName = cardHolderName;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
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

    public boolean validateCardInfo(String cardHolderName, String cardType, int cardNumber){
        return false;
    }


}
