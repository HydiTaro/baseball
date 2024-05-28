package com.baseball.scrape.dto;

import com.baseball.common.CommonEnum;

import lombok.Data;

@Data
public class NpbPlayerMasterDTO {
	
	/**
	 * 選手ID
	 */
	private int playerId;
	/**
	 * 姓
	 */
	private String fname;
	/**
	 * 名
	 */
	private String lname;
	/**
	 * 守備位置
	 */
	private String position="000";
	/**
	 * 投球の利き手
	 */
	private String throwHand="0";
	/**
	 * バッティングの利き手
	 */
	private String hitHand="0";
	/**
	 * データの記録年
	 */
	private int yearOfInfo;
	/**
	 * チームID
	 */
	private String teamId;
	/**
	 * 誕生年
	 */
	private int age;
	
	/**
	 * 取得したデータから、名前と利き手を設定する
	 */
	public void setNameAndHandP(String[] data) {
		if(data[0].equals("*") || data[0].equals("+")) {// 表の左端が "*" or "+"の場合は左投げ
			// 名字と名前を区別したい
			String[] fnameAndLname = data[1].split("　");
			// 以降statマップにセット
			setFname(fnameAndLname[0]);
			setLname((fnameAndLname.length == 2) ? fnameAndLname[1] : fnameAndLname[0]);// 名
			setThrowHand((data[0].equals("*")) ? CommonEnum.DominantHand.LEFT.getValue()
					: CommonEnum.DominantHand.BOTHSIDE.getValue());// 打席
		} else {
			// 名字と名前を区別したい
			String[] fnameAndLname = data[0].split("　");
			setFname(fnameAndLname[0]);
			setLname((fnameAndLname.length == 2) ? fnameAndLname[1] : fnameAndLname[0]);// 名
			setThrowHand(CommonEnum.DominantHand.RIGHT.getValue());// 打席
		}
	}
	
	/**
	 * 取得したデータから、名前と利き手を設定する
	 */
	public void setNameAndHandH(String[] data) {
		if(data[0].equals("*") || data[0].equals("+")) {// 表の左端が "*" or "+"の場合は左投げ
			// 名字と名前を区別したい
			String[] fnameAndLname = data[1].split("　");
			// 以降statマップにセット
			setFname(fnameAndLname[0]);
			setLname((fnameAndLname.length == 2) ? fnameAndLname[1] : fnameAndLname[0]);// 名
			setHitHand((data[0].equals("*")) ? CommonEnum.DominantHand.LEFT.getValue()
					: CommonEnum.DominantHand.BOTHSIDE.getValue());// 打席
		} else {
			// 名字と名前を区別したい
			String[] fnameAndLname = data[0].split("　");
			setFname(fnameAndLname[0]);
			setLname((fnameAndLname.length == 2) ? fnameAndLname[1] : fnameAndLname[0]);// 名
			setHitHand(CommonEnum.DominantHand.RIGHT.getValue());// 打席
		}
	}
	
}
