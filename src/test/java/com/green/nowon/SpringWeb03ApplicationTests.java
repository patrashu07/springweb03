package com.green.nowon;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.green.nowon.domain.dto.Board;
import com.green.nowon.domain.dto.Member;
import com.green.nowon.mapper.BoardMapper;
import com.green.nowon.mapper.MemberMapper;

@SpringBootTest
class SpringWeb03ApplicationTests {
	
	@Autowired
	BoardMapper mapper;
	
	@Autowired
	MemberMapper memberMapper;
	
	@Test
	void 멤버매퍼() {
		System.out.println("memberMapper : " +  memberMapper);
		Member result = memberMapper.findbyEmail("test@test");
		System.out.println(result);
	}
	
	
	
	@Test
	void 매퍼테스트실행() {
	System.out.println("mapper : " + mapper);
	List<Board> result = mapper.findAll();
	for(Board dto : result) {
		System.out.println(dto);
	}
	
	
	
	
	}

}
