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
        LOGISTICS_COMPANY_ID int null,
        LOGISTICS_NO varchar(255) not null,
        PRICE decimal(10,2) null,
        ADDRESS varchar(255) null,
        NAME varchar(255) null,
        MOBILE varchar(255) null,
        PROVINCE varchar(255) null,
        CITY varchar(255) null,
        DISTRICT varchar(255) null,
        NOTE varchar(255) null,
        PACKAGE BIT(1) not null,
        INSURANCE BIT(1) not null,
        LOGISTICS_TYPE varchar(255) not null,
        ARRIVAL_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
        primary key (ID)
  );
  
  create table LOGISTICS_COMPANY (
        ID int NOT NULL AUTO_INCREMENT ,
        CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
        LOCK_VERSION BIGINT NOT NULL DEFAULT 1,
        UPDATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
        COMPANY_NAME varchar(255) not null,
        UNIT_PRICE decimal(10,2) not null,
        COMPANY_TYPE varchar(255) not null,
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
		STATUS varchar(255) not null,
		LOGISTICS_ID int not null,
		primary key (ID)
  );
  
  
  create table DELIVERY_DETAILS (
        ID int NOT NULL AUTO_INCREMENT ,
        CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
        LOCK_VERSION BIGINT NOT NULL DEFAULT 1,
        UPDATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
		DELIVERY_ID int not null,
        CUSTOMER_ID int not null,
        QUANTITY_ID int not null,
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
  
  create table EXPRESS (
        ID int NOT NULL AUTO_INCREMENT ,
        CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
        LOCK_VERSION BIGINT NOT NULL DEFAULT 1,
        UPDATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
        CUSTOMER_ID int not null,
        EXPRESS_NO varchar(255) not null,
        LOGISTICS_ID int not null,
        STATUS varchar(255) not null,
        primary key (ID)
  );
  
  create table EXPRESS_DETAILS (
        ID int NOT NULL AUTO_INCREMENT ,
        CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
        LOCK_VERSION BIGINT NOT NULL DEFAULT 1,
        UPDATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
		EXPRESS_ID int not null,
        CUSTOMER_ID int not null,
        INVENTORY_ID int not null,
        NUMBER int not null,
        primary key (ID)
  );
  
  create table DAILY_SETTLEMENT (
        ID int NOT NULL AUTO_INCREMENT ,
        CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
        LOCK_VERSION BIGINT NOT NULL DEFAULT 1,
        UPDATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
        TOTAL_WEIGHT decimal(10,2) not null,
        TOTAL_VOLUME decimal(10,2) not null,
        TOTAL_NUMBER int not null,
        CUSTOMER_ID int not null,
        MONTHLY_STATEMENT_ID int null,
        PRICE decimal(10,2) not null,
        SETTLEMENT_DATE DATE NULL,
        STATUS varchar(255) not null,
        primary key (ID)
  );
  
  create table DAILY_SETTLEMENT_DETAILS (
        ID int NOT NULL AUTO_INCREMENT ,
        CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
        LOCK_VERSION BIGINT NOT NULL DEFAULT 1,
        UPDATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
        DAILY_SETTLEMENT_ID int not null,
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
        SETTLEMENT_DATE DATE NULL,
        primary key (ID)
  );
  
  create table MONTHLY_STATEMENT (
        ID int NOT NULL AUTO_INCREMENT ,
        CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
        LOCK_VERSION BIGINT NOT NULL DEFAULT 1,
        UPDATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
        STATUS varchar(255) not null,
        SETTLEMENT_NO varchar(255) not null,
        MONTH varchar(255) not null,
        AMOUNT decimal(10,2) not null,
        CUSTOMER_ID int not null,
        primary key (ID)
  );
  
  create table CONFIGURATION (
        ID int NOT NULL AUTO_INCREMENT ,
        CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
        LOCK_VERSION BIGINT NOT NULL DEFAULT 1,
        UPDATE_DATE TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
        NAME varchar(255) not null,
        VALUE varchar(255) not null,
        OPERATIVE BIT(1) not null,
        primary key (ID)
  );
  
  
  
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '服饰');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '鞋');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '包');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '内衣');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '皮带');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '帽子');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '电脑');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '数码');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '电脑配件');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '家电');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '家电配件');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '厨房');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '个人饰品');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '母婴');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '儿童');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '手工艺品');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '五金');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '工具');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '家具');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '家用饰品');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '日用品');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '成人用品');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '卫浴');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '运动');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '乐器');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '办公');
INSERT INTO `mystore`.`CATEGORY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '车用');
                                                                                                


INSERT INTO `mystore`.`QUANTITY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '个');
INSERT INTO `mystore`.`QUANTITY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '只');
INSERT INTO `mystore`.`QUANTITY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '包');
INSERT INTO `mystore`.`QUANTITY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '盒');
INSERT INTO `mystore`.`QUANTITY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '件');
INSERT INTO `mystore`.`QUANTITY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '台');
INSERT INTO `mystore`.`QUANTITY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '瓶');
INSERT INTO `mystore`.`QUANTITY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '箱');
INSERT INTO `mystore`.`QUANTITY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '袋');
INSERT INTO `mystore`.`QUANTITY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '套');
INSERT INTO `mystore`.`QUANTITY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '卷');
INSERT INTO `mystore`.`QUANTITY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`) VALUES (now(), '0', now(), '桶');

  
INSERT INTO `mystore`.`LOGISTICS_COMPANY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `COMPANY_NAME`, `UNIT_PRICE`, `COMPANY_TYPE`) VALUES (now(), '0', now(), '顺丰快递', '1.5', 'SPECIAL');
INSERT INTO `mystore`.`LOGISTICS_COMPANY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `COMPANY_NAME`, `UNIT_PRICE`, `COMPANY_TYPE`) VALUES (now(), '0', now(), '申通快递', '1.6', 'NORMAL');
INSERT INTO `mystore`.`LOGISTICS_COMPANY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `COMPANY_NAME`, `UNIT_PRICE`, `COMPANY_TYPE`) VALUES (now(), '0', now(), '中通快递', '1.7', 'NORMAL');
INSERT INTO `mystore`.`LOGISTICS_COMPANY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `COMPANY_NAME`, `UNIT_PRICE`, `COMPANY_TYPE`) VALUES (now(), '0', now(), '韵达快递', '1.8', 'NORMAL');
INSERT INTO `mystore`.`LOGISTICS_COMPANY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `COMPANY_NAME`, `UNIT_PRICE`, `COMPANY_TYPE`) VALUES (now(), '0', now(), '佳吉快递', '1.9', 'NORMAL');
INSERT INTO `mystore`.`LOGISTICS_COMPANY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `COMPANY_NAME`, `UNIT_PRICE`, `COMPANY_TYPE`) VALUES (now(), '0', now(), '德邦快递', '2.0', 'NORMAL');
INSERT INTO `mystore`.`LOGISTICS_COMPANY` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `COMPANY_NAME`, `UNIT_PRICE`, `COMPANY_TYPE`) VALUES (now(), '0', now(), '天地华宇', '1.5', 'NORMAL');

    
INSERT INTO `mystore`.`CONFIGURATION` (`CREATE_DATE`, `LOCK_VERSION`, `UPDATE_DATE`, `NAME`, `VALUE`, `OPERATIVE`) VALUES (now(), '0', now(), 'PER_CUBIC_PRICE', '2.56', 1);
