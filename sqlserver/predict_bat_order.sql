create table [dbo].[predict_bat_order](
    [user_id] INT not null
    ,[game_id] INT not null
    ,[team_id] CHAR(2) not null
    , [player_id_1] INT 
    -- , [position_1] CHAR(1) 
    , [player_id_2] INT 
    -- , [position_2] CHAR(1) 
    , [player_id_3] INT 
    -- , [position_3] CHAR(1) 
    , [player_id_4] INT 
    -- , [position_4] CHAR(1) 
    , [player_id_5] INT 
    -- , [position_5] CHAR(1) 
    , [player_id_6] INT 
    -- , [position_6] CHAR(1) 
    , [player_id_7] INT 
    -- , [position_7] CHAR(1) 
    , [player_id_8] INT 
    -- , [position_8] CHAR(1) 
    , [player_id_9] INT 
    -- , [position_9] CHAR(1) 
    , [predit_date] char(8) 
    , [regist_date] DATETIME DEFAULT CURRENT_TIMESTAMP
    , [update_date] DATETIME DEFAULT CURRENT_TIMESTAMP
    , primary key (user_id,game_id,team_id)
);
--コメント付与
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'登録者のID'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'predict_bat_order'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'user_id'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'試合のID'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'predict_bat_order'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'game_id'
                                 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'チームID'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'predict_bat_order'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'team_id'
 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'試合日'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'predict_bat_order'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'game_date'

EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'登録日時'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'predict_bat_order'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'regist_date'
 
EXEC sys.sp_addextendedproperty  @name=N'MS_Description'
                                ,@value=N'更新日時'
                                ,@level0type=N'SCHEMA'
                                ,@level0name=N'dbo'
                                ,@level1type=N'TABLE'
                                ,@level1name=N'predict_bat_order'
                                ,@level2type=N'COLUMN'
                                ,@level2name=N'update_date'
                                