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
--コメント付与
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'選手ID'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo' 
                                ,@level1type=N'TABLE'
                                ,@level1name=N'player_master'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'player_id'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'名前（姓）'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'player_master'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'fname'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'名前（名）'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'player_master'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'lname'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'選手区分（ポジション）'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'player_master'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'position'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'投球の右左'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'player_master'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'throw_hand'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'打席の右左'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'player_master'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'hit_hand'
 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'年齢'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'player_master'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'age'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'プロ入り年度'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'player_master'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'start_year'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'所属チーム'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'player_master'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'team_id'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'状態（現役、引退、メジャー）'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'player_master'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'status'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'登録日時'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'player_master'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'regist_date'
 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'更新日時'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'player_master'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'update_date'
                                