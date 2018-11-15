package com.e.zhongjieruan.donationmakedifference;

public class User {
    private int userid;
    private String username;
    private String useremail;
    private String userpassword;

    //Whether user can post donation
    private Boolean canPost;

    public User(int userid, String username, String useremail, String userpassword) {
        this.userid = userid;
        this.username = username;
        this.useremail = useremail;
        this.userpassword = userpassword;
        this.canPost = false;
    }

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

    public boolean logInVarification(){
        return false;
    }

    public boolean registrationVarification(){
        return false;
    }

    public boolean saveUserDataToDB(int userid, String username, String useremail,String userpassword){
        return false;
    }
}
