
package business;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.Book;
import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {

	public static Auth currentAuth = null;
	
	public CheckoutRecord checkoutBook(String memberId,String ISBN) throws LibrarySystemException{
		DataAccess da = new DataAccessFacade(); 
		HashMap<String, LibraryMember>memberMap = da.readMemberMap();
		HashMap<String, Book>bookMap = da.readBooksMap();
		if(!memberMap.containsKey(memberId)){
			throw new LibrarySystemException("MemberId"+memberId+"not found");
		}
		
		LibraryMember libraryMember = memberMap.get(memberId);			// get the member for checkout
		if(!bookMap.containsKey(ISBN)){
			throw new LibrarySystemException("ISBN"+ISBN+"not found");
		}
		Book book = bookMap.get(ISBN);							// get the book for checkout
		if(!book.isAvailable()){
			throw new LibrarySystemException("Book is unvailable");
		}
		
		// get the date now and get the due Date
		int dueDay = book.getMaxCheckoutLength();					// the Max Checkout length of days
		Date nowDate = new Date();									// get the date now
		Date dueDate = getDueDate(nowDate,dueDay);					// get due date
		CheckoutRecord checkoutRecord = new CheckoutRecord();		// declare checkoutRecord
		BookCopy bookCopy = book.getNextAvailableCopy();			// get the next available copy of the book
		CheckoutRecordEntry checkoutRecordEntry = new CheckoutRecordEntry(DateToLocalDate(nowDate),DateToLocalDate(dueDate),bookCopy,checkoutRecord);
		bookCopy.changeAvailability();								// change book copy unavailability
		checkoutRecord.addEntry(checkoutRecordEntry);				// add the checkoutRecordEntry to checkoutRecord
		libraryMember.setCheckoutRecord(checkoutRecord);			// add the checkout record to library member
		//System.out.println(libraryMember);
		//System.out.println(checkoutRecord);
		//System.out.println(checkoutRecordEntry);
		
		return libraryMember.getCheckoutRecord();
		
		//LocalDate dueDate = localDate.
		//System.out.println(dueDate);
		//int maxCheckoutLength = book.getMaxCheckoutLength();
		//BookCopy bookCopy = book.getNextAvailableCopy();
		
		//((LibraryMember) libraryMember).checkout(bookCopy,LocalDate.now(),LocalDate.now().getDayOfMonth()+maxCheckoutLength);
		//DataAccessFacade.saveToStorage(DataAccessFacade.StorageType.MEMBERS, libraryMember);
		//DataAccessFacade.saveToStorage(DataAccessFacade.StorageType.BOOKS, book);
	}
	
	// Search for Due Date
	public Book SearchDueDate(String ISBN) throws LibrarySystemException{
		DataAccess da = new DataAccessFacade(); 
		HashMap<String, Book>bookMap = da.readBooksMap();
		if(!bookMap.containsKey(ISBN)){
			throw new LibrarySystemException("ISBN "+ ISBN +" not found");
		}
		return bookMap.get(ISBN);	// get the book
	}
	
	// get the due date: just for get the date after the date d.
	public Date getDueDate(Date d,int day){  
		Calendar now =Calendar.getInstance();  
		   now.setTime(d);  
		   now.set(Calendar.DATE,now.get(Calendar.DATE)+day);  
		   return now.getTime();  
	}
	
	// Date to LocalDate
	public LocalDate DateToLocalDate(Date d){
		LocalDate localDate;
		Instant instant = d.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		localDate = localDateTime.toLocalDate();
		return localDate;
	}
	
	@Override
	public Auth login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if (!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if (!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}

		return map.get(id).getAuthorization();
	}

	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}

	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}

	@Override
	public void addNewLibaryMember(LibraryMember member) throws LibrarySystemException {

		// TODO Auto-generated method stub

		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember> map = da.readMemberMap();
		if (map.containsKey(member.getMemberId())) {
			throw new LibrarySystemException("ID " + member.getMemberId() + " is existence");
		}

		List<LibraryMember> members = new ArrayList<LibraryMember>();
		for (Map.Entry<String, LibraryMember> entry : map.entrySet()) {
			members.add(entry.getValue());
		}

		members.add(member);
		DataAccessFacade.loadMemberMap(members);
	}

	@Override
	public void addNewBook(Book book, int bookCopiesNum) throws LibrarySystemException {
		// TODO Auto-generated method stub
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> map = da.readBooksMap();

		while (bookCopiesNum > 0) {
			book.addCopy();
			bookCopiesNum--;
		}

		List<Book> books = new ArrayList<Book>();
		for (Map.Entry<String, Book> entry : map.entrySet()) {
			books.add(entry.getValue());
		}

		books.add(book);
		DataAccessFacade.loadBookMap(books);
	}

	@Override
	public void addNewAuthor(Author author) throws LibrarySystemException {
		// TODO Auto-generated method stub
		DataAccess da = new DataAccessFacade();
		HashMap<String, Author> map = da.readAuthorMap();
		if(map == null){
			map = new HashMap<String, Author>();
		}
		List<Author> authors = new ArrayList<Author>();
		for (Map.Entry<String, Author> entry : map.entrySet()) {
			authors.add(entry.getValue());
		}

		authors.add(author);
		DataAccessFacade.loadAuthorMap(authors);
	}
	// Search Member
	public LibraryMember searchMember(String memberId){
		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember> map = da.readMemberMap();
		if (map.containsKey(memberId)) {
			return map.get(memberId);
		}else{
			return null;
		}
	}
}

