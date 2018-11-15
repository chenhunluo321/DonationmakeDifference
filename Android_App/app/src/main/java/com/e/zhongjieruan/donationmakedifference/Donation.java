package com.e.zhongjieruan.donationmakedifference;

public abstract class Donation {
    private String donationName;
    private String donationDetail;
    private String donationAmount;

    public Boolean whetherAmountReached(){
        return false;
    }

    public void donate(){

    }

    //SpecialUser can edit the post they posted   Admin canedit any posts
    public abstract void editPost();
    public abstract void deletePost();



}
