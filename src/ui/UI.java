package ui;

import business.LibraryMember;
import business.LibrarySystemException;
import business.SystemController;

	public class UI {
		private SystemController  sysController;
		
		public void checkout(String memberId, String ISBN){
			try {
				sysController.checkoutBook(memberId, ISBN);
			} catch (LibrarySystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(memberId +"check out "+ ISBN+" is OK!");
		}
		
		

}
