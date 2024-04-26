CREATE TABLE `Attraction` (
	`content_id`	int	NOT NULL	COMMENT '받아온 데이터 내 content_id 를 PK 로 사용',
	`thumbnail_image_id`	int	NOT NULL	COMMENT 'Auto Increment',
	`title`	varchar(100)	NOT NULL,
	`tel`	varchar(50)	NULL,
	`description`	varchar(10000)	NULL	COMMENT '장소 설명'
);

-- UPDATE Attraction a
-- JOIN attraction_description ad ON a.content_id = ad.content_id
-- SET a.description = ad.overview;

-- INSERT INTO Attraction (content_id, thumbnail_image_id, title, tel, description)
-- SELECT content_id, 0, title, tel, NULL
-- FROM attraction_info;

CREATE TABLE `Address` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT 'Auto Increment',
    `content_id` int NOT NULL,
    `sido_code` int NOT NULL,
    `gugun_code` int NOT NULL,
    `address` varchar(100) NULL COMMENT '전체 주소',
    `latitude` decimal(20, 17) NULL COMMENT '위도',
    `longitude` decimal(20, 17) NULL COMMENT '경도',
    PRIMARY KEY (`id`)
);


-- INSERT INTO Address ( content_id, sido_code, gugun_code, address, latitude, longitude)
-- SELECT  content_id, sido_code, gugun_code, CONCAT(addr1, ' ', addr2), latitude, longitude
-- FROM attraction_info;

CREATE TABLE `Content_Type` (
	`content_type_id`	int	NOT NULL	COMMENT '0 : 구분없음',
	`content_id`	int	NOT NULL,
	`type_name`	varchar(30)	NOT NULL
);
CREATE TABLE `Content_Type` (
    `content_type_id` int NOT NULL COMMENT '0 : 구분없음',
    `content_id` int NOT NULL,
    `type_name` varchar(30) NOT NULL,
    PRIMARY KEY (`content_type_id`, `content_id`)
);
-- INSERT INTO Content_Type (content_type_id, content_id, type_name)
-- SELECT 
--     content_type_id,
--     content_id,
--     CASE 
--         WHEN content_type_id = 12 THEN '관광지'
--         WHEN content_type_id = 14 THEN '문화시설'
--         WHEN content_type_id = 15 THEN '축제공연행사'
--         WHEN content_type_id = 25 THEN '여행코스'
--         WHEN content_type_id = 28 THEN '레포츠'
--         WHEN content_type_id = 32 THEN '숙박'
--         WHEN content_type_id = 38 THEN '쇼핑'
--         WHEN content_type_id = 39 THEN '음식점'
--         ELSE '구분없음'
--     END AS type_name
-- FROM attraction_info;

CREATE TABLE `SiDo` (
	`sido_code`	int	NOT NULL,
	`sido_name`	varchar(30)	NOT NULL
);

CREATE TABLE `GuGun` (
	`gugun_code`	int	NOT NULL,
	`sido_code`	int	NULL,
	`gugun_name`	varchar(30)	NOT NULL
);

CREATE TABLE `members` (
	`id`	int	NOT NULL AUTO_INCREMENT	 COMMENT 'Auto Increment',
	`user_name`	varchar(45)	NOT NULL,
	`user_id`	varchar(45)	NOT NULL,
	`user_password`	varchar(45)	NOT NULL,
	`email`	varchar(200)	NOT NULL,
    `join_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`agreeYN`	tinyint(1)	NOT NULL	DEFAULT 0	COMMENT '0: disagree 1: agree',
	`image_id`	int NULL,
    PRIMARY KEY (`id`),
	UNIQUE KEY `unique_user_id` (`user_id`)
);
ALTER TABLE `members`
ADD UNIQUE INDEX `unique_user_id` (`user_id`);

-- INSERT INTO `ssafyproject`.`members` (`user_id`, `user_name`, `user_password`, `email`, `join_date`, `agreeYN`)
-- VALUES ('ssafy', '김싸피', '1234', 'ssafy@naver.com', NOW(), 1), 
--        ('admin', '관리자', '1234', 'admin@naver.com', NOW(), 1);


CREATE TABLE IF NOT EXISTS `ssafyproject`.`board` (
  `article_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(16) NULL DEFAULT NULL,
  `subject` VARCHAR(100) NULL DEFAULT NULL,
  `content` VARCHAR(2000) NULL DEFAULT NULL,
  `hit` INT NULL DEFAULT 0,
  `register_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_no`),
  INDEX `board_to_members_user_id_fk` (`user_id` ASC),
  CONSTRAINT `board_to_members_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafyweb`.`members` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `List` (
	`id`	int	NOT NULL	COMMENT 'Auto Increment',
	`user_id`	int	NOT NULL,
	`title`	varchar(30)	NULL,
	`description`	varchar(100)	NULL
);

CREATE TABLE `Attraction_List` (
	`list_id`	int	NOT NULL,
	`content_id`	int	NOT NULL
);

CREATE TABLE `Review` (
	`id`	int	NOT NULL	COMMENT 'Auto Increment',
	`title`	varchar(50)	NOT NULL,
	`content`	varchar(10000)	NULL,
	`user_id`	int	NOT NULL,
	`content_id`	int	NOT NULL,
	`hit`	int	NOT NULL	DEFAULT 0
);

CREATE TABLE `Image` (
	`id`	int	NOT NULL	AUTO_INCREMENT COMMENT 'Auto Increment',
	`url`	varchar(5000)	NULL
);

-- INSERT INTO Image (url)
-- SELECT first_image
-- FROM attraction_info;

