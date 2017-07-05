package business;

import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord {
	private List<CheckoutRecordEntry> checkoutRecordEntry;

	public CheckoutRecord() {
		super();
		checkoutRecordEntry = new ArrayList<CheckoutRecordEntry>();
	}
	
	public void addEntry(CheckoutRecordEntry entry){
		checkoutRecordEntry.add(entry);
	}
	
	
	public CheckoutRecordEntry getEntry(String ISBN){
		for(CheckoutRecordEntry entry:checkoutRecordEntry){
			if(entry.getBookCopy().getBook().getIsbn().equals(ISBN))
				return entry;
		}
		return null;
	}

}
