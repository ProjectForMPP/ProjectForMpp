package business;

import java.util.List;

import dataaccess.Auth;

public interface ControllerInterface {
	public Auth login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	//add By LeiChen
	public void addNewLibaryMember(LibraryMember member) throws LibrarySystemException;
	
}
