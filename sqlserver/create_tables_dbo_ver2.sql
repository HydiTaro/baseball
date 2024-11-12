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
  , CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES player_master(player_id)
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
  , CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES player_master(player_id)
);
