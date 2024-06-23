package com.example.pharmacymanagement;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Supplier;
import utils.DBConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListOfSuppliers implements Initializable {

    @FXML
    public TableColumn<Supplier, String> nameColumn;
    @FXML
    public TableColumn<Supplier, String> emailColumn;
    @FXML
    public TableColumn<Supplier, String> categoryColumn;
    @FXML
    public TableColumn<Supplier, String> contactColumn;
    @FXML
    public TableColumn<Supplier, String> addressColumn;
    @FXML
    public TableColumn<Supplier, String> regionColumn;
    @FXML
    public TableColumn<Supplier, String> cityColumn;
    @FXML
    public TableColumn<Supplier, String> postalCodeColumn;
    @FXML
    public TableColumn<Supplier,String> countryColumn;
    @FXML
    public TableColumn<Supplier, String > dateColumn;
    @FXML
    public TableColumn<Supplier,String> paymentTermColumn;
    @FXML
    public TableColumn<Supplier,String> websiteColumn;
    @FXML
    private TextField filterValue;
    @FXML
    private TableView<Supplier> supplierTableView;

    @FXML
    private TextField customer2;

    private  List<Supplier> supplierListsValues = new ArrayList<>(); ;
    private ObservableList<Supplier>  supplierObservableList;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        supplierListsValues.clear();
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        categoryColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSupplyCategories()));
        contactColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        addressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        regionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRegion()));
        cityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCity()));
        postalCodeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPostalCode()));
        countryColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCity()));
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDateAdded()));
        paymentTermColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPaymentTerms()));
        websiteColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getWebsite()));


        try {

            Connection connectDB = DBConnection.getConnection();
            Statement statement = connectDB.createStatement();
            ResultSet result = statement.executeQuery("Select * from supplier order by supplierID desc ");

            while (result.next()){
                supplierListsValues.add(
                        new Supplier(
                                result.getInt("supplierID"),
                                result.getString("name"),
                                result.getString("phoneNumber"),
                                result.getString("email"),
                                result.getString("address"),
                                result.getString("postalCode"),
                                result.getString("region"),
                                result.getString("city"),
                                result.getString("country"),
                                result.getString("website"),
                                result.getString("supplyCategories"),
                                result.getString("paymentTerms"),
                                result.getString("dateAdded")
                        )
                );

            }
            result.close();
            statement.close();
            connectDB.close();

            supplierObservableList  = FXCollections.observableArrayList(supplierListsValues);

            supplierTableView.getItems().setAll(supplierObservableList);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void onFilter(ActionEvent actionEvent) {
        supplierListsValues.clear();
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        categoryColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSupplyCategories()));
        contactColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        addressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        regionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRegion()));
        cityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCity()));
        postalCodeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPostalCode()));
        countryColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCity()));
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDateAdded()));
        paymentTermColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPaymentTerms()));
        websiteColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getWebsite()));


        try {

            Connection connectDB = DBConnection.getConnection();
            PreparedStatement preparedStatement = connectDB.prepareStatement("Select * from supplier where supplyCategories=?");
            preparedStatement.setString(1, filterValue.getText());

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()){
                supplierListsValues.add(
                        new Supplier(
                                result.getInt("supplierID"),
                                result.getString("name"),
                                result.getString("phoneNumber"),
                                result.getString("email"),
                                result.getString("address"),
                                result.getString("postalCode"),
                                result.getString("region"),
                                result.getString("city"),
                                result.getString("country"),
                                result.getString("website"),
                                result.getString("supplyCategories"),
                                result.getString("paymentTerms"),
                                result.getString("dateAdded")
                        )
                );

            }
            result.close();
            preparedStatement.close();
            connectDB.close();

            supplierObservableList  = FXCollections.observableArrayList(supplierListsValues);

            supplierTableView.getItems().setAll(supplierObservableList);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
