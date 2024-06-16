

--SELECT TOP 1 player_id ,fname,lname,start_year FROM player_master where fname ='和田'AND lname='一浩' ORDER BY start_year desc ;

--併殺打の計算
SELECT m.fname,m.lname,h.plate_appearance,h.at_bat,h.bat,h.slu,h.obp,h.rbi, h.double_play FROM hitter_from_npb_official_2024 h LEFT JOIN player_master m  ON h.player_id = m.player_id
WHERE h.at_bat>50 AND m.team_id='11' ORDER BY h.rbi desc;
--併殺打の計算
SELECT m.fname,m.lname,m.team_id, h.double_play FROM hitter_from_npb_official_2024 h LEFT JOIN player_master m  ON h.player_id = m.player_id WHERE h.at_bat>50 AND m.team_id='11' ORDER BY h.double_play desc;

SELECT m.fname,m.lname,m.team_id, h.double_play, h.at_bat FROM hitter_from_npb_official_2024 h LEFT JOIN player_master m  ON h.player_id = m.player_id WHERE h.at_bat=210;
