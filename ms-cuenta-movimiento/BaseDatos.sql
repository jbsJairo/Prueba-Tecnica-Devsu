create database db_cuenta_movimiento;

--=======================================================================================================

--TABLA--
create table cuenta (
    id int primary key,
    cliente_id int not null,
	numero_cuenta varchar(15) not null,
    tipo_cuenta CHAR(1) CHECK (tipo_cuenta IN ('A', 'C')) not null, -- 'A' AHORRO, 'C' CORRIENTE
    saldo_ini decimal not null,
    estado boolean not null default true  -- 'true' para Activo, 'false' para Inactivo
);

--CONSTRAINT--
alter table cuenta 
add constraint UNIQUE_cuenta_id
unique(id);

alter table cuenta 
add constraint UNIQUE_cuenta_numero_cuenta
unique(numero_cuenta);

--SECUENCIA--
create sequence cuenta_id_seq;
alter table cuenta alter id set default nextval('cuenta_id_seq');


--=======================================================================================================

--TABLA--
create table movimiento (
    id int primary key,
    cuenta_id int not null,
	tipo_movimiento CHAR(1) CHECK (tipo_movimiento IN ('I', 'E')) not null, -- 'I' INGRESO, 'E' EGRESO
    fecha timestamp,
    valor decimal not null,
    saldo decimal not null
);

--CONSTRAINT--
alter table movimiento 
add constraint UNIQUE_movimiento_id
unique(id);

--SECUENCIA--
create sequence movimiento_id_seq;
alter table movimiento alter id set default nextval('movimiento_id_seq');

