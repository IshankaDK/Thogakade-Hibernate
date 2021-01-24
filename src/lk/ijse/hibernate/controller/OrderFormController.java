package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hibernate.business.BOFactory;
import lk.ijse.hibernate.business.BOType;
import lk.ijse.hibernate.business.custom.impl.CustomerBOImpl;
import lk.ijse.hibernate.business.custom.impl.ItemBOImpl;
import lk.ijse.hibernate.business.custom.impl.OrderBOImpl;
import lk.ijse.hibernate.dto.CustomerDTO;
import lk.ijse.hibernate.dto.ItemDTO;
import lk.ijse.hibernate.dto.OrderDTO;
import lk.ijse.hibernate.view.tm.OrderTM;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class OrderFormController {
    @FXML
    public Label lblTotal;

    @FXML
    public Label lblOrderId;

    @FXML
    public Label lblDate;

    @FXML
    private AnchorPane root;

    @FXML
    private JFXComboBox<String> cmbCustomerId;

    @FXML
    private JFXComboBox<String> cmbCode;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblCustomerAddress;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblPrice;

    @FXML
    private TextField txtQty;

    @FXML
    private TableView<OrderTM> tblOrder;

    @FXML
    private TableColumn colCode;

    @FXML
    private TableColumn colDescription;

    @FXML
    private TableColumn colQty;

    @FXML
    private TableColumn colPrice;

    @FXML
    private TableColumn colTotal;

    OrderBOImpl orderBO = BOFactory.getInstance().getBO(BOType.ORDERS);
    CustomerBOImpl customerBO = BOFactory.getInstance().getBO(BOType.CUSTOMER);
    ItemBOImpl itemBO = BOFactory.getInstance().getBO(BOType.ITEM);


    public void initialize() {
        lblOrderId.setText(generateNewId());
        loadCustomer();
        loadItems();
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    private void loadItems() {
        try {
            List<ItemDTO> list = itemBO.getAllItem();
            for (ItemDTO dto : list) {
                cmbCode.getItems().add(dto.getCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCustomer() {
        try {
            List<CustomerDTO> list = customerBO.getAllCustomer();
            for (CustomerDTO dto : list) {
                cmbCustomerId.getItems().add(dto.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ObservableList<OrderTM> observableList = FXCollections.observableArrayList();

    @FXML
    void btnAddOnAction(ActionEvent event) {
        colCode.setCellValueFactory(new PropertyValueFactory("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory("description"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory("price"));
        colTotal.setCellValueFactory(new PropertyValueFactory("total"));
        try {
            String code = cmbCode.getValue();
            String desc = lblDescription.getText();
            double qty = Double.parseDouble(txtQty.getText());
            double price = Double.parseDouble(lblPrice.getText());

            if (!observableList.isEmpty()) { // check observableList is empty
                for (int i = 0; i < tblOrder.getItems().size(); i++) { // check all rows in table
                    if (colCode.getCellData(i).equals(code)) { // check  itemcode in table == itemcode we enter
                        double temp = (double) colQty.getCellData(i); // get qty in table for temp
                        temp += qty; // add new qty to old qty
                        if (temp <= Double.parseDouble(lblQtyOnHand.getText())) {
                            double tot = (temp * price); // get new total
                            observableList.get(i).setQty(temp); // set new qty to observableList
                            observableList.get(i).setTotal(tot); // set new total to observableList
                            getSubTotal();
                            tblOrder.refresh(); // refresh table
                            return;
                        } else {
                            new Alert(Alert.AlertType.ERROR, "No items").show();
                            return;
                        }
                    }
                }
            }
            observableList.add(new OrderTM(code, desc, qty, price, ((qty * price))));
            tblOrder.setItems(observableList); // if their is no list or, no matched itemcode
            getSubTotal();
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Not Added").show();
            return;
        }
    }

    private void getSubTotal() {
        double tot = 0.0;
        for (OrderTM orderTM : observableList) {
            tot += orderTM.getTotal();
        }
        lblTotal.setText(String.valueOf(tot));
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/MainForm.fxml"))));
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        CustomerDTO customerDTO = new CustomerDTO(cmbCustomerId.getValue(), lblCustomerName.getText(), lblCustomerAddress.getText());
        ArrayList<ItemDTO> itemData = getItemData();
        String oId = lblOrderId.getText().trim();
        Date oDate = Date.valueOf(lblDate.getText());
        boolean b = false;
        try {
            if (tblOrder.getItems().size() == 0) {
                new Alert(Alert.AlertType.ERROR, "No Item Selected").show();
            } else {
                OrderDTO orderDTO = new OrderDTO(oId, oDate, customerDTO, itemData);
                boolean saved = orderBO.addOrder(orderDTO);

                List<OrderDTO> orderDTOList = new ArrayList<>();
                orderDTOList.add(orderDTO);

                for (ItemDTO item : itemData) {
                    item.setOrders(orderDTOList);
                }
                orderDTO.setItemDTOS(itemData);

                for (ItemDTO dto:itemData) {
                    ItemDTO itemDTO = new ItemDTO(dto.getCode(), dto.getDescription(), dto.getQty(), dto.getPrice(), dto.getOrders());
                     b = itemBO.updateItem(itemDTO);
                    if(!b){
                        new Alert(Alert.AlertType.ERROR, "Order Failed...!").show();
                    }
                }
                if (saved & b){
                    new Alert(Alert.AlertType.CONFIRMATION, "Order Completed...!").show();
                    lblOrderId.setText(generateNewId());
                    observableList.clear();
                    tblOrder.refresh();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    ArrayList<ItemDTO> dtoArrayList;

    private ArrayList<ItemDTO> getItemData() {
        try {
            dtoArrayList = new ArrayList<>();
            int size = colCode.getTableView().getItems().size();
            for (int i = 0; i < size; i++) {
                String code = String.valueOf(colCode.getCellData(i));
                String desc = String.valueOf(colDescription.getCellData(i));
                double qty = (double) colQty.getCellData(i);
                double stockQty = itemBO.getItem(code).getQty();
                double price = (double) colPrice.getCellData(i);
                dtoArrayList.add(new ItemDTO(code, desc, (stockQty - qty), price));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dtoArrayList;
    }

    @FXML
    void btnRemoveOnAction(ActionEvent event) throws IOException {
        OrderTM selectedItem = tblOrder.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            observableList.remove(selectedItem);
            tblOrder.getItems().remove(selectedItem);
            getSubTotal();
        } else {
            new Alert(Alert.AlertType.ERROR, "No Row Selected").show();
            tblOrder.requestFocus();
        }
    }


    private String generateNewId() {
        String s = null;
        try {
            s = orderBO.newOrderID();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    @FXML
    public void cmbCustomerIdOnAction(ActionEvent actionEvent) {
        String id = cmbCustomerId.getValue();
        CustomerDTO customer = null;
        try {
            customer = customerBO.getCustomer(id);
            lblCustomerName.setText(customer.getName());
            lblCustomerAddress.setText(customer.getAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void cmbCodeOnAction(ActionEvent actionEvent) {
        String code = cmbCode.getValue();
        ItemDTO item = null;
        try {
            item = itemBO.getItem(code);
            lblDescription.setText(item.getDescription());
            lblQtyOnHand.setText(String.valueOf(item.getQty()));
            lblPrice.setText(String.valueOf(item.getPrice()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



