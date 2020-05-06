create table customernote(
    id integer primary key autoincrement,
    customerName varchar(255) ,
    note varchar(10000)
);

create table fitems(
    id integer primary key autoincrement,
    searchin varchar(255) ,
    searchout varchar(255)
);

create table customer(
id integer primary key autoincrement ,
area varchar(255),
connectionname varchar(255),
connectionprotocol varchar(255),
connectiontype varchar(255),
contacts varchar(1000),
counterpartytype varchar(255),
manager varchar(255),
name varchar(255),
note varchar(5000),
platform varchar(255),
createtime varchar(100),
customernote_id integer,
foreign key(customernote_id) references customernote(id)
);

insert into customer(area, connectionname, connectionprotocol, connectiontype, contacts, counterpartytype,
manager, name,note,platform,createtime,customernote_id) values(?, ?, ?, ?, ?, ?,?, ?,?,?,?,?);

insert into customernote(customerName, note) values("test", "test");

SELECT * FROM customer WHERE id = (SELECT MAX(id) FROM customer);
SELECT * FROM customernote WHERE id = (SELECT MAX(id) FROM customernote);

