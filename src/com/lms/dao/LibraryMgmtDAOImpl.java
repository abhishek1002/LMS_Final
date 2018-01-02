package com.lms.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lms.entities.BookVO;
import com.lms.entities.UserVO;

public class LibraryMgmtDAOImpl implements LibraryMgmtDAO {

	private Map<String, BookVO> bookDetails = new HashMap<>();
	private Map<String, List<String>> bookSearchDetails = new HashMap<>();
	private Map<String, UserVO> userDetails = new HashMap<>();
	
	@Override
	public void addBook(BookVO bookVO) {
		bookDetails.put(bookVO.getId(), bookVO);
		
		List<String> bookIdList = bookSearchDetails.get(bookVO.getTitle()+"_"+bookVO.getAuthor());
		if(bookIdList == null){
			bookIdList = new  ArrayList<>();	
		}
		bookIdList.add(bookVO.getId());
		bookSearchDetails.put(bookVO.getTitle()+"_"+bookVO.getAuthor(),bookIdList);
		System.out.println("bookSearchDetails");
	}

	@Override
	public List<BookVO> searchBook(String searchCriteria) {
		System.out.println("SearchBook: "+searchCriteria);
		List<BookVO> searchList = new ArrayList<>(); 
		for(String bookId : bookSearchDetails.get(searchCriteria)){
			searchList.add(bookDetails.get(bookId));
		}
		return searchList;
	}

	@Override
	public void addUser(UserVO user) {
		userDetails.put(user.getName(), user);
	}

	@Override
	public UserVO searchUser(String name) {
		return userDetails.get(name);
	}

	@Override
	public void lendBook(String username, String bookId) {

		
		List<String> bookIdList = userDetails.get(username).getBookList();
		
		if(bookIdList == null){
			bookIdList = new ArrayList<>();
			bookIdList.add(bookId);
		}else{
			bookIdList.add(bookId);
		}
		userDetails.get(username).setBookList(bookIdList);
	}

	@Override
	public boolean canIssue(String username) {
		if(userDetails.get(username).getBookList() !=null){
			return userDetails.get(username).getBookList().size() < userDetails.get(username).getBookLimit();
		}
		return true;
	}

	@Override
	public void setBookLimit(String username, int bookLimit) {
		userDetails.get(username).setBookLimit(bookLimit);
	}

	@Override
	public void returnBook(String username, String bookId) {
		userDetails.get(username).getBookList().remove(bookId);
	}
}
