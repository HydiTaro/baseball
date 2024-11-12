IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[player_master') AND type in (N'U'))
DROP TABLE [dbo].[player_master]

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[hitter_from_npb_official]') AND type in (N'U'))
DROP TABLE [dbo].[hitter_from_npb_official]

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[hitter_from_npb_official_2024]') AND type in (N'U'))
DROP TABLE [dbo].[hitter_from_npb_official_2024]

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pitcher_from_npb_official') AND type in (N'U'))
DROP TABLE [dbo].[pitcher_from_npb_official]

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pitcher_from_npb_official_2024]') AND type in (N'U'))
DROP TABLE [dbo].[pitcher_from_npb_official_2024]

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[starting_from_yahoo]') AND type in (N'U'))
DROP TABLE [dbo].[starting_from_yahoo]

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[predict_bat_order]') AND type in (N'U'))
DROP TABLE [dbo].[predict_bat_order]



