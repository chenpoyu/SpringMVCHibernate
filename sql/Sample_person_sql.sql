drop table person;
CREATE TABLE PERSON (
  ID INT NOT NULL PRIMARY KEY,
  NAME VARCHAR(10) NOT NULL,
  COUNTRY VARCHAR(10)
);
drop SEQUENCE person_seq;
create sequence person_seq
    start with 1
    minvalue 1
    increment by 1
    nocycle;
    
select * from person;
insert into person values (person_seq.nextval, 'admin', 'world');
insert into person values (person_seq.nextval, 'chen', 'tw');
insert into person values (person_seq.nextval, 'lee', 'japan');
commit;