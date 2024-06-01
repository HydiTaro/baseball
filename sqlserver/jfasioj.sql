UPDATE [dbo].[pitcher_from_npb_official_2024] 
SET
    [year] = :year
    , [games] = :games
    , [win_num] = :win_num
    , [lose_num] = :lose_num
    , [save_num] = :save_num
    , [hold] = :hold
    , [hold_point] = :hold_point
    , [complete_game] = :complete_game
    , [whitewash_win_num] = :whitewash_win_num
    , [no_walk] = :no_walk
    , [win_rate] = :win_rate
    , [against_batters] = :against_batters
    , [innings] = :innings
    , [hits] = :hits
    , [homeruns] = :homeruns
    , [fourball] = :fourball
    , [got_walked] = :got_walked
    , [dead_ball] = :dead_ball
    , [wild_pitch] = :wild_pitch
    , [bork] = :bork
    , [kk] = :kk
    , [allowed_runs] = :allowed_runs
    , [earned_run_average] = :earned_run_average 
WHERE
    [player_id] = :player_id

