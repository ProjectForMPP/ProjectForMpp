package ui;

import business.LibraryMember;
import business.SystemController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SearchMemberWindow extends Stage implements LibWindow {
	public static final SearchMemberWindow INSTANCE = new SearchMemberWindow();
	
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
        btnSearch.setOnAction(new EventHandler(){ //  Action Listener
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				SystemController sc = new SystemController();
				String memberID = textFieldMemberID.getText();
				LibraryMember libraryMember =sc.searchMember(memberID);
				if(libraryMember==null){
					// do not find out the member
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Message");
					alert.setHeaderText(null);
					alert.setContentText("We do not have this Member ID");
					alert.showAndWait();
				}else{
					// find out the member
					messageBar.setText("Member Information:\n" +
									   "Member ID:" + memberID + "\n" + 
									   "First Name:" + libraryMember.getFirstName() + "\n" + 
									   "First Name:" + libraryMember.getLastName()  + "\n" +
									   "Telephone:" + libraryMember.getTelephone() + "\n" +
									   "Address" + libraryMember.getAddress());
					Button btnPrint = new Button("Print");
					grid.add(btnPrint, 1, 4);
					// add listener to button
					btnPrint.setOnAction(new EventHandler(){	// add a button listener
							@Override
							public void handle(Event event) {
								// Print to console
								System.out.println("Member Information:\n" +
												   "Member ID:" + memberID + "\n" + 
												   "First Name:" + libraryMember.getFirstName() + "\n" + 
												   "First Name:" + libraryMember.getLastName()  + "\n" +
												   "Telephone:" + libraryMember.getTelephone() + "\n" +
												   "Address" + libraryMember.getAddress());
								Alert alert = new Alert(Alert.AlertType.INFORMATION);
								alert.setTitle("Print Message");
								alert.setHeaderText(null);
								alert.setContentText("Print Success!");
								alert.showAndWait();
							}
				        });
				}
			}
        });
		
		Scene scene = new Scene(grid,600,500);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);
	}
}
