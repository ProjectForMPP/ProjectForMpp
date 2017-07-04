<<<<<<< HEAD
package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.Book;
import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	
	public void checkoutBook(String memberId,String ISBN) throws LibrarySystemException{
		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember>map1 = da.readMemberMap();
		HashMap<String, Book>map2 = da.readBooksMap();
		if(!map1.containsKey(memberId)){
			throw new LibrarySystemException("MemberId"+memberId+"not found");
		}
		
		Person libraryMember = map1.get(memberId);
		if(!map2.containsKey(ISBN)){
			throw new LibrarySystemException("ISBN"+ISBN+"not found");
		}
		Book bookCopy = map2.get(ISBN);
		if(!bookCopy.isAvailable()){
			throw new LibrarySystemException("BookCopy is unvailable");
		}
		bookCopy.getNextAvailableCopy();
		int maxCheckoutLength = bookCopy.getMaxCheckoutLength();
		((LibraryMember) libraryMember).checkout(bookCopy,LocalDate.now(),LocalDate.now().getDayOfMonth()+maxCheckoutLength);
		
	}
	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
		
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
	
	
}
=======
package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.Book;
import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
	
	public Auth login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
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
		System.out.println("--------"+member.getFirstName());
		
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		System.out.println("----:"+map);
		if(map.containsKey(member.getMemberId())) {
			throw new LibrarySystemException("ID " + member.getMemberId() + " is existence");
		}
		
		List<LibraryMember> members = new ArrayList<LibraryMember>();
		members.add(member);
		DataAccessFacade.loadMemberMap(members);
		
	}
	
	
}
>>>>>>> 04a0959c7a61f48be86d4b7773a4f5e473a0147d
