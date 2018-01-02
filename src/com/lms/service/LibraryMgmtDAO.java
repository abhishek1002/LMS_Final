package com.lms.service;

import java.util.List;

import com.lms.core.UserVO;
import com.lms.entities.BookVO;

public interface LibraryMgmtDAO {

	void addBook(BookVO bookVO);

	List<BookVO> searchBook(String searchCriteria);

	void addUser(UserVO user);

	UserVO searchUser(String name);

	void lendBook(String username, String bookId);

	boolean canIssue(String username);

	void setBookLimit(String username, int bookLimit);

	void returnBook(String username, String bookId);

}
