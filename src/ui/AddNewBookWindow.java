package ui;

import java.util.ArrayList;
import java.util.List;

import business.Author;
import business.Book;
import business.ControllerInterface;
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

public class AddNewBookWindow extends Stage implements LibWindow {
	public static final AddNewBookWindow INSTANCE = new AddNewBookWindow();
	private boolean isInitialized = false;
	private Book book;
//	private Address address;
	
	private Text messageBar = new Text();
	public void clear() {
		messageBar.setText("");
	}

	private AddNewBookWindow() {}
	
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

        
        
        //ISBN
        Label ISBN = new Label("ISBN:");
        grid.add(ISBN, 0, 1);

        TextField ISBNTextField = new TextField();
        grid.add(ISBNTextField, 1, 1);

        //title
        Label title = new Label("title:");
        grid.add(title, 0, 2);
        grid.setGridLinesVisible(false) ;

        TextField titleTextField = new TextField();
        grid.add(titleTextField, 1, 2);
        
      //copiesNum
        Label copiesNum = new Label("copiesNum:");
        grid.add(copiesNum, 2, 1);

        TextField copiesNumTextField = new TextField();
        grid.add(copiesNumTextField, 3, 1);
        
        //maximum
        Label maximum = new Label("maximum:");
        grid.add(maximum, 2, 2);
        grid.setGridLinesVisible(false) ;

        TextField maximumTextField = new TextField();
        grid.add(maximumTextField, 3, 2);
        
        
      //authors
        
        Label authors = new Label("authors:");
        grid.add(authors, 1, 3);

        Button authorsButton = new Button("Add New Author");
        grid.add(authorsButton, 2, 3);
        
        authorsButton.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		try {
        			
        		} catch(LibrarySystemException ex) {
        			messageBar.setFill(Start.Colors.red);
        			messageBar.setText("Error! " + ex.getMessage());
        		}
        	   
        	}
        });

        // add submit button
        Button loginBtn = new Button("Submit");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(loginBtn);
        grid.add(hbBtn, 3, 5);

        HBox messageBox = new HBox(10);
        messageBox.setAlignment(Pos.BOTTOM_RIGHT);
        messageBox.getChildren().add(messageBar);;
        grid.add(messageBox, 2, 5);
        
        // deal the submit button click action
        loginBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		try {
        			ControllerInterface c = new SystemController();
        			String   ISBN = ISBNTextField.getText().trim();
        			String   title = titleTextField.getText().trim();
        			String   authors = authorsButton.getText().trim();
        			String   maximum = maximumTextField.getText().trim();
        			String   copiesNum = copiesNumTextField.getText().trim();
        			
        			//rules
        			String exceptionMessage = "This item must not be empty:";
        			boolean throwException = false;
        			if(ISBN == null || ISBN.isEmpty()){
        				exceptionMessage += "ISBN";
        				throwException = true;
        			}else if(title == null || title.isEmpty()){
        				exceptionMessage += "title";
        				throwException = true;
        			}else if(authors == null || authors.isEmpty()){
        				exceptionMessage += "authors";
        				throwException = true;
        			}else if(maximum == null || maximum.isEmpty()){
        				exceptionMessage += "maximum";
        				throwException = true;
        			}else if(copiesNum == null || copiesNum.isEmpty()){
        				exceptionMessage += "copiesNum";
        				throwException = true;
        			}else{}
        			
        			if(!maximum.matches("[0-9]+")){
        				exceptionMessage = "Maximum Must Be Number";
        				throwException = true;
        			}
        			
        			if(!copiesNum.matches("[0-9]+")){
        				exceptionMessage = "CopiesNum Must Be Number";
        				throwException = true;
        			}
        			
        			
        			if(throwException){
        				throw new LibrarySystemException(exceptionMessage);
        			}
        			
        			//add new book
        			List<Author> authorsList = new ArrayList<Author>();
//        			String[] authorsArray = authors.split(",");
//        			authorsList.add(authorsArray);
        			book = new Book(ISBN,title,Integer.parseInt(maximum),authorsList); 
//        			c.addNewLibaryMember(member);
        			c.addNewBook(book, Integer.parseInt(copiesNum));

        			messageBar.setFill(Start.Colors.green);
             	    messageBar.setText("Add successful");
        		} catch(LibrarySystemException ex) {
        			messageBar.setFill(Start.Colors.red);
        			messageBar.setText("Error! " + ex.getMessage());
        		}
        	   
        	}
        });

        Button backBtn = new Button("<=Back Main");
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
