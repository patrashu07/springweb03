package com.green.nowon.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Board {
	private long bno;
	private	String title;
	private	String content;
}
