package com.baseball.common;

import java.util.EnumSet;
import java.util.Set;

import lombok.Data;

public class CommonEnum {

	public static enum ResultOfBat {

		HIT_SINGLE("一塁打", "1"), HIT_DOUBLE("二塁打", "2"), HIT_TRIPLE("三塁打", "3"), HIT_HOMERUN("本塁打", "4"),
		HIT_FOURBALL("四球", "5"), OUT_NONKK("凡退", "10"), OUT_KK("三振", "11"), OUT_BUNT("犠打", "12"),
		OUT_SACRIFICEFLY("犠飛", "13");

		private final String name;
		private final String value;

		ResultOfBat(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public String getName() {
			return name;
		}
	}

	public static enum ResultOfDef {

		NOERROR("エラーなし", "0"), ERROR("エラーあり", "10");

		private final String name;
		private final String value;

		ResultOfDef(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public String getName() {
			return name;
		}
	}

	public static enum ResultOfRun {

		ONEBASE("一つ進塁成功", "1"), TWOBASE("二つ進塁成功", "2"), THIRDBASE("三つ進塁成功", "3"), OUT("走塁失敗", "10");

		private final String name;
		private final String value;

		ResultOfRun(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public String getName() {
			return name;
		}
	}

	public static enum Base {
		FRSTBASE("一塁", "1", false), SCNDBASE("二塁", "2", false), THRDBASE("三塁", "3", false);

		private final String name;
		private final String value;
		private final boolean b;

		Base(String name, String value, boolean b) {
			this.name = name;
			this.value = value;
			this.b = b;
		}

		public String getValue() {
			return value;
		}

		public String getName() {
			return name;
		}

		public boolean isOnOff() {
			return b;
		}
	}

	public static enum DominantHand {
		LEFT("右", "1"), RIGHT("左", "2"), BOTHSIDE("両方", "0");

		private final String name;
		private final String value;

		DominantHand(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public String getName() {
			return name;
		}
	}

	public static enum Position {
		PITCHER("投手", "001"), CATCHER("捕手", "002"), FIRSTBASE("一塁手", "003"), SECONDBASE("二塁手", "004"),
		THIRDBASE("三塁手", "005"), SHORTSTOP("遊撃手", "006"), LEFTFIELD("左翼手", "007"), CENTERFIELD("中堅手", "008"),
		RIGHTFIELD("右翼手", "009"), DH("DH", "010");

		private final static Set<Position> setOfPacificRule = EnumSet.of(PITCHER, CATCHER, FIRSTBASE, SECONDBASE,
				THIRDBASE, SHORTSTOP, LEFTFIELD, CENTERFIELD, RIGHTFIELD, DH);
		private final static Set<Position> setOfCentralRule = EnumSet.of(PITCHER, CATCHER, FIRSTBASE, SECONDBASE,
				THIRDBASE, SHORTSTOP, LEFTFIELD, CENTERFIELD, RIGHTFIELD);

		private final String name;
		private final String value;

		Position(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public String getName() {
			return name;
		}

		public static Set<Position> getAllTeam() {
			return setOfPacificRule;
		}

		public static Set<Position> getNow12Teams() {
			return setOfCentralRule;
		}
	}

	public static enum Leag{
    	
    	PACIFIC("パリーグ","1"),
    	CENTRAL("セリーグ","2");
    	
        private final String name;
        private final String value;
        
        Leag(String name, String value){
            this.name = name;
            this.value = value;
        }
        public String getValue() {
			return value;
		}
		public String getName() {
			return name;
		}
    }
    
    public static enum Team{
    	HAWKS("福岡ソフトバンクホークス","ホークス","11","h"),
    	LIONS("埼玉西武ライオンズ","ライオンズ","12","l"),
    	EAGLES("東北楽天ゴールデンイーグルス","イーグルス","13","e"),
    	FIGHTERS("北海道日本ハムファイターズ","ファイターズ","14","f"),
    	MARINES("千葉ロッテマリーンズ","マリーンズ","15","m"),
    	BUFFERORS("オリックス・バッファローズ","バッファローズ","16","b"),
    	GIANTS("読売ジャイアンツ","ジャイアンツ","21","g"),
    	DRAGONS("中日ドラゴンズ","ドラゴンズ","22","d"),
    	BAYSTARS("横浜DeNAベイスターズ","ベイスターズ","23","db"),
    	TIGERS("阪神タイガース","タイガース","24","t"),
    	CARP("広島東洋カープ","マリーンズ","25","c"),
    	SWALLOWS("東京ヤクルトスワローズ","スワローズ","26","s"),
    	OLDBAYSTARS("横浜ベイスターズ","ベイスターズ","31","yb");
    	
        private final String fullName;
        private final String shortenName;
        private final String value;
        private final String prefix;
        private final static Set<Team> setOfAll = EnumSet.allOf(Team.class);
        private final static Set<Team> setNow12 = EnumSet.of(HAWKS, LIONS, EAGLES,FIGHTERS,MARINES,BUFFERORS
        		,GIANTS,DRAGONS,BAYSTARS,TIGERS,CARP,SWALLOWS);
         
        Team(String fullName,String shortenName, String value,String prefix){
            this.fullName = fullName;
            this.shortenName = shortenName;
            this.value = value;
            this.prefix = prefix;
        }
        public String getValue() {
			return value;
		}
		public String getFullName() {
			return fullName;
		}
		public String getShortenName() {
			return shortenName;
		}
		public String getPrefix() {
			return prefix;
		}
		public static Set<Team> getAllTeam(){
			return setOfAll;
		}
		public static Set<Team> getNow12Teams(){
			return setNow12;
		}
    }
}
	
