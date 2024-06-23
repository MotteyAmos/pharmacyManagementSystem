package model;
public class Supplier {
    private int supplierID;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private String region;
    private  String city;
    private String country;
    private String website;
    private String supplyCategories;
    private String paymentTerms;
    private String dateAdded;
    private String postalCode;

    public Supplier(int supplierID, String name, String phoneNumber, String email, String address,String postalCode, String region, String city, String country, String website, String supplyCategories, String paymentTerms, String dateAdded) {
        this.supplierID = supplierID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.region = region;
        this.city = city;
        this.country = country;
        this.website = website;
        this.supplyCategories = supplyCategories;
        this.paymentTerms = paymentTerms;
        this.dateAdded = dateAdded;
        this.postalCode =postalCode;
    }

    public Supplier(int supplierID, String name, String phoneNumber, String email, String address, String country, String dateAdded, String supplyCategories) {
        this.supplierID = supplierID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.country = country;
        this.dateAdded = dateAdded;
        this.supplyCategories = supplyCategories;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getSupplyCategories() {
        return supplyCategories;
    }

    public void setSupplyCategories(String supplyCategories) {
        this.supplyCategories = supplyCategories;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }
}
