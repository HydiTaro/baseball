create table [dbo].[login_user] (
  [user_id] int identity not null
  , [name] varchar(128) not null
  , [email] varchar(256) not null
  , [password] varchar(128) not null
  , primary key (user_id)
);