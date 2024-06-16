package com.baseball.controll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baseball.dto.req_res.PBO1010RequestDto;
import com.baseball.dto.req_res.PBO1010ResponseDto;
import com.baseball.dto.req_res.PBO1020RequestDto;
import com.baseball.dto.req_res.PBO1020ResponseDto;
import com.baseball.service.PBOService;

/**
 * 打順予測機能コントローラ
 */
@RestController
public class PBOController {
	
	@Autowired
	private PBOService pboService;

	/**
	 * 打順登録
	 * @param pbo1010RequestDto
	 * @return
	 */
	@PostMapping("/api/pbo1010")
	public PBO1010ResponseDto pbo1010(@RequestBody PBO1010RequestDto pbo1010RequestDto) {
		System.out.println("startpoint at controller class");
		long startTime = System.currentTimeMillis();
		//スクレイピングデータをリストに変換 (リーグ変数でセ・パ方式に変換)
		PBO1010ResponseDto pbO1010ResponseDto = pboService.pbo1010(pbo1010RequestDto);
		long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        // 処理時間を出力	
        System.out.println("処理にかかった時間: " + elapsedTime + "ミリ秒");

		return pbO1010ResponseDto;
	}
	
	/**
	 * 登録打順の合致成績取得
	 * @param pbo1020RequestDto
	 * @return
	 */
	@GetMapping("/api/pbo1020")
	public PBO1020ResponseDto pbo1020(@RequestBody PBO1020RequestDto pbo1020RequestDto) {
		System.out.println("startpoint at controller class");
		long startTime = System.currentTimeMillis();
		//スクレイピングデータをリストに変換 (リーグ変数でセ・パ方式に変換)
		PBO1020ResponseDto pbO1020ResponseDto = pboService.pbo1020(pbo1020RequestDto);
		long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        // 処理時間を出力
        System.out.println("処理にかかった時間: " + elapsedTime + "ミリ秒");

		return pbO1020ResponseDto;
	}
}
