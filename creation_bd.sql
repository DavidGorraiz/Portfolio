--ALTER SESSION SET "_ORACLE_SCRIPT"=true;
DROP USER ADMIN CASCADE;
CREATE USER ADMIN IDENTIFIED BY 1234;
--ALTER USER ADMIN QUOTA UNLIMITED ON USERS;
GRANT CONNECT, RESOURCE TO ADMIN;
CONN ADMIN/1234;

create table PAISES(
	ID_PAIS NUMBER(10)  not null,
	NOMBRE_PAIS VARCHAR2(100) not null,
	constraint PK_PAISES primary key (ID_PAIS)
);

create table PATROCINADORES(
	ID_PATROCINADOR NUMBER(10) not null,
	NIT_PATROCINADOR VARCHAR2(15) not null,
	NOMBRE_PATROCINADOR VARCHAR2(100) not null,
	constraint PK_PATROCINADORES primary key (ID_PATROCINADOR)
);

create table EQUIPOS(
	ID_EQUIPO NUMBER(10) not null,
	NOMBRE_EQUIPO VARCHAR2(100) not null,
	FECHA_FUNDACION DATE not null,
	constraint PK_EQUIPOS primary key (ID_EQUIPO)
);

create table CORREDORES(
	ID_CORREDOR NUMBER(10) not null,
	NOMBRE_CORREDOR VARCHAR2(50) not null,
	APELLIDOS_CORREDOR  VARCHAR2(50) not null,
	FECHA_NACIMIENTO_CORREDOR DATE not null,
	ID_PAIS NUMBER(10) not null,
	constraint PK_CORREDORES primary key (ID_CORREDOR)
);
create table EDICIONES(
	ID_EDICION NUMBER(10) not null,
	FECHA_INICIO DATE not null,
	FECHA_FIN DATE not null,
	constraint PK_EDICIONES primary key (ID_EDICION)
);

create table ETAPAS(
	ID_ETAPA NUMBER(10) not null,
	ID_EDICION NUMBER(10) not null,
	ORIGEN VARCHAR2(100) not null,
	DESTINO VARCHAR2(100) not null,
	LONGITUD NUMBER(8,3) not null,
	TIPO_ETAPA VARCHAR2(30) not null,
	constraint PK_ETAPAS primary key (ID_ETAPA)
);

create table PODIOS(
	ID_PODIO NUMBER(10) not null,
	ID_ETAPA NUMBER(10) not null,
	ID_CORREDOR NUMBER(10) not null,
	POSICION NUMBER(10) not null,
	TIEMPO TIMESTAMP not null,
	constraint PK_PODIOS primary key (ID_PODIO)
);

create table HISTORIAL_EQUIPOS(
	ID_HISTORIAL NUMBER(10) not null,
	ID_EQUIPO NUMBER(10) not null,
	ID_CORREDOR NUMBER(10) not null,
	ANIO_VINCULACION NUMBER(5) NOT NULL,
	constraint PK_HISTORIAL_EQUIPOS primary key (ID_HISTORIAL)
);

create table HISTORIAL_EDICION_EQUIPO(
	ID_EQUIPO NUMBER(10) not null,
	ID_EDICION NUMBER(10) not null,
	ID_PATROCINADOR NUMBER(10) not null,
	constraint PK_HISTORIAL_ED_EQU primary key (ID_EQUIPO,ID_EDICION,ID_PATROCINADOR)
);


alter table HISTORIAL_EQUIPOS
   add constraint FK_CORREDOR_EQUIPO foreign key (ID_EQUIPO)
      references EQUIPOS(ID_EQUIPO); 

alter table HISTORIAL_EQUIPOS
   add constraint FK_EQUIPO_CORREDOR foreign key (ID_CORREDOR)
      references CORREDORES(ID_CORREDOR); 

alter table CORREDORES
   add constraint FK_CORREDOR_PAIS foreign key (ID_PAIS)
      references PAISES(ID_PAIS);

alter table PODIOS
   add constraint FK_PODIO_CORREDOR foreign key (ID_CORREDOR)
      references CORREDORES(ID_CORREDOR);

alter table PODIOS
   add constraint FK_PODIO_ETAPA foreign key (ID_ETAPA)
      references ETAPAS(ID_ETAPA);  

alter table ETAPAS
   add constraint FK_ETAPA_EDICION foreign key (ID_EDICION)
      references EDICIONES(ID_EDICION); 

alter table HISTORIAL_EDICION_EQUIPO
   add constraint FK_HIST_EQUIPO foreign key (ID_EQUIPO)
      references EQUIPOS(ID_EQUIPO); 

alter table HISTORIAL_EDICION_EQUIPO
   add constraint FK_HIST_EDICION foreign key (ID_EDICION)
      references EDICIONES(ID_EDICION); 

alter table HISTORIAL_EDICION_EQUIPO
   add constraint FK_HIST_PATROCINADOR foreign key (ID_PATROCINADOR)
      references PATROCINADORES(ID_PATROCINADOR); 




