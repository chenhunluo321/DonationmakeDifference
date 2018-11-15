package com.donation.DonationMakeDifference;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	User user2 = new User("Raymond","zhongjie.com","zhongjie");
    	user2.registerAccount(user2);
    	if(user2.logIn("zhongjie.com", "zhongjie")== true) {
    		System.out.print("login successful");
    	}
    	else {
    		System.out.print("failed");
    	}
    }
}
