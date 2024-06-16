package com.baseball.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baseball.dao.condition.InsertPredictBatOrderCondition;
import com.baseball.dao.condition.SelectLatestPredictBatOrderCondition;
import com.baseball.dao.condition.SelectPredictBatOrderDtoResponse;

@Mapper
public interface PredictBatOrderDao {
	
//	void insertPredictBatOrder(
//			@Param("conditionEntity") InsertPredictBatOrderCondition dto);
//	
//	SelectPredictBatOrderDtoResponse getLatestPredictBatOrder(
//			@Param("conditionEntity") SelectLatestPredictBatOrderCondition selectLatestPredictBatOrder);
}
