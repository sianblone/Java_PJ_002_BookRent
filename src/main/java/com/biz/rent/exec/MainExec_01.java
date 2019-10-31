package com.biz.rent.exec;

import java.util.Scanner;

import com.biz.rent.service.BookServiceExt2;
import com.biz.rent.service.RentBookServiceExt2;
import com.biz.rent.service.UserServiceExt2;

public class MainExec_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BookServiceExt2 bs = new BookServiceExt2();
		UserServiceExt2 us = new UserServiceExt2();
		RentBookServiceExt2 rbs = new RentBookServiceExt2();
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			System.out.println("====================================================");
			System.out.println("도서 대여 서비스 V1");
			System.out.println("====================================================");
			System.out.println("1.대여정보  2.도서정보  3.회원정보  0.종료");
			System.out.print(">>");
			String getScan = scan.nextLine();
			if(getScan.equals("1")) rbs.rentMenu();
			else if(getScan.equals("2")) bs.bookMenu();
			else if(getScan.equals("3")) us.userMenu();
			else if(getScan.equals("0")) {
				System.out.println("도서 대여 서비스를 종료합니다");
				return;
			}
		}

	}

}
