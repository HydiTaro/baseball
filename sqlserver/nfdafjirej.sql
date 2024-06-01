truncate table    [dbo].[hitter_from_npb_official]

IF NOT EXISTS (
                SELECT 1
                FROM pitcher_from_npb_official
                WHERE player_id = (
                            SELECT player_id
                            FROM player_master as p 
                            WHERE p.team_id = #{listEntity.teamId}
                                AND p.fname = #{listEntity.fname}
                                AND p.lname = #{listEntity.lname} 
                         )
                  AND year = #{listEntity.yearOfInfo}
            )
            BEGIN
                INSERT INTO dbo.pitcher_from_npb_official ( 
                    player_id
                    , year, games, win_num
                    , lose_num, save_num, hold
                    , hold_point
                    , complete_game
                    , whitewash_win_num
                    , no_walk
                    , win_rate
                    , against_batters
                    , innings
                    , hits
                    , homeruns
                    , fourball
                    , got_walked
                    , dead_ball
                    , wild_pitch
                    , bork
                    , kk
                    , allowed_runs
                    , earned_run_average
                ) 
                VALUES ( 
                    (SELECT player_id 
                            FROM player_master as p 
                            WHERE p.team_id = 11
                                AND p.fname = '和田'
                            ),
                    , 2024
                    , #{lis
                    , #{listEntity.winNum}
                    , #{listEntity.loseNum}
                    , #{listEntity.saveNum}
                    , #{listEntity.hold}
                    , #{listEntity.holdPoint}
                    , #{listEntity.completeGame}
                    , #{listEntity.whitewashWinNum}
                    , #{listEntity.noWalk}
                    , #{listEntity.winRate}
                    , #{listEntity.againstBatters}
                    , #{listEntity.innings}
                    , #{listEntity.hits}
                    , #{listEntity.homeruns}
                    , #{listEntity.fourBalls}
                    , #{listEntity.gotWalked}
                    , #{listEntity.deadBalls}
                    , #{listEntity.wildPitch}
                    , #{listEntity.bork}
                    , #{listEntity.kk}
                    , #{listEntity.allowedRuns}
                    , #{listEntity.era}
                )
            END



--LEFT JOIN OPSを求めて取得（年度別最高）
SELECT 
    h.player_id,
    m.team_id,
    m.fname,
    m.lname,
    h.year,
    h.at_bat,
    h.double_num
    --( h.slu + h.obp ) as record_ops
FROM
    hitter_from_npb_official as h
--where p.fname ='和田';
--where p.player_id = 2941;
LEFT JOIN 
    player_master m
ON
    h.player_id = m.player_id
WHERE
--規定打席
    h.at_bat > 443
    AND m.team_id > 1  
ORDER BY
    h.double_num desc;
    
--INNER JOIN OPSを求めて取得（二つのテーブルに条件がある）
SELECT 
    h.player_id,
    m.team_id,
    m.fname,
    m.lname,
    h.year,
    h.at_bat,
    h.double_num,
    ( h.slu + h.obp ) as record_ops
FROM
    hitter_from_npb_official as h
--where p.fname ='和田';
--where p.player_id = 2941;
INNER JOIN 
    player_master   as  m   ON  h.player_id = m.player_id
WHERE
    m.team_id = 11
--規定打席
    AND h.at_bat > 443
ORDER BY
    record_ops desc;
    
--INNER JOIN OPSを求めて取得（二つのテーブルに条件がある）
SELECT 
    h.player_id,
    m.team_id,
    m.fname,
    m.lname,
    h.year,
    h.at_bat,
    h.bat,
    h.homerun_num,
    h.double_num,
    ( h.slu + h.obp ) as record_ops
FROM
    hitter_from_npb_official as h
INNER JOIN 
    player_master   as  m   ON  h.player_id = m.player_id
WHERE
    --m.team_id = 11
    m.fname = '小久保'
--規定打席
    --AND h.at_bat > 443
    AND h.at_bat > 100
ORDER BY
    record_ops desc;
