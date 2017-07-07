package ui;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import business.CheckoutSet;
import business.LibraryMember;
import business.LibrarySystemException;
import business.SystemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class SearchMemberWindow extends Stage implements LibWindow {
	public static final SearchMemberWindow INSTANCE = new SearchMemberWindow();
	private  TableView<CheckoutRecordInfo> table;
	Alert dialog = new Alert(Alert.AlertType.INFORMATION);
	private boolean isInitialized = false;
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}
	private TextArea ta;
	public void setData(String data) {
		ta.setText(data);
	}
	private SearchMemberWindow() {}
	
	public void init() {
		List<CheckoutRecordInfo> CheckoutRecordInfoList = new ArrayList<CheckoutRecordInfo>();
		table = new TableView<>();
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Input member ID:");
        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); 
        grid.add(scenetitle, 0, 0, 2, 1);

        // Member ID Label
        Label labelMemberID = new Label();
        labelMemberID.setText("Member ID:");
        grid.add(labelMemberID, 0, 1);
        
        // Member ID TextFiled
        TextField textFieldMemberID = new TextField();
        grid.add(textFieldMemberID, 1, 1);  
        
        // Print Message
        Text messageBar = new Text();
        grid.add(messageBar, 1, 3);
        
        // Search Button
        Button btnSearch = new Button();
        btnSearch.setText("Search");
        grid.add(btnSearch, 2, 1);
        
        
        btnSearch.setOnAction(new EventHandler<ActionEvent>(){ //  Action Listener
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
				//Read member info
		        SystemController sc = new SystemController();
				String memberID = textFieldMemberID.getText();
				List<CheckoutSet> checkoutSetList =sc.searchMember(memberID);
				
				if(checkoutSetList==null || checkoutSetList.isEmpty()){
					// do not find out the member
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Message");
					alert.setHeaderText(null);
					alert.setContentText("We do not have this Member ID");
					alert.showAndWait();
				}else{
					// find out the member
					String memberId = textFieldMemberID.getText();
					DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;			//  Date format
					
					for(CheckoutSet cs:checkoutSetList){
						CheckoutRecordInfo checkoutRecordInfo = new CheckoutRecordInfo(
								cs.getISBN(),cs.getBookName(),formatter.format(cs.getCheckoutDate()),
								formatter.format(cs.getDueDate()),cs.getMemberID(),String.valueOf(cs.getCopyNumber())
								);
						
						CheckoutRecordInfoList.add(checkoutRecordInfo);
					}
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
				}
				
				 //Print button
		        Button btnPrint= new Button();
		        btnPrint.setText("Print");
		        grid.add(btnPrint, 1, 3);
		        btnPrint.setOnAction(new EventHandler<ActionEvent>() {
		        	@Override
		        	public void handle(ActionEvent e) {
		        		if(checkoutSetList==null || checkoutSetList.isEmpty()){
							// do not find out the member
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setTitle("Message");
							alert.setHeaderText(null);
							alert.setContentText("We do not have this Member ID");
							alert.showAndWait();
						}else{
							for(CheckoutSet cs:checkoutSetList){
								String outPutText = "ISBN:"+cs.getISBN() + " BooKName:["+cs.getBookName()+
										"] CheckOutData:["+cs.getCheckoutDate()+"] CheckDueDate:["+cs.getDueDate()
										+"] MemberId:["+cs.getMemberID() +"] CopyNum:["+cs.getCopyNumber()+"]";
								System.out.println(outPutText);
							}
						}
		        	}
		        });
			}
        });
		
       
        
        //Add all button
		Scene scene = new Scene(grid,400,200);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);
	}
}
