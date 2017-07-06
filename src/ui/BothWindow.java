package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BothWindow extends Stage implements LibWindow {
	public static final BothWindow INSTANCE = new BothWindow();
	
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
	private BothWindow() {}
	
	public void init() {
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome Super Adminstor");
        scenetitle.setFont(Font.font("Georgia", FontWeight.NORMAL, 20)); 
        grid.add(scenetitle, 0, 0, 2, 1);
		
        // Add Member Button
        Button btnAddMember = new Button();
        btnAddMember.setPrefWidth(108);
        btnAddMember.setText("Add Member");
        HBox hbxBtn = new HBox(10);
        hbxBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbxBtn.getChildren().add(btnAddMember);
        grid.add(hbxBtn, 1, 1);
        
        btnAddMember.setOnAction(new EventHandler<ActionEvent>(){	// add a button listener
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if(!AddNewMemberWindow.INSTANCE.isInitialized()) {
					AddNewMemberWindow.INSTANCE.init();
				}
				AddNewMemberWindow.INSTANCE.setData("BOTH");
				BothWindow.INSTANCE.hide();
				AddNewMemberWindow.INSTANCE.show();
			}
        });
        
        // Edit Member Button
        //Button btnEditMember = new Button();
        //btnEditMember.setText("Edit Member");
        //HBox hbxBtn2 = new HBox(10);
        //hbxBtn2.setAlignment(Pos.BOTTOM_LEFT);
        //hbxBtn2.getChildren().add(btnEditMember);
        //grid.add(hbxBtn2, 1, 2);
        
        // All Member IDs button
        Button btnAllMembersID = new Button();
        btnAllMembersID.setPrefWidth(108);
        btnAllMembersID.setText("All Members");
        HBox hbxBtn3 = new HBox(10);
        hbxBtn3.setAlignment(Pos.BOTTOM_LEFT);
        hbxBtn3.getChildren().add(btnAllMembersID);
        grid.add(hbxBtn3, 1, 2);
        
        btnAllMembersID.setOnAction(new EventHandler<ActionEvent>(){	// add a button listener
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if(!AllMembersWindow.INSTANCE.isInitialized()) {
					AllMembersWindow.INSTANCE.init();
				}
				AllMembersWindow.INSTANCE.setData("BOTH");
				BothWindow.INSTANCE.hide();
				AllMembersWindow.INSTANCE.show();
			}
        });
        
        // Add book Button
        Button btnAddBook = new Button();
        btnAddBook.setPrefWidth(108);
        btnAddBook.setText("Add Book");
        HBox hbxBtn4 = new HBox(10);
        hbxBtn4.setAlignment(Pos.BOTTOM_LEFT);
        hbxBtn4.getChildren().add(btnAddBook);
        grid.add(hbxBtn4, 1, 3);
        
        btnAddBook.setOnAction(new EventHandler<ActionEvent>(){	// add a button listener
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if(!AddNewBookWindow.INSTANCE.isInitialized()) {
					AddNewBookWindow.INSTANCE.init();
				}
			
				AddNewBookWindow.INSTANCE.setData("BOTH");
				BothWindow.INSTANCE.hide();
				AddNewBookWindow.INSTANCE.show();
			}
        });
        
        // All Book IDs Button
        Button btnAllBooks = new Button();
        btnAllBooks.setPrefWidth(108);
        btnAllBooks.setText("All Books");
        grid.add(btnAllBooks, 2, 1);
        
        btnAllBooks.setOnAction(new EventHandler<ActionEvent>(){	// add a button listener
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if(!AllBooksWindow.INSTANCE.isInitialized()) {
					AllBooksWindow.INSTANCE.init();
				}
				AllBooksWindow.INSTANCE.setData("BOTH");
				BothWindow.INSTANCE.hide();
				AllBooksWindow.INSTANCE.show();
			}
        });
        
        //Add Book Copy
        Button bookCopyBtn = new Button();
        bookCopyBtn.setPrefWidth(108);
        bookCopyBtn.setText("Add Book Copy");
        grid.add(bookCopyBtn, 2, 2);
        bookCopyBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
				if(!AddBookCopyWindow.INSTANCE.isInitialized()) {
					AddBookCopyWindow.INSTANCE.init();
				}
				
				AddBookCopyWindow.INSTANCE.setData("BOTH");
				BothWindow.INSTANCE.hide();
				AddBookCopyWindow.INSTANCE.show();
            }
		});
        
        // Checkout Button
        Button btnCheckout = new Button();
        btnCheckout.setPrefWidth(108);
        btnCheckout.setText("Checkout");
        HBox hbxBtn6 = new HBox(10);
        hbxBtn6.setAlignment(Pos.BOTTOM_LEFT);
        hbxBtn6.getChildren().add(btnCheckout);
        grid.add(hbxBtn6, 2, 3);
        
        btnCheckout.setOnAction(new EventHandler<ActionEvent>(){	// add a button listener
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				//Start.hideAllWindows();
				if(!CheckoutWindow.INSTANCE.isInitialized()) {
					CheckoutWindow.INSTANCE.init();
				}
				
				CheckoutWindow.INSTANCE.show();
			}
        });
        
        // Logout Button
        Button btnLogout = new Button();
        btnLogout.setText("Logout");
        btnLogout.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				BothWindow.INSTANCE.hide();
				Start.hideAllWindows();
        		Start.primStage().show();
			}
        });
        
        HBox hbxBtn7 = new HBox(10);
        hbxBtn7.setAlignment(Pos.BOTTOM_LEFT);
        hbxBtn7.getChildren().add(btnLogout);
        grid.add(hbxBtn7, 2, 4);
        
		Scene scene = new Scene(grid,400,200);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);
	}
}
