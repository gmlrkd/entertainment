-- 사원 테이블
create table manager(
no number unique not null,
m_id varchar2(20) unique not null,
m_password varchar2(20) not null,
m_name varchar2(20) not null,
position varchar2(20) not null,
employeenumber number(6) primary key,
m_phonenumber varchar2(20) not null,
m_email varchar2(50) not null
);
-- 사원 시퀀스
create sequence manager_sqe
start with 1
increment by 1
maxvalue 999999999;

-- 지원자
create table appicante(
no number primary key, 
list varchar2(20),
name varchar2(20) not null,
age number(3) not null,
height varchar2(6),
weight varchar2(6),
phonenumber varchar2(20) not null,
email varchar2(50) not null,
employeenumber varchar2(12) references manager(employeenumber),
image varchar2(100)
);

create sequence applicate_sqe
start with 1
INCREMENT by 1;


