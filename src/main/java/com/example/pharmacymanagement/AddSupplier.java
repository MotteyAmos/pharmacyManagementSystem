package com.example.pharmacymanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.w3c.dom.Text;
import utils.DBConnection;
import utils.RemvoeSpacesInString;
import utils.Validator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.Optional;

public class AddSupplier {
    @FXML
    public TextField name;
    @FXML
    public TextField phoneNumber;
    @FXML
    public TextField email;
    @FXML
    public TextField address;
    @FXML
    public TextField region;
    @FXML
    public TextField city;
    @FXML
    public TextField postalCode;
    @FXML
    public TextField country;
    @FXML
    public TextField supplyCategory;
    @FXML
    public DatePicker date;
    @FXML
    public TextArea paymentTerm;
    @FXML
    public TextArea website;

    @FXML
    public Button button;

    @FXML
    void addSupplier(ActionEvent actionEvent) {
        button.setText("Add...");
        String nameValue = name.getText();
        String phoneNumberValue = phoneNumber.getText();
        String emailValue = email.getText();
        String addressValue = address.getText();
        String regionValue = region.getText();
        String cityValue = city.getText();
        String postalCodeValue = postalCode.getText();
        String countryValue = country.getText();
        String supplyCategoryValue = supplyCategory.getText();
        LocalDate dataValue = date.getValue();
        String paymentTermValue = paymentTerm.getText();
        String websiteValue = website.getText();

        // fields validation


       if (nameValue.isEmpty()){
           alertUser(name, "name");
           return;
       }else if(phoneNumberValue.isEmpty()){
           alertUser(phoneNumber, "Phone Number");
            return;
       }
       else if(emailValue.isEmpty()){
           alertUser(email, "Email");
            return;
       }
       else if(!Validator.emailValidator(emailValue)) {
           alertUser(email, "only valid emails are allowed");
            return;

       } else if(countryValue.isEmpty()){
           alertUser(country, "Country");
            return;
       }
       else if(supplyCategoryValue.isEmpty()){
           alertUser(supplyCategory, "Supply Category");
            return;
       }
       else if(dataValue == null){
           Alert alert = new Alert( Alert.AlertType.WARNING , "Date Field field should not be empty");
           Optional<ButtonType> result = alert.showAndWait();
           if (result.isPresent() && result.get() == ButtonType.OK) {
               date.requestFocus();
                return;
           }
       }


        try {
            Connection connectDB = DBConnection.getConnection();
            PreparedStatement preStatement = connectDB.prepareStatement(
                    "INSERT INTO supplier (name, phoneNumber, email, address, region, city, postalCode, country, website,supplyCategories, paymentTerms,dateAdded ) " +
                            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)"
            );

            preStatement.setString(1, RemvoeSpacesInString.remove(nameValue.trim().toLowerCase()));
            preStatement.setString(2,  RemvoeSpacesInString.remove(phoneNumberValue.trim().toLowerCase()));
            preStatement.setString(3, emailValue);
            preStatement.setString(4, addressValue);
            preStatement.setString(5, regionValue.trim().toLowerCase());
            preStatement.setString(6, RemvoeSpacesInString.remove(cityValue.trim().toLowerCase()));
            preStatement.setString(7, postalCodeValue);
            preStatement.setString(8, RemvoeSpacesInString.remove(countryValue.toLowerCase().trim()));
            preStatement.setString(9,websiteValue);
            preStatement.setString(10,RemvoeSpacesInString.remove(supplyCategoryValue.trim().toLowerCase()));
            preStatement.setString(11, paymentTermValue);
            preStatement.setString(12,dataValue.toString());

            preStatement.executeUpdate();

            preStatement.close();
            connectDB.close();
            //write the code to requery all suppliers
            // wrtiet the code to clear the textfield values


        }catch (Exception e){
                    e.printStackTrace();
        }

        button.setText("Add Supplier");
        name.setText("");
        phoneNumber.setText("");
        email.setText("");
        address.setText("");
        region.setText("");
        city.setText("");
        postalCode.setText("");
        country.setText("");
        supplyCategory.setText("");
        paymentTerm.setText("");
        website.setText("");


    }

    public void alertUser(TextField textfield,String textType){
        Alert alert = new Alert( Alert.AlertType.WARNING ,textType +" field should not be empty");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            textfield.requestFocus();
        }
    }
}
