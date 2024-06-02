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
	private String position = "000";
	/**
	 * 投球の利き手
	 */
	private String throwHand = "0";
	/**
	 * バッティングの利き手
	 */
	private String hitHand = "0";
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
		setFnameAndLname(data);
		if (data[0].equals("*")) {// 表の左端が "*" or "+"の場合は左投げ
			// 名字と名前を区別したい
			setThrowHand(CommonEnum.DominantHand.LEFT.getValue());// 打席
		} else if(!data[0].equals("+")) {
			// 名字と名前を区別したい
			setThrowHand(CommonEnum.DominantHand.RIGHT.getValue());// 打席
		} else {
			setThrowHand(CommonEnum.DominantHand.BOTHSIDE.getValue());// 打席
		}
	}

	/**
	 * 取得したデータから、名前と利き手を設定する
	 */
	public void setNameAndHandH(String[] data) {
		setFnameAndLname(data);
		if (data[0].equals("*")) {// 表の左端が "*" or "+"の場合は左投げ
			// 名字と名前を区別したい
			setHitHand(CommonEnum.DominantHand.LEFT.getValue());// 打席（左打ちまたは両打ち）
		} else if(!data[0].equals("+")) {
			// 名字と名前を区別したい
			setHitHand(CommonEnum.DominantHand.RIGHT.getValue());// 打席
		} else {
			setHitHand(CommonEnum.DominantHand.BOTHSIDE.getValue());// 打席
		}
	
	}
	
	private void setFnameAndLname(String[] data) {
		String[] fnameAndLname = data[1].split("　");
		if (data[0].equals("*") || data[0].equals("+")) {// 表の左端が "*" or "+"の場合は左投げ
			setFname(fnameAndLname[0]);
		// 右投げ
		} else {
			// 名字と名前を区別したい
			fnameAndLname = data[0].split("　");
			setFname(fnameAndLname[0]);
		}
		setLname((fnameAndLname.length == 2) ? fnameAndLname[1]
				: (fnameAndLname.length == 1) ? fnameAndLname[0] : fnameAndLname[1]+fnameAndLname[2]);// 名
	}

}
