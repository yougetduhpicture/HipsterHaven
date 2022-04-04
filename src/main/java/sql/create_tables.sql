CREATE TABLE albumitaulu (
    id int auto_increment not null,
    artisti varchar(50) not null,
    nimi varchar(50) not null,
    genre varchar(100) not null,
    kuvaus varchar(100) not null,
    kansikuva varchar(300),
    primary key (id)
)engine=InnoDB;