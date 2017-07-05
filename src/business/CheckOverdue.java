package business;

import java.time.LocalDate;

public class CheckOverdue {
	private String ISBN;
	private String memberId;
	private LocalDate dueDate;
	private CheckoutRecord chkRecord;
	
	public CheckOverdue(String iSBN, String memberId, LocalDate dueDate) {
		super();
		ISBN = iSBN;
		this.memberId = memberId;
		this.dueDate = dueDate;
	}
	public boolean isOverDue(String ISBN){
		return chkRecord.isEntry(ISBN);
		
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	

}
