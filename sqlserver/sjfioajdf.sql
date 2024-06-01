UPDATE hitter_from_npb_official_2024 
SET
    [games] = :games
    , [plate_appearance] = :plate_appearance
    , [at_bat] = :at_bat
    , [score] = :score
    , [hit_num] = :hit_num
    , [double_num] = :double_num
    , [triple_num] = :triple_num
    , [homerun_num] = :homerun_num
    , [total_bases] = :total_bases
    , [rbi] = :rbi
    , [steal_success] = :steal_success
    , [steal_failure] = :steal_failure
    , [bunt] = :bunt
    , [sacrifice_fly] = :sacrifice_fly
    , [fourball] = :fourball
    , [got_walked] = :got_walked
    , [dead_ball] = :dead_ball
    , [kk] = :kk
    , [double_play] = :double_play
    , [bat] = :bat
    , [slu] = :slu
    , [obp] = :obp
    , [regist_date] = :regist_date
    , [update_date] = :update_date 
WHERE
    [player_id] = :player_id 
    and [year] = :year

