package com.biz.rent.service;

import java.util.List;
import java.util.Scanner;

import com.biz.rent.mybatis.DBConnection;
import com.biz.rent.mybatis.dao.BookDao;
import com.biz.rent.mybatis.dao.RentBookDao;
import com.biz.rent.persistence.BookDTO;

public class BookServiceExt1 {
	
	protected BookDao bookDao;
	protected RentBookDao rentBookDao;
	protected Scanner scan;
	
	public BookServiceExt1() {
		// TODO Auto-generated constructor stub
		bookDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(BookDao.class);
		rentBookDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(RentBookDao.class);
		scan = new Scanner(System.in);
	}
	
	public void bookMenu() {
		while(true) {
			System.out.println("------------------------------------------------");
			System.out.println("2. 도서정보 메뉴 선택");
			System.out.println("------------------------------------------------");
			System.out.println("1.검색  2.추가  3.수정  4.삭제  0.뒤로");
			System.out.print(">>");
			String getScan = scan.nextLine();
			if(getScan.equals("1")) {
				this.searchBooks(getScan);
			} else if(getScan.equals("2")) {
				this.insert();
			} else if(getScan.equals("3")) {
				this.update();
			} else if(getScan.equals("4")) {
				this.delete();
			} else if(getScan.equals("0")) {
				return;
			}
		}
	}
	
	protected void searchBooks(String getScan) {
		System.out.println("------------------------------------------------");
		System.out.println("2-1. 도서정보 검색");
		System.out.println("------------------------------------------------");
		System.out.println("1.전체  2.도서코드  3.도서명  4.저자  0.뒤로");
		System.out.print(">>");
		getScan = scan.nextLine();
		if(getScan.equals("1")) {
			this.selectAll();
		} else if(getScan.equals("2")) {
			this.findById();
		} else if(getScan.equals("3")) {
			this.findByName();
		} else if(getScan.equals("4")) {
			this.findByAuthor();
		} else if(getScan.equals("0")) {
			return;
		}
	}
	
	protected void selectAll() {
		// TODO Auto-generated method stub
		List<BookDTO> bookList = bookDao.selectAll();
		if(bookList.size() > 0) {
			this.infoAsList(bookList);
		}
	}
	
	protected void findById() {
		System.out.print("도서코드>>");
		String b_code = scan.nextLine().toUpperCase();
		BookDTO bookDTO = bookDao.findById(b_code);
		if(bookDTO != null) {
			this.infoAsDTO(bookDTO);
		} else {
			System.out.println("도서 정보가 없습니다");
		}
		
		
	}
	
	protected void findByName() {
		// TODO Auto-generated method stub
		System.out.print("도서명>>");
		String b_name = scan.nextLine();
		List<BookDTO> bookList = bookDao.findByName(b_name);
		if(bookList.size() > 0) {
			this.infoAsList(bookList);
		} else {
			System.out.println("도서 정보가 없습니다");
		}
		
	}

	protected void findByAuthor() {
		// TODO Auto-generated method stub
		System.out.print("저자>>");
		String b_author = scan.nextLine();
		List<BookDTO> bookList = bookDao.findByAuthor(b_author);
		if(bookList.size() > 0) {
			this.infoAsList(bookList);
		} else {
			System.out.println("도서 정보가 없습니다");
		}
	}

	protected void infoAsDTO(BookDTO bookDTO) {
		System.out.println("============================================");
		System.out.println("도서코드:" + bookDTO.getB_code() );
		System.out.println("도서명:" + bookDTO.getB_name() );
		System.out.println("저자:" + bookDTO.getB_author() );
		System.out.println("출판사:" + bookDTO.getB_comp() );
		System.out.println("구입년도:" + bookDTO.getB_year() );
		System.out.println("구입가격:" + bookDTO.getB_iprice() );
		System.out.println("대여가격:" + bookDTO.getB_rprice() );
		System.out.println("============================================");
		
	}
	
	protected void infoAsList(List<BookDTO> bookList) {
		System.out.println("================================================================");
		System.out.println("도서코드\t도서명\t저자\t출판사\t구입년도\t구입가격\t대여가격");
		System.out.println("----------------------------------------------------------------");
		for(BookDTO bookDTO : bookList) {
			System.out.print(bookDTO.getB_code() + "\t");
			System.out.print(bookDTO.getB_name() + "\t");
			System.out.print(bookDTO.getB_author() + "\t");
			System.out.print(bookDTO.getB_comp() + "\t");
			System.out.print(bookDTO.getB_year() + "\t");
			System.out.print(bookDTO.getB_iprice() + "\t");
			System.out.print(bookDTO.getB_rprice() + "\n");
		}
		System.out.println("================================================================");
	}
	
	protected void DTO1toDTO2(BookDTO oldBookDTO, BookDTO newBookDTO) {
		System.out.println("========================================================");
		System.out.println("도서코드:" + oldBookDTO.getB_code() + " -> "  + newBookDTO.getB_code() );
		System.out.println("도서명:" + oldBookDTO.getB_name() + " -> "  + newBookDTO.getB_name() );
		System.out.println("저자:" + oldBookDTO.getB_author() + " -> "  + newBookDTO.getB_author() );
		System.out.println("출판사:" + oldBookDTO.getB_comp() + " -> "  + newBookDTO.getB_comp() );
		System.out.println("구입년도:" + oldBookDTO.getB_year() + " -> "  + newBookDTO.getB_year() );
		System.out.println("구입가격:" + oldBookDTO.getB_iprice() + " -> "  + newBookDTO.getB_iprice() );
		System.out.println("대여가격:" + oldBookDTO.getB_rprice() + " -> "  + newBookDTO.getB_rprice() );
		System.out.println("========================================================");
	}
	
	protected void insert() {
		// TODO Auto-generated method stub
		
	}

	protected void update() {
		// TODO Auto-generated method stub
		
	}

	protected void delete() {
		// TODO Auto-generated method stub
		
	}

}
