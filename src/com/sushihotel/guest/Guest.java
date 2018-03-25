package com.sushihotel.guest;

public class Guest  {
    private int guestID;
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
        int guestID,
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
        this.guestID = guestID;
        this.name = name;
        this.creditCardNo = creditCardNo;
        this.billingAddress = billingAddress;
        this.address = addresss;
        this.country = country;
        this.gender = gender;
        this.nationality = nationality;
        this.contactNo = contactNo;
        this.passportNo = passportNo;
    }

    protected void setGuestID(int guestID){
        this.guestID = guestID;
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

    protected int getGuestID() {
        return this.guestID;
    } 
    protected String getName() {
        return this.name;
    } 
    protected int getCreditCardNumber() {
        return this.creditCardNo;
    } 
    protected String getAddress() {
        return this.address;
    } 
    protected String getCountry() {
        return this.country;
    } 
    protected String getGender() {
        return this.gender;
    } 
    protected String getNationality() {
        return this.nationality;
    } 
    protected int getContactNumber() {
        return this.contactNo;
    } 
    protected String getPassportNumber() {
        return this.passportNo;
    } 
    protected String getBillingAddress () {
        return this.billingAddress;
    } 
}