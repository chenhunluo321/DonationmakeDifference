package com.e.zhongjieruan.donationmakedifference;

/**
 * This class contains user information and user related action
 */
public class User {
    private int userid;
    private String username;
    private String useremail;
    private String userpassword;
    private boolean cardLinked;
    private Payment payment;
    private Boolean canPost;
    private DonationSpecialUser donation;

    /**
     * Constructor that initialize user account information.
     * @param username username of the account
     * @param useremail email address associated with the account
     * @param userpassword password of the account
     */
    public User(String username, String useremail, String userpassword) {
        this.username = username;
        this.useremail = useremail;
        this.userpassword = userpassword;
        this.canPost = false;
        this.cardLinked=false;
        payment = new Payment();
        donation = new DonationSpecialUser();
    }

    public User(int userid, String username, String useremail, String userpassword) {
        this.userid=userid;
        this.username = username;
        this.useremail = useremail;
        this.userpassword = userpassword;
        this.canPost = false;
        this.cardLinked=false;
        payment = new Payment();
        donation = new DonationSpecialUser();
    }

    /**
     * Default constructor
     */
    public User(){}
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public Boolean getCanPost() {
        return canPost;
    }

    public void setCanPost(Boolean canPost) {
        this.canPost = canPost;
    }

    public boolean getCardLinked() {
        return cardLinked;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public DonationSpecialUser getDonation() {
        return donation;
    }

    public void setDonation(DonationSpecialUser donation) {
        this.donation = donation;
    }

    /**
     * This method check whether the information user inputed for account
     * registration is valid
     * @return return type is a boolean indicate whether registration is success
     */
    public boolean registrationVerification(){
        if (username.isEmpty() || userpassword.isEmpty() || useremail.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * This method will added payment information to user's existed account
     * @param name Credit card holder name
     * @param type Credit card type
     * @param number Credit card type
     * @return Return User object which will update the old one in database
     */
    public User addPaymentInfo(String name, String type, int number){
        payment = new Payment();
        payment.setCardHolderName(name);
        payment.setCardType(type);
        payment.setCardNumber(number);
        cardLinked=true;
        return this;
    }

    /**
     * This method will mark can post after admin prove user's application
     * when canPost attribute is true
     * user will be able to post their own donation
     * @return return type is User
     */
    public User updateUserStatus(){
        canPost = true;
        return this;
    }

    /**
     * This method is called when user post their own donation
     * The donation user posted will be binded to this user
     * @param donation2 the donation user posted
     * @return return type is User
     */
    public User linkUserPost(DonationSpecialUser donation2){
        donation = new DonationSpecialUser();
        this.donation=donation2;
        return  this;
    }


}