CREATE TABLE `ReviewImage` (
	`review_id`	int	NOT NULL,
	`file_id`	int	NOT NULL
);

CREATE TABLE `Comment` (
	`id`	int	NOT NULL	COMMENT 'Auto Increment',
	`review_id`	int	NOT NULL,
	`user_id`	int	NOT NULL,
	`parent_id`	int	NULL	DEFAULT NULL	COMMENT 'NULL : 일반 댓글, NOT NULL : 대댓글'
);

CREATE TABLE `Like` (
	`review_id`	int	NOT NULL,
	`user_id`	int	NOT NULL
);

ALTER TABLE `Attraction` ADD CONSTRAINT `PK_ATTRACTION` PRIMARY KEY (
	`content_id`
);

ALTER TABLE `Address` ADD CONSTRAINT `PK_ADDRESS` PRIMARY KEY (
	`id`
);

ALTER TABLE `Content_Type` ADD CONSTRAINT `PK_CONTENT_TYPE` PRIMARY KEY (
	`content_type_id`
);

ALTER TABLE `SiDo` ADD CONSTRAINT `PK_SIDO` PRIMARY KEY (
	`sido_code`
);

ALTER TABLE `GuGun` ADD CONSTRAINT `PK_GUGUN` PRIMARY KEY (
	`gugun_code`
);

ALTER TABLE `Members` ADD CONSTRAINT `PK_MEMBERS` PRIMARY KEY (
	`id`
);

ALTER TABLE `List` ADD CONSTRAINT `PK_LIST` PRIMARY KEY (
	`id`
);

ALTER TABLE `Review` ADD CONSTRAINT `PK_REVIEW` PRIMARY KEY (
	`id`
);

ALTER TABLE `Image` ADD CONSTRAINT `PK_IMAGE` PRIMARY KEY (
	`id`
);

ALTER TABLE `Comment` ADD CONSTRAINT `PK_COMMENT` PRIMARY KEY (
	`id`
);

ALTER TABLE `Attraction` ADD CONSTRAINT `FK_Image_TO_Attraction_1` FOREIGN KEY (
	`thumbnail_iamge_id`
)
REFERENCES `Image` (
	`id`
);

ALTER TABLE `Address` ADD CONSTRAINT `FK_Attraction_TO_Address_1` FOREIGN KEY (
	`content_id`
)
REFERENCES `Attraction` (
	`content_id`
);

ALTER TABLE `Address` ADD CONSTRAINT `FK_SiDo_TO_Address_1` FOREIGN KEY (
	`sido_code`
)
REFERENCES `SiDo` (
	`sido_code`
);

ALTER TABLE `Address` ADD CONSTRAINT `FK_GuGun_TO_Address_1` FOREIGN KEY (
	`gugun_code`
)
REFERENCES `GuGun` (
	`gugun_code`
);

ALTER TABLE `Content_Type` ADD CONSTRAINT `FK_Attraction_TO_Content_Type_1` FOREIGN KEY (
	`content_id`
)
REFERENCES `Attraction` (
	`content_id`
);

ALTER TABLE `GuGun` ADD CONSTRAINT `FK_SiDo_TO_GuGun_1` FOREIGN KEY (
	`sido_code`
)
REFERENCES `SiDo` (
	`sido_code`
);

ALTER TABLE `Members` ADD CONSTRAINT `FK_Image_TO_M_1` FOREIGN KEY (
	`image_id`
)
REFERENCES `Image` (
	`id`
);

ALTER TABLE `List` ADD CONSTRAINT `FK_User_TO_List_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `Members` (
	`id`
);

ALTER TABLE `Attraction_List` ADD CONSTRAINT `FK_List_TO_Attraction_List_1` FOREIGN KEY (
	`list_id`
)
REFERENCES `List` (
	`id`
);

ALTER TABLE `Attraction_List` ADD CONSTRAINT `FK_Attraction_TO_Attraction_List_1` FOREIGN KEY (
	`content_id`
)
REFERENCES `Attraction` (
	`content_id`
);

ALTER TABLE `Review` ADD CONSTRAINT `FK_Members_TO_Review_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `Members` (
	`id`
);

ALTER TABLE `Review` ADD CONSTRAINT `FK_Attraction_TO_Review_1` FOREIGN KEY (
	`content_id`
)
REFERENCES `Attraction` (
	`content_id`
);

ALTER TABLE `ReviewImage` ADD CONSTRAINT `FK_Review_TO_ReviewImage_1` FOREIGN KEY (
	`review_id`
)
REFERENCES `Review` (
	`id`
);

ALTER TABLE `ReviewImage` ADD CONSTRAINT `FK_Image_TO_ReviewImage_1` FOREIGN KEY (
	`file_id`
)
REFERENCES `Image` (
	`id`
);

ALTER TABLE `Comment` ADD CONSTRAINT `FK_Review_TO_Comment_1` FOREIGN KEY (
	`review_id`
)
REFERENCES `Review` (
	`id`
);

ALTER TABLE `Comment` ADD CONSTRAINT `FK_User_TO_Comment_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `Members` (
	`id`
);

ALTER TABLE `Comment` ADD CONSTRAINT `FK_Comment_TO_Comment_1` FOREIGN KEY (
	`parent_id`
)
REFERENCES `Comment` (
	`id`
);

ALTER TABLE `Like` ADD CONSTRAINT `FK_Review_TO_Like_1` FOREIGN KEY (
	`review_id`
)
REFERENCES `Review` (
	`id`
);

ALTER TABLE `Like` ADD CONSTRAINT `FK_Members_TO_Like_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `Members` (
	`id`
);

