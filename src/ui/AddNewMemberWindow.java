package ui;

import java.util.HashMap;
import java.util.Map;

import business.Address;
import business.ControllerInterface;
import business.LibraryMember;
import business.LibrarySystemException;
import business.LoginException;
import business.SystemController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddNewMemberWindow extends Stage implements LibWindow {
	public static final AddNewMemberWindow INSTANCE = new AddNewMemberWindow();
	private boolean isInitialized = false;
	private LibraryMember member;
	private Address address;
	
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	private AddNewMemberWindow() {
//		address = new Address("","","","");
//		member = new LibraryMember("","","","",address);
	}
	
	private Text messageBar = new Text();
	public void clear() {
		messageBar.setText("");
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub

		GridPane grid = new GridPane();
		grid.setId("top-container");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Add New Member");
        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); //Tahoma
        grid.add(scenetitle, 0, 0, 2, 1);

        
        
        //ID
        Label Id = new Label("Member ID:");
        grid.add(Id, 0, 1);

        TextField idTextField = new TextField();
        grid.add(idTextField, 1, 1);

        //firstName
        Label firstName = new Label("FirstName:");
        grid.add(firstName, 0, 2);
        grid.setGridLinesVisible(false) ;

        TextField firstNameTextField = new TextField();
        grid.add(firstNameTextField, 1, 2);
        
        //LastName
        Label lastName = new Label("LastName:");
        grid.add(lastName, 0, 3);

        TextField lastNameTextField = new TextField();
        grid.add(lastNameTextField, 1, 3);

        //street
        Label street = new Label("Street:");
        grid.add(street, 0, 4);
        grid.setGridLinesVisible(false) ;

        TextField streetTextField = new TextField();
        grid.add(streetTextField, 1, 4);
        
      //city
        Label city = new Label("City:");
        grid.add(city, 2, 1);

        TextField cityTextField = new TextField();
        grid.add(cityTextField, 3, 1);
        
      //state
        Label state = new Label("State:");
        grid.add(state, 2, 2);

        TextField stateTextField = new TextField();
        grid.add(stateTextField, 3, 2);
        
      //Zip
        Label zip = new Label("Zip:");
        grid.add(zip, 2, 3);

        TextField zipTextField = new TextField();
        grid.add(zipTextField, 3, 3);
        
      //telephone Number
        Label telephoneNumber = new Label("Phone:");
        grid.add(telephoneNumber, 2, 4);

        TextField telephoneNumberTextField = new TextField();
        grid.add(telephoneNumberTextField, 3, 4);
        //

        Button loginBtn = new Button("Submit");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(loginBtn);
        grid.add(hbBtn, 3, 5);

        HBox messageBox = new HBox(10);
        messageBox.setAlignment(Pos.BOTTOM_RIGHT);
        messageBox.getChildren().add(messageBar);;
        grid.add(messageBox, 2, 5);
        
        loginBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		try {
        			ControllerInterface c = new SystemController();
        			String   id = idTextField.getText().trim();
        			String   firstName = firstNameTextField.getText().trim();
        			String   lastName = lastNameTextField.getText().trim();
        			String   phoneNumber = telephoneNumberTextField.getText().trim();
        			String   street = streetTextField.getText().trim();
        			String   city = cityTextField.getText().trim();
        			String   state = stateTextField.getText().trim();
        			String   zip = zipTextField.getText().trim();
        			
        			//add member
        			address = new Address(street, city, state, zip);
        			member = new LibraryMember(id,firstName,lastName,phoneNumber,address); 
        			c.addNewLibaryMember(member);

        			messageBar.setFill(Start.Colors.green);
             	    messageBar.setText("Add successful");
        		} catch(LibrarySystemException ex) {
        			messageBar.setFill(Start.Colors.red);
        			messageBar.setText("Error! " + ex.getMessage());
        		}
        	   
        	}
        });

        Button backBtn = new Button("Cancel");
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		Start.hideAllWindows();
        		Start.primStage().show();
        	}
        });
        HBox hBack = new HBox(10);
        hBack.setAlignment(Pos.BOTTOM_LEFT);
        hBack.getChildren().add(backBtn);
        grid.add(hBack, 1, 5);
        Scene scene = new Scene(grid);
        scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);
	}

	

}
