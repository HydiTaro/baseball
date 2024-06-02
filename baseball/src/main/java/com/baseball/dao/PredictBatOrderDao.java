package com.baseball.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baseball.dto.req_res.PBO1010RequestDto.PredictOrder;

@Mapper
public interface PredictBatOrderDao {
	
	void insertPredictBatOrder(
			@Param("conditionEntity") PredictOrder condition);
}
