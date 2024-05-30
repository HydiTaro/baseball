create table [dbo].[starting_from_yahoo](
  [player_id] INT not null
  , [team_id] CHAR(2) not null
  , [vs_team] CHAR(2) not null
  , [startNo] char(1) not null
  , [position] CHAR(1) not null
  , [game_date] char(8) not null
  , [regist_date] DATETIME DEFAULT CURRENT_TIMESTAMP
  , [update_date] DATETIME DEFAULT CURRENT_TIMESTAMP
  , primary key (team_id)
);
--コメント付与
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'選手ID'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'starting_from_yahoo'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'player_id'
                                 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'チームID'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'starting_from_yahoo'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'team_id'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'対戦相手のチームID'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'starting_from_yahoo'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'vs_team'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'打順'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'starting_from_yahoo'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'startNo'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'守備位置'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'starting_from_yahoo'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'position'
 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'試合日'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'starting_from_yahoo'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'game_date'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'登録日時'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'starting_from_yahoo'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'regist_date'
 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'更新日時'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'starting_from_yahoo'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'update_date'
                                