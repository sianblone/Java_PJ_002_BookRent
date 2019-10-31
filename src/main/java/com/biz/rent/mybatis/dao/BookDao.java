package com.biz.rent.mybatis.dao;

import java.util.List;

import com.biz.rent.persistence.BookDTO;

public interface BookDao {
	
	public List<BookDTO> selectAll();
	public BookDTO findById(String b_code);
	public List<BookDTO> findByName(String b_name);
	public List<BookDTO> findByEQName(String b_name);
	public List<BookDTO> findByAuthor(String b_author);
	
	public int insert(BookDTO bookDTO);
	public int update(BookDTO bookDTO);
	public int delete(String b_code);
	
}
