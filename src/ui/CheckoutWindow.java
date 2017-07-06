package ui;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import business.CheckoutSet;
import business.LibrarySystemException;
import business.SystemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CheckoutWindow extends Stage implements LibWindow {
	public static final CheckoutWindow INSTANCE = new CheckoutWindow();
	
	private boolean isInitialized = false;
	Alert dialog = new Alert(Alert.AlertType.INFORMATION);
	
	private  TableView<CheckoutRecordInfo> table;		// declare for Table View
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	private CheckoutWindow() {
	}
	
	public void init() {
		
		table = new TableView<>();	// get table view
		List<CheckoutRecordInfo> CheckoutRecordInfoList = new ArrayList<CheckoutRecordInfo>();
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
//        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Checkout Book:");
        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); 
        grid.add(scenetitle, 0, 0);

        // ISBN Label
        Label labelISBN = new Label();
        labelISBN.setText("Book ISBN:");
        grid.add(labelISBN, 0, 1);
        
        // ISBN TextFiled
        TextField textFieldISBN = new TextField();
        grid.add(textFieldISBN, 1, 1);  
        
        // Member ID Label
        Label labelMemberID = new Label();
        labelMemberID.setText("Member ID:");
        grid.add(labelMemberID, 0, 2);
        
        // Member ID TextFiled
        TextField textFieldMemberID = new TextField();
        grid.add(textFieldMemberID, 1, 2);  
        
        // Checkout Button
        Button btnCheckout = new Button();
        btnCheckout.setText("Checkout");
        grid.add(btnCheckout, 1, 3);
        btnCheckout.setOnAction(new EventHandler(){ //  Action Listener
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				SystemController sc = new SystemController();
				String memberId = textFieldMemberID.getText();
				String ISBN = textFieldISBN.getText();
				try {
					// check out
					CheckoutSet checkoutSet = sc.checkoutBook(memberId, ISBN);
					//CheckoutRecordEntry checkoutRecordEntry = checkoutRecord.getEntry(ISBN); // get Entry
					// display in TableView
					String bookName = checkoutSet.getBookName();							//	get book name
					DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;			//  Date format
					String strCheckoutDate = formatter.format(checkoutSet.getCheckoutDate());	//	checkout date
					String strDueDate = formatter.format(checkoutSet.getDueDate());			//	due date
					int intCopyNum = checkoutSet.getCopyNumber();							//	get Copy Number
					String copyNum = String.valueOf(intCopyNum);
					// set the value to TableView
					CheckoutRecordInfo checkoutRecordInfo = new CheckoutRecordInfo(ISBN,bookName,strCheckoutDate,strDueDate,memberId,copyNum);
					CheckoutRecordInfoList.add(checkoutRecordInfo);
					// setup TableView
					final ObservableList<CheckoutRecordInfo> data = FXCollections.observableArrayList(CheckoutRecordInfoList);
			        // setup column
					TableColumn ISBNCol = new TableColumn("ISBN");
			        ISBNCol.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
			        TableColumn bookNameCol = new TableColumn("Book Name");
			        bookNameCol.setCellValueFactory(new PropertyValueFactory<>("bookName"));
			        TableColumn checkoutDateCol = new TableColumn("Checkout Date");
			        checkoutDateCol.setCellValueFactory(new PropertyValueFactory<>("checkoutDate"));
			        TableColumn dueDateCol = new TableColumn("Due Date");
			        dueDateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
			        TableColumn memberIDCol = new TableColumn("Member");
			        memberIDCol.setCellValueFactory(new PropertyValueFactory<>("memberID"));
			        TableColumn copyNumberCol = new TableColumn("Copy Number");
			        copyNumberCol.setCellValueFactory(new PropertyValueFactory<>("copyNumber"));
			        
			        table.setItems(data);
			        table.setPrefWidth(550);
			        table.setPrefHeight(300);
			        table.getColumns().addAll(ISBNCol,bookNameCol, checkoutDateCol, dueDateCol,memberIDCol,copyNumberCol);
			               
			        dialog.getDialogPane().setContent(table);
			        dialog.setHeaderText(null);
			        dialog.setContentText(null);
			        dialog.showAndWait();
				} catch (LibrarySystemException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					// show a failed alert
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText(e.getMessage());
					alert.showAndWait();
					
				}
				
			}
        });
		
		Scene scene = new Scene(grid,400,200);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);
	}
}
