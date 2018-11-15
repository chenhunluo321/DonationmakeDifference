package com.donation.DonationMakeDifference;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

public class DonationApplication {
	private int id;
	private String name;
	private String email;
	private int govId;
	private String donationTitle;
	private String donationReason;
	private int donationAmount;
	private Boolean applicationStatus;
	
	
	public DonationApplication(int id, String email, String name) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
	}
	
	public void setInformation(String govid, String title, String reason, String amount) {
		setGovId(Integer.parseInt(govid));
		setDonationTitle(title);
		setDonationReason(reason);
		setDonationAmount(Integer.parseInt(amount));
		this.applicationStatus = false;
	}
	
	public boolean saveApplication(DonationApplication application){
		try
        {    
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream(String.format("applications/%s.ser",application.donationTitle)); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            // Method for serialization of object 
            out.writeObject(application); 
              
            out.close(); 
            file.close(); 
              
            System.out.println("Object has been serialized"); 
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
        } 
		return false;
	}


	public void setGovId(int govId) {
		this.govId = govId;
	}


	public void setDonationTitle(String donationTitle) {
		this.donationTitle = donationTitle;
	}


	public void setDonationReason(String donationReason) {
		this.donationReason = donationReason;
	}


	public void setDonationAmount(int donationAmount) {
		this.donationAmount = donationAmount;
	}


	public void setApplicationStatus(Boolean applicationStatus) {
		this.applicationStatus = applicationStatus;
	}


	public Boolean CheckStatus() {
		return applicationStatus;
	}
	

}
