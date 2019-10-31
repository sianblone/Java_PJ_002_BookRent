package com.biz.rent.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class BookDTO {
	
	private String b_code;//	varchar2(6 byte)
	private String b_name;//	nvarchar2(125 char)
	private String b_author;//	nvarchar2(125 char)
	private String b_comp;//	nvarchar2(125 char)
	private int b_year;//	number
	private int b_iprice;//	number
	private int b_rprice;//	number

}
