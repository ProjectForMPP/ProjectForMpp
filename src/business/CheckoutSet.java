package business;

import java.io.Serializable;
import java.time.LocalDate;

// CheckoutSet: a structure of checkout record,saved in file system, and also used by checkout method
// Author:Ma

public class CheckoutSet implements Serializable{
	
	private static final long serialVersionUID = 6110690276685962829L;
	
	private String ISBN;
	private String bookName;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private String memberID;	// the member who gets the book
	int copyNumber;				// the copy number of the book copy
	// constructor
	public CheckoutSet(){}
	
	public CheckoutSet(String ISBN,String bookName,LocalDate checkoutDate,LocalDate dueDate,String memberID,int copyNumber){
		this.ISBN = ISBN;
		this.bookName = bookName;
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
		this.memberID = memberID;
		this.copyNumber = copyNumber;
	}
	public void setISBN(String ISBN){
		this.ISBN = ISBN;
	}
	public String getISBN(){
		return this.ISBN;
	}
	public void setBookName(String bookName){
		this.bookName = bookName;
	}
	
	public String getBookName(){
		return this.bookName;
	}
	
	public void setCheckoutDate(LocalDate checkoutDate){
		this.checkoutDate = checkoutDate;
	}
	
	public LocalDate getCheckoutDate(){
		return this.checkoutDate;
	}
	public void setDueDate(LocalDate dueDate){
		this.dueDate = dueDate;
	}
	
	public LocalDate getDueDate(){
		return this.dueDate;
	}
	public void setMemberID(String memberID){
		this.memberID = memberID;
	}
	
	public String getMemberID(){
		return this.memberID;
	}
	
	public void setCopyNumber(int copyNumber){
		this.copyNumber = copyNumber;
	}
	
	public int getCopyNumber(){
		return this.copyNumber;
	}
}
