package ui;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import business.Author;
import business.Book;
import business.ControllerInterface;
import business.SystemController;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
				
		VBox topContainer = new VBox();
		topContainer.setId("top-container");
		MenuBar mainMenu = new MenuBar();
		VBox imageHolder = new VBox();
		Image image = new Image("ui/library.png", 400, 300, false, false);

        // simply displays in ImageView the image as is
        ImageView iv = new ImageView();
        iv.setImage(image);
        imageHolder.getChildren().add(iv);
        imageHolder.setAlignment(Pos.CENTER);
        HBox splashBox = new HBox();
        Label splashLabel = new Label("The Library System");
        splashLabel.setFont(Font.font("Trajan Pro", FontWeight.BOLD, 30));
        splashBox.getChildren().add(splashLabel);
        splashBox.setAlignment(Pos.CENTER);
		
		topContainer.getChildren().add(mainMenu);
		topContainer.getChildren().add(splashBox);
		topContainer.getChildren().add(imageHolder);
		
		Menu optionsMenu = new Menu("Options");
		MenuItem login = new MenuItem("Login");
		
		login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	hideAllWindows();
    			if(!LoginWindow.INSTANCE.isInitialized()) {
    				LoginWindow.INSTANCE.init();
    			}
    			LoginWindow.INSTANCE.clear();
    			LoginWindow.INSTANCE.show();
            }
        });			
							
		MenuItem bookIds = new MenuItem("All Book Ids");
		bookIds.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
				hideAllWindows();
				if(!AllBooksWindow.INSTANCE.isInitialized()) {
					AllBooksWindow.INSTANCE.init();
				}
				AllBooksWindow.INSTANCE.show();
            }
		});
		
		MenuItem memberIds = new MenuItem("All Member Ids");
		memberIds.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
				hideAllWindows();
				if(!AllMembersWindow.INSTANCE.isInitialized()) {
					AllMembersWindow.INSTANCE.init();
				}
				AllMembersWindow.INSTANCE.show();
            }
		});	
		
		//Add new functional 
		MenuItem newMemberId = new MenuItem("Add New Member Id");
		newMemberId.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
				hideAllWindows();
				if(!AddNewMemberWindow.INSTANCE.isInitialized()) {
					AddNewMemberWindow.INSTANCE.init();
				}
				ControllerInterface ci = new SystemController();
				List<String> ids = ci.allMemberIds();
				Collections.sort(ids);
				System.out.println(ids);
				StringBuilder sb = new StringBuilder();
				for(String s: ids) {
					sb.append(s + "\n");
				}
				System.out.println(sb.toString());
//				AddNewMemberWindow.INSTANCE.setData(sb.toString());
				AddNewMemberWindow.INSTANCE.show();
            }
		});	
		//
		MenuItem newBookId = new MenuItem("Add New Book");
		newBookId.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
				hideAllWindows();
				if(!AddNewBookWindow.INSTANCE.isInitialized()) {
					AddNewBookWindow.INSTANCE.init();
				}
				ControllerInterface ci = new SystemController();
				List<String> ids = ci.allMemberIds();
				Collections.sort(ids);
				System.out.println(ids);
				StringBuilder sb = new StringBuilder();
				for(String s: ids) {
					sb.append(s + "\n");
				}
				System.out.println(sb.toString());
				AddNewBookWindow.INSTANCE.show();
            }
		});	
		
		//
		MenuItem bookCopy = new MenuItem("Add Book Copy");
		bookCopy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
				hideAllWindows();
				if(!AddBookCopyWindow.INSTANCE.isInitialized()) {
					AddBookCopyWindow.INSTANCE.init();
				}
				ControllerInterface ci = new SystemController();
				List<String> ids = ci.allMemberIds();
				Collections.sort(ids);
				System.out.println(ids);
				StringBuilder sb = new StringBuilder();
				for(String s: ids) {
					sb.append(s + "\n");
				}
				System.out.println(sb.toString());
				AddBookCopyWindow.INSTANCE.show();
            }
		});
		//
		optionsMenu.getItems().addAll(login, bookIds, memberIds,newMemberId,newBookId,bookCopy);

		mainMenu.getMenus().addAll(optionsMenu);
		Scene scene = new Scene(topContainer, 420, 375);
		primaryStage.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
		primaryStage.show();
	}
	
}
