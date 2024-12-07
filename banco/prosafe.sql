create database prosafe;
use prosafe;

create table tbusuarios(
	iduser int primary key auto_increment,
    usuario varchar(70) not null,
    cargo varchar(530),
	email varchar(50) not null unique,
    senha varchar(50) not null,
    perfil varchar(20) not null
);

-- login (usuário: admin | senha: admin)
insert into tbusuarios(usuario, cargo, email, senha, perfil)
values('Calixto','Chefe','gustavo@gmail.com','123','admin');

create table tbfontes(
	idfonte int primary key auto_increment,
	areafonte varchar(50) not null,
	obsfonte varchar(350),
    identificador varchar(30) unique
);

create table tbmaquinas(
	idmaq int primary key auto_increment,
	nomemaq varchar(50) not null,
    areamaq varchar(100) not null,
    tipomaq varchar(30),
	idfonte int,
    foreign key(idfonte) references tbfontes(idfonte)
);

create table tbblock(
	idblock int primary key auto_increment,
    data_block timestamp default current_timestamp,
	tipo varchar(15) not null,
	usuario int not null,
    foreign key(usuario) references tbusuarios(iduser),
	nomemaq int not null,
    foreign key(nomemaq) references tbmaquinas(nomemaq),
	motivoblock varchar(350) not null
);