package com.example.pharmacymanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainDashBoard  {

    @FXML
    public BorderPane rootBoard;



    public void onNavigateToMakeSale(ActionEvent actionEvent) {
        loadIntoMainSection("SaleBoard.fxml");

    }

    public void onNavigateToPurchaseHistory(ActionEvent actionEvent)
        {
            loadIntoMainSection("ListOfPurchases.fxml");

        }

    private  void loadIntoMainSection(String url) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(url));

        try{
             rootBoard.setCenter(fxmlLoader.load());
        }catch(Exception e){
                e.printStackTrace();
        }
    }


    public void onAddMedicine(ActionEvent actionEvent) {
        loadIntoMainSection("AddProduct.fxml");

    }

    public void onListOfMedicine(ActionEvent actionEvent) {
        loadIntoMainSection("ListOfMedicines.fxml");

    }

    public void onAddEmployee(ActionEvent actionEvent) {
        loadIntoMainSection("AddPhamarcist.fxml");

    }

    public void onViewEmployees(ActionEvent actionEvent) {
        loadIntoMainSection("ListOfPharmacists.fxml");

    }

    public void onAddSupplier(ActionEvent actionEvent) {
        loadIntoMainSection("AddSupplier.fxml");

    }

    public void onNavigateToSuppliersList(ActionEvent actionEvent) {
        loadIntoMainSection("ListOfSuppliers.fxml");

    }
}
