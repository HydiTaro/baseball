create table [dbo].[player_master](
  [player_id] INT IDENTITY(1, 1)
  ,[fname] nvarchar(16) not null
  ,[lname] nvarchar(16) not null
  ,[position] char(3) not null  
  , [throw_hand] char(1)
  , [hit_hand] char(1)
  , [age] SMALLINT
  , [start_year] SMALLINT
  , [fin_year] SMALLINT
  , [team_id] char(2) not null
  , [regist_date] DATETIME DEFAULT CURRENT_TIMESTAMP
  , [update_date] DATETIME DEFAULT CURRENT_TIMESTAMP
  , primary key (player_id)
);

create table [dbo].[starting_from_yahoo](
    [game_id] INT not null
    ,[team_id] CHAR(2) not null 
    , [player_id_1] INT 
    , [position_1] CHAR(1) 
    , [player_id_2] INT 
    , [position_2] CHAR(1) 
    , [player_id_3] INT 
    , [position_3] CHAR(1) 
    , [player_id_4] INT 
    , [position_4] CHAR(1) 
    , [player_id_5] INT 
    , [position_5] CHAR(1) 
    , [player_id_6] INT 
    , [position_6] CHAR(1) 
    , [player_id_7] INT 
    , [position_7] CHAR(1) 
    , [player_id_8] INT 
    , [position_8] CHAR(1) 
    , [player_id_9] INT 
    , [position_9] CHAR(1) 
    , [game_date] char(8) 
    , [regist_date] DATETIME DEFAULT CURRENT_TIMESTAMP
    , [update_date] DATETIME DEFAULT CURRENT_TIMESTAMP
    , primary key (game_id,team_id)
    , CONSTRAINT fk_starting_from_yahoo_player_id_1 FOREIGN KEY (player_id_1) REFERENCES player_master(player_id) ON UPDATE NO ACTION ON DELETE NO ACTION
    , CONSTRAINT fk_starting_from_yahoo_player_id_2 FOREIGN KEY (player_id_2) REFERENCES player_master(player_id) ON UPDATE NO ACTION ON DELETE NO ACTION
    , CONSTRAINT fk_starting_from_yahoo_player_id_3 FOREIGN KEY (player_id_3) REFERENCES player_master(player_id) ON UPDATE NO ACTION ON DELETE NO ACTION
    , CONSTRAINT fk_starting_from_yahoo_player_id_4 FOREIGN KEY (player_id_4) REFERENCES player_master(player_id) ON UPDATE NO ACTION ON DELETE NO ACTION
    , CONSTRAINT fk_starting_from_yahoo_player_id_5 FOREIGN KEY (player_id_5) REFERENCES player_master(player_id) ON UPDATE NO ACTION ON DELETE NO ACTION
    , CONSTRAINT fk_starting_from_yahoo_player_id_6 FOREIGN KEY (player_id_6) REFERENCES player_master(player_id) ON UPDATE NO ACTION ON DELETE NO ACTION
    , CONSTRAINT fk_starting_from_yahoo_player_id_7 FOREIGN KEY (player_id_7) REFERENCES player_master(player_id) ON UPDATE NO ACTION ON DELETE NO ACTION
    , CONSTRAINT fk_starting_from_yahoo_player_id_8 FOREIGN KEY (player_id_8) REFERENCES player_master(player_id) ON UPDATE NO ACTION ON DELETE NO ACTION
    , CONSTRAINT fk_starting_from_yahoo_player_id_9 FOREIGN KEY (player_id_9) REFERENCES player_master(player_id) ON UPDATE NO ACTION ON DELETE NO ACTION
);

