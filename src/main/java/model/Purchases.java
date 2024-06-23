package model;

public class Purchases {
        private String quantity;
        private String totalPrice;
        private String customerName;
        private String medicineGenericName;
        private String medicineNameByBrand;
        private String pharmacistEmail;
        private String pharmacistName;
        private String timeStamp;

    public Purchases(String quantity, String totalPrice, String customerName, String medicineGenericName, String medicineNameByBrand, String pharmacistEmail, String pharmacistName, String timeStamp) {
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.customerName = customerName;
        this.medicineGenericName = medicineGenericName;
        this.medicineNameByBrand = medicineNameByBrand;
        this.pharmacistEmail = pharmacistEmail;
        this.pharmacistName = pharmacistName;
        this.timeStamp = timeStamp;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMedicineGenericName() {
        return medicineGenericName;
    }

    public void setMedicineGenericName(String medicineGenericName) {
        this.medicineGenericName = medicineGenericName;
    }

    public String getMedicineNameByBrand() {
        return medicineNameByBrand;
    }

    public void setMedicineNameByBrand(String medicineNameByBrand) {
        this.medicineNameByBrand = medicineNameByBrand;
    }

    public String getPharmacistEmail() {
        return pharmacistEmail;
    }

    public void setPharmacistEmail(String pharmacistEmail) {
        this.pharmacistEmail = pharmacistEmail;
    }

    public String getPharmacistName() {
        return pharmacistName;
    }

    public void setPharmacistName(String pharmacistName) {
        this.pharmacistName = pharmacistName;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
