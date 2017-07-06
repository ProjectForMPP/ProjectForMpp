package ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.Author;
import business.Book;
import business.ControllerInterface;
import business.LibrarySystemException;
import business.SystemController;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddBookCopyWindow extends Stage implements LibWindow {
	public static final AddBookCopyWindow INSTANCE = new AddBookCopyWindow();
	private boolean isInitialized = false;
	Alert alert = new Alert(Alert.AlertType.INFORMATION);

	private Text messageBar = new Text();

	public void clear() {
		messageBar.setText("");
	}

	private AddBookCopyWindow() {
		alert.setTitle("Warning");
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
	
	private  String fromTo = "";
	public void setData(String data) {
		fromTo = data;
	}
	
	@Override
	public void init() {
		
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Add Book Copy");
		scenetitle.setFont(Font.font("Georgia", FontWeight.NORMAL, 20)); // Tahoma
		grid.add(scenetitle, 0, 0, 2, 1);

		// ISBN
		Label ISBN = new Label("ISBN:");
		grid.add(ISBN, 0, 1);

		TextField ISBNTextField = new TextField();
		grid.add(ISBNTextField, 1, 1);

		

		// add submit button
		Button submitBtn = new Button("Add");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(submitBtn);
		grid.add(hbBtn, 3, 5);

		HBox messageBox = new HBox(10);
		messageBox.setAlignment(Pos.BOTTOM_RIGHT);
		messageBox.getChildren().add(messageBar);
		grid.add(messageBox, 2, 5);

		// deal the submit button click action
		submitBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					ControllerInterface c = new SystemController();
					String ISBN = ISBNTextField.getText().trim();

					// rules
					String exceptionMessage = "This item must not be empty:";
					boolean throwException = false;
					DataAccess da = new DataAccessFacade();
					HashMap<String, Book> map = da.readBooksMap();
					
					if (ISBN == null || ISBN.isEmpty()) {
						exceptionMessage += "ISBN";
						throwException = true;
					} else {
						
						if(map == null ||map.isEmpty() || !map.containsKey(ISBN)){
							exceptionMessage += "This book is not existence";
							throwException = true;
						}
					}

					if (throwException) {
						throw new LibrarySystemException(exceptionMessage);
					}

					// add new book
					map.get(ISBN).addCopy();
					
					List<Book> bookList = new ArrayList<Book>();

					for (Map.Entry<String, Book> entry : map.entrySet()) {
						bookList.add(entry.getValue());
					}
					DataAccessFacade.loadBookMap(bookList);
					
					alert.setHeaderText(null);
					alert.setContentText("Add successful.This book has "+map.get(ISBN).getNumCopies()+" Copies");
					alert.showAndWait();
					List<Author> a = new ArrayList<Author>();
					DataAccessFacade.loadAuthorMap(a);
				} catch (LibrarySystemException ex) {
					messageBar.setFill(Start.Colors.red);
					alert.setHeaderText(null);
					alert.setContentText(ex.getMessage());
					alert.showAndWait();
					// messageBar.setText("Error! " + ex.getMessage());
				}

			}
		});

		Button backBtn = new Button("Back");
		backBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if(fromTo.isEmpty() || fromTo.equals("")){
        			Start.hideAllWindows();
            		Start.primStage().show();
        		}else{
        			if(fromTo.toUpperCase().equals("ADMIN")){
        				if(!AdminWindow.INSTANCE.isInitialized()) {
        					AdminWindow.INSTANCE.init();
        				}
        				AdminWindow.INSTANCE.show();
        			}else if(fromTo.toUpperCase().equals("BOTH")){
        				if(!BothWindow.INSTANCE.isInitialized()) {
        					BothWindow.INSTANCE.init();
        				}
        				BothWindow.INSTANCE.show();
        			}
        		}
        		
        		AddBookCopyWindow.INSTANCE.hide();
			}
		});
		HBox hBack = new HBox(10);
		hBack.setAlignment(Pos.BOTTOM_LEFT);
		hBack.getChildren().add(backBtn);
		grid.add(hBack, 1, 5);
		Scene scene = new Scene(grid,400,200);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
		setScene(scene);
	}

}
