package com.green.nowon.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.green.nowon.domain.dto.Board;

@Mapper //mapper는 항상 interface로 만들어주고 @Mapper작성
public interface BoardMapper {
	 
	@Select("select * from board")
	List<Board> findAll();
}
