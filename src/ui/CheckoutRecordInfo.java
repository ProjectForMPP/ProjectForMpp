package ui;

import javafx.beans.property.SimpleStringProperty;

public class CheckoutRecordInfo {
	private SimpleStringProperty ISBN = new SimpleStringProperty();
	private SimpleStringProperty bookName = new SimpleStringProperty();
	private SimpleStringProperty checkoutDate = new SimpleStringProperty();
	private SimpleStringProperty dueDate = new SimpleStringProperty();
	private SimpleStringProperty memberID = new SimpleStringProperty();
	private SimpleStringProperty copyNumber = new SimpleStringProperty();
	
	CheckoutRecordInfo(String ISBN, 
					   String bookName,
					   String checkoutDate,
					   String dueDate,
					   String memberID,
					   String copyNumber){
		this.ISBN = new SimpleStringProperty(ISBN);
		this.bookName = new SimpleStringProperty(bookName);
		this.checkoutDate = new SimpleStringProperty(checkoutDate);
		this.dueDate = new SimpleStringProperty(dueDate);
		this.memberID = new SimpleStringProperty(memberID);
		this.copyNumber = new SimpleStringProperty(copyNumber);
	}
	
	public String getISBN() {
		return ISBN.get();
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
	public String getMemberID() {
		return memberID.get();
	}
	public String getCopyNumber() {
		return copyNumber.get();
	}
	public void setISBN(String ISBN) {
		this.ISBN.set(ISBN);
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
	public void setMemberID(String memberID) {
		this.memberID.set(memberID);
	}
	public void setCopyNumber(String copyNumber) {
		this.copyNumber.set(copyNumber);
	}
}
