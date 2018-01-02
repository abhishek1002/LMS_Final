package com.lms.core;

import java.util.List;
import java.util.Scanner;

import com.lms.entities.BookVO;
import com.lms.entities.UserVO;
import com.lms.service.LibraryService;

public class LibraryMgmt {

	private static LibraryService libraryService = new LibraryService();
	
	public static void main(String[] args) {
		
		showOptions();
		Scanner sc=new Scanner(System.in); 
		
		try{
			int choice = 0;
			
			while(choice !=9){
				System.out.print("\nEnter your choice: ");  
			    choice = sc.nextInt();    
				
				switch(choice){
				
					case 1: addBook(sc); break;
					case 2: addUser(sc); break;
					case 3: lendBook(sc); break;
					case 4: returnBook(sc); break;
					case 5: setBookLimit(sc); break;
					case 6: searchBook(sc);break;
					case 7: searchUser(sc);break;
					case 8: showOptions();break;
					case 9: System.out.println("\n\n Thanks for Using Library Management System!!! ");break;
				}
			}
		}catch(Exception e){
			System.out.println("Something went wrong !!!");
		}finally{
			sc.close();
		}
	}

	private static void returnBook(Scanner sc) {
		try{
			System.out.print("Enter User Name: ");sc.nextLine(); //skip the \n character
			String username = sc.nextLine();
			
			System.out.print("Book Id: ");	
			String bookId = sc.nextLine();
			
			libraryService.returnBook(username,bookId);
			
			System.out.println("BookId: "+bookId + " is returned successfully to user: "+username);
			
		}catch(Exception e){
			System.out.println("Error while returning book.");
		}
	}

	private static void setBookLimit(Scanner sc) {
		
		try{
			System.out.print("Enter Username: ");sc.nextLine(); //skip the \n character
			String username = sc.nextLine();
			
			System.out.print("Enter Book Limit: ");
			int bookLimit = sc.nextInt();
			
			boolean limitStatus = libraryService.setBookLimit(username, bookLimit);
			
			if(limitStatus){
				System.out.println("Book Limit Set Succesfully for user: "+ username);
			}else{
				System.out.println("User not found !!!");
			}
		}catch(Exception e){
			System.out.println("Error while setting book limit");
		}
		
	}

	private static void lendBook(Scanner sc) {
		
		try{
			System.out.print("Enter User Name: ");sc.nextLine(); //skip the \n character
			String username = sc.nextLine();
			
			System.out.print("Book Id: ");	
			String bookId = sc.nextLine();
			
			boolean lendStatus = libraryService.lendBook(username,bookId);
			
			if(lendStatus){
				System.out.println("BookId: "+bookId + " is issued successfully to user: "+username);
			}else{
				System.out.println("Lending Limit Exceeds for user: "+username);
			}
			
		}catch(Exception e){
			System.out.println("Error while lending a book.");
		}
		
	}

	private static void searchUser(Scanner sc) {
		try{
			System.out.print("\n User Name: ");sc.nextLine(); //skip the \n character
			String name = sc.nextLine();

			UserVO user = libraryService.searchUserDetails(name);
			
			if(user == null){
				System.out.println("No User Found !!!");
			}else{
				System.out.println("User Search Sucessful. User Details: \n ");
				System.out.println(user);
			}
			
			
		}catch(Exception e){
			System.out.println("Error while searching the book.");
		}
	}

	private static void showOptions() {
		System.out.println("\nSelect Options \n:");
		System.out.println("1. Add Book");
		System.out.println("2. Add User");
		System.out.println("3. Lend Book");
		System.out.println("4. Return Book");
		System.out.println("5. Set Book Limit");
		System.out.println("6. Search Book");
		System.out.println("7. Search User");
		System.out.println("8. Show Options");
		System.out.println("9. Exit");
	}
	
	private static void addUser(Scanner sc) {
		try{
			UserVO user = new UserVO();
			System.out.println("\n Enter User Details: ");sc.nextLine(); //skip the \n character
			
			System.out.print("Name: "); user.setName(sc.nextLine());
			System.out.print("Address: "); user.setAddress(sc.nextLine());
			
			libraryService.addUserDetails(user);
			System.out.println("1 user added successfully..");
		}catch(Exception e){
			System.out.println("Error while adding user.");
		}
		
	}
	
	private static void searchBook(Scanner sc) {
		try{
			System.out.print("\n Book Title: ");sc.nextLine(); //skip the \n character
			String title = sc.nextLine();

			System.out.println("Book Author: ");
			String author = sc.nextLine();
			
			List<BookVO> books = libraryService.searchBookDetails(title,author);
			
			if(books == null || books.isEmpty()){
				System.out.println("No Book Found !!!");
			}else{
				System.out.println("Book Search Sucessful. Books Count: "+ books.size());
				System.out.println(books);
			}
			
			
		}catch(Exception e){
			System.out.println("Error while searching the book.");
		}
	}

	private static void addBook(Scanner sc) {
		
		try{
			BookVO bookVO = new BookVO();
			
			System.out.println("Enter Book Details... \n");sc.nextLine();//skip the \n character
			
			System.out.print("Book Id: ");
			bookVO.setId(sc.nextLine());
			
			System.out.print("Title:");
			bookVO.setTitle(sc.nextLine());
			
			System.out.print("Author: ");
			bookVO.setAuthor(sc.nextLine());
			
			System.out.print("Price: ");
			bookVO.setPrice(sc.nextDouble());
			
			libraryService.addBookDetails(bookVO);
			
			System.out.println("\n 1 book added successfully");
		}catch(Exception e){
			System.out.println("Error while adding book.");
		}
	}

}
