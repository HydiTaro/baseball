package com.baseball.scrape.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baseball.scrape.service.ScrNpbService;

@RestController
public class ScrNpbController {
	
	@Autowired
	private ScrNpbService scrNpbService;
	
	@GetMapping("/scrape/npbMasterPlayerData")
	public int scrapePlayerMasterStats() throws IOException{
		long startTime = System.currentTimeMillis();
		//スクレイピングデータをリストに変換 (リーグ変数でセ・パ方式に変換)
		int temp = scrNpbService.npbPlayerMaster();
		long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        // 処理時間を出力
        System.out.println("処理にかかった時間: " + elapsedTime + "ミリ秒");
		//スクレイピングデータをリストに変換 (リーグ変数でセ・パ方式に変換)
		return temp;
	}
	
	@GetMapping("/scrape/npbHitAndPitchPerTeamData")
	public int scrapeHitterStats() throws IOException{
		long startTime = System.currentTimeMillis();
		//スクレイピングデータをリストに変換 (リーグ変数でセ・パ方式に変換)
		int temp = scrNpbService.npbHitAndPitchStatsPerTeamAndYear();
		long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        // 処理時間を出力
        System.out.println("処理にかかった時間: " + elapsedTime + "ミリ秒");
        return temp;
	}
	
	/**
	 * 今年の打者成績取得
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/scrape/npbThisYearData")
	public int scrapeStatThisYear() throws IOException{
		long startTime = System.currentTimeMillis();
		//スクレイピングデータをリストに変換 (リーグ変数でセ・パ方式に変換)
		int temp = scrNpbService.npbStatsThisYear();
		long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        // 処理時間を出力
        System.out.println("処理にかかった時間: " + elapsedTime + "ミリ秒");
        return temp;
	}
}
