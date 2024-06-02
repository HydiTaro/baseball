package com.baseball.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baseball.dao.PredictBatOrderDao;
import com.baseball.dto.req_res.PBO1010RequestDto;
import com.baseball.dto.req_res.PBO1010ResponseDto;
import com.baseball.dto.req_res.PBO1020ResponseDto;

@Service
public class PBOService {
	
	@Autowired
	private PredictBatOrderDao predictBatOrderDao;
	
	public PBO1010ResponseDto pbo1010(PBO1010RequestDto pbo1010RequestDto) {
		
		PBO1010ResponseDto response = new PBO1010ResponseDto();
		predictBatOrderDao.insertPredictBatOrder(pbo1010RequestDto.getOrder());
		
		return response;
	}
	
	public PBO1020ResponseDto pbo1020(PBO1010RequestDto pbo1010RequestDto) {
		PBO1020ResponseDto response = new PBO1020ResponseDto();
		return response;
	}
	
}
