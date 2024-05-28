package com.baseball.dto.req_res;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 登場人物のBaseDto
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
public class VBO1020ResponseDto {
	
	private double aveScore;
	private int gameScore;
	private int hits;
	private int singles;
	private int doubles;
	private int triples;
	private int homeruns;
	
}
