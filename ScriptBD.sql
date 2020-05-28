/**create schema Mezcal authorization postgres;*/
set search_path to Mezcal;

create table gradoAlcohol(
id_grado int primary key,
valor float8
);

create table tipoMezcal(
id_tipo int primary key,
nombre varchar(20)
);

create table Tanda(
id_tanda int primary key,
tipoMaguey int references Maguey(id_maguey),
gradoAlcohol int references gradoAlcohol(id_grado),
tipoMezcal int references tipoMezcal(id_tipo),
cantidadPinias int,
status varchar(20),
fecha_inicio date,
fecha_final date
);
