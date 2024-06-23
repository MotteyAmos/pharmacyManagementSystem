package com.example.pharmacymanagement;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import store.LoginUser;
import utils.DBConnection;
import utils.Validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class Login {
    @FXML
    public TextField emailI;
    @FXML
    public TextField password;
    @FXML
    public Button loginBtn;
    @FXML
    public Label header;
    @FXML
    public StackPane mainWorkingArea;


    public void onLgonBtn(ActionEvent actionEvent) {

        String emailValue = emailI.getText();
        String passwordVale = password.getText();

        // validate the text fields
        if (emailValue.isEmpty()){
            alertUser(emailI, "Email");
            return;
        }else if (!Validator.emailValidator(emailValue)){
            alertUser(emailI, "Provide a valid email and email");
            return;
        }else if (passwordVale.isEmpty()){
            alertUser(password, "password");
            return;
        }

        try{
            CompletableFuture<ResultSet> userExit = CompletableFuture.supplyAsync(()->{
                try{
                    Connection connectDB = DBConnection.getConnection();
                    PreparedStatement preparedStatement = connectDB.prepareStatement("SELECT * FROM pharmacist WHERE email=? AND password=? ");
                    preparedStatement.setString(1,emailValue);
                    preparedStatement.setString(2, passwordVale);
                    return preparedStatement.executeQuery();
                }catch(SQLException e){
                    throw new RuntimeException(e);
                }
            });

            userExit.thenAccept((s)->{
               System.out.println("found iii");
               try{
                   if (!userExit.get().next()){

                       Platform.runLater(()-> {
                           Alert alert = new Alert( Alert.AlertType.WARNING ,"Login details invalid" );
                           Optional<ButtonType> result = alert.showAndWait();
                           if (result.isPresent() && result.get() == ButtonType.OK) {
                               emailI.requestFocus();

                           }
                       });
                       return;
                       //header.setText("Login Details invalid");

                   }
                   else{
                       Connection connectDB = DBConnection.getConnection();
                       PreparedStatement preparedStatement = connectDB.prepareStatement("update pharmacist set loginStatus ='login' where email=?");
                       preparedStatement.setString(1, emailValue);
                       preparedStatement.executeUpdate();

                       preparedStatement.close();
                       connectDB.close();

                       LoginUser userInfo = LoginUser.createInstance;
                       userInfo.setUserEmail(userExit.get().getString("email"));
                       userInfo.setUserName(userExit.get().getString("name"));


                       System.out.println(userInfo.getUserEmail() + userInfo.getUserName());

                       Platform.runLater(()->{
                           /// navigate to main dashboard
                           FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainDashBoard.fxml"));

                           try{
                               mainWorkingArea.getChildren().remove(0);
                               mainWorkingArea.getChildren().add(fxmlLoader.load());

                           }catch(Exception e){
                               throw new RuntimeException(e);
                           }
                       });

                   }
               }catch(Exception e){
                   throw new RuntimeException(e);
               }
            });


        }catch(Exception e){
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
