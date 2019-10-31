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

public class RentBookDTO {
	
	private String rent_seq;//	number
	private String rent_date;//	varchar2(10 byte)
	private String rent_return_date;//	varchar2(10 byte)
	private String rent_bcode;//	varchar2(6 byte)
	private String rent_ucode;//	varchar2(6 byte)
	private String rent_return_yn;//	varchar2(1 byte)
	private int rent_point;//	number

}
