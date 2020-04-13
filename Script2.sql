create schema mezcal authorization postgres;
set search_path to mezcal;
create table Maguey(
 id_maguey serial primary key,
 nombre varchar(60)
);
create table Mezcal(
 id_mezcal serial primary key, 
 nombre varchar(60),
 tipo varchar(30),
 id_maguey serial references Maguey(id_maguey) 
);

create table Persona(
 id_persona serial primary key,
 sexo char,
 edad int,
 nombre varchar(60)
);

create table Empleado(
 id_persona serial unique references Persona(id_persona),
 id_empleado serial primary key,
 telefono varchar(11)
);

create table Cliente(
 id_persona serial unique references Persona(id_persona),
 id_cliente serial primary key,
 rfc varchar(14),
 telefono varchar(11)
);

create table Usuario(
 id_usuario serial primary key,
 usuario varchar(30),
 password char(32),
 correo varchar(100),
 permiso char,
 id_persona serial references Persona(id_persona)
);

create table Botella(
 id_botella serial primary key,
 existencia int,
 precio int,
 presentacion varchar(10),
 fecha_envasado date,
 id_mezcal serial references Mezcal(id_mezcal),
 id_empleado serial references Empleado(id_empleado)
);

create table Venta(
 id_venta serial primary key,
 fecha date,
 id_cliente serial references Cliente(id_cliente)
);

create table Detalle_Venta(
 id_venta serial references Venta(id_venta),
 id_botella serial references Botella(id_botella),
 cantidad int
);

