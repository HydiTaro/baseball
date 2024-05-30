package com.baseball.controll;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baseball.dto.req_res.VBO1010RequestDto;
import com.baseball.dto.req_res.VBO1010ResponseDto;
import com.baseball.dto.req_res.VBO1020RequestDto;
import com.baseball.dto.req_res.VBO1020ResponseDto;
import com.baseball.dto.req_res.VBO1030RequestDto;
import com.baseball.dto.req_res.VBO1030ResponseDto;
import com.baseball.service.VBOService;

/**
 * 打順コントロール
 * VerifyBatOrderController
 */
@RestController
public class VBOController {
	
	@Autowired
	private VBOService vboService;
	
	@GetMapping("/api/vbo1010")
	public VBO1010ResponseDto vbo1010(@RequestBody VBO1010RequestDto vbo1010RequestDto) {
		System.out.println("startpoint at controller class");
		long startTime = System.currentTimeMillis();
		//スクレイピングデータをリストに変換 (リーグ変数でセ・パ方式に変換)
		VBO1010ResponseDto vBO1010ResponseDto = vboService.vbo1010(vbo1010RequestDto);
		long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        // 処理時間を出力	
        System.out.println("処理にかかった時間: " + elapsedTime + "ミリ秒");

		return vBO1010ResponseDto;
	}
	
	/**
	 * 入力された打順をただ検証する
	 */
	@GetMapping("/api/vbo1020")
	public VBO1020ResponseDto vbo1020(@RequestBody VBO1020RequestDto vbo1020RequestDto) {
		System.out.println("startpoint at controller class");
		long startTime = System.currentTimeMillis();
		//スクレイピングデータをリストに変換 (リーグ変数でセ・パ方式に変換)
		VBO1020ResponseDto vBO1020ResponseDto = vboService.vbo1020(vbo1020RequestDto);
		long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        // 処理時間を出力
        System.out.println("処理にかかった時間: " + elapsedTime + "ミリ秒");

		return vBO1020ResponseDto;
	}
	
	/**
	 * 入力された打順をただ検証する
	 */
	@GetMapping("/api/vbo1030")
	public VBO1030ResponseDto vbo1030(@RequestBody VBO1030RequestDto vbo1030RequestDto) {
		System.out.println("startpoint at controller class");
		long startTime = System.currentTimeMillis();
		//スクレイピングデータをリストに変換 (リーグ変数でセ・パ方式に変換)
		VBO1030ResponseDto vBO1030ResponseDto = vboService.vbo1030(vbo1030RequestDto);
		long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        // 処理時間を出力
        System.out.println("処理にかかった時間: " + elapsedTime + "ミリ秒");
		return vBO1030ResponseDto;
	}
	
}
