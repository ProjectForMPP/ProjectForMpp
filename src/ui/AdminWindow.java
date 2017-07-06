package ui;

import java.util.Collections;
import java.util.List;

import business.ControllerInterface;
import business.SystemController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminWindow extends Stage implements LibWindow {
	public static final AdminWindow INSTANCE = new AdminWindow();
	
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
	private AdminWindow() {}
	
	public void init() {
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome Admin:");
        scenetitle.setFont(Font.font("Georgia", FontWeight.NORMAL, 20)); 
        grid.add(scenetitle, 0, 0, 2, 1);
        
        // Add Member Button
        Button btnAddMember = new Button();
        btnAddMember.setPrefWidth(108);
        btnAddMember.setText("Add Member");
        HBox hbxBtn = new HBox(10);
        hbxBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbxBtn.getChildren().add(btnAddMember);
        grid.add(hbxBtn, 0, 1);
        
        btnAddMember.setOnAction(new EventHandler<ActionEvent>(){	// add a button listener
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				//Start.hideAllWindows();
				if(!AddNewMemberWindow.INSTANCE.isInitialized()) {
					AddNewMemberWindow.INSTANCE.init();
				}
				AddNewMemberWindow.INSTANCE.setData("ADMIN");
				AdminWindow.INSTANCE.hide();
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
        
        // All Members ID Button
        Button btnAllMembersID = new Button();
        btnAllMembersID.setPrefWidth(108);
        btnAllMembersID.setText("All Members");
        HBox hbxBtn3 = new HBox(10);
        hbxBtn3.setAlignment(Pos.BOTTOM_LEFT);
        hbxBtn3.getChildren().add(btnAllMembersID);
        grid.add(hbxBtn3, 0, 2);
        
        btnAllMembersID.setOnAction(new EventHandler<ActionEvent>(){	// add a button listener
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if(!AllMembersWindow.INSTANCE.isInitialized()) {
					AllMembersWindow.INSTANCE.init();
				}
				AllMembersWindow.INSTANCE.setData("ADMIN");
				AdminWindow.INSTANCE.hide();
				AllMembersWindow.INSTANCE.show();
			}
        });
        
        //All Books
        Button bookBtn = new Button();
        bookBtn.setPrefWidth(108);
        bookBtn.setText("All Books");
        
        grid.add(bookBtn, 0, 3);
        bookBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
				if(!AllBooksWindow.INSTANCE.isInitialized()) {
					AllBooksWindow.INSTANCE.init();
				}
				AllBooksWindow.INSTANCE.setData("ADMIN");
				AdminWindow.INSTANCE.hide();
				AllBooksWindow.INSTANCE.show();
            }
		});
        
        // Add book Button
        Button btnAddBook = new Button();
        btnAddBook.setPrefWidth(108);
        btnAddBook.setText("Add Book");
        HBox hbxBtn4 = new HBox(10);
        hbxBtn4.setAlignment(Pos.BOTTOM_LEFT);
        hbxBtn4.getChildren().add(btnAddBook);
        grid.add(hbxBtn4, 4, 1);
        
        btnAddBook.setOnAction(new EventHandler<ActionEvent>(){	// add a button listener
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if(!AddNewBookWindow.INSTANCE.isInitialized()) {
					AddNewBookWindow.INSTANCE.init();
				}
			
				AddNewBookWindow.INSTANCE.setData("ADMIN");
				AdminWindow.INSTANCE.hide();
				AddNewBookWindow.INSTANCE.show();
			}
        });
        
        
        //Add Book Copy
        Button bookCopyBtn = new Button();
        bookCopyBtn.setPrefWidth(108);
        bookCopyBtn.setText("Add Book Copy");
        grid.add(bookCopyBtn, 4, 2);
        bookCopyBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
				if(!AddBookCopyWindow.INSTANCE.isInitialized()) {
					AddBookCopyWindow.INSTANCE.init();
				}
				
				AddBookCopyWindow.INSTANCE.setData("ADMIN");
				AdminWindow.INSTANCE.hide();
				AddBookCopyWindow.INSTANCE.show();
            }
		});
		
        // Logout Button
        Button btnLogout = new Button();
        btnLogout.setPrefWidth(66);
        btnLogout.setText("Logout");
        btnLogout.setAlignment(Pos.CENTER_RIGHT);
        btnLogout.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				AdminWindow.INSTANCE.hide();
				Start.hideAllWindows();
        		Start.primStage().show();
			}
        });
        
        HBox hbxBtn5 = new HBox(10);
        hbxBtn5.setAlignment(Pos.BOTTOM_LEFT);
        hbxBtn5.getChildren().add(btnLogout);
        grid.add(hbxBtn5, 4, 3);
		
		Scene scene = new Scene(grid,400,200);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);
	}
}
