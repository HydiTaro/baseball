truncate table    [dbo].[hitter_from_npb_official]

--LEFT JOIN OPSを求めて取得（年度別最高）
SELECT  h.player_id,m.team_id,m.fname,m.lname,h.year,h.at_bat,h.double_num
    --( h.slu + h.obp ) as record_ops FROM hitter_from_npb_official as h
--where p.fname ='和田';
--where p.player_id = 2941;

--規定打席(443)
LEFT JOIN player_master m ON h.player_id = m.player_id WHERE h.at_bat > 443 AND m.team_id > 1  ORDER BY h.double_num desc;
    


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
