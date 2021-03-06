package dataaccess;

import java.util.HashMap;

import business.Author;
import business.Book;
import business.CheckoutSet;
import business.LibraryMember;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public HashMap<String, Author> readAuthorMap();
	public HashMap<String, CheckoutSet> readCheckoutRecordsMap();
}
