create database db_cliente_persona;

--=======================================================================================================

--TABLA--
create table cliente (
    id int primary key, 
	password varchar(255) not null,
    identificacion varchar(100) not null,
    nombre varchar(100) not null,
    genero CHAR(1) CHECK (genero IN ('M', 'F', 'O')),  -- 'M' para masculino, 'F' para femenino, 'O' para otro
    edad int4,
    direccion varchar(255),
    telefono varchar(15),
    estado boolean not null default true  -- 'true' para Activo, 'false' para Inactivo
);

--CONSTRAINT--
alter table cliente 
add constraint UNIQUE_cliente_id
unique(id);

alter table cliente 
add constraint UNIQUE_cliente_identificacion
unique(identificacion);

--SECUENCIA--
create sequence cliente_id_seq;
alter table cliente alter id set default nextval('cliente_id_seq');




