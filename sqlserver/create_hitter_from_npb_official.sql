create table [dbo].[hitter_from_npb_official](
--   [player_id] int 
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
);
--コメント付与
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'選手ID'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'hitter_from_npb_official'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'player_id'
                                 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'データ年度'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'hitter_from_npb_official'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'year'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'試合数'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'hitter_from_npb_official'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'games'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'打席'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'hitter_from_npb_official'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'plate_appearance'
 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'打数'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'hitter_from_npb_official'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'at_bat'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'得点'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'hitter_from_npb_official'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'score'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'安打数'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'hitter_from_npb_official'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'hit_num'
 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'二塁打'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'hitter_from_npb_official'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'double_num'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'三塁打'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'hitter_from_npb_official'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'triple_num'
 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'本塁打'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'hitter_from_npb_official'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'homerun_num'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'塁打'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'hitter_from_npb_official'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'total_bases'
 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'打点'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'hitter_from_npb_official'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'rbi'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'盗塁数'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'hitter_from_npb_official'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'steal_success'
 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'盗塁失敗'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'hitter_from_npb_official'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'steal_failure'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'犠打'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'hitter_from_npb_official'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'bunt'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'犠飛 '
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'hitter_from_npb_official'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'sacrifice_fly'
 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'四球'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'hitter_from_npb_official'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'fourball'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'故意四球'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'hitter_from_npb_official'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'got_walked'
 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'死球'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'hitter_from_npb_official'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'dead_ball'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'三振'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'hitter_from_npb_official'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'kk'
 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'併殺打'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'hitter_from_npb_official'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'double_play'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'打率'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'hitter_from_npb_official'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'bat'
 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'長打率'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'hitter_from_npb_official'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'slu'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'出塁率'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'hitter_from_npb_official'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'obp'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'登録日時'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'hitter_from_npb_official'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'regist_date'
 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'更新日時'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'hitter_from_npb_official'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'update_date'
                                