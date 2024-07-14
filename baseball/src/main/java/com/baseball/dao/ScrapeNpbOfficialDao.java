package com.baseball.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baseball.dto.req_res.VBO1010ResponseDto.BattersForVerify;
import com.baseball.scrape.dto.NPBStatsHitter;
import com.baseball.scrape.dto.NpbPlayerMasterDTO;
import com.baseball.scrape.dto.NpbStatsPitcher;

@Mapper
public interface ScrapeNpbOfficialDao {
	
	/**
	 * NPBの選手マスタデータ挿入
	 * 
	 * @param conditionEntity
	 * @return 挿入件数
	 */
	int insertPlayerMaster(
			@Param("conditionEntity") List<NpbPlayerMasterDTO> condition);
	/**
	 * NPBの打者データ挿入
	 * 
	 * @param conditionEntity
	 * @return 挿入件数
	 */
	int insertHitterPerTeamAndYear(
			@Param("conditionEntity") List<NPBStatsHitter> condition);
	/**
	 * NPBの打者データ挿入
	 * 
	 * @param conditionEntity
	 * @return 挿入件数
	 */
	int insertPitcherPerTeamAndYear(
			@Param("conditionEntity") List<NpbStatsPitcher> condition);
	/**
	 * NPBの打者データ挿入
	 * 
	 * @param conditionEntity
	 * @return 挿入件数
	 */
	int insertHitterThisYear(
			@Param("conditionEntity") List<NPBStatsHitter> condition);
	/**
	 * NPBの投手データ挿入
	 * 
	 * @param conditionEntity
	 * @return 挿入件数
	 */
	int insertPitcherThisYear(
			@Param("conditionEntity") List<NpbStatsPitcher> condition);
	/**
	 * 今年の打者データは別テーブルに保存
	 * 
	 * @param conditionEntity
	 * @return 挿入件数
	 */
	int updateHitterThisYear(
			@Param("conditionEntity") List<NPBStatsHitter> condition);
	
	/**
	 * 今年の投手データは別テーブルに保存
	 * @param condition
	 * @return
	 */
	int updatePitcherThisYear(
			@Param("conditionEntity") List<NpbStatsPitcher> condition);
	
	
	
	/**
	 * チームごとの今年の打者成績取得
	 * @param teamId
	 * @return
	 */
	List<BattersForVerify> getHitterFromTeam(
			@Param("conditionEntity") int teamId);
}
