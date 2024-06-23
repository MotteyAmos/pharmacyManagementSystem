package com.example.pharmacymanagement;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Medicine;
import utils.DBConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

public class ListOfMedicines implements Initializable {
    @FXML
    public TableColumn<Medicine, String> name;
    @FXML
    public TableColumn<Medicine, String> brand;
    @FXML
    public TableColumn<Medicine, String> expiringDate;
    @FXML
    public TableColumn<Medicine, String> category;
    @FXML
    public TableColumn<Medicine, String> quantity;
    @FXML
    public TableColumn<Medicine, String> manufacture;
    @FXML
    public TableColumn<Medicine, String> supplierName;

    @FXML
    public TableView<Medicine> tableView;

    private ObservableList<Medicine> medicineObservableList;
    private List<Medicine> medicineList = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        medicineList.clear();
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGenericName()));
        brand.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBrand()));
        expiringDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExpiringDate()));
        category.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory()));
        quantity.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuantity()));
        manufacture.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getManufacture()));
        supplierName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSupplierName()));

        try{
            CompletableFuture<ResultSet> requestQuery = CompletableFuture.supplyAsync(()->{
               try{
                   Connection connectDB = DBConnection.getConnection();
                   Statement statement = connectDB.createStatement();
                   return statement.executeQuery("Select medicine.nameByBrand, medicine.genericName, medicine.timeStamps, medicine.quantity, medicinedescriptions.description,\n" +
                           "       medicinedescriptions.DosageForm, medicinedescriptions.strenght, medicinedescriptions.location, medicinedescriptions.manufacture,\n" +
                           "       medicinedescriptions.prescriptionRequirement, medicinedescriptions.storageInstruction, medicinedescriptions.sideEffects,\n" +
                           "       medicinedescriptions.contrainDictions, medicinedescriptions.category,medicinedescriptions.brand, medicinedescriptions.interaction,\n" +
                           "       medicine.supplierName, medicine.supplierEmail From medicine LEFT JOIN medicinedescriptions on medicine.genericName = medicinedescriptions.genericName AND medicinedescriptions.nameByBrand = medicine.nameByBrand;");
               }catch(SQLException e){
                   throw new RuntimeException(e);
               }
            });

            ResultSet result = requestQuery.get();

            while (result.next()){
                System.out.println(result.getString("nameByBrand"));
                medicineList.add(
                        new Medicine(

                                result.getString("nameByBrand"),
                                result.getString("brand"),
                                result.getString("description"),
                                result.getString("dosageForm"),
                                result.getString("strenght"),
                                result.getString("location"),
                                result.getString("manufacture"),
                                result.getString("prescriptionRequirement"),
                                result.getString("storageInstruction"),
                                result.getString("sideEffects"),
                                result.getString("contrainDictions"),
                                result.getString("category"),
                                result.getString("interaction"),
                                result.getString("timeStamps"),
                                result.getString("quantity"),
                                result.getString("genericName"),
                                result.getString("supplierName"),
                                result.getString("supplierEmail")
                        )
                );
            }

            System.out.println(medicineList.size());
            medicineObservableList = FXCollections.observableArrayList(medicineList);
            tableView.getItems().addAll(medicineObservableList);

        }catch(Exception e){
                e.printStackTrace();
        }
    }
}
