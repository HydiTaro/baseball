UPDATE hitter_from_npb_official_2024 
SET
    games = #{listEntity.games}
    , plate_appearance = #{listEntity.plate_appearance}
    , at_bat = #{listEntity.at_bat}
    , score = #{listEntity.score}
    , hit_num = #{listEntity.hit_num}
    , double_num = #{listEntity.double_num}
    , triple_num = #{listEntity.triple_num}
    , homerun_num = #{listEntity.homerun_num}
    , total_bases = #{listEntity.total_bases}
    , rbi = #{listEntity.rbi}
    , steal_success = #{listEntity.steal_success}
    , steal_failure = #{listEntity.steal_failure}
    , bunt = #{listEntity.bunt}
    , sacrifice_fly = #{listEntity.sacrifice_fly}
    , fourball = #{listEntity.fourball}
    , got_walked = #{listEntity.got_walked}
    , dead_ball = #{listEntity.dead_ball}
    , kk = #{listEntity.kk}
    , double_play = #{listEntity.double_play}
    , bat = #{listEntity.bat}
    , slu = #{listEntity.slu}
    , obp = #{listEntity.obp}
    , regist_date = #{listEntity.regist_date}
    , update_date = #{listEntity.update_date }
WHERE
    player_id = #{listEntity.player_id }
    and year = #{listEntity.year}
