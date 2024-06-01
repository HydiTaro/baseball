INSERT 
INTO [dbo].[pitcher_from_npb_official] ( 
    [player_id]
    , [year]
    , [games]
    , [win_num]
    , [lose_num]
    , [save_num]
    , [hold]
    , [hold_point]
    , [complete_game]
    , [whitewash_win_num]
    , [no_walk]
    , [win_rate]
    , [against_batters]
    , [innings]
    , [hits]
    , [homeruns]
    , [fourball]
    , [got_walked]
    , [dead_ball]
    , [wild_pitch]
    , [bork]
    , [kk]
    , [allowed_runs]
    , [earned_run_average]
    , [regist_date]
    , [update_date]
) 
VALUES ( 
    :player_id
    , :year
    , :games
    , :win_num
    , :lose_num
    , :save_num
    , :hold
    , :hold_point
    , :complete_game
    , :whitewash_win_num
    , :no_walk
    , :win_rate
    , :against_batters
    , :innings
    , :hits
    , :homeruns
    , :fourball
    , :got_walked
    , :dead_ball
    , :wild_pitch
    , :bork
    , :kk
    , :allowed_runs
    , :earned_run_average
    , :regist_date
    , :update_date
)

