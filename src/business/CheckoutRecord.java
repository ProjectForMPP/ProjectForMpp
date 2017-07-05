package business;

import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord {
	private List<CheckoutRecordEntry> checkoutRecordEntries;

	public CheckoutRecord() {
		super();
		checkoutRecordEntries = new ArrayList<CheckoutRecordEntry>();
	}
	
	public void addEntry(CheckoutRecordEntry entry){
		checkoutRecordEntries.add(entry);
	}
	
	
	public CheckoutRecordEntry getEntry(String ISBN){
		for(CheckoutRecordEntry entry:checkoutRecordEntries){
			if(entry.getBookCopy().getBook().getIsbn().equals(ISBN))
				return entry;
		}
		return null;
	}

}
