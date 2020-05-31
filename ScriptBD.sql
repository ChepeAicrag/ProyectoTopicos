create schema Mezcal authorization postgres;
set search_path to Mezcal;

create table Maguey(
 id_maguey serial primary key,
 nombre varchar(30),
 cantidadPinia int
);

create table Cortador(
 id_cortador serial primary key,
 nombre varchar(30),
 estado varchar(30)
);

create table Horno(
 id_horno serial primary key,
 nombre varchar(30),
 estado varchar(30)
);

create table Molino(
 id_molino serial primary key,
 nombre varchar(30),
 estado varchar(30)
);

create table Fermentador(
 id_fermentador serial primary key,
 nombre varchar(30),
 estado varchar(30)
);

create table Destilador(
 id_destilador serial primary key,
 nombre varchar(30),
 estado varchar(30)
);

create table Enbotelladora(
 id_enbotelladora serial primary key,
 nombre varchar(30),
 estado varchar(30)
);

create table Cliente(
 id_cliente serial primary key,
 nombre varchar(30),
 estado varchar(30)
);

create table gradoAlcohol(
 id_grado int primary key,
 valor float8
);

create table tipoMezcal(
id_tipo int primary key,
nombre varchar(20)
);

create table Tanda(
 id_tanda serial primary key,
 tipoMaguey int references Maguey(id_maguey),
 gradoAlcohol int references gradoAlcohol(id_grado),
 tipoMezcal int references tipoMezcal(id_tipo),
 cantidadPinias int,
 status varchar(20),
 id_cortador serial references Cortador(id_cortador),
 id_horno int references Horno(id_horno),
 id_molino int references Molino(id_molino),
 id_fermentador int references Fermentador(id_fermentador),
 id_destilador int references Destilador(id_destilador),
 id_enbotelladora int references Enbotelladora(id_enbotelladora),
 id_cliente int references Cliente(id_cliente),
 fecha_inicio date,
 fecha_final date
);
