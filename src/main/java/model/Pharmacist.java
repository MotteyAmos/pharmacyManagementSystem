package model;

public class Pharmacist {

    private int pharmacistId;
    private String name;
    private String role;
    private String address;
    private String email;
    private String phoneNumber;
    private String licenseNumber;
    private String password;

    public Pharmacist(int pharmacistId,String name, String role, String address, String phoneNumber, String email, String licenseNumber) {
        this.name = name;
        this.role = role;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.licenseNumber = licenseNumber;
        this.password = password;
        this.pharmacistId = pharmacistId;
    }

    public int getPharmacistId() {
        return pharmacistId;
    }

    public void setPharmacistId(int pharmacistId) {
        this.pharmacistId = pharmacistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
