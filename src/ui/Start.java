package ui;

import business.ControllerInterface;
import business.LoginException;
import business.SystemController;
import dataaccess.Auth;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Start extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	private Text messageBar = new Text();

	public void clear() {
		messageBar.setText("");
	}

	private static Stage primStage = null;

	public static Stage primStage() {
		return primStage;
	}

	public static class Colors {
		static Color green = Color.web("#034220");
		static Color red = Color.FIREBRICK;
	}

	private static Stage[] allWindows = { LoginWindow.INSTANCE, AllMembersWindow.INSTANCE, AllBooksWindow.INSTANCE,
			AddNewMemberWindow.INSTANCE // add
	};

	public static void hideAllWindows() {
		primStage.hide();
		for (Stage st : allWindows) {
			st.hide();
		}
	}

	@Override
	public void start(Stage primaryStage) {
		primStage = primaryStage;
		primaryStage.setTitle("Main Page");

		VBox topContainer = new VBox();
		topContainer.setId("top-container");
		Image image = new Image("ui/library.png", 400, 300, false, false);

		// simply displays in ImageView the image as is
		HBox splashBox = new HBox();
		Label splashLabel = new Label("The Library System");
		splashLabel.setFont(Font.font("Georgia", 30));
		splashLabel.setPadding(new Insets(20, 0, 0, 0));
		splashBox.getChildren().add(splashLabel);
		splashBox.setAlignment(Pos.CENTER);

		topContainer.getChildren().add(splashBox);

		//
		BackgroundImage myBI = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		//
		topContainer.setBackground(new Background(myBI));
		//
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Label userName = new Label("User Name:");
		grid.add(userName, 0, 1);

		TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Label pw = new Label("Password:");
		grid.add(pw, 0, 2);
		grid.setGridLinesVisible(false);

		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);

		Button loginBtn = new Button("Log In");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(loginBtn);
		grid.add(hbBtn, 1, 4);

		HBox messageBox = new HBox(10);
		messageBox.setAlignment(Pos.BOTTOM_RIGHT);
		messageBox.getChildren().add(messageBar);
		grid.add(messageBox, 1, 5);

		loginBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					ControllerInterface c = new SystemController();
					
					if (userTextField.getText().trim() == null || userTextField.getText().trim().equals("")) {
						throw new LoginException("User Name Is Empty");
					}

					if (pwBox.getText().trim() == null || pwBox.getText().trim().equals("")) {
						throw new LoginException("Password Is Empty");
					}

					Auth currentAuth = c.login(userTextField.getText().trim(), pwBox.getText().trim());
					if (currentAuth == Auth.LIBRARIAN) {
						// Librarian login
						Start.hideAllWindows();
						if (!LibrarianWindow.INSTANCE.isInitialized()) {
							LibrarianWindow.INSTANCE.init();
						}
						LibrarianWindow.INSTANCE.show();
					} else if (currentAuth == Auth.ADMIN) {
						// Admin login
						Start.hideAllWindows();
						if (!AdminWindow.INSTANCE.isInitialized()) {
							AdminWindow.INSTANCE.init();
						}
						AdminWindow.INSTANCE.show();
					} else {
						// a user is both Librarian and Admin
						Start.hideAllWindows();
						if (!BothWindow.INSTANCE.isInitialized()) {
							BothWindow.INSTANCE.init();
						}
						BothWindow.INSTANCE.show();
					}
				} catch (LoginException ex) {
					messageBar.setFill(Start.Colors.red);
					messageBar.setText("" + ex.getMessage());
				}

			}
		});

		topContainer.getChildren().add(grid);
		Scene scene = new Scene(topContainer, 400, 250);
		primaryStage.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
		primaryStage.show();
	}

}
