create database encrypt_sample default character set = utf8mb4 default collate = utf8mb4_unicode_ci;

create table user (
    id                    int auto_increment
        primary key,
    identification_number varchar(255) null,
    phone                 varchar(255) null,
    age                   int          null,
    company_address       varchar(255) null,
    company_lat           double       null,
    company_lng           double       null,
    home_address          varchar(255) null,
    home_lat              double       null,
    home_lng              double       null,
    name                  varchar(255) null,
    nickname              varchar(255) null
)
    ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO encrypt_sample.user (identification_number, phone, age, company_address, company_lat, company_lng, home_address, home_lat, home_lng, name, nickname) VALUES ('871201-1000000', '010-0000-0000', 30, '서울시 강남구 어딘가', 37.8, 128.9, '하남시 학암동 어딘가', 37.1, 128.2, '배영섭', '배님');
INSERT INTO encrypt_sample.user (identification_number, phone, age, company_address, company_lat, company_lng, home_address, home_lat, home_lng, name, nickname) VALUES ('850101-1000000', '010-0001-0000', 25, '서울시 마포구 어딘가', 37.5, 128.8, '경기도 성남시 어딘가', 37.3, 128.1, '홍길동', '번쩍');
INSERT INTO encrypt_sample.user (identification_number, phone, age, company_address, company_lat, company_lng, home_address, home_lat, home_lng, name, nickname) VALUES ('860101-1000000', '010-0002-0000', 27, '서울시 마포구 어딘가 2', 37.8, 128.9, '경기도 성남시 어딘가 2', 37.4, 128.2, '아무개1', '별명1');
INSERT INTO encrypt_sample.user (identification_number, phone, age, company_address, company_lat, company_lng, home_address, home_lat, home_lng, name, nickname) VALUES ('880101-1000000', '010-0003-0003', 29, '서울시 마포구 어딘가 3', 37.81234, 128.12349, '경기도 성남시 어딘가 3', 37.455, 128.22345, '아무개2', '별명2');
INSERT INTO encrypt_sample.user (identification_number, phone, age, company_address, company_lat, company_lng, home_address, home_lat, home_lng, name, nickname) VALUES ('880101-1000034', '010-0004-0005', 29, '서울시 마포구 어딘가 4', 37.3562234, 128.6432349, '경기도 성남시 어딘가 4', 37.2355, 128.1324345, '아무개3', '별명3');
INSERT INTO encrypt_sample.user (identification_number, phone, age, company_address, company_lat, company_lng, home_address, home_lat, home_lng, name, nickname) VALUES ('880301-1000034', '010-0006-0007', 43, '서울시 마포구 어딘가 2345', 37.190624, 128.2349349, '경기도 성남시 어딘가 234', 37.82355, 128.584345, '아무개4', '별명4');
INSERT INTO encrypt_sample.user (identification_number, phone, age, company_address, company_lat, company_lng, home_address, home_lat, home_lng, name, nickname) VALUES ('880301-1000035', '010-0006-0008', 44, '서울시 마포구 어딘가 1257', 37.850624, 128.264359, '경기도 성남시 어딘가 823', 37.26355, 128.954345, '아무개5', '별명5');


# Phase 1
create table ENCRYPT_KEY_EXPIRATION (
    name varchar(64),
    expiration timestamp,
    enc_DEK varchar(300),
    version int,
    primary key(name, version)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table user
    add column name_enc varchar(255),
    add column identification_number_enc varchar(255),
    add column phone_enc varchar(255),
    add column age_enc varchar(255),
    add column home_lat_enc varchar(255),
    add column home_lng_enc varchar(255),
    add column enc_dek varchar(300),
    add column name_hashed varchar(255),
    algorithm=inplace, lock=none;
