package ui;

import java.util.ArrayList;
import java.util.List;

import business.Address;
import business.Author;
import business.Book;
import business.ControllerInterface;
import business.LibraryMember;
import business.LibrarySystemException;
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

public class AddNewAuthorsWindow extends Stage implements LibWindow {
	public static final AddNewAuthorsWindow INSTANCE = new AddNewAuthorsWindow();
	private boolean isInitialized = false;
	private Text messageBar = new Text();
	private Address address;
	private Author author;
	
	public void clear() {
		messageBar.setText("");
	}
	
	@Override
	public boolean isInitialized() {
		// TODO Auto-generated method stub
		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {
		// TODO Auto-generated method stub
		isInitialized = val;
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

        Text scenetitle = new Text("Add New Book");
        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); //Tahoma
        grid.add(scenetitle, 0, 0, 2, 1);

        
      //Add authors
      //firstName
        Label firstName = new Label("FirstName:");
        grid.add(firstName, 0, 1);

        TextField firstNameTextField = new TextField();
        grid.add(firstNameTextField, 1, 2);
        
        //LastName
        Label lastName = new Label("LastName:");
        grid.add(lastName, 0, 2);

        TextField lastNameTextField = new TextField();
        grid.add(lastNameTextField, 1, 2);
        
        // phoneNumber
        Label phoneNumber = new Label("Phone Number:");
        grid.add(phoneNumber, 0, 3);

        TextField phoneNumberTextField = new TextField();
        grid.add(phoneNumberTextField, 1, 3);
        
        //street
        Label street = new Label("Street:");
        grid.add(street, 2, 0);
        grid.setGridLinesVisible(false) ;

        TextField streetTextField = new TextField();
        grid.add(streetTextField, 3, 0);
        
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
        

        // add submit button
        Button submitBtn = new Button("Submit");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(submitBtn);
        grid.add(hbBtn, 3, 5);

        HBox messageBox = new HBox(10);
        messageBox.setAlignment(Pos.BOTTOM_RIGHT);
        messageBox.getChildren().add(messageBar);;
        grid.add(messageBox, 2, 5);
        
        // deal the submit button click action
        submitBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		try {
        			ControllerInterface c = new SystemController();
        			String   firstName = firstNameTextField.getText().trim();
        			String   lastName = lastNameTextField.getText().trim();
        			String   street = streetTextField.getText().trim();
        			String   city = cityTextField.getText().trim();
        			String   state = stateTextField.getText().trim();
        			String   zip = zipTextField.getText().trim();
        			String 	 phoneNumber = phoneNumberTextField.getText().trim();
        			
        			//rules
        			String exceptionMessage = "This item must not be empty:";
        			boolean throwException = false;
        			 if(firstName == null || firstName.isEmpty()){
        				exceptionMessage += "firstName";
        				throwException = true;
        			}else if(lastName == null || lastName.isEmpty()){
        				exceptionMessage += "lastName";
        				throwException = true;
        			}else if(street == null || street.isEmpty()){
        				exceptionMessage += "street";
        				throwException = true;
        			}else if(city == null || city.isEmpty()){
        				exceptionMessage += "city";
        				throwException = true;
        			}else if(state == null || state.isEmpty()){
        				exceptionMessage += "state";
        				throwException = true;
        			}else if(zip == null || zip.isEmpty()){
        				exceptionMessage += zip;
        				throwException = true;
        			}else{}
        			
        			
        			
        			if(!zip.matches("[0-9]+")){
        				exceptionMessage = "Zip must be number";
        				throwException = true;
        			}
        			
        			if(!phoneNumber.matches("[0-9]+")){
        				exceptionMessage = "telphone number must be number";
        				throwException = true;
        			}
        			
        			if(zip.length() != 5){
        				exceptionMessage = "Zip must be 5 digital";
        				throwException = true;
        			}
        			
        			if(phoneNumber.length() != 10){
        				exceptionMessage = "phoneNumber must be 10 digital";
        				throwException = true;
        			}
        			
        			if(throwException){
        				throw new LibrarySystemException(exceptionMessage);
        			}
        			
        			//add member
        			address = new Address(street, city, state, zip);
        			author = new Author(firstName,lastName,phoneNumber,address,""); 
        			c.addNewAuthor(author);

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
