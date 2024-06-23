package com.example.pharmacymanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import  javafx.scene.control.TextField;
import javafx.scene.control.*;
import utils.DBConnection;
import utils.RemvoeSpacesInString;
import utils.Validator;

import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class AddProductController {

@FXML
private TextField genericName;
@FXML
    private TextField nameByBrand;
@FXML
    private TextField unitPrice;
@FXML
    private TextField quantity;

@FXML
    private TextField dosageForm;

@FXML
    private DatePicker expireDate;

@FXML
    private TextField locationName;
@FXML
    private TextField manufacture;
@FXML
    private TextArea prescriptionRequirement;
@FXML
    private TextArea sideEffect;
@FXML
    private TextArea storageInstruction;
@FXML
    private TextField supplierName;
@FXML
    private TextField supplierEmail;
@FXML
    private TextArea contrainDictions;

@FXML
    private  TextArea catgory;
@FXML
    private TextArea interation;

@FXML
        private TextArea medicineDescription;
@FXML
        private TextField medicineStrenght;

    String genericNameValue ;
    String nameByBrandValue;
    String unitPriceValue;
    String quantityValue;
    String dosageFormValue;
    LocalDate expireDateValue;
    String storageInstructionValue;
    String locationValue;
    String catgoryValue;
    String contrainDictionsvalue;
    String  prescriptionRequirementValue;
    String sideEffectValue;
    String interationValue;
    String supplierEmailVaulue;
    String supplierNameValue;
    String manufactureValue;
    String medicineDescriptionVAlue;
    String medicineStrenghtValue;

//    public AddProduct(TextField manufacture) {
//        this.manufacture = manufacture;
//    }


    @FXML
    void onAddDrug(ActionEvent event) {
        genericNameValue = genericName.getText();
        nameByBrandValue = nameByBrand.getText();
        unitPriceValue = unitPrice.getText();
        quantityValue = quantity.getText();
        dosageFormValue = dosageForm.getText();
        expireDateValue = expireDate.getValue();
        storageInstructionValue = storageInstruction.getText();
         locationValue = locationName.getText();
        catgoryValue = catgory.getText();
        contrainDictionsvalue = contrainDictions.getText();
        prescriptionRequirementValue = prescriptionRequirement.getText();
        sideEffectValue = sideEffect.getText();
        interationValue = interation.getText();
        supplierEmailVaulue = supplierEmail.getText();
        supplierNameValue = supplierName.getText();
        manufactureValue = manufacture.getText();
         medicineDescriptionVAlue = medicineDescription.getText();
         medicineStrenghtValue = medicineStrenght.getText();


            try{
                medicineTextFieldValidator();
               // medicineDescriptionFieldValidator();
                String genericNameValueT = RemvoeSpacesInString.remove(genericNameValue);
                String nameByBrandT = RemvoeSpacesInString.remove(nameByBrandValue);

                // check if generic name and nameByBrandValue of the medicine description table exit in the database


                        CompletableFuture<ResultSet> getMedicines = CompletableFuture.supplyAsync(()->{

                            try {
                                Connection connectDB = DBConnection.getConnection();
                                PreparedStatement preparedStatement = connectDB.prepareStatement("SELECT * FROM medicine WHERE genericName = ? AND nameByBrand = ?");
                                preparedStatement.setString(1, genericNameValueT);
                                preparedStatement.setString(2, nameByBrandT);
                               return preparedStatement.executeQuery();
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }

                        });

                    CompletableFuture<Void> saveMedicineDrugInDB = CompletableFuture.runAsync(()->{
                        try {
                            Connection connectSave = DBConnection.getConnection();
                            PreparedStatement preparedStatement1 = connectSave.prepareStatement("INSERT INTO medicine (nameByBrand, genericName, quantity, timeStamps) values (?,?,?,?)");
                            preparedStatement1.setString(1,RemvoeSpacesInString.remove(nameByBrandValue.toLowerCase().trim()));
                            preparedStatement1.setString(2, RemvoeSpacesInString.remove(genericNameValue.toLowerCase().trim()));
                            preparedStatement1.setInt(3, Integer.parseInt(quantityValue));
                            preparedStatement1.setDate(4,Date.valueOf(expireDateValue));
                            preparedStatement1.executeUpdate();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    });



                    CompletableFuture<ResultSet> saveMedicineDecDrugInDB = getMedicines.thenCompose((s)->{

                        try {
                            if (!s.next()){
                                //ensure user include descriptions values
                                medicineDescriptionFieldValidator();
                            }
                            Connection connectSave = DBConnection.getConnection();
                            PreparedStatement preparedStatement1 = connectSave.prepareStatement("INSERT INTO medicinedescriptions (nameByBrand, genericName, description," +
                                    " DosageForm, strenght, location, manufacture, prescriptionRequirement, storageInstruction, sideEffects,contrainDictions, category, interaction) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
                            preparedStatement1.setString(1,RemvoeSpacesInString.remove(nameByBrandValue.toLowerCase().trim()));
                            preparedStatement1.setString(2, RemvoeSpacesInString.remove(genericNameValue.toLowerCase().trim()));
                            preparedStatement1.setString(3, RemvoeSpacesInString.remove(medicineDescriptionVAlue.trim().toLowerCase()));
                            preparedStatement1.setString(4, RemvoeSpacesInString.remove(dosageFormValue.trim().toLowerCase()));
                            preparedStatement1.setString(5, RemvoeSpacesInString.remove(medicineStrenghtValue.toLowerCase().trim()));
                            preparedStatement1.setString(6, RemvoeSpacesInString.remove(locationValue.toLowerCase().trim()));
                            preparedStatement1.setString(7, RemvoeSpacesInString.remove(manufactureValue.toLowerCase().trim()));
                            preparedStatement1.setString(8, RemvoeSpacesInString.remove(prescriptionRequirementValue.toLowerCase().trim()));
                            preparedStatement1.setString(9, RemvoeSpacesInString.remove(storageInstructionValue.toLowerCase().trim()));
                            preparedStatement1.setString(10, RemvoeSpacesInString.remove(sideEffectValue.toLowerCase().trim()));
                            preparedStatement1.setString(11, RemvoeSpacesInString.remove(contrainDictionsvalue.trim().toLowerCase()));
                            preparedStatement1.setString(12, RemvoeSpacesInString.remove(catgoryValue.trim().toLowerCase()));
                            preparedStatement1.setString(13, RemvoeSpacesInString.remove(interationValue.trim().toLowerCase()));

                            preparedStatement1.executeUpdate();

                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        return null;
                    });

               // Statement statement = connectDB.createStatement();

            }catch (Exception e){
                    e.printStackTrace();
            }
    }



    public void alertUser(TextField textfield,String textType){
        Alert alert = new Alert( Alert.AlertType.WARNING ,textType +" field should not be empty");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            textfield.requestFocus();
        }
    }

    public void alertUserTextAreaError(TextArea textfield,String textType){
        Alert alert = new Alert( Alert.AlertType.WARNING ,textType +" field should not be empty");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            textfield.requestFocus();
        }
    }


    public void medicineTextFieldValidator(){
        if (genericNameValue.isEmpty()){
            alertUser(genericName, "Generic ");

        }else if (nameByBrandValue.isEmpty()){
            alertUser(nameByBrand,"Name By Brand");

        }
        else if (quantityValue.isEmpty()){
            alertUser(quantity, "Quantity");

        }

        else if (supplierNameValue.isEmpty()){
            alertUser(supplierName, "Supplier Name");

        }else if (supplierEmailVaulue.isEmpty()){
            alertUser(supplierEmail,"Supplier Email");
        }
        else if(!Validator.emailValidator(supplierEmailVaulue)) {
            alertUser(supplierEmail, "only valid emails are allowed");


        }
        else if(expireDateValue == null){
            Alert alert = new Alert( Alert.AlertType.WARNING , "Date Field field should not be empty");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                expireDate.requestFocus();

            }
        }
    }

    public void medicineDescriptionFieldValidator(){

        // make enquire to check if brand is mandatory
//        else if(brand.isEmpty()){
//            alertUser(email, "Email");
//
//        }
        if(medicineDescriptionVAlue.isEmpty()) {
            alertUserTextAreaError(medicineDescription, "medicine description ");
        } else if(dosageFormValue.isEmpty()){
            alertUser(dosageForm, "DosabeForm ");
        }
        else if(locationValue.isEmpty()){
            alertUser(locationName, "Medicine location ");
        }
        else if(prescriptionRequirementValue.isEmpty()){
            alertUserTextAreaError(prescriptionRequirement, "Medicien strenght");

        }else if(storageInstructionValue.isEmpty()){
            alertUserTextAreaError(storageInstruction, "Medicien storage instruction");

        }
        else if(sideEffectValue.isEmpty()){
            alertUserTextAreaError(sideEffect, "Mediciene side effects");

        }

        else if(catgoryValue.isEmpty()){
            alertUserTextAreaError(catgory, "Category ");

        }
    }

}
