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
	mNo        INTEGER      PRIMARY KEY, -- 일련번호
	email      VARCHAR2(50) NOT NULL, 	-- 아이디(이메일)
	name       VARCHAR2(50) NOT NULL, 	-- 품명
	price      INTEGER      NOT NULL, 	-- 금액
	remainDate DATE         NOT NULL, 	-- 기한
	success    NUMBER(1,0)  NULL,     	-- 성공여부
	img        VARCHAR2(50) NULL     	-- 이미지파일명
);

-- MyWish
ALTER TABLE MyWish
	ADD
		CONSTRAINT PK_MyWish -- MyWish 기본키
		PRIMARY KEY (
			mNo,   -- 일련번호
			email, -- 아이디(이메일)
			aNo    -- 계좌번호
		);

-- Account
DROP TABLE Account;
CREATE TABLE Account (
	email   VARCHAR2(50)	NOT NULL,	-- 아이디(이메일)
	bank    VARCHAR2(20)	NOT NULL,	-- 은행명
	account NUMBER(20)    	PRIMARY KEY,	-- 계좌번호
	pass	VARCHAR2(4)		NOT NULL,	-- 패스워드
	money   NUMBER(20)    	NULL		-- 잔고
	
);
DELETE Account;
SELECT * FROM Account;
insert into account values('uu@gmail.com', '우리', 1000101, '0000', 900000000);









