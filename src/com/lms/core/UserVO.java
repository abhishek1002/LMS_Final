package com.lms.core;

import java.util.List;

public class UserVO {

	private String name;
	private String address;
	
	private int bookLimit = 100; // By default 100 books can be issued.
	private List<String> bookList;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBookLimit() {
		return bookLimit;
	}
	public void setBookLimit(int bookLimit) {
		this.bookLimit = bookLimit;
	}
	public List<String> getBookList() {
		return bookList;
	}
	public void setBookList(List<String> bookList) {
		this.bookList = bookList;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "User [Name=" + name + ", Address=" + address + ", bookLimit=" + (bookLimit==100?"No Limit":bookLimit)
				+ ", Issued Books=" + (bookList==null?"No Book Issued":bookList) + "]";
	}
	
	
}
