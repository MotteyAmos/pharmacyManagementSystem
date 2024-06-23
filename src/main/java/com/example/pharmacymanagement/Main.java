package com.example.pharmacymanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add("com/example/pharmacymanagement/css/loginStyle.css");
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

//        try{
//            DBConnection.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacymanagementsystem", "root","1234567890");
//            PreparedStatement pstmt = DBConnection.connection.prepareStatement("INSERT INTO person (firstName, lastName)  VALUES('DFDF','SDFDF')");
//
//            pstmt.executeUpdate();
//
//
//        }catch(Exception e){
//                    e.printStackTrace();
//        }
        launch();
    }
}