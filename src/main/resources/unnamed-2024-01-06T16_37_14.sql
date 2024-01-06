
CREATE TABLE board
(
  board_number      INT          NOT NULL DEFAULT AUTO_INCREMENT COMMENT '게시물번호',
  title             VARCHAR(100) NOT NULL COMMENT '제목',
  content           TEXT         NOT NULL COMMENT '내용',
  board_category_id INT          NOT NULL COMMENT '게시물 카테고리 아이디',
  writer_email      VARCHAR(50)  NOT NULL COMMENT '아이디',
  write_datetime    DATETIME     NOT NULL COMMENT '작성일자',
  update_datetime   DATETIME     NULL     COMMENT '수정일자',
  comment_count     INT          NULL     COMMENT '댓글 수',
  favorite_count    INT          NULL     COMMENT '좋아요 수',
  view_count        INT          NULL     COMMENT '조회 수',
  PRIMARY KEY (board_number)
) COMMENT '게시물 테이블';

CREATE TABLE board_category
(
  board_category_id INT         NOT NULL DEFAULT AUTO_INCREMENT COMMENT '게시물 카테고리 아이디',
  board_name        VARCHAR(30) NOT NULL COMMENT '게시판 이름',
  PRIMARY KEY (board_category_id)
) COMMENT '게시물 카테고리';

CREATE TABLE board_favorite
(
  email        VARCHAR(50) NOT NULL COMMENT '아이디',
  board_number INT         NOT NULL DEFAULT AUTO_INCREMENT COMMENT '게시물번호',
  PRIMARY KEY (email, board_number)
) COMMENT '게시물 좋아요 테이블';

CREATE TABLE boardattachment
(
  board_attachment_id INT          NOT NULL DEFAULT AUTO_INCREMENT COMMENT '첨부파일 아이디',
  board_number        INT          NOT NULL DEFAULT AUTO_INCREMENT COMMENT '게시물번호',
  filename            VARCHAR(255) NOT NULL COMMENT '첨부파일명',
  filetype            VARCHAR(255) NOT NULL COMMENT '첨부파일형식',
  filesize            VARCHAR(255) NOT NULL COMMENT '첨부파일크기',
  filepath            VARCHAR(255) NOT NULL COMMENT '첨부파일경로(key)',
  PRIMARY KEY (board_attachment_id)
) COMMENT '게시물 첨부파일 테이블';

CREATE TABLE comment
(
  comment_id     INT         NULL     DEFAULT AUTO_INCREMENT COMMENT '댓글번호',
  board_number   INT         NOT NULL DEFAULT AUTO_INCREMENT COMMENT '게시물번호',
  user_email     VARCHAR(50) NOT NULL COMMENT '아이디',
  content        TEXT        NOT NULL COMMENT '댓글내용',
  write_datetime DATETIME    NOT NULL COMMENT '작성일자',
  PRIMARY KEY (comment_id)
) COMMENT '댓글 테이블';

CREATE TABLE user
(
  email          VARCHAR(50)  NOT NULL COMMENT '아이디',
  password       VARCHAR(100) NOT NULL COMMENT '패스워드',
  nickname       VARCHAR(50)  NOT NULL COMMENT '닉네임',
  tel_number     VARCHAR(15)  NOT NULL COMMENT '전화번호',
  address        TEXT         NOT NULL COMMENT '주소',
  address_detail TEXT         NULL     COMMENT '상세주소',
  profile_image  TEXT         NULL     COMMENT '프로필 사진',
  oauth_naver    VARCHAR(100) NULL    ,
  oauth_kakao    VARCHAR(100) NULL    ,
  oauth_google   VARCHAR(100) NULL    ,
  PRIMARY KEY (email)
) COMMENT '사용자 테이블';

ALTER TABLE board
  ADD CONSTRAINT FK_board_category_TO_board
    FOREIGN KEY (board_category_id)
    REFERENCES board_category (board_category_id);

ALTER TABLE board
  ADD CONSTRAINT FK_user_TO_board
    FOREIGN KEY (writer_email)
    REFERENCES user (email);

ALTER TABLE board_favorite
  ADD CONSTRAINT FK_user_TO_board_favorite
    FOREIGN KEY (email)
    REFERENCES user (email);

ALTER TABLE board_favorite
  ADD CONSTRAINT FK_board_TO_board_favorite
    FOREIGN KEY (board_number)
    REFERENCES board (board_number);

ALTER TABLE comment
  ADD CONSTRAINT FK_board_TO_comment
    FOREIGN KEY (board_number)
    REFERENCES board (board_number);

ALTER TABLE comment
  ADD CONSTRAINT FK_user_TO_comment
    FOREIGN KEY (user_email)
    REFERENCES user (email);

ALTER TABLE boardattachment
  ADD CONSTRAINT FK_board_TO_boardattachment
    FOREIGN KEY (board_number)
    REFERENCES board (board_number);
