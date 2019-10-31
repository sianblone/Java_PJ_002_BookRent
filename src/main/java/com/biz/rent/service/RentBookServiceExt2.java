package com.biz.rent.service;

import java.time.LocalDate;

import com.biz.rent.persistence.BookDTO;
import com.biz.rent.persistence.RentBookDTO;
import com.biz.rent.persistence.UserDTO;

public class RentBookServiceExt2 extends RentBookServiceExt1 {

	protected void rent() {
		System.out.println("< 대여 등록 >");
//		1.대여정보에 도서코드,회원코드 입력하기
//		2.대출일 찍기
//		3.반납일 찍기
//		4.도서반납여부 정하기
//		-포인트는 따로
		System.out.print("도서코드>>");
		String rent_bcode = scan.nextLine().toUpperCase();
		BookDTO bookDTO = bookDao.findById(rent_bcode);
		if(bookDTO == null) {
			System.out.println("도서 정보가 없습니다");
			return;
		}
		RentBookDTO rbDTO = rentBookDao.findByRent_Bcode(rent_bcode);
		if(rbDTO != null && rbDTO.getRent_return_yn().equalsIgnoreCase("y")) {
			System.out.println("이미 대여중인 도서입니다");
			return;
		}
		 
		this.bookInfoAsDTO(bookDTO);
		
		System.out.print("회원코드>>");
		String rent_ucode = scan.nextLine().toUpperCase();
		UserDTO userDTO = userDao.findById(rent_ucode);
		if(userDTO == null) {
			System.out.println("회원 정보가 없습니다");
			return;
		}
		this.userInfoAsDTO(userDTO);
		
		LocalDate local = LocalDate.now();
		String rent_date = local.toString();
		String rent_return_date = local.plusDays(14).toString();
		
		String rent_return_yn = "y";
		
		int rent_point = 0;
		
		rbDTO = rentBookDao.findByRent_Ucode(rent_ucode);
		if(rbDTO != null) {
			rent_point = rbDTO.getRent_point();
		}
		
		RentBookDTO rentBookDTO = RentBookDTO.builder()
				.rent_date(rent_date)
				.rent_return_date(rent_return_date)
				.rent_bcode(rent_bcode)
				.rent_ucode(rent_ucode)
				.rent_return_yn(rent_return_yn)
				.rent_point(rent_point).build();
		
		this.rentInfoAsDTO(rentBookDTO);
		
		while(true) {
			System.out.print("정말로 추가하시겠습니까?(y/n)>>");
			String yn = scan.nextLine();
			if(yn.equalsIgnoreCase("y")) {
				if(rentBookDao.insert(rentBookDTO) > 0) {
					System.out.println("데이터 추가 성공");
				} else {
					System.out.println("데이터 추가 실패");
				}
			} else if(yn.equalsIgnoreCase("n")) {
				System.out.println("데이터 추가 취소");
			} else {
				continue;
			}
			break;
		}
		
	}
	
	protected void rentReturn() {
		System.out.print("도서코드>>");
		String rent_bcode = scan.nextLine().toUpperCase();
		BookDTO bookDTO = bookDao.findById(rent_bcode);
		if(bookDTO == null) {
			System.out.println("도서 정보가 없습니다");
			return;
		}
		RentBookDTO rentBookDTO = rentBookDao.findByRent_Bcode(rent_bcode);
		if(rentBookDTO == null || !rentBookDTO.getRent_return_yn().equalsIgnoreCase("y")) {
			System.out.println("대여중인 도서가 아닙니다");
			return;
		}
		rentBookDTO.setRent_return_yn(null);
		
		LocalDate local = LocalDate.now();
		if( rentBookDTO.getRent_return_date().compareTo( local.toString() ) >= 0) {
			rentBookDTO.setRent_point(rentBookDTO.getRent_point() + 5);
		}
		
		rentBookDao.update(rentBookDTO);
		System.out.println("도서를 반납했습니다");
		
	}
	
}