create table [dbo].[predict_bat_order](
  -- アカウントテーブルを外部参照するべし
    [user_id] INT not null
    , [game_date] char(8) 
    ,[team_id] CHAR(2) not null
    ,[predict_score] TINYINT 
    , [player_id_1] INT 
    , [position_1] CHAR(1) 
    , [player_id_2] INT 
    , [position_2] CHAR(1) 
    , [player_id_3] INT 
    , [position_3] CHAR(1) 
    , [player_id_4] INT 
    , [position_4] CHAR(1) 
    , [player_id_5] INT 
    , [position_5] CHAR(1) 
    , [player_id_6] INT 
    , [position_6] CHAR(1) 
    , [player_id_7] INT 
    , [position_7] CHAR(1) 
    , [player_id_8] INT 
    , [position_8] CHAR(1) 
    , [player_id_9] INT 
    , [position_9] CHAR(1) 
    , [regist_date] DATETIME DEFAULT CURRENT_TIMESTAMP
    , [update_date] DATETIME DEFAULT CURRENT_TIMESTAMP
    , primary key (user_id,team_id,game_date)
    , CONSTRAINT fk_predict_bat_order_player_id_1 FOREIGN KEY (player_id_1) REFERENCES player_master(player_id) ON UPDATE NO ACTION ON DELETE NO ACTION
    , CONSTRAINT fk_predict_bat_order_player_id_2 FOREIGN KEY (player_id_2) REFERENCES player_master(player_id) ON UPDATE NO ACTION ON DELETE NO ACTION
    , CONSTRAINT fk_predict_bat_order_player_id_3 FOREIGN KEY (player_id_3) REFERENCES player_master(player_id) ON UPDATE NO ACTION ON DELETE NO ACTION
    , CONSTRAINT fk_predict_bat_order_player_id_4 FOREIGN KEY (player_id_4) REFERENCES player_master(player_id) ON UPDATE NO ACTION ON DELETE NO ACTION
    , CONSTRAINT fk_predict_bat_order_player_id_5 FOREIGN KEY (player_id_5) REFERENCES player_master(player_id) ON UPDATE NO ACTION ON DELETE NO ACTION
    , CONSTRAINT fk_predict_bat_order_player_id_6 FOREIGN KEY (player_id_6) REFERENCES player_master(player_id) ON UPDATE NO ACTION ON DELETE NO ACTION
    , CONSTRAINT fk_predict_bat_order_player_id_7 FOREIGN KEY (player_id_7) REFERENCES player_master(player_id) ON UPDATE NO ACTION ON DELETE NO ACTION
    , CONSTRAINT fk_predict_bat_order_player_id_8 FOREIGN KEY (player_id_8) REFERENCES player_master(player_id) ON UPDATE NO ACTION ON DELETE NO ACTION
    , CONSTRAINT fk_predict_bat_order_player_id_9 FOREIGN KEY (player_id_9) REFERENCES player_master(player_id) ON UPDATE NO ACTION ON DELETE NO ACTION
);

create table [dbo].[pitcher_from_npb_official](
  [player_id] INT not null
  ,[year] CHAR(4) not null
  , [games] TINYINT not null
  , [win_num] TINYINT not null
  , [lose_num] TINYINT not null
  , [save_num] TINYINT not null
  , [hold] TINYINT not null 
  , [hold_point] TINYINT not null
  , [complete_game] TINYINT not null
  , [whitewash_win_num] TINYINT not null
  , [no_walk] TINYINT not null
  , [win_rate] Float not null
  , [against_batters] SMALLINT not null
  , [innings] FLOAT not null
  , [hits] SMALLINT not null
  , [homeruns] TINYINT not null
  , [fourball] SMALLINT not null
  , [got_walked] TINYINT not null
  , [dead_ball] TINYINT not null
  , [wild_pitch] TINYINT not null
  , [bork] TINYINT not null
  , [kk] SMALLINT not null
  , [allowed_runs] SMALLINT not null
  , [earned_run_average] FLOAT not null
  , [regist_date] DATETIME DEFAULT CURRENT_TIMESTAMP
  , [update_date] DATETIME DEFAULT CURRENT_TIMESTAMP
  , PRIMARY KEY (player_id,year)
  , CONSTRAINT fk_pitcher_from_npb_official_player_id FOREIGN KEY (player_id) REFERENCES player_master(player_id) ON UPDATE NO ACTION ON DELETE NO ACTION
);
                  
