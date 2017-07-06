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
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LibrarianWindow extends Stage implements LibWindow {
	public static final LibrarianWindow INSTANCE = new LibrarianWindow();
	
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
	private LibrarianWindow() {}
	
	public void init() {
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome Librarian:");
        scenetitle.setFont(Font.font("Georgia", FontWeight.NORMAL, 20)); 
        grid.add(scenetitle, 0, 0, 1, 1);
        
        // Checkout Button
        Button btnCheckout = new Button();
        btnCheckout.setPrefWidth(108);
        btnCheckout.setText("Checkout");
        HBox hbxBtn = new HBox();
        hbxBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbxBtn.getChildren().add(btnCheckout);
        grid.add(hbxBtn, 0, 1);
        
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

        
        // Search Member Button
        Button btnSearchMember = new Button();
        btnSearchMember.setText("Search Member");
        HBox hbxBtn3 = new HBox(10);
        hbxBtn3.setAlignment(Pos.BOTTOM_LEFT);
        hbxBtn3.getChildren().add(btnSearchMember);
        grid.add(hbxBtn3, 0, 3);
        
        btnSearchMember.setOnAction(new EventHandler<ActionEvent>(){	// add a button listener
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Start.hideAllWindows();
				if(!SearchMemberWindow.INSTANCE.isInitialized()) {
					SearchMemberWindow.INSTANCE.init();
				}
				//LibrarianWindow.INSTANCE.hide();
        		SearchMemberWindow.INSTANCE.show();
			}
        });
        
        // Logout Button
        Button btnLogout = new Button();
        btnLogout.setText("Logout");
        btnLogout.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				LibrarianWindow.INSTANCE.hide();
				Start.hideAllWindows();
        		Start.primStage().show();
			}
        });
        
        HBox hbxBtn4 = new HBox(10);
        hbxBtn4.setAlignment(Pos.BOTTOM_LEFT);
        hbxBtn4.getChildren().add(btnLogout);
        grid.add(hbxBtn4, 1, 2);
		
		Scene scene = new Scene(grid,400,200);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);
	}
}
