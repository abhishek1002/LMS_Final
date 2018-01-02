package com.lms.service;

import java.util.List;

import com.lms.core.UserVO;
import com.lms.entities.BookVO;

public class LibraryService {

	private LibraryMgmtDAO libraryDao = new LibraryMgmtDAOImpl();
	
	public void addBookDetails(BookVO book) {
		
		libraryDao.addBook(book);
		
	}

	public List<BookVO> searchBookDetails(String title, String author) {
		
		return libraryDao.searchBook(title+"_"+author);
		
	}

	public void addUserDetails(UserVO user) {
		libraryDao.addUser(user);	
	}

	public UserVO searchUserDetails(String name) {

		return libraryDao.searchUser(name);
	}

	public boolean lendBook(String username, String bookId) {
		if(libraryDao.canIssue(username)){
			libraryDao.lendBook(username,bookId);
			return true;
		}
		return false;
	}

	public boolean setBookLimit(String username, int bookLimit) {
		if(libraryDao.searchUser(username) != null){
			libraryDao.setBookLimit(username,bookLimit);
			return true;
		}
		return false;
	}

	public void returnBook(String username, String bookId) {
		libraryDao.returnBook(username,bookId);
		
	}
}
