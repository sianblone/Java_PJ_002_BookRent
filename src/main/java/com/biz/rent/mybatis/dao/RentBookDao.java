package com.biz.rent.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.biz.rent.persistence.RentBookDTO;

public interface RentBookDao {
	
	public List<RentBookDTO> selectAll();
	public RentBookDTO findById(String rent_seq);
	public RentBookDTO findByRent_Bcode(String rent_bcode);
	public RentBookDTO findByRent_Ucode(String rent_ucode);
	
	public int insert(RentBookDTO rentBookDTO);
	public int update(RentBookDTO rentBookDTO);
	public int delete(String rent_seq);

}
