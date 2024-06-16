-- create table dbo.roles(
--     role_id INT primary key,
--     name VARCHAR(32) not null
-- );

-- create table dbo.login_user(
--     user_id int IDENTITY primary key,
--     name VARCHAR(128) not null,
--     email VARCHAR(256) not null,
--     password VARCHAR(128) not null
-- );

-- create table dbo.user_role(
--     user_id int,
--     role_id int,
--     constraint pk_user_role primary key (user_id,role_id),
--     constraint fk_user_role_user_id FOREIGN KEY (user_id) REFERENCES login_user(user_id),
--     constraint fk_user_role_role_id FOREIGN KEY (role_id) REFERENCES roles(role_id),
-- )

-- INSERT INTO roles(role_id,name) VALUES(1,'ROLE_GENERAL');
-- INSERT INTO roles(role_id,name) VALUES(2,'ROLE_ADMIN');

INSERT INTO login_user(name, email, password) VALUES ('zenn','2401.dress@gmail.com'
,'pass');
-- INSERT INTO login_user(name, email, password) VALUES ('管理太郎','admin@gmail.com'
-- ,'pass');

-- INSERT INTO user_role(user_id, role_id) VALUES(1,1);
-- INSERT INTO user_role(user_id, role_id) VALUES(2,1);
-- INSERT INTO user_role(user_id, role_id) VALUES(2,2);