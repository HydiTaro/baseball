package com.baseball.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baseball.dao.condition.InsertPredictBatOrderCondition;
import com.baseball.dao.condition.SelectLatestPredictBatOrderCondition;
import com.baseball.dao.condition.SelectPredictBatOrderDtoResponse;
import com.baseball.dto.req_res.VBO1010ResponseDto.BattersForVerify;
import com.baseball.dto.req_res.VBO1030ResponseDto.PitchersForVerify;

@Mapper
public interface VerifyBatOrderDao {
	
	/**
	 * チームごとの今年の打者成績取得
	 * 
	 * @param チームID
	 * @return 
	 */
	List<BattersForVerify> getHittersFromTeam(
			@Param("conditionEntity") int teamId);
	
	/**
	 * チームごとの今年の打者成績取得
	 * 
	 * @param チームID
	 * @return 
	 */
	List<PitchersForVerify> getPitchersFromTeam(
			@Param("conditionEntity") int teamId);
	
	void insertPredictBatOrder(
			@Param("conditionEntity") InsertPredictBatOrderCondition dto);
	
	SelectPredictBatOrderDtoResponse getLatestPredictBatOrder(
			@Param("conditionEntity") SelectLatestPredictBatOrderCondition selectLatestPredictBatOrder);
}
