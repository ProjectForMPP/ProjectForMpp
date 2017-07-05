package business;

import java.time.LocalDate;

public class CheckOverdue {
	private String ISBN;
	private String memberId;
	private LocalDate dueDate;
	private CheckoutRecord chkRecord;
	private LibraryMember libraryMember ;
	
	public CheckOverdue(String iSBN, String memberId, LocalDate dueDate) {
		super();
		ISBN = iSBN;
		this.memberId = memberId;
		this.dueDate = dueDate;
	}
	public boolean isOverDue(String ISBN) {
		if(chkRecord.getEntry(ISBN)==null) 
			return false;
		if(chkRecord.getEntry(ISBN).getDueDate().isBefore(LocalDate.now()))
			return true;
		else
			return false;
	}
	public CheckoutRecordEntry getOverdueEntry(String ISBN) throws LibrarySystemException{
		if(chkRecord.getEntry(ISBN)==null) throw new LibrarySystemException(ISBN+" of book is not checkout");
		if(this.isOverDue(ISBN))
			return chkRecord.getEntry(ISBN);
		else 
			return null;
	}
	
//	public LibraryMember getOverdueMember(String ISBN){
//		if(libraryMember.getCheckoutRecord().getEntry(ISBN).equals(obj)
//	}
	
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
		try {
			LocalDate dueDate= this.getOverdueEntry(ISBN).getDueDate();
		} catch (LibrarySystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	

}
