package com.example.pharmacymanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import utils.DBConnection;
import utils.Validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Optional;

public class AddPhamarcist  {

    @FXML
    public TextField phoneNumber;
    @FXML
    public TextField email;
    @FXML
    public TextField password;
    @FXML
    public TextField licenceNumber;
    @FXML
    public TextField address;
    @FXML
    public TextField role;
    @FXML
    public TextField name;

    @FXML
    public  void addPhamarcist(ActionEvent Event){
        String nameValue = name.getText();
        String roleValue = role.getText();
        String addressValue = address.getText();
        String licenceNumberValue = licenceNumber.getText();
        String passwordValue = password.getText();
        String emailValue = email.getText();
        String phoneNumberValue = phoneNumber.getText();


        if (nameValue.isEmpty()){
            alertUser(name, "name field should not be empty");
            return;
        }else if(phoneNumberValue.isEmpty()){
            alertUser(phoneNumber, "Phone Number field should not be empty");
            return;
        }
        else if(emailValue.isEmpty()){
            alertUser(email, "Email field should not be empty");
            return;
        }
        else if(!Validator.emailValidator(emailValue)) {
            alertUser(email, "only valid emails are allowed field should not be empty");
            return;

        } else if(roleValue.isEmpty()){
            alertUser(role, "role field should not be empty");
            return;
        }
        else if(addressValue.isEmpty()){
            alertUser(address, "address field should not be empty");
            return;
        }
        else if(licenceNumberValue.isEmpty()){
            alertUser(licenceNumber, "licence Number field should not be empty");
            return;
        }
        else if (passwordValue.isEmpty()){
            alertUser(password, "password field should not be empty");
            return;
        }else if (passwordValue.length() <8){
            alertUser(password, "password shold more than 8 characters");
            return;
        }

        try{
            Connection connectDB = DBConnection.getConnection();
            PreparedStatement preparedStatement = connectDB.prepareStatement("INSERT INTO pharmacist (name, role, address, email, phoneNumber, licenseNumber, password)" +
                    " VALUES (?,?,?,?,?,?,?)");

            preparedStatement.setString(1,nameValue);
            preparedStatement.setString(2,roleValue);
            preparedStatement.setString(3, addressValue);
            preparedStatement.setString(4, emailValue);
            preparedStatement.setString(5, phoneNumberValue);
            preparedStatement.setString(6,licenceNumberValue);
            preparedStatement.setString(7, passwordValue);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connectDB.close();
        }catch (Exception e){
                    e.printStackTrace();
        }
    }

    public void alertUser(TextField textfield,String textType){
        Alert alert = new Alert( Alert.AlertType.WARNING ,textType );
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            textfield.requestFocus();
        }
    }
}
