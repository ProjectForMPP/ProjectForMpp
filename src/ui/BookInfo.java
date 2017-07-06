package ui;

import javafx.beans.property.SimpleStringProperty;

public class BookInfo {
	private SimpleStringProperty ISBN = new SimpleStringProperty();
	private SimpleStringProperty authorName = new SimpleStringProperty();
	private SimpleStringProperty CopiesNum = new SimpleStringProperty();
	private SimpleStringProperty Title = new SimpleStringProperty();
	private SimpleStringProperty CopyNumber = new SimpleStringProperty();
	private SimpleStringProperty member = new SimpleStringProperty();
	private SimpleStringProperty returnDay = new SimpleStringProperty();
	
	BookInfo(String isbn, String authorName,String CopiesNum, String Title,
			String CopyNumber, String member,String returnDay){
		this.ISBN = new SimpleStringProperty(isbn);
		this.authorName = new SimpleStringProperty(authorName);
		this.CopiesNum = new SimpleStringProperty(CopiesNum);
		this.Title = new SimpleStringProperty(Title);
		this.CopyNumber = new SimpleStringProperty(CopyNumber);
		this.member = new SimpleStringProperty(member);
		this.returnDay = new SimpleStringProperty(returnDay);
	}
	
	
	public String getISBN() {
		return ISBN.get();
	}
	public String getAuthorName() {
		return authorName.get();
	}
	public SimpleStringProperty getCopiesNum() {
		return CopiesNum;
	}
	public String getTitle() {
		return Title.get();
	}
	public String getCopyNumber() {
		return CopyNumber.get();
	}
	public String getMember() {
		return member.get();
	}
	public String getReturnDay() {
		return returnDay.get();
	}
	
	
	//
	public void setISBN(String iSBN) {
		this.ISBN.set(iSBN);
	}
	public void setAuthorName(String authorName) {
		this.authorName.set(authorName);
	}
	public void setCopiesNum(String copiesNum) {
		this.CopiesNum.set(copiesNum);
	}
	public void setTitle(String title) {
		this.Title.set(title);
	}
	public void setCopyNumber(String copyNumber) {
		this.CopyNumber.set(copyNumber);
	}
	public void setMember(String member) {
		this.member.set(member);
	}
	public void setReturnDay(String returnDay) {
		
		this.returnDay.set(returnDay);
	}
	
}
