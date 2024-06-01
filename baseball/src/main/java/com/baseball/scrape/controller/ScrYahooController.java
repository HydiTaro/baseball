package com.baseball.scrape.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baseball.scrape.service.ScrYahooService;

@RestController
public class ScrYahooController {
	
	@Autowired
	private ScrYahooService scrYahooService;
	
	@GetMapping("/scrape/yahooStarrting")
	public int scrapeYahooStarting() throws IOException{
		long startTime = System.currentTimeMillis();
		//スクレイピングデータをリストに変換 (リーグ変数でセ・パ方式に変換)
		int temp = scrYahooService.getAndInsertStarting();
		long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        // 処理時間を出力
        System.out.println("処理にかかった時間: " + elapsedTime + "ミリ秒");
		//スクレイピングデータをリストに変換 (リーグ変数でセ・パ方式に変換)
		return temp;
	}
}
