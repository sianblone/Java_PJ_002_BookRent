package com.biz.rent.service;

import java.util.List;
import java.util.Scanner;

import com.biz.rent.mybatis.DBConnection;
import com.biz.rent.mybatis.dao.BookDao;
import com.biz.rent.mybatis.dao.RentBookDao;
import com.biz.rent.mybatis.dao.UserDao;
import com.biz.rent.persistence.BookDTO;
import com.biz.rent.persistence.RentBookDTO;
import com.biz.rent.persistence.UserDTO;

public class RentBookServiceExt1 {
	
	protected UserDao userDao;
	protected BookDao bookDao;
	protected RentBookDao rentBookDao;
	protected Scanner scan;
	
	public RentBookServiceExt1() {
		// TODO Auto-generated constructor stub
		userDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(UserDao.class);
		bookDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(BookDao.class);
		rentBookDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(RentBookDao.class);
		scan = new Scanner(System.in);
	}
	
	public void rentMenu() {
		while(true) {
			System.out.println("------------------------------------------------");
			System.out.println("1. 대여정보 메뉴 선택");
			System.out.println("------------------------------------------------");
			System.out.println("1.검색  2.대여  3.반납  0.뒤로");
			System.out.print(">>");
			String getScan = scan.nextLine();
			if(getScan.equals("1")) {
				this.searchRent(getScan);
			} else if(getScan.equals("2")) {
				this.rent();
			} else if(getScan.equals("3")) {
				this.rentReturn();
			} else if(getScan.equals("0")) {
				System.out.println("서비스를 종료합니다");
				return;
			}
			
			
		}
	}
	
	protected void searchRent(String getScan) {
		System.out.println("------------------------------------------------");
		System.out.println("1-1. 대여정보 검색");
		System.out.println("------------------------------------------------");
		System.out.println("1.전체  2.도서코드  3.회원코드  0.뒤로");
		System.out.print(">>");
		getScan = scan.nextLine();
		if(getScan.equals("1")) {
			this.selectAll();
		} else if(getScan.equals("2")) {
			this.findByRent_Bcode();
		} else if(getScan.equals("3")) {
			this.findByRent_Ucode();
		} else if(getScan.equals("0")) {
			return;
		}
	}
	
	protected void selectAll() {
		// TODO Auto-generated method stub
		List<RentBookDTO> rentBookList = rentBookDao.selectAll();
		if(rentBookList.size() > 0) {
			this.rentInfoAsList(rentBookList);
		}
	}
	
	protected void findByRent_Bcode() {
		System.out.print("도서코드>>");
		String rent_bcode = scan.nextLine().toUpperCase();
		RentBookDTO rentBookDTO = rentBookDao.findByRent_Bcode(rent_bcode);
		if(rentBookDTO != null) {
			this.rentInfoAsDTO(rentBookDTO);
		} else {
			System.out.println("대여 정보가 없습니다");
		}
		
		
	}
	
	protected void findByRent_Ucode() {
		System.out.print("회원코드>>");
		String rent_ucode = scan.nextLine().toUpperCase();
		RentBookDTO rentBookDTO = rentBookDao.findByRent_Ucode(rent_ucode);
		if(rentBookDTO != null) {
			this.rentInfoAsDTO(rentBookDTO);
		} else {
			System.out.println("대여 정보가 없습니다");
		}
		
		
	}
	
	protected void rentInfoAsDTO(RentBookDTO rentBookDTO) {
		System.out.println("============================================");
		System.out.println("대출일:" + rentBookDTO.getRent_date() );
		System.out.println("반납예정일:" + rentBookDTO.getRent_return_date() );
		System.out.println("도서코드:" + rentBookDTO.getRent_bcode() );
		System.out.println("회원코드:" + rentBookDTO.getRent_ucode() );
		System.out.println("도서대출여부:" + rentBookDTO.getRent_return_yn() );
		System.out.println("포인트:" + rentBookDTO.getRent_point() );
		System.out.println("============================================");
		
	}
	
	protected void rentInfoAsList(List<RentBookDTO> rentBookList) {
		System.out.println("================================================================");
		System.out.println("일련번호\t대출일\t반납예정일\t도서코드\t회원코드\t도서대출여부\t포인트");
		System.out.println("----------------------------------------------------------------");
		for(RentBookDTO rentBookDTO : rentBookList) {
			System.out.print(rentBookDTO.getRent_seq() + "\t");
			System.out.print(rentBookDTO.getRent_date() + "\t");
			System.out.print(rentBookDTO.getRent_return_date() + "\t");
			System.out.print(rentBookDTO.getRent_bcode() + "\t");
			System.out.print(rentBookDTO.getRent_ucode() + "\t");
			System.out.print(rentBookDTO.getRent_return_yn() + "\t");
			System.out.print(rentBookDTO.getRent_point() + "\n");
		}
		System.out.println("================================================================");
	}

	protected void bookInfoAsDTO(BookDTO bookDTO) {
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
	
	protected void bookInfoAsList(List<BookDTO> bookList) {
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
	
	protected void userInfoAsDTO(UserDTO userDTO) {
		System.out.println("============================================");
		System.out.println("회원코드:" + userDTO.getU_code() );
		System.out.println("이름:" + userDTO.getU_name() );
		System.out.println("전화번호:" + userDTO.getU_tel() );
		System.out.println("주소:" + userDTO.getU_addr() );
		System.out.println("============================================");
		
	}
	
	protected void userInfoAsList(List<UserDTO> userList) {
		System.out.println("================================================================");
		System.out.println("회원코드\t이름\t전화번호\t주소");
		System.out.println("----------------------------------------------------------------");
		for(UserDTO userDTO : userList) {
			System.out.print(userDTO.getU_code() + "\t");
			System.out.print(userDTO.getU_name() + "\t");
			System.out.print(userDTO.getU_tel() + "\t");
			System.out.print(userDTO.getU_addr() + "\n");
		}
		System.out.println("================================================================");
	}
	
	protected void rent() {
		// TODO Auto-generated method stub
		
	}

	protected void rentReturn() {
		// TODO Auto-generated method stub
		
	}

}
