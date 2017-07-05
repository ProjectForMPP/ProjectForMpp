package business;

import java.util.List;

import dataaccess.Auth;

public interface ControllerInterface {
	public Auth login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	//add By LeiChen
	public void addNewLibaryMember(LibraryMember member) throws LibrarySystemException;
	public void addNewBook(Book book,int bookCopiesNum) throws LibrarySystemException;
	public void addNewAuthor(Author author) throws LibrarySystemException;
	
}
