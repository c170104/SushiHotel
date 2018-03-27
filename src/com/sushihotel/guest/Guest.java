package com.sushihotel.guest;

import java.io.Serializable;

public class Guest implements Serializable {
    private int guestID;
    private String identificationNo;
    private String name;
    private int creditCardNo;
    private String billingAddress;
    private String address;
    private String country;
    private String gender;
    private String nationality;
    private int contactNo;
    private String passportNo;

    public Guest(
        String identificationNo,
        String name,
        int creditCardNo,
        String billingAddress,
        String address,
        String country,
        String gender,
        String nationality,
        int contactNo,
        String passportNo
    )  {
        this.identificationNo = identificationNo;
        this.name = name;
        this.creditCardNo = creditCardNo;
        this.billingAddress = billingAddress;
        this.address = address;
        this.country = country;
        this.gender = gender;
        this.nationality = nationality;
        this.contactNo = contactNo;
        this.passportNo = passportNo;
    }

    protected void setGuestID(int guestID){
        this.guestID = guestID;
    }
    protected void setIdentificationNo(String identificationNo)    {
        this.identificationNo = identificationNo;
    }
    protected void setName(String name){
        this.name = name;
    }
    protected void setCreditCardNumber(int creditCardNumber){
        this.creditCardNo = creditCardNumber;
    }
    protected void setAddress(String address){
        this.address = address;
    }
    protected void setCountry(String country){
        this.country = country;
    }
    protected void setGender(String gender){
        this.gender = gender;
    }
    protected void setNationality(String nationality){
        this.nationality = nationality;
    }
    protected void setContactNumber(int contactNumber){
        this.contactNo = contactNumber;
    }
    protected void setPassportNumber(String passportNo){
        this.passportNo = passportNo;
    }
    protected void setBillingAddress(String billingAddress){
        this.billingAddress = billingAddress;
    }

    public int getGuestID() {
        return this.guestID;
    } 
    public String getIdentificationNo()    {
        return this.identificationNo;
    }
    public String getName() {
        return this.name;
    } 
    public int getCreditCardNumber() {
        return this.creditCardNo;
    } 
    public String getAddress() {
        return this.address;
    } 
    public String getCountry() {
        return this.country;
    } 
    public String getGender() {
        return this.gender;
    } 
    public String getNationality() {
        return this.nationality;
    } 
    public int getContactNumber() {
        return this.contactNo;
    } 
    public String getPassportNumber() {
        return this.passportNo;
    } 
    public String getBillingAddress () {
        return this.billingAddress;
    } 
}