create table [dbo].[pitcher_from_npb_official_2024](
  [player_id] INT not null
  ,[year] CHAR(4) not null
  , [games] TINYINT not null
  , [win_num] TINYINT not null
  , [lose_num] TINYINT not null
  , [save_num] TINYINT not null
  , [hold] TINYINT not null 
  , [hold_point] TINYINT not null
  , [complete_game] TINYINT not null
  , [whitewash_win_num] TINYINT not null
  , [no_walk] TINYINT not null
  , [win_rate] Float not null
  , [against_batters] SMALLINT not null
  , [innings] FLOAT not null
  , [hits] SMALLINT not null
  , [homeruns] TINYINT not null
  , [fourball] SMALLINT not null
  , [got_walked] TINYINT not null
  , [dead_ball] TINYINT not null
  , [wild_pitch] TINYINT not null
  , [bork] TINYINT not null
  , [kk] SMALLINT not null
  , [allowed_runs] SMALLINT not null
  , [earned_run_average] FLOAT not null
  , [regist_date] DATETIME DEFAULT CURRENT_TIMESTAMP
  , [update_date] DATETIME DEFAULT CURRENT_TIMESTAMP
  , primary key (player_id)
  , CONSTRAINT fk_pitcher_from_npb_official_2024_player_id FOREIGN KEY (player_id) REFERENCES player_master(player_id) ON UPDATE NO ACTION ON DELETE NO ACTION
);

create table [dbo].[hitter_from_npb_official_2024](
  [player_id] INT not null
  ,[year] CHAR(4) not null
  , [games] TINYINT not null
  , [plate_appearance] SMALLINT not null
  , [at_bat] SMALLINT not null
  , [score] TINYINT not null
  , [hit_num] TINYINT not null 
  , [double_num] TINYINT not null
  , [triple_num] TINYINT not null
  , [homerun_num] TINYINT not null
  , [total_bases] SMALLINT not null
  , [rbi] TINYINT not null
  , [steal_success] TINYINT not null
  , [steal_failure] TINYINT not null
  , [bunt] TINYINT not null
  , [sacrifice_fly] TINYINT not null
  , [fourball] TINYINT not null
  , [got_walked] TINYINT not null
  , [dead_ball] TINYINT not null
  , [kk] TINYINT not null
  , [double_play] TINYINT not null
  , [bat] DECIMAL(5,4) NOT NULL
  , [slu] DECIMAL(5,4) not null
  , [obp] DECIMAL(5,4) not null
  , [regist_date] DATETIME DEFAULT CURRENT_TIMESTAMP
  , [update_date] DATETIME DEFAULT CURRENT_TIMESTAMP
  , primary key (player_id)
  , CONSTRAINT fk_hitter_from_npb_official_2024_player_id FOREIGN KEY (player_id) REFERENCES player_master(player_id) ON UPDATE NO ACTION ON DELETE NO ACTION
);

create table [dbo].[hitter_from_npb_official](
  [player_id] INT not null
  ,[year] CHAR(4) not null
  , [games] TINYINT not null
  , [plate_appearance] SMALLINT not null
  , [at_bat] SMALLINT not null
  , [score] TINYINT not null
  , [hit_num] TINYINT not null  
  , [double_num] TINYINT not null
  , [triple_num] TINYINT not null
  , [homerun_num] TINYINT not null
  , [total_bases] SMALLINT not null
  , [rbi] TINYINT not null
  , [steal_success] TINYINT not null
  , [steal_failure] TINYINT not null
  , [bunt] TINYINT not null
  , [sacrifice_fly] TINYINT not null
  , [fourball] TINYINT not null
  , [got_walked] TINYINT not null
  , [dead_ball] TINYINT not null
  , [kk] TINYINT not null
  , [double_play] TINYINT not null
  , [bat] DECIMAL(5,4) NOT NULL
  , [slu] DECIMAL(5,4) not null
  , [obp] DECIMAL(5,4) not null
  , [regist_date] DATETIME DEFAULT CURRENT_TIMESTAMP
  , [update_date] DATETIME DEFAULT CURRENT_TIMESTAMP
  , primary key (player_id,year)
  , CONSTRAINT fk_hitter_from_npb_official_player_id FOREIGN KEY (player_id) REFERENCES player_master(player_id) ON UPDATE NO ACTION ON DELETE NO ACTION
);
