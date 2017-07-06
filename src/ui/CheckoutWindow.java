package ui;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import business.CheckoutRecord;
import business.CheckoutRecordEntry;
import business.LibraryMember;
import business.LibrarySystemException;
import business.SystemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CheckoutWindow extends Stage implements LibWindow {
	public static final CheckoutWindow INSTANCE = new CheckoutWindow();
	
	private boolean isInitialized = false;
	private  TableView<CheckoutRecordInfo> table;		// declare for Table View
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	private CheckoutWindow() {}
	
	public void init() {
		
		table = new TableView<>();	// get table view
		List<CheckoutRecordInfo> CheckoutRecordInfoList = new ArrayList<CheckoutRecordInfo>();
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

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
					CheckoutRecord checkoutRecord = sc.checkoutBook(memberId, ISBN);
					CheckoutRecordEntry checkoutRecordEntry = checkoutRecord.getEntry(ISBN); // get Entry
					// display in TableView
					String bookName = checkoutRecordEntry.getBookCopy().getBook().getTitle();
					LocalDate nowDate = checkoutRecordEntry.getCheckoutDate();
					LocalDate dueDate = checkoutRecordEntry.getDueDate();
					DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
					String strNowDate = formatter.format(nowDate);
					String strDueDate = formatter.format(dueDate);
					CheckoutRecordInfo checkoutRecordInfo = new CheckoutRecordInfo(bookName,strNowDate,strDueDate);
					CheckoutRecordInfoList.add(checkoutRecordInfo);
					// setup TableView
					final ObservableList<CheckoutRecordInfo> data = FXCollections.observableArrayList(CheckoutRecordInfoList);
			        // setup column
			        TableColumn bookNameCol = new TableColumn("Book Name");
			        bookNameCol.setCellValueFactory(new PropertyValueFactory<>("bookName"));
			        TableColumn checkoutDateCol = new TableColumn("Checkout Date");
			        checkoutDateCol.setCellValueFactory(new PropertyValueFactory<>("checkoutDate"));
			        TableColumn dueDateCol = new TableColumn("Due Date");
			        dueDateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
			        
			        table.setItems(data);
			        table.setPrefWidth(500);
			        table.getColumns().addAll(bookNameCol, checkoutDateCol, dueDateCol);
			               
			        grid.add(table, 0, 1);
			        // hide the butten,label and textField
			        labelISBN.setVisible(false);
			        labelMemberID.setVisible(false);
			        textFieldISBN.setVisible(false);
			        textFieldMemberID.setVisible(false);
			        btnCheckout.setVisible(false);
					// show a success Alert
					//Alert alert = new Alert(Alert.AlertType.INFORMATION);
					//alert.setTitle("Success");
					//alert.setHeaderText(null);
					//alert.setContentText("Success Checkout");
					//alert.showAndWait();
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
		
		Scene scene = new Scene(grid,600,400);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);
	}
}
