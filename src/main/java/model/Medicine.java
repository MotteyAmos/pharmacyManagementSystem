package model;

public class Medicine {


    private String nameByBrand;

    private String brand;
    private String description;
    private String dosageForm;
    private String streght;
    private String location;
    private String manufacture;
    private String prescriptionRequirement;
    private String storageInstruction;
    private String sideEffect;
    private String contrainDictions;
    private String category;
    private String interaction;
    private String expiringDate;
    private String quantity;
    private String supplierName;
    private String supplierEmail;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Medicine( String nameByBrand, String brand, String description,
                     String dosageForm, String streght, String location, String manufacture,
                    String prescriptionRequirement, String storageInstruction, String sideEffect, String contrainDictions,
                    String category, String interaction, String expiringDate, String quantity, String genericName, String supplierName,String supplierEmail)
    {
        this.supplierEmail = supplierEmail;
        this.supplierName = supplierName;
        this.nameByBrand = nameByBrand;
        this.brand = brand;
        this.description = description;
        this.dosageForm = dosageForm;
        this.streght = streght;
        this.location = location;
        this.manufacture = manufacture;
        this.prescriptionRequirement = prescriptionRequirement;
        this.storageInstruction = storageInstruction;
        this.sideEffect = sideEffect;
        this.contrainDictions = contrainDictions;
        this.category = category;
        this.interaction = interaction;
        this.expiringDate = expiringDate;
        this.quantity = quantity;
        this.genericName = genericName;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    private String genericName;



    public String getNameByBrand() {
        return nameByBrand;
    }

    public void setNameByBrand(String nameByBrand) {
        this.nameByBrand = nameByBrand;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getStreght() {
        return streght;
    }

    public void setStreght(String streght) {
        this.streght = streght;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getPrescriptionRequirement() {
        return prescriptionRequirement;
    }

    public void setPrescriptionRequirement(String prescriptionRequirement) {
        this.prescriptionRequirement = prescriptionRequirement;
    }

    public String getStorageInstruction() {
        return storageInstruction;
    }

    public void setStorageInstruction(String storageInstruction) {
        this.storageInstruction = storageInstruction;
    }

    public String getSideEffect() {
        return sideEffect;
    }

    public void setSideEffect(String sideEffect) {
        this.sideEffect = sideEffect;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContrainDictions() {
        return contrainDictions;
    }

    public void setContrainDictions(String contrainDictions) {
        this.contrainDictions = contrainDictions;
    }

    public String getInteraction() {
        return interaction;
    }

    public void setInteraction(String interaction) {
        this.interaction = interaction;
    }

    public String getExpiringDate() {
        return expiringDate;
    }

    public void setExpiringDate(String expiringDate) {
        this.expiringDate = expiringDate;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }
}
