package com.donation.DonationMakeDifference;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

public class User implements Serializable  {
	private int id;
	private String name;
	private String email;
	private String password;
	private Payment payment;
	private Donation donation;
	private int applicationCount;
	private DonationApplication application;
	private ArrayList<User> userdata;
	private ArrayList<Donation> donationList;
	
	public User(String name, String email, String password) {
		Random rand = new Random();
		id = rand.nextInt(99999999) + 100000000;
		setId(id);
		setName(name);
		setEmail(email);
		setPassword(password);
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public boolean registerAccount(User user){
		try
        {   if (loadUserData()==null) {
        	userdata = new ArrayList<User>();
        }
        else {
        	userdata = loadUserData();
        }
			userdata.add(user);
			FileOutputStream file = new FileOutputStream("user");
            ObjectOutputStream out = new ObjectOutputStream(file); 
            out.writeObject(userdata); 
            out.close(); 
            file.close(); 
            System.out.println("Object has been serialized"); 
            return true;
  
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
        } 
		return false;
	}
	
	public Boolean logIn(String email, String password) {
		userdata = loadUserData();
		for (User user : userdata) { 	
	           if(user.getEmail().equals(email) && user.getPassword().equals(password)) {
	        	   return true;
	           }
	      }
		return false;
		
	}
	
	public void displayDonationList(ArrayList<Donation> arrayList1) {
		int i=1;
		donationList=loadDonationData();
		for (Donation donation : donationList) { 		      
			System.out.println(i+" "+donation.getDonationTitle());
	    }
	}
	
	
	public ArrayList<User> loadUserData() {
		ArrayList<User> users = null;
        try        {              
            FileInputStream file = new FileInputStream("user"); 
            ObjectInputStream in = new ObjectInputStream(file);                           // Method for deserialization of object 
            users = (ArrayList<User>)in.readObject(); 
            in.close(); 
            file.close(); 
            System.out.println("Object has been deserialized "); 
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
        } 
          
        catch(ClassNotFoundException ex) 
        { 
            System.out.println("ClassNotFoundException is caught"); 
        } 
        return users;
	}
	
	public ArrayList<Donation> loadDonationData() {
		ArrayList<Donation> donationlist = null;
        try        {              
            FileInputStream file = new FileInputStream("donation"); 
            ObjectInputStream in = new ObjectInputStream(file);                           // Method for deserialization of object 
            donationlist = (ArrayList<Donation>)in.readObject(); 
              
            in.close(); 
            file.close(); 
            System.out.println("Object has been deserialized "); 
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
        } 
          
        catch(ClassNotFoundException ex) 
        { 
            System.out.println("ClassNotFoundException is caught"); 
        } 
        return donationlist;
	}
	public String checkDonationDetail(Donation donationinfo) {
		if (loadDonationData()==null) {
			return null;
		}
		else {
			donationList = loadDonationData();
			for (Donation donation : donationList) { 		      
				if(donationinfo == donation) {
					return donation.getDonationDescription()
				}
		    }
			
		}
	}
	
	
//	public abstract boolean linkCard(Payment payment) {
//		
//	}
//	public abstract String donate(int amount);
	
	public void submitApplication() {
		Scanner input = new Scanner(System.in);
		application = new DonationApplication(id,email,name);
		System.out.println("Please enter your valid government issued id: ");
		String govid = input.nextLine();
		System.out.println("Please enter donation title: ");
		String title = input.nextLine();
		System.out.println("Please enter donation Reason: ");
		String reason = input.nextLine();
		System.out.println("Please enter donation amount: ");
		String amount = input.nextLine();
		application.setInformation(govid, title, reason, amount);
		applicationCount+=1;
		application.saveApplication(application);
		System.out.println("Submit Successfully");
		input.close();
	}
	
	
	
	
}
