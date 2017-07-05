package ui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SearchMemberWindow extends Stage implements LibWindow {
	public static final SearchMemberWindow INSTANCE = new SearchMemberWindow();
	
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
	private SearchMemberWindow() {}
	
	public void init() {
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Input member ID:");
        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); 
        grid.add(scenetitle, 0, 0, 2, 1);

        // Member ID Label
        Label labelMemberID = new Label();
        labelMemberID.setText("Member ID:");
        grid.add(labelMemberID, 0, 1);
        // Member ID TextFiled
        TextField textFieldMemberID = new TextField();
        grid.add(textFieldMemberID, 1, 1);
        // Search Button
        Button btnSearch = new Button();
        btnSearch.setText("Search");
        grid.add(btnSearch, 2, 1);
        // Error Message Text
        Text errorMessageBar = new Text();
        grid.add(errorMessageBar, 1, 2);
        // Print Message
        Text printMessageBar = new Text();
        grid.add(printMessageBar, 1, 3);
        
        /*/ Logout Button
        Button btnLogout = new Button();
        btnLogout.setText("Logout");
        btnLogout.setOnAction(new EventHandler(){
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				SearchMemberWindow.INSTANCE.hide();
				Start.hideAllWindows();
        		Start.primStage().show();
			}
        });
        
        HBox hbxBtn3 = new HBox(10);
        hbxBtn3.setAlignment(Pos.BOTTOM_LEFT);
        hbxBtn3.getChildren().add(btnLogout);
        grid.add(hbxBtn3, 1, 3);
        */
		
		Scene scene = new Scene(grid);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);
	}
}
