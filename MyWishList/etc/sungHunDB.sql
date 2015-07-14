-- MyWish
ALTER TABLE MyWish
	DROP CONSTRAINT FK_Account_TO_MyWish; -- Account -> MyWish

-- MyWish
ALTER TABLE MyWish
	DROP CONSTRAINT PK_MyWish; -- MyWish 기본키

-- Account
ALTER TABLE Account
	DROP CONSTRAINT PK_Account; -- Account 기본키

-- MyWish
DROP TABLE MyWish;

-- Account
DROP TABLE Account;

-- MyWish
CREATE TABLE MyWish (
	wishNo	   NUMBER		PRIMARY KEY, -- 위시리스트 번호
	email      VARCHAR2(50) NOT NULL, 	 -- 아이디(이메일)
	product    VARCHAR2(50) NOT NULL, 	 -- 품명
	price      INTEGER      NOT NULL, 	 -- 금액
	remainDate DATE         NOT NULL, 	 -- 기한
	success    NUMBER		NULL,     	 -- 성공여부
	img        VARCHAR2(50) NULL      	 -- 이미지파일명
);

delete MyWish;

insert into myWish values(1, 'ch@gmail.com', '새우깡', 20000, '2015-07-16', 0, null);
insert into myWish values(2, 'ch@gmail.com', '신발', 50000, '2015-07-17', 0, null);
insert into myWish values(3, 'ch@gmail.com', '아미고(보드게임)', 52100, '2015-07-18', 0, null);
insert into myWish values(4, 'ch@gmail.com', '그림', 7400, '2015-07-15', 0, null);
insert into myWish values(5, 'ch@gmail.com', '스노우보드', 102300, '2015-07-14', 0, null);
insert into myWish values(6, 'ch@gmail.com', '새우깡1', 55210, '2015-07-16', 0, null);
insert into myWish values(7, 'ch@gmail.com', '신발1', 73300, '2015-07-17', 0, null);
insert into myWish values(8, 'ch@gmail.com', '아미고(보드게임)1', 30000, '2015-07-18', 0, null);
insert into myWish values(9, 'ch@gmail.com', '그림1', 305000, '2015-07-15', 0, null);
insert into myWish values(10, 'ch@gmail.com', '스노우보드1', 55100, '2015-07-14', 0, null);
insert into myWish values(11, 'ch@gmail.com', '새우깡2', 25600, '2015-07-16', 0, null);
insert into myWish values(12, 'ch@gmail.com', '신발2', 35000, '2015-07-17', 0, null);
insert into myWish values(13, 'ch@gmail.com', '아미고(보드게임)2', 11000, '2015-07-18', 0, null);
insert into myWish values(14, 'ch@gmail.com', '그림2', 74000, '2015-07-15', 0, null);
insert into myWish values(15, 'ch@gmail.com', '스노우보드2', 95000, '2015-07-14', 0, null);

select * from mywish;

select * from account;

delete ACCOUNT;


-- Account
CREATE TABLE Account (
	account	 VARCHAR2(30) PRIMARY KEY,  -- 계좌번호
	email  	 VARCHAR2(50) NOT NULL, -- 아이디(이메일)
	password VARCHAR2(20) NOT NULL, -- 통장 비밀번호
	bank   	 VARCHAR2(20) NOT NULL, -- 은행명
	money  	 INTEGER      NULL      -- 잔고
);

insert into Account values('110-028-383568', '123', 'ch@gmail.com', '신한', 200000);
insert into Account values('130-331-358612', '123', 'ch@gmail.com', '농협', 100000);
insert into Account values('140-125-648532', '123', 'ch@gmail.com', '신협', 50000);
insert into Account values('110-835-872496', '123', 'ch@gmail.com', '신한', 7000);

