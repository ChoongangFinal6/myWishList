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
	mNo        INTEGER      NOT NULL, -- 일련번호
	email      VARCHAR2(50) NOT NULL, -- 아이디(이메일)
	name       VARCHAR2(50) NOT NULL, -- 품명
	price      INTEGER      NOT NULL, -- 금액
	remainDate DATE         NOT NULL, -- 기한
	img        VARCHAR2(50) NULL,     -- 이미지파일명
	aNo        INTEGER      NOT NULL  -- 계좌번호
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
CREATE TABLE Account (
	aNo     INTEGER      NOT NULL, -- 일련번호
	email   VARCHAR2(50) NOT NULL, -- 아이디(이메일)
	bank    VARCHAR2(20) NOT NULL, -- 은행명
	account INTEGER      NOT NULL  -- 계좌번호
);

-- Account
ALTER TABLE Account
	ADD
		CONSTRAINT PK_Account -- Account 기본키
		PRIMARY KEY (
			aNo -- 일련번호
		);
-- MyWish
ALTER TABLE MyWish
	ADD
		CONSTRAINT FK_Account_TO_MyWish -- Account -> MyWish
		FOREIGN KEY (
			aNo -- 계좌번호
		)
		REFERENCES Account ( -- Account
			aNo -- 일련번호
		);