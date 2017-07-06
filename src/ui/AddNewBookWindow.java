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

public class AddNewBookWindow extends Stage implements LibWindow {
	public static final AddNewBookWindow INSTANCE = new AddNewBookWindow();
	private boolean isInitialized = false;
	private Book book;
	Alert alert = new Alert(Alert.AlertType.INFORMATION);

	private Text messageBar = new Text();

	public void clear() {
		messageBar.setText("");
	}

	private AddNewBookWindow() {
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

	private static String fromTo = "";
	public void setData(String data) {
		fromTo = data;
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		//clear AUTORS file data
		List<Author> a = new ArrayList<Author>();
		DataAccessFacade.loadAuthorMap(a);
		
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Add New Book");
		scenetitle.setFont(Font.font("Georgia", FontWeight.NORMAL, 20)); // Tahoma
		grid.add(scenetitle, 0, 0, 2, 1);

		// ISBN
		Label ISBN = new Label("ISBN:");
		grid.add(ISBN, 0, 1);

		TextField ISBNTextField = new TextField();
		grid.add(ISBNTextField, 1, 1);

		// title
		Label title = new Label("Title:");
		grid.add(title, 0, 2);
		grid.setGridLinesVisible(false);

		TextField titleTextField = new TextField();
		grid.add(titleTextField, 1, 2);

		// copiesNum
		Label copiesNum = new Label("CopiesNum:");
		grid.add(copiesNum, 2, 1);

		TextField copiesNumTextField = new TextField();
		grid.add(copiesNumTextField, 3, 1);

		// maximum
		Label maximum = new Label("Maximum:");
		grid.add(maximum, 2, 2);
		grid.setGridLinesVisible(false);

		TextField maximumTextField = new TextField();
		grid.add(maximumTextField, 3, 2);

		// add authors button

		Label authors = new Label("Authors:");
		grid.add(authors, 0, 3);

		Button authorsButton = new Button("Add Author");
		grid.add(authorsButton, 1, 3);

		authorsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (!AddNewAuthorsWindow.INSTANCE.isInitialized()) {
					AddNewAuthorsWindow.INSTANCE.init();
				}
				AddNewAuthorsWindow.INSTANCE.show();
			}
		});

		// add submit button
		Button submitBtn = new Button("Submit");
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
					String title = titleTextField.getText().trim();
					String authors = authorsButton.getText().trim();
					String maximum = maximumTextField.getText().trim();
					String copiesNum = copiesNumTextField.getText().trim();

					// rules
					String exceptionMessage = "This item must not be empty:";
					boolean throwException = false;
					if (ISBN == null || ISBN.isEmpty()) {
						exceptionMessage += "ISBN";
						throwException = true;
					} else if (title == null || title.isEmpty()) {
						exceptionMessage += "title";
						throwException = true;
					} else if (authors == null || authors.isEmpty()) {
						exceptionMessage += "authors";
						throwException = true;
					} else if (maximum == null || maximum.isEmpty()) {
						exceptionMessage += "maximum";
						throwException = true;
					} else if (copiesNum == null || copiesNum.isEmpty()) {
						exceptionMessage += "copiesNum";
						throwException = true;
					} else {
						if (!maximum.matches("[0-9]+")) {
							exceptionMessage = "Maximum Must Be Number";
							throwException = true;
						}

						if (!copiesNum.matches("[0-9]+")) {
							exceptionMessage = "CopiesNum Must Be Number";
							throwException = true;
						}
					}

					if (throwException) {
						throw new LibrarySystemException(exceptionMessage);
					}

					// add new book
					List<Author> authorList = new ArrayList<Author>();

					// get Authors
					DataAccess da = new DataAccessFacade();
					HashMap<String, Author> map = da.readAuthorMap();
					if (map == null || map.isEmpty()) {
						throw new LibrarySystemException("The Author is empty");

					} else {

						for (Map.Entry<String, Author> entry : map.entrySet()) {
							authorList.add(entry.getValue());
						}
					}

					book = new Book(ISBN, title, Integer.parseInt(maximum), authorList);
					c.addNewBook(book, Integer.parseInt(copiesNum));

					// messageBar.setFill(Start.Colors.green);
					// messageBar.setText("Add successful");
					alert.setHeaderText(null);
					alert.setContentText("Add successful");
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
				
				AddNewBookWindow.INSTANCE.hide();
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
