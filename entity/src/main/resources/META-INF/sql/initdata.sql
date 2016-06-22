 create table persistent_logins (
        SERIES varchar(255) not null,
        LAST_USED TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
        TOKEN varchar(100) not null,
        USERNAME varchar(80) not null,
        primary key (SERIES)
  );
  
  create table ROLE (
        ID int NOT NULL AUTO_INCREMENT  ,
        CREATE_DATE TIMESTAMP not NULL DEFAULT CURRENT_TIMESTAMP,
        LOCK_VERSION BIGINT not null DEFAULT 1 ,
        UPDATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
        ROLE varchar(100) not null,
        primary key (ID)
  );
  
  create table USER_ROLE (
        USER_ID int not null,
        ROLE_ID int not null,
        primary key (USER_ID, ROLE_ID)
  );
  
  create table CUSTOMER (
        ID int NOT NULL AUTO_INCREMENT ,
        CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
        LOCK_VERSION BIGINT not null DEFAULT 1 ,
        UPDATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
        CUSTOMER_NO varchar(255) not null,
        CUSTOMER_NAME varchar(255) not null,
        ACRONYM varchar(255) null,
        primary key (ID)
  );
  
  create table USERS (
        ID int NOT NULL AUTO_INCREMENT ,
        CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
        LOCK_VERSION BIGINT not null DEFAULT 1 ,
        UPDATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
        EMAIL varchar(100) not null,
        MOBILE varchar(20) not null,
        PASSWORD varchar(255) not null,
        STATE varchar(255) not null,
        USERNAME varchar(80) not null,
        CUSTOMER_NAME varchar(80) not null,
        CUSTOMER_ID int not null,
        primary key (ID)
  );
  
  create table USER_VALIDATE (
        ID int NOT NULL AUTO_INCREMENT ,
        CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
        LOCK_VERSION BIGINT NOT NULL DEFAULT 1,
        UPDATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
        REGISTER_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
        VALIDATA_CODE varchar(255) not null,
        USER_ID int,
        primary key (ID)
  );
  
  
  create table CATEGORY (
        ID int NOT NULL AUTO_INCREMENT ,
        CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
        LOCK_VERSION BIGINT NOT NULL DEFAULT 1,
        UPDATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
        NAME varchar(255) not null,
        primary key (ID)
  );
  
  create table LOGISTICS (
        ID int NOT NULL AUTO_INCREMENT ,
        CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
        LOCK_VERSION BIGINT NOT NULL DEFAULT 1,
        UPDATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
        COMPANY_NAME varchar(255) not null,
        LOGISTICS_NO varchar(255) not null,
        NOTE varchar(255) null,
        ARRIVAL_DATE TIMESTAMP not NULL DEFAULT CURRENT_TIMESTAMP,
        primary key (ID)
  );
  
  create table QUANTITY (
        ID int NOT NULL AUTO_INCREMENT ,
        CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
        LOCK_VERSION BIGINT NOT NULL DEFAULT 1,
        UPDATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
        NAME varchar(255) not null,
        primary key (ID)
  );
  
  create table DELIVERY (
        ID int NOT NULL AUTO_INCREMENT ,
        CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
        LOCK_VERSION BIGINT NOT NULL DEFAULT 1,
        UPDATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
        DELIVERY_NO varchar(255) not null,
        CUSTOMER_ID int not null,
        QUANTITY_ID int not null,
        LOGISTICS_ID int not null,
        CATEGORY_ID int not null,
        BAR_CODE varchar(255) null,
        COLOR varchar(255) null,
        NOTE varchar(255) null,
        NUMBER int null,
        VOLUME decimal(10,2) null,
        WEIGHT decimal(10,2) null,
        PRODUCT_NAME varchar(255) NOT null,
        MD5 varchar(255) NOT null,
        primary key (ID)
  );
  
  create table INVENTORY (
        ID int NOT NULL AUTO_INCREMENT ,
        CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
        LOCK_VERSION BIGINT NOT NULL DEFAULT 1,
        UPDATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
        BAR_CODE varchar(255) null,
        SHELVES_NO varchar(255) null,
        CUSTOMER_ID int not null,
        PRODUCT_NAME varchar(255) NOT null,
        COLOR varchar(255) null,
        NUMBER int null,
        VOLUME decimal(10,2) null,
        WEIGHT decimal(10,2) null,
        QUANTITY_ID int not null,
        CATEGORY_ID int not null,
        MD5 varchar(255) NOT null,
        primary key (ID)
  );
  
  
  
  
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '服饰');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '鞋');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '包');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '内衣');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '皮带');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '帽子');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '电脑');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '数码');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '电脑配件');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '家电');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '家电配件');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '厨房');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '个人饰品');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '母婴');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '儿童');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '手工艺品');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '五金');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '工具');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '家具');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '家用饰品');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '日用品');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '成人用品');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '卫浴');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '运动');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '乐器');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '办公');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '车用');
                                                                                                


INSERT INTO `mystore`.`QUANTITY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '个');
INSERT INTO `mystore`.`QUANTITY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '只');
INSERT INTO `mystore`.`QUANTITY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '包');
INSERT INTO `mystore`.`QUANTITY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '盒');
INSERT INTO `mystore`.`QUANTITY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '件');
INSERT INTO `mystore`.`QUANTITY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '台');
INSERT INTO `mystore`.`QUANTITY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '瓶');
INSERT INTO `mystore`.`QUANTITY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '箱');
INSERT INTO `mystore`.`QUANTITY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '袋');
INSERT INTO `mystore`.`QUANTITY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '套');
INSERT INTO `mystore`.`QUANTITY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '卷');
INSERT INTO `mystore`.`QUANTITY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES ('2016-06-05 15:05:11', '0', '2016-06-05 15:05:11', '桶');

  
  
    