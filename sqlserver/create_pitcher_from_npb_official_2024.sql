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
);
--コメント付与
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'選手ID'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'pitcher_from_npb_official_2024'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'player_id'
                                 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'データ年度'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'pitcher_from_npb_official_2024'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'year'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'登板数'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'pitcher_from_npb_official_2024'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'games'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'勝利'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'pitcher_from_npb_official_2024'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'win_num'
 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'敗北'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'pitcher_from_npb_official_2024'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'lose_num'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'セーブ'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'pitcher_from_npb_official_2024'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'save_num'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'ホールド'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'pitcher_from_npb_official_2024'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'hold'
 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'ホールドポイント'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'pitcher_from_npb_official_2024'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'hold_point'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'完投'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'pitcher_from_npb_official_2024'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'complete_game'
 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'完封勝'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'pitcher_from_npb_official_2024'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'whitewash_win_num'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'無四球'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'pitcher_from_npb_official_2024'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'no_walk'
 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'勝率'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'pitcher_from_npb_official_2024'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'win_rate'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'打者'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'pitcher_from_npb_official_2024'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'against_batters'
 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'投球回'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'pitcher_from_npb_official_2024'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'innings'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'安打'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'pitcher_from_npb_official_2024'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'hits'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'本塁打'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'pitcher_from_npb_official_2024'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'homeruns'
 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'四球'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'pitcher_from_npb_official_2024'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'fourball'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'故意四球'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'pitcher_from_npb_official_2024'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'got_walked'
 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'死球'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'pitcher_from_npb_official_2024'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'dead_ball'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'三振'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'pitcher_from_npb_official_2024'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'kk'
 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'暴投'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'pitcher_from_npb_official_2024'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'wild_pitch'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'ボーク'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'pitcher_from_npb_official_2024'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'bork'
 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'失点'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'pitcher_from_npb_official_2024'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'allowed_runs'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'防御率'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'pitcher_from_npb_official_2024'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'earned_run_average'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'登録日時'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'pitcher_from_npb_official_2024'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'regist_date'
 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'更新日時'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'pitcher_from_npb_official_2024'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'update_date'
                                