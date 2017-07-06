package ui;

import javafx.beans.property.SimpleStringProperty;

public class CheckoutRecordInfo {
	private SimpleStringProperty bookName = new SimpleStringProperty();
	private SimpleStringProperty checkoutDate = new SimpleStringProperty();
	private SimpleStringProperty dueDate = new SimpleStringProperty();
	
	CheckoutRecordInfo(String bookName, String checkoutDate,String dueDate){
		this.bookName = new SimpleStringProperty(bookName);
		this.checkoutDate = new SimpleStringProperty(checkoutDate);
		this.dueDate = new SimpleStringProperty(dueDate);
	}
	
	
	public String getBookName() {
		return bookName.get();
	}
	public String getCheckoutDate() {
		return checkoutDate.get();
	}
	public String getDueDate() {
		return dueDate.get();
	}
	public void setBookName(String bookName) {
		this.bookName.set(bookName);
	}
	public void setCheckoutDate(String checkoutDate) {
		this.checkoutDate.set(checkoutDate);
	}
	public void setDueDate(String dueDate) {
		this.dueDate.set(dueDate);
	}
}
