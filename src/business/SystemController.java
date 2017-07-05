
package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.Book;
import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {

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
		// if(map.containsKey(member.getMemberId())) {
		// throw new LibrarySystemException("ID " + member.getMemberId() + " is
		// existence");
		// }

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

		List<Author> authors = new ArrayList<Author>();
		for (Map.Entry<String, Author> entry : map.entrySet()) {
			authors.add(entry.getValue());
		}

		authors.add(author);
		DataAccessFacade.loadAuthorMap(authors);
	}

}
