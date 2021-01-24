package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hibernate.business.BOFactory;
import lk.ijse.hibernate.business.BOType;
import lk.ijse.hibernate.business.custom.impl.CustomerBOImpl;
import lk.ijse.hibernate.dto.CustomerDTO;
import lk.ijse.hibernate.view.tm.CustomerTM;

import java.io.IOException;
import java.util.List;


public class CustomerController {
    public AnchorPane root;
    @FXML
    private JFXTextField txtCustomerId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXButton btnSave;


    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private TableView <CustomerTM>tblCustomer;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colAddress;

    CustomerBOImpl customerBO = BOFactory.getInstance().getBO(BOType.CUSTOMER);

    public void initialize() throws Exception {
        txtCustomerId.setText(generateNewID());

        loadAllCustomer();
       colId.setCellValueFactory(new PropertyValueFactory("id"));
       colName.setCellValueFactory(new PropertyValueFactory("name"));
       colAddress.setCellValueFactory(new PropertyValueFactory("address"));

    }

    private void loadAllCustomer()   {
        List<CustomerDTO> allCustomer = null;
        try {
            allCustomer = customerBO.getAllCustomer();
            ObservableList<CustomerTM> tms = FXCollections.observableArrayList();
            for (CustomerDTO customerDTO : allCustomer) {
                CustomerTM customerTM = new CustomerTM(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress());
                tms.add(customerTM);
            }
            tblCustomer.setItems(tms);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtCustomerId.getText();
        try {
            boolean flag = customerBO.deleteCustomer(id);
            if (flag){
                new Alert(Alert.AlertType.CONFIRMATION, "OK").showAndWait();
            }
            txtCustomerId.clear();
            txtName.clear();
            txtAddress.clear();
            txtCustomerId.setText(generateNewID());
            loadAllCustomer();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String id = txtSearch.getText();
        try {
            CustomerDTO customer = customerBO.getCustomer(id);
            if (customer !=null){
               txtCustomerId.setText(customer.getId());
               txtName.setText(customer.getName());
               txtAddress.setText(customer.getAddress());
            }
//            txtCustomerId.setText(generateNewID());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtCustomerId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();

        CustomerDTO customer = new CustomerDTO(id, name, address);


        try {
            boolean flag = customerBO.updateCustomer(customer);

            if (flag) {
                new Alert(Alert.AlertType.CONFIRMATION, "OK").showAndWait();
            }else {
                new Alert(Alert.AlertType.ERROR, "OK").showAndWait();
            }
            txtCustomerId.clear();
            txtName.clear();
            txtAddress.clear();
            txtCustomerId.setText(generateNewID());
            loadAllCustomer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtCustomerId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();

        CustomerDTO customer = new CustomerDTO(id, name, address);


        try {
            boolean added = customerBO.addCustomer(customer);

            if (added) {
                new Alert(Alert.AlertType.CONFIRMATION, "OK").showAndWait();
            }
            txtCustomerId.clear();
            txtName.clear();
            txtAddress.clear();
            txtCustomerId.setText(generateNewID());
            loadAllCustomer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String generateNewID() {
        String s = null;
        try {
            s = customerBO.newCustomerID();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/MainForm.fxml"))));
    }
}
