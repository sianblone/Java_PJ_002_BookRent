package com.biz.rent.service;

import com.biz.rent.persistence.BookDTO;
import com.biz.rent.persistence.RentBookDTO;

public class BookServiceExt2 extends BookServiceExt1 {
	
	protected void insert() {
		System.out.println("< 도서 추가 >");
		String b_name = "";
		while(true) {
			System.out.print("도서명>>");
			b_name = scan.nextLine();
			// 도서명 중복 검사
			if(bookDao.findByEQName(b_name).size() > 0) {
				System.out.println("같은 도서명이 이미 존재합니다 다시 입력해주세요");
				continue;
			}
			break;
		}
		
		System.out.print("저자>>");
		String b_author = scan.nextLine();
		
		System.out.print("출판사>>");
		String b_comp = scan.nextLine();
		
		int b_year = 0;
		while(true) {
			System.out.print("구입년도>>");
			String str_year = scan.nextLine();
			if(str_year.length() != 4) {
				System.out.println("구입년도 형식이 잘못되었습니다 다시 입력해주세요 (Ex : 2019)");
				continue;
			}
			try {
				b_year = Integer.valueOf(str_year);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("구입년도는 숫자만 입력해주세요");
				continue;
			}
			break;
		}
		
		int b_iprice = 0;
		while(true) {
			System.out.print("구입가격>>");
			String str_iprice = scan.nextLine();
			try {
				b_iprice = Integer.valueOf(str_iprice);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("구입가격은 숫자만 입력해주세요");
				continue;
			}
			break;
		}
		
		int b_rprice = 0;
		while(true) {
			System.out.print("대여가격>>");
			String str_rprice = scan.nextLine();
			try {
				b_rprice = Integer.valueOf(str_rprice);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("구입가격은 숫자만 입력해주세요");
				continue;
			}
			break;
		}
		
		
		BookDTO bookDTO = BookDTO.builder()
				.b_name(b_name)
				.b_author(b_author)
				.b_comp(b_comp)
				.b_year(b_year)
				.b_iprice(b_iprice)
				.b_rprice(b_rprice).build();
		
		this.infoAsDTO(bookDTO);
		
		while(true) {
			System.out.print("정말로 추가하시겠습니까?(y/n)>>");
			String yn = scan.nextLine();
			if(yn.equalsIgnoreCase("y")) {
				if(bookDao.insert(bookDTO) > 0) {
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
	
	protected void update() {
		
		System.out.println("< 도서 수정 >");
		System.out.print("수정할 도서코드를 입력해주세요>>");
		String b_code = scan.nextLine().toUpperCase();
//		BookDTO oldBookDTO = bookDao.findById(b_code);
		BookDTO newBookDTO = bookDao.findById(b_code);
		if(newBookDTO == null) {
			System.out.println("도서 정보가 없습니다");
			return;
		}
		
		System.out.println("아무 것도 입력하지 않고 Enter를 입력하면 기존 값이 유지됩니다");
		// 도서 정보가 있으면
		String b_name = "";
		while(true) {
			System.out.print("도서명>>");
			b_name = scan.nextLine();
			if(b_name.isEmpty()) break;
			// 도서명 중복 검사
			if(bookDao.findByEQName(b_name).size() > 0) {
				System.out.println("같은 도서명이 이미 존재합니다 다시 입력해주세요");
				continue;
			}
			newBookDTO.setB_name(b_name);
			break;
		}
		
		System.out.print("저자>>");
		String b_author = scan.nextLine();
		if(!b_author.isEmpty()) {
			newBookDTO.setB_author(b_author);
		}
		
		System.out.print("출판사>>");
		String b_comp = scan.nextLine();
		if(!b_comp.isEmpty()) {
			newBookDTO.setB_comp(b_comp);
		}
		
		int b_year = 0;
		while(true) {
			System.out.print("구입년도>>");
			String str_year = scan.nextLine();
			if(str_year.isEmpty()) break;
			if(str_year.length() != 4) {
				System.out.println("구입년도 형식이 잘못되었습니다 다시 입력해주세요 (Ex : 2019)");
				continue;
			}
			try {
				b_year = Integer.valueOf(str_year);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("구입년도는 숫자만 입력해주세요");
				continue;
			}
			newBookDTO.setB_year(b_year);
			break;
		}
		
		int b_iprice = 0;
		while(true) {
			System.out.print("구입가격>>");
			String str_iprice = scan.nextLine();
			if(str_iprice.isEmpty()) break;
			try {
				b_iprice = Integer.valueOf(str_iprice);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("구입가격은 숫자만 입력해주세요");
				continue;
			}
			newBookDTO.setB_iprice(b_iprice);
			break;
		}
		
		int b_rprice = 0;
		while(true) {
			System.out.print("대여가격>>");
			String str_rprice = scan.nextLine();
			if(str_rprice.isEmpty()) break;
			try {
				b_rprice = Integer.valueOf(str_rprice);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("대여가격은 숫자만 입력해주세요");
				continue;
			}
			newBookDTO.setB_rprice(b_rprice);
			break;
		}
		
//		this.DTO1toDTO2(oldBookDTO, newBookDTO);
		this.infoAsDTO(newBookDTO);
		while(true) {
			System.out.print("정말로 수정하시겠습니까?(y/n)>>");
			String yn = scan.nextLine();
			if(yn.equalsIgnoreCase("y")) {
				if(bookDao.update(newBookDTO) > 0) {
					System.out.println("데이터 수정 성공");
				} else {
					System.out.println("데이터 수정 실패");
				}
			} else if(yn.equalsIgnoreCase("n")) {
				System.out.println("데이터 수정 취소");
			} else {
				continue;
			}
			break;
		}
		
	}
	
	protected void delete() {
		
		System.out.print("삭제할 도서코드>>");
		String b_code = scan.nextLine().toUpperCase();
		BookDTO bookDTO = bookDao.findById(b_code);
		if(bookDTO == null) {
			System.out.println("도서 정보가 없습니다");
			return;
		}
		RentBookDTO rbDTO = rentBookDao.findByRent_Bcode(b_code);
		if(rbDTO != null && rbDTO.getRent_return_yn().equalsIgnoreCase("y")) {
			System.out.println("대출중인 도서는 삭제가 불가능합니다");
			return;
		}
		
		this.infoAsDTO(bookDTO);
		while(true) {
			System.out.print("정말로 이 도서를 삭제하시겠습니까?(y/n)>>");
			String yn = scan.nextLine();
			if(yn.equalsIgnoreCase("y")) {
				if(bookDao.delete(b_code) > 0) {
					System.out.println("데이터 삭제 성공");
				} else {
					System.out.println("데이터 삭제 실패");
				}
			} else if(yn.equalsIgnoreCase("n")) {
				System.out.println("데이터 삭제 취소");
			} else {
				continue;
			}
			break;
		}
		
	}

}
