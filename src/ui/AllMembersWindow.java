package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.Address;
import business.Author;
import business.Book;
import business.LibraryMember;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AllMembersWindow extends Stage implements LibWindow {
	public static final AllMembersWindow INSTANCE = new AllMembersWindow();
	private  TableView<MemberInfo> table;
	
	private boolean isInitialized = false;
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}
	private static String fromTo = "";
	public void setData(String data) {
		fromTo = data;
	}
	private AllMembersWindow() {}
	
	public void init() {
		table = new TableView<>();
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("All Member");
        scenetitle.setFont(Font.font("Georgia", FontWeight.NORMAL, 20)); //Tahoma
        grid.add(scenetitle, 0, 0, 2, 1);

//----------------
        
        List<MemberInfo> memberInfoList = new ArrayList<MemberInfo>();
        
    	DataAccess da = new DataAccessFacade();
		Map<String,LibraryMember> memberInfoMap = da.readMemberMap();
		
		StringBuilder sb = new StringBuilder();
		
		LibraryMember member = null;
		MemberInfo memberInfo = null;
		Address address = null;
		
		for (Map.Entry<String, LibraryMember> entry : memberInfoMap.entrySet()) {
			member = entry.getValue();
			address = member.getAddress();
			System.out.println("member.getLastName():"+member.getLastName());
			memberInfo = new MemberInfo(member.getMemberId(), member.getFirstName(),member.getLastName(),address.getStreet(),
					address.getCity(),address.getState(),member.getTelephone());
			memberInfoList.add(memberInfo);
		}
		
        // Set Column
        final ObservableList<MemberInfo> data = FXCollections.observableArrayList(memberInfoList);
        
        TableColumn IdCol = new TableColumn("ID");
        IdCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        
        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        
        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        
        TableColumn phoneNumCol = new TableColumn("Telephone No.");
        phoneNumCol.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        
        TableColumn streetCol = new TableColumn("Street");
        streetCol.setCellValueFactory(new PropertyValueFactory<>("street"));
        
        TableColumn cityCol = new TableColumn("City");
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        
        TableColumn stateCol = new TableColumn("State");
        stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));
        
        table.setItems(data);
        table.setPrefWidth(800);
        table.getColumns().addAll(IdCol, firstNameCol, lastNameCol,phoneNumCol,streetCol,cityCol,stateCol);
        
        
        grid.add(table, 0, 1);
        
        //-----------------
//		ta = new TextArea();
//		grid.add(ta, 0,1);	
		
		Button backBtn = new Button("Back");
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		if(fromTo.isEmpty()){
        			Start.hideAllWindows();
            		Start.primStage().show();
        		}else{
        			if(fromTo.toUpperCase().equals("ADMIN")){
        				if(!AdminWindow.INSTANCE.isInitialized()) {
        					AdminWindow.INSTANCE.init();
        				}
        				AllMembersWindow.INSTANCE.hide();
        				AdminWindow.INSTANCE.show();
        			}else if(fromTo.toUpperCase().equals("BOTH")){
        				if(!BothWindow.INSTANCE.isInitialized()) {
        					BothWindow.INSTANCE.init();
        				}
        				AllMembersWindow.INSTANCE.hide();
        				BothWindow.INSTANCE.show();
        			}
        		}
        		
        	}
        });
        HBox hBack = new HBox(10);
        hBack.setAlignment(Pos.BOTTOM_LEFT);
        hBack.getChildren().add(backBtn);
        grid.add(hBack, 0, 2);
		Scene scene = new Scene(grid);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);
	}
}
