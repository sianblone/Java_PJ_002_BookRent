package com.biz.rent.service;

import java.util.List;
import java.util.Scanner;

import com.biz.rent.mybatis.DBConnection;
import com.biz.rent.mybatis.dao.RentBookDao;
import com.biz.rent.mybatis.dao.UserDao;
import com.biz.rent.persistence.UserDTO;

public class UserServiceExt1 {
	
	protected UserDao userDao;
	protected RentBookDao rentBookDao;
	protected Scanner scan;
	
	public UserServiceExt1() {
		// TODO Auto-generated constructor stub
		userDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(UserDao.class);
		rentBookDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(RentBookDao.class);
		scan = new Scanner(System.in);
	}
	
	public void userMenu() {
		while(true) {
			System.out.println("------------------------------------------------");
			System.out.println("3. 회원정보 메뉴 선택");
			System.out.println("------------------------------------------------");
			System.out.println("1.검색  2.추가  3.수정  0.뒤로");
			System.out.print(">>");
			String getScan = scan.nextLine();
			if(getScan.equals("1")) {
				this.searchUsers(getScan);
			} else if(getScan.equals("2")) {
				this.insert();
			} else if(getScan.equals("3")) {
				this.update();
			} else if(getScan.equals("0")) {
				return;
			}
		}
	}
	
	protected void searchUsers(String getScan) {
		System.out.println("------------------------------------------------");
		System.out.println("3-1. 회원정보 검색");
		System.out.println("------------------------------------------------");
		System.out.println("1.전체  2.회원코드  3.이름  4.전화번호  0.뒤로");
		System.out.print(">>");
		getScan = scan.nextLine();
		if(getScan.equals("1")) {
			this.selectAll();
		} else if(getScan.equals("2")) {
			this.findById();
		} else if(getScan.equals("3")) {
			this.findByName();
		} else if(getScan.equals("4")) {
			this.findByTel();
		} else if(getScan.equals("0")) {
			return;
		}
	}
	
	protected void selectAll() {
		// TODO Auto-generated method stub
		List<UserDTO> userList = userDao.selectAll();
		if(userList.size() > 0) {
			this.infoAsList(userList);
		}
	}
	
	protected void findById() {
		System.out.print("회원코드>>");
		String u_code = scan.nextLine().toUpperCase();
		UserDTO userDTO = userDao.findById(u_code);
		if(userDTO != null) {
			this.infoAsDTO(userDTO);
		} else {
			System.out.println("회원 정보가 없습니다");
		}
		
		
	}
	
	protected void findByName() {
		// TODO Auto-generated method stub
		System.out.print("이름>>");
		String u_name = scan.nextLine();
		List<UserDTO> userList = userDao.findByName(u_name);
		if(userList.size() > 0) {
			this.infoAsList(userList);
		} else {
			System.out.println("회원 정보가 없습니다");
		}
		
	}

	protected void findByTel() {
		// TODO Auto-generated method stub
		System.out.print("전화번호>>");
		String u_tel = scan.nextLine();
		List<UserDTO> userList = userDao.findByTel(u_tel);
		if(userList.size() > 0) {
			this.infoAsList(userList);
		} else {
			System.out.println("회원 정보가 없습니다");
		}
	}

	protected void infoAsDTO(UserDTO userDTO) {
		System.out.println("============================================");
		System.out.println("회원코드:" + userDTO.getU_code() );
		System.out.println("이름:" + userDTO.getU_name() );
		System.out.println("전화번호:" + userDTO.getU_tel() );
		System.out.println("주소:" + userDTO.getU_addr() );
		System.out.println("============================================");
		
	}
	
	protected void infoAsList(List<UserDTO> userList) {
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
	
	protected void DTO1toDTO2(UserDTO oldUserDTO, UserDTO newUserDTO) {
		System.out.println("========================================================");
		System.out.println("회원코드:" + oldUserDTO.getU_code() + " -> "  + newUserDTO.getU_code() );
		System.out.println("이름:" + oldUserDTO.getU_name() + " -> "  + newUserDTO.getU_name() );
		System.out.println("전화번호:" + oldUserDTO.getU_tel() + " -> "  + newUserDTO.getU_tel() );
		System.out.println("주소:" + oldUserDTO.getU_addr() + " -> "  + newUserDTO.getU_addr() );
		System.out.println("========================================================");
	}
	
	protected void insert() {
		// TODO Auto-generated method stub
		
	}

	protected void update() {
		// TODO Auto-generated method stub
		
	}

}
