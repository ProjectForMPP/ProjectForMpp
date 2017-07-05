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

}
