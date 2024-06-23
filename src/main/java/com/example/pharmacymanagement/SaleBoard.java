package com.example.pharmacymanagement;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Medicine;
import model.Purchases;
import store.LoginUser;
import utils.DBConnection;
import utils.Validator;

import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

public class SaleBoard implements Initializable {
    @FXML
    public TextField customerName;
    @FXML
    public TextField genericName;
    @FXML
    public TextField nameByBrand;
    @FXML
    public TextField quantity;
    @FXML
    public Button buyButton;
    @FXML
    public Label genericNameDoc;
    @FXML
    public Label descriptionDoc;
    @FXML
    public Label presriptionRequirementDoc;
    @FXML
    public Label storageInstructionDoc;
    @FXML
    public Label sideEffectDoc;
    @FXML
    public Label contrainDictionsDoc;
    @FXML
    public Label interactionDoc;
    @FXML
    public TableColumn<Purchases, String> tableMedicineName;
    @FXML
    public TableColumn<Purchases, String > tableMedicineNameByBrand;
    @FXML
    public TableColumn<Purchases, String> tableCustomerName;
    @FXML
    public TableColumn<Purchases, String> tableQuantity;
    @FXML
    public TableColumn<Purchases, String > tableTotalPrice;
    @FXML
    public TableColumn<Purchases, String > tableTimeStamp;
    public TableView<Purchases> recentSaleTable;

    private ObservableList recentSaleObservableList;
    private List<Purchases> recentSaleList = new ArrayList<>();

    String genericNameValue;
    String nameByBrandValue;
    String quantityValue;
    String customerNameValue;
    LoginUser pharmacist = LoginUser.getCreateInstance();


    @FXML
    public void onBuy(ActionEvent actionEvent) {
        genericNameValue = genericName.getText();
        nameByBrandValue = nameByBrand.getText();
        quantityValue = quantity.getText();
        customerNameValue = customerName.getText();

        try{

            saleTextFieldValidator();
//
//            CompletableFuture<ResultSet> ph_md_value = CompletableFuture.supplyAsync(()->{
//
//            });
            try{
                //save customr to customer table
                CompletableFuture<Void> saveCustomer = CompletableFuture.runAsync(()->{
                  try{
                      Connection connectionDB1 = DBConnection.getConnection();
                      PreparedStatement preparedStatement1 = connectionDB1.prepareStatement("INSERT INTO customer (name, timeStamps) values (?,?)");
                      preparedStatement1.setString(1, customerNameValue);
                      preparedStatement1.setString(2, LocalDateTime.now().toString());

                      preparedStatement1.executeUpdate();

                      preparedStatement1.close();
                      connectionDB1.close();

                  }catch (SQLException e){
                      throw new RuntimeException(e);
                  }

                });

                CompletableFuture<Void> savePurchase = CompletableFuture.runAsync(()->{
                    /// if you have time verify whether the medicine exit first !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    try{
                        Connection connectDB1 = DBConnection.getConnection();
                        PreparedStatement preparedStatement1 = connectDB1.prepareStatement("INSERT INTO purchases  (quantity,totalPrice,customerName,medicineNameByBrand, pharmacistEmail, pharmacistName, timeStamp, medicineGenericName ) VALUES (?,?,?,?,?,?,?,?)");
                        preparedStatement1.setString(1,quantityValue);
                        preparedStatement1.setFloat(2,50);
                        preparedStatement1.setString(3, customerNameValue);
                        preparedStatement1.setString(4,nameByBrandValue);
                        preparedStatement1.setString(5, pharmacist.getUserEmail() );
                        preparedStatement1.setString(6, pharmacist.getUserName());
                        preparedStatement1.setString(7, LocalDateTime.now().toString());
                       preparedStatement1.setString(8, genericNameValue);
                        preparedStatement1.executeUpdate();

                        preparedStatement1.close();
                        connectDB1.close();

                        fetchData();

                    }catch(SQLException e){
                        throw new RuntimeException(e);
                    }
                });

            }catch(Exception e){
                e.printStackTrace();
            }

        }
        catch(Exception e){

        }

    }


    public void saleTextFieldValidator(){
        if (genericNameValue.isEmpty()){
            alertUser(genericName, "Generic ");

        }else if (nameByBrandValue.isEmpty()){
            alertUser(nameByBrand,"Name By Brand");

        }
        else if (quantityValue.isEmpty()){
            alertUser(quantity, "Quantity");

        }
        else if (customerNameValue.isEmpty()){
            alertUser(customerName, "Customer Name");
        }

    }


    public void alertUser(TextField textfield,String textType){
        Alert alert = new Alert( Alert.AlertType.WARNING ,textType +" field should not be empty");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            textfield.requestFocus();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
           fetchData();
    }

    private void fetchData(){
        recentSaleList.clear();


        tableMedicineName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMedicineGenericName()));
        tableMedicineNameByBrand.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMedicineNameByBrand()));
        tableCustomerName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCustomerName()));
        tableQuantity.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuantity()));
        tableTotalPrice.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTotalPrice()));
        tableTimeStamp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTimeStamp()));

        try{
            CompletableFuture<ResultSet> rescentSales = CompletableFuture.supplyAsync(()->{
                try{
                    Connection connectDB = DBConnection.getConnection();
                    Statement statement = connectDB.createStatement();
                    return statement.executeQuery("SELECT * FROM Purchases ORDER BY purchaseId desc");

                }catch(SQLException e){
                    throw new RuntimeException(e);
                }
            });
            ResultSet res = rescentSales.get();
            while (res.next()){
                recentSaleList.add(
                        new Purchases(
                                res.getString("quantity"),
                                res.getString("totalPrice"),
                                res.getString("CustomerName"),
                                res.getString("medicineGenericName"),
                                res.getString("medicinenameByBrand"),
                                res.getString("pharmacistName"),
                                res.getString("pharmacistName"),
                                res.getString("timeStamp")
                        )
                );
            }

            recentSaleObservableList = FXCollections.observableArrayList(recentSaleList);

            recentSaleTable.getItems().setAll(recentSaleObservableList);


            // displaying the products descriptions
            CompletableFuture<ResultSet> medicineDescriptionResults = CompletableFuture.supplyAsync(()->{
                try{
                    Connection connectDB = DBConnection.getConnection();
                    PreparedStatement preparedStatement2 = connectDB.prepareStatement("select * from medicinedescriptions where nameByBrand=? AND genericName=?");

                    preparedStatement2.setString(1, nameByBrandValue);
                    preparedStatement2.setString(2, genericNameValue);

                    return preparedStatement2.executeQuery();

                }catch(SQLException e){
                    throw new RuntimeException(e);
                }
            });

            ResultSet mdDescriptionRts = medicineDescriptionResults.get();

            if (mdDescriptionRts.next()){
//                System.out.println(  res.getString("quantity"),);
                Platform.runLater(()->{

                    try {
                        genericName.setText(mdDescriptionRts.getString("genericName"));
                        presriptionRequirementDoc.setText(mdDescriptionRts.getString("prescriptionRequirement"));
                        descriptionDoc.setText(mdDescriptionRts.getString("description"));
                        storageInstructionDoc.setText(mdDescriptionRts.getString("storageInstruction"));
                        sideEffectDoc.setText(mdDescriptionRts.getString("sideEffects"));
                        contrainDictionsDoc.setText(mdDescriptionRts.getString("contrainDictions"));
                        interactionDoc.setText(mdDescriptionRts.getString("interaction"));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                });
                System.out.println(mdDescriptionRts.getString("genericName"));
            }



        }catch (Exception e){

        }
    }
}
