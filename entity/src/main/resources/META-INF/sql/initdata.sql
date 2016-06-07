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
  
    