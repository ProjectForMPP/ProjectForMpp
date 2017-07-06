package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.Author;
import business.Book;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AllBooksWindow extends Stage implements LibWindow {
	public static final AllBooksWindow INSTANCE = new AllBooksWindow();
	private  TableView<BookInfo> table;
	
	private static String fromTo = "";
	public void setData(String data) {
		fromTo = data;
	}
	
	private boolean isInitialized = false;
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}
	
	private AllBooksWindow() {}
	
	public void init() {
		 table = new TableView<>();
		 
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("All Book");
        scenetitle.setFont(Font.font("Georgia", FontWeight.NORMAL, 20)); //Tahoma
        grid.add(scenetitle, 0, 0, 2, 1);
		//----------------
        
        List<BookInfo> bookInfoList = new ArrayList<BookInfo>();
        
        //
    	DataAccess da = new DataAccessFacade();
		Map<String,Book> bookMap = da.readBooksMap();
		
		StringBuilder sb = new StringBuilder();
		
		Book book = null;
		BookInfo bookInfo = null;
		
		//String isbn, String authorName,String CopiesNum, String Title,
		//String CopyNumber, String member,String returnDay
		for (Map.Entry<String, Book> entry : bookMap.entrySet()) {
			book = entry.getValue();
			String authors = "";
			for(Author author:book.getAuthors()){
				authors += author.getFirstName() +" " + author.getLastName()+" ";
			}
			bookInfo = new BookInfo(book.getIsbn(), authors, String.valueOf(book.getNumCopies()),book.getTitle(),
					String.valueOf(book.getCopyNums()),"","", Boolean.toString(book.isAvailable()));
			
			System.out.println("---:"+Boolean.toString(book.isAvailable()));
			bookInfoList.add(bookInfo);
		}
        //
        final ObservableList<BookInfo> data = FXCollections.observableArrayList(bookInfoList);
        
        TableColumn isbnCol = new TableColumn("ISBN");
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        
        TableColumn authorNameCol = new TableColumn("Author Name");
        authorNameCol.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        
        TableColumn CopiesNumCol = new TableColumn("Copies Num");
        CopiesNumCol.setCellValueFactory(new PropertyValueFactory<>("CopiesNum"));
        
        TableColumn TitleCol = new TableColumn("Title");
        TitleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        
        TableColumn CopyNumberCol = new TableColumn("Copy Number");
        CopyNumberCol.setCellValueFactory(new PropertyValueFactory<>("CopyNumber"));
        
        TableColumn memberCol = new TableColumn("isAvailable");
        memberCol.setCellValueFactory(new PropertyValueFactory<>("isAvailable"));
        
        table.setItems(data);
        table.setPrefWidth(800);
        table.getColumns().addAll(isbnCol, authorNameCol, CopiesNumCol,TitleCol,CopyNumberCol,memberCol);
        
        
        grid.add(table, 0, 1);
        
        //-----------------
		Button backBtn = new Button("Back to Main");
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
        		AllBooksWindow.INSTANCE.hide();
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
