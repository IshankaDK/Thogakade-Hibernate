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
import lk.ijse.hibernate.business.custom.impl.ItemBOImpl;
import lk.ijse.hibernate.dto.CustomerDTO;
import lk.ijse.hibernate.dto.ItemDTO;
import lk.ijse.hibernate.view.tm.CustomerTM;
import lk.ijse.hibernate.view.tm.ItemTM;

import java.io.IOException;
import java.util.List;

public class ItemFormController {

    public AnchorPane root;
    @FXML
    private JFXTextField txtCode;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private TableView<ItemTM> tblItem;

    @FXML
    private TableColumn colCode;

    @FXML
    private TableColumn colDescription;

    @FXML
    private TableColumn colQtyOnHand;

    @FXML
    private TableColumn colPrice;

    ItemBOImpl itemBO = BOFactory.getInstance().getBO(BOType.ITEM);

    public void initialize() {
        txtCode.setText(generateNewCode());
        loadAllItem();

        colCode.setCellValueFactory(new PropertyValueFactory("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory("description"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory("price"));
    }

    private void loadAllItem() {
        List<ItemDTO> allItem = null;
        try {
            allItem = itemBO.getAllItem();
            ObservableList<ItemTM> tms = FXCollections.observableArrayList();
            for (ItemDTO itemDTO : allItem) {
                ItemTM itemTM = new ItemTM(itemDTO.getCode(),itemDTO.getDescription(),itemDTO.getQty(),itemDTO.getPrice());
                tms.add(itemTM);
            }
            tblItem.setItems(tms);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event)  {
        String code = txtCode.getText();

        boolean flag = false;
        try {
            flag = itemBO.deleteItem(code);
            if (flag){
                new Alert(Alert.AlertType.CONFIRMATION,"ok").show();
            }
            txtCode.setText(generateNewCode());
            loadAllItem();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String code = txtCode.getText();
        String description = txtDescription.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double price = Double.parseDouble(txtPrice.getText());

        ItemDTO itemDTO = new ItemDTO(code, description, qty, price);

        boolean flag = false;
        try {
            flag = itemBO.addItem(itemDTO);

            if (flag){
                new Alert(Alert.AlertType.CONFIRMATION,"ok").show();
            }
            txtCode.clear();
            txtDescription.clear();
            txtQty.clear();
            txtPrice.clear();
            txtCode.setText(generateNewCode());
            loadAllItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String code = txtSearch.getText();
        try {
            ItemDTO item = itemBO.getItem(code);
            if (item != null){
                txtCode.setText(item.getCode());
                txtDescription.setText(item.getDescription());
                txtQty.setText(String.valueOf(item.getQty()));
                txtPrice.setText(String.valueOf(item.getPrice()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String code = txtCode.getText();
        String description = txtDescription.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double price = Double.parseDouble(txtPrice.getText());

        ItemDTO itemDTO = new ItemDTO(code, description, qty, price);

        boolean flag = false;
        try {
            flag = itemBO.updateItem(itemDTO);

            if (flag){
                new Alert(Alert.AlertType.CONFIRMATION,"ok").show();
            }
            txtCode.clear();
            txtDescription.clear();
            txtQty.clear();
            txtPrice.clear();
            txtCode.setText(generateNewCode());
            loadAllItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateNewCode() {
        String s = null;
        try {
            s = itemBO.newItemCode();
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
