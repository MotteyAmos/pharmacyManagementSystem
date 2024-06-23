package com.example.pharmacymanagement;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Pharmacist;
import model.Purchases;
import utils.DBConnection;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

public class ListOfPurchases implements Initializable {
    @FXML
    public TableColumn<Purchases, String> genericName;
    @FXML
    public TableColumn<Purchases, String> nameByBrand;
    @FXML
    public TableColumn<Purchases, String> seller;
    @FXML
    public TableColumn<Purchases, String> totalPrice;
    @FXML
    public TableColumn<Purchases, String> quantity;
    @FXML
    public TableColumn<Purchases, String> timeStamp;
    @FXML
    public TableColumn<Purchases, String> pharmacistEmail;
    @FXML
    public TableColumn<Purchases, String> customerName;

    @FXML
    public Button filter;
    @FXML
    public DatePicker filterDate;
    @FXML
    public TextField filterCustomerName;
    @FXML
    public TextField filterMedicineNameByBrand;
    @FXML
    public TextField filterMedicineGenericName;
    @FXML
    public TableView<Purchases> purchaseTable;


    private List<Purchases> purchaseList = new ArrayList<>();
    ObservableList<Purchases> purchasesObservableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        purchaseList.clear();
        genericName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMedicineGenericName()));
        nameByBrand.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMedicineNameByBrand()));
        seller.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPharmacistName()));
        totalPrice.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTotalPrice()));
        quantity.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuantity()));
        timeStamp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTimeStamp()));
        pharmacistEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPharmacistEmail()));
        customerName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCustomerName()));

        try{
            // get  data from the database
            CompletableFuture<ResultSet> purchasesResults = CompletableFuture.supplyAsync(()->{
                try{
                    Connection connectDB = DBConnection.getConnection();
                    Statement statement = connectDB.createStatement();
                    return statement.executeQuery("SELECT * FROM purchases");

                }catch(SQLException e){
                    throw new RuntimeException(e);
                }
            });

            ResultSet results = purchasesResults.get();
            while (results.next()){
                purchaseList.add(
                        new Purchases(results.getString("quantity"),
                                results.getString("totalPrice"),
                                results.getString("customerName"),
                                results.getString("medicineGenericName"),
                                results.getString("medicineNameByBrand"),
                                results.getString("pharmacistEmail"),
                                results.getString("pharmacistName"),
                                results.getString("timeStamp")
                        )
                );
            }
            results.close();


            purchasesObservableList = FXCollections.observableArrayList(purchaseList);

            purchaseTable.getItems().setAll(purchasesObservableList);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void onFilter(ActionEvent actionEvent) {
        purchaseList.clear();
        genericName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMedicineGenericName()));
        nameByBrand.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMedicineNameByBrand()));
        seller.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPharmacistName()));
        totalPrice.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTotalPrice()));
        quantity.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuantity()));
        timeStamp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTimeStamp()));
        pharmacistEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPharmacistEmail()));
        customerName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCustomerName()));

        try{
            // get  data from the database
            CompletableFuture<ResultSet> purchasesResults = CompletableFuture.supplyAsync(()->{
                try{
                    Connection connectDB = DBConnection.getConnection();
                    PreparedStatement preparedStatement = connectDB.prepareStatement("SELECT * FROM purchases where (medicineGenericName=? OR medicineNameByBrand=? OR timeStamp=? ) AND customerName=?  ");
                    preparedStatement.setString(1, filterMedicineGenericName.getText());
                    preparedStatement.setString(2, filterMedicineNameByBrand.getText());
                    preparedStatement.setString(4, filterCustomerName.getText());
                    preparedStatement.setString(3, filterDate.toString());
                    return preparedStatement.executeQuery();

                }catch(SQLException e){
                    throw new RuntimeException(e);
                }
            });

            ResultSet results = purchasesResults.get();
            while (results.next()){
                System.out.println( results.getString("totalPrice"));
                purchaseList.add(
                        new Purchases(results.getString("quantity"),
                                results.getString("totalPrice"),
                                results.getString("customerName"),
                                results.getString("medicineGenericName"),
                                results.getString("medicineNameByBrand"),
                                results.getString("pharmacistEmail"),
                                results.getString("pharmacistName"),
                                results.getString("timeStamp")
                        )
                );
            }
            results.close();


            purchasesObservableList = FXCollections.observableArrayList(purchaseList);

            purchaseTable.getItems().setAll(purchasesObservableList);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
