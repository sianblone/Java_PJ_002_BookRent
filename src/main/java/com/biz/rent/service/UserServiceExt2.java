package com.biz.rent.service;

import com.biz.rent.persistence.BookDTO;
import com.biz.rent.persistence.UserDTO;

public class UserServiceExt2 extends UserServiceExt1 {
	
	protected void insert() {
		System.out.println("< 회원 추가 >");
		String u_name = "";
		String u_tel = "";
		while(true) {
			System.out.print("이름>>");
			u_name = scan.nextLine();
			
			System.out.print("전화번호>>");
			u_tel = scan.nextLine();
			
			if(userDao.findByNameAndTel(u_name, u_tel).size() > 0) {
				System.out.println("이름과 전화번호가 같은 회원이 이미 있습니다 다시 입력해주세요");
				continue;
			}
			break;
		}
		
		System.out.print("주소>>");
		String u_addr = scan.nextLine();
		
		
		UserDTO userDTO = UserDTO.builder()
				.u_name(u_name)
				.u_tel(u_tel)
				.u_addr(u_addr).build();
		
		this.infoAsDTO(userDTO);
		
		while(true) {
			System.out.print("정말로 추가하시겠습니까?(y/n)>>");
			String yn = scan.nextLine();
			if(yn.equalsIgnoreCase("y")) {
				if(userDao.insert(userDTO) > 0) {
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
		
		System.out.println("< 회원정보 수정 >");
		System.out.print("수정할 회원코드를 입력해주세요>>");
		String u_code = scan.nextLine().toUpperCase();
//		UserDTO oldUserDTO = userDao.findById(u_code);
		UserDTO newUserDTO = userDao.findById(u_code);
		if(newUserDTO == null) {
			System.out.println("회원 정보가 없습니다");
			return;
		}
		
		System.out.println("아무 것도 입력하지 않고 Enter를 입력하면 기존 값이 유지됩니다");
		
		String u_name = "";
		String u_tel = "";
		while(true) {
			System.out.print("이름>>");
			u_name = scan.nextLine();
			
			System.out.print("전화번호>>");
			u_tel = scan.nextLine();
			
			if(userDao.findByNameAndTel(u_name, u_tel).size() > 0) {
				System.out.println("이름과 전화번호가 같은 회원이 이미 있습니다 다시 입력해주세요");
				continue;
			}
			
			if(!u_name.isEmpty()) newUserDTO.setU_name(u_name);
			if(!u_tel.isEmpty()) newUserDTO.setU_tel(u_tel);
			break;
		}
		
		System.out.print("주소>>");
		String u_addr = scan.nextLine();
		if(!u_addr.isEmpty()) {
			newUserDTO.setU_addr(u_addr);
		}
		
		
//		this.DTO1toDTO2(oldUserDTO, newUserDTO);
		this.infoAsDTO(newUserDTO);
		while(true) {
			System.out.print("정말로 수정하시겠습니까?(y/n)>>");
			String yn = scan.nextLine();
			if(yn.equalsIgnoreCase("y")) {
				if(userDao.update(newUserDTO) > 0) {
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
	
	/*
	protected void delete() {
		
		System.out.print("삭제할 회원코드>>");
		String u_code = scan.nextLine().toUpperCase();
		UserDTO userDTO = userDao.findById(u_code);
		if(userDTO == null) {
			System.out.println("회원 정보가 없습니다");
			return;
		}
		if(rentBookDao.findByRent_Ucode(u_code).getRent_return_yn().equalsIgnoreCase("y")) {
			System.out.println("대출중인 회원정보는 삭제가 불가능합니다");
			return;
		}
		
		this.infoAsDTO(userDTO);
		while(true) {
			System.out.print("정말로 이 회원정보를 삭제하시겠습니까?(y/n)>>");
			String yn = scan.nextLine();
			if(yn.equalsIgnoreCase("y")) {
				if(userDao.delete(u_code) > 0) {
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
	*/

}
