package com.example.pharmacymanagement;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Pharmacist;
import utils.DBConnection;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

public class ListOfPharmacists implements Initializable {
    @FXML
    public TableColumn<Pharmacist, String> name;
    @FXML
    public TableColumn<Pharmacist, String> email;
    @FXML
    public TableColumn<Pharmacist, String> role;
    @FXML
    public TableColumn<Pharmacist, String> address;
    @FXML
    public TableColumn<Pharmacist, String> phoneNumber;
    @FXML
    public TableColumn<Pharmacist, String> licenseNumber;

    @FXML
    public TextField filterName;
    @FXML
    public TableView<Pharmacist> tableView;

    private ObservableList<Pharmacist> pharmacistsObservalbleList;
    private List<Pharmacist> pharmacistList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        role.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRole()));
        address.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        phoneNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        licenseNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLicenseNumber()));

        try{
            // get  data from the database
            CompletableFuture<ResultSet> pharmacistResults = CompletableFuture.supplyAsync(()->{
                try{
                    Connection connectDB = DBConnection.getConnection();
                    Statement statement = connectDB.createStatement();
                    return statement.executeQuery("SELECT * FROM Pharmacist");

                }catch(SQLException e){
                    throw new RuntimeException(e);
                }
            });

            ResultSet results = pharmacistResults.get();
            while (results.next()){
                pharmacistList.add(
                        new Pharmacist(results.getInt("pharmacistId"),
                                results.getString("name"),
                                results.getString("role"),
                                results.getString("address"),
                                results.getString("phoneNumber"),
                                results.getString("email"),
                                results.getString("licenseNumber")
                        )
                );
            }
            results.close();

            pharmacistsObservalbleList = FXCollections.observableArrayList(pharmacistList);

            tableView.getItems().setAll(pharmacistsObservalbleList);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void onFilter(ActionEvent event){
        pharmacistList.clear();
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        role.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRole()));
        address.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        phoneNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        licenseNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLicenseNumber()));

        try{
            // get  data from the database
            CompletableFuture<ResultSet> pharmacistResults = CompletableFuture.supplyAsync(()->{
                try{
                    Connection connectDB = DBConnection.getConnection();
                    PreparedStatement preparedStatement = connectDB.prepareStatement("SELECT * FROM Pharmacist where name=?");
                    preparedStatement.setString(1, filterName.getText());

                    return preparedStatement.executeQuery();

                }catch(SQLException e){
                    throw new RuntimeException(e);
                }
            });

            ResultSet results = pharmacistResults.get();
            while (results.next()){
                pharmacistList.add(
                        new Pharmacist(results.getInt("pharmacistId"),
                                results.getString("name"),
                                results.getString("role"),
                                results.getString("address"),
                                results.getString("phoneNumber"),
                                results.getString("email"),
                                results.getString("licenseNumber")
                        )
                );
            }
            results.close();

            pharmacistsObservalbleList = FXCollections.observableArrayList(pharmacistList);

            tableView.getItems().setAll(pharmacistsObservalbleList);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
