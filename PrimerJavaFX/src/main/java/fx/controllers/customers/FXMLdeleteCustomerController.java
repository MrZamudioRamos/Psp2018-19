/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.customers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import model.Customer;
import services.CustomersServices;

/**
 * FXML Controller class
 *
 * @author Laura
 */
public class FXMLdeleteCustomerController implements Initializable {

    @FXML
    private ListView customerBox;

    public void loadCustomersList() {
        CustomersServices cs = new CustomersServices();
        List<Customer> customers = cs.getAllCustomers() ;
        if(customers!= null){
            customerBox.getItems().setAll(customers);
        }else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Could not load the customers from the database");
            alert.showAndWait();
        }
        
    }
    
    public void deleteCustomer() {
        CustomersServices cs = new CustomersServices();
        Alert alert = new Alert(AlertType.INFORMATION);
        if (customerBox.getSelectionModel().getSelectedItem() != null) {

            cs.deleteCustomer((Customer) customerBox.getSelectionModel().getSelectedItem());
            customerBox.getItems().clear();
            loadCustomersList();

            alert.setTitle("Purchase deleted");
            alert.setHeaderText(null);
            alert.setContentText("The customer was deleted successfully!");
            alert.showAndWait();
        } else {
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please, select a customer from the list.");
            alert.showAndWait();
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadCustomersList();
    }

}
