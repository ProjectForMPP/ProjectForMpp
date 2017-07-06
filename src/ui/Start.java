package ui;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import business.Author;
import business.Book;
import business.ControllerInterface;
import business.LoginException;
import business.SystemController;
import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Start extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	private static Stage primStage = null;
	public static Stage primStage() {
		return primStage;
	}
	
	public static class Colors {
		static Color green = Color.web("#034220");
		static Color red = Color.FIREBRICK;
	}
	
	private static Stage[] allWindows = { 
		LoginWindow.INSTANCE,
		AllMembersWindow.INSTANCE,	
		AllBooksWindow.INSTANCE,
		AddNewMemberWindow.INSTANCE // add
	};
	
	public static void hideAllWindows() {
		primStage.hide();
		for(Stage st: allWindows) {
			st.hide();
		}
	}
	

	@Override
	public void start(Stage primaryStage) {
		primStage = primaryStage;
		primaryStage.setTitle("Main Page");
				
		GridPane grid = new GridPane();
        grid.setId("top-container");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Login");
        scenetitle.setFont(Font.font("Georgia", FontWeight.NORMAL, 20)); //Tahoma
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        //userTextField.setPrefColumnCount(10);
        //userTextField.setPrefWidth(30);
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);
        grid.setGridLinesVisible(false) ;

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button loginBtn = new Button("Log in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(loginBtn);
        grid.add(hbBtn, 1, 4);

        HBox messageBox = new HBox(10);
        messageBox.setAlignment(Pos.BOTTOM_RIGHT);
        grid.add(messageBox, 1, 6);
        
        loginBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		try {
        			ControllerInterface c = new SystemController();
        			Auth currentAuth = c.login(userTextField.getText().trim(), pwBox.getText().trim());
        			if(currentAuth==Auth.LIBRARIAN){
        				// Librarian login
        				Start.hideAllWindows();
            			if(!LibrarianWindow.INSTANCE.isInitialized()) {
            				LibrarianWindow.INSTANCE.init();
            			}
            			LibrarianWindow.INSTANCE.show();
        			}else if(currentAuth==Auth.ADMIN){
        				// Admin login
        				Start.hideAllWindows();
            			if(!AdminWindow.INSTANCE.isInitialized()) {
            				AdminWindow.INSTANCE.init();
            			}
            			AdminWindow.INSTANCE.show();
        			}else{
        				// a user is both Librarian and Admin
        				Start.hideAllWindows();
            			if(!BothWindow.INSTANCE.isInitialized()) {
            				BothWindow.INSTANCE.init();
            			}
            			BothWindow.INSTANCE.show();
        			}	
        		} catch(LoginException ex) {
//        			messageBar.setFill(Start.Colors.red);
//        			messageBar.setText("Error! " + ex.getMessage());
        		}
        	   
        	}
        });
        
		Scene scene = new Scene(grid, 420, 375);
		primaryStage.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
		primaryStage.show();
	}
	
}
