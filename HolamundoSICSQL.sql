drop table if exists neg_procesodocumento;
drop table if exists adm_colaborador;
drop table if exists neg_documento;
drop table if exists sys_propaso;
drop table if exists sys_proceso;
drop table if exists rf_tipodocumento;

create table if not exists  rf_tipodocumento(
tdc_id varchar(5) primary key,
tdc_nombre varchar(500),
tdc_estado boolean default true
);


-- Drop table

-- DROP TABLE public.sys_proceso;

CREATE TABLE if not exists sys_proceso (
	spr_id varchar(25) NOT NULL,
	spr_nombre varchar(200) NULL,
	spr_desc text NULL,
	spr_est bool NOT NULL DEFAULT true,
	indversion int4 NOT NULL DEFAULT 1,
	CONSTRAINT sys_proceso_pkey PRIMARY KEY (spr_id),
	CONSTRAINT sys_proceso_spr_nombre_key UNIQUE (spr_nombre)
);

-- Drop table

-- DROP TABLE public.sys_propaso;

CREATE TABLE if not exists sys_propaso (
	sps_id int8 NOT null primary key,
	sps_nombre varchar(200) NOT NULL,
	sps_desc text NULL,
	sps_est bool NOT NULL DEFAULT true,
	indversion int4 NOT NULL DEFAULT 1,
	spr_id varchar(25) NULL,
	CONSTRAINT fk_sys_propaso_spr_id FOREIGN KEY (spr_id) REFERENCES sys_proceso(spr_id)
);


create table if not exists neg_documento(
doc_id bigserial primary key,
doc_nombre varchar(4000),
doc_estado boolean default true

) ;

-- Drop table

-- DROP TABLE public.adm_colaborador;

CREATE TABLE if not exists adm_colaborador (
    col_id bigserial primary key,
    tdc_id varchar NULL,
	col_documento varchar(50) NOT NULL,
	col_nombre1 varchar(150) NULL,	
	col_nombre2 varchar(150) NULL,
	col_apellido1 varchar(150) NULL,
	col_apellido2 varchar(150) NULL,
	col_estado bool NULL DEFAULT true,
	indversion int4 NULL DEFAULT 1,
	
	
	
	
	
	CONSTRAINT adm_colaborador_tdc_id_fkey FOREIGN KEY (tdc_id) REFERENCES rf_tipodocumento(tdc_id) ON UPDATE RESTRICT ON DELETE RESTRICT

	
);


create table if not exists neg_procesodocumento(
pdc_id bigserial primary key,
pdc_observacion text,
pdc_estado boolean,
pdc_fechaproceso timestamp with time zone,
col_id bigint,
doc_id bigint,
sps_id bigint,
CONSTRAINT neg_procesodocumento_col_id_fkey FOREIGN KEY (col_id) REFERENCES adm_colaborador(col_id) ON UPDATE RESTRICT ON DELETE restrict,
CONSTRAINT neg_procesodocumento_doc_id_fkey FOREIGN KEY (doc_id) REFERENCES neg_documento(doc_id) ON UPDATE RESTRICT ON DELETE restrict,
CONSTRAINT neg_procesodocumento_sps_id_fkey FOREIGN KEY (sps_id) REFERENCES sys_propaso(sps_id) ON UPDATE RESTRICT ON DELETE restrict
);




