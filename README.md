add schema called `pets`

`create schema pets`

add the following table:

`create table pets.pet
 (
 	id bigint auto_increment
 		primary key,
 	name varchar(50) null,
 	age int default 0 null,
 	type varchar(10) null,
 	constraint pet_id_uindex
 		unique (id)
 )
 engine=InnoDB
 ;
 `