-- modeloproyectos.proyectos definition

-- Drop table

-- DROP TABLE modeloproyectos.proyectos;

CREATE SCHEMA modeloproyectos;

CREATE TABLE modeloproyectos.proyectos (
	codigo serial4 NOT NULL,
	nombre varchar(40) NOT NULL,
	horas_estimadas int4 NULL,
	horas_consumidas int4 NULL,
	fecha_estimada_cierre date NULL,
	fecha_cierre date NULL,
	fecha_alta timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT proyectos_pk PRIMARY KEY (codigo)
);


-- modeloproyectos.empleados definition

-- Drop table

-- DROP TABLE modeloproyectos.empleados;

CREATE TABLE modeloproyectos.empleados (
    codigo serial4 NOT NULL,
	nif varchar(9) NOT NULL,
	nombre varchar(40) NOT NULL,
	apellidos varchar(80) NOT NULL,
	correo varchar(80) NOT NULL,
	fecha_alta timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT empleados_pk PRIMARY KEY (codigo)
);


-- modeloproyectos.tareas definition

-- Drop table

-- DROP TABLE modeloproyectos.tareas;

CREATE TABLE modeloproyectos.tareas (
	codigo serial4 NOT NULL,
	proyecto int4 NOT NULL,
	nombre varchar(80) NOT NULL,
	fecha_inicio date NULL,
	fecha_fin date NULL,
	horas_estimadas int4 NULL,
	horas_realizadas int4 NULL,
	fecha_alta timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT tareas_pk PRIMARY KEY (codigo),
	CONSTRAINT tareas_proyectos_fk FOREIGN KEY (proyecto) REFERENCES modeloproyectos.proyectos(codigo)
);


-- modeloproyectos.imputaciones definition

-- Drop table

-- DROP TABLE modeloproyectos.imputaciones;

CREATE TABLE modeloproyectos.imputaciones (
	codigo serial4 NOT NULL,
	empleado int4 NOT NULL,
	tarea int4 NOT NULL,
	fecha date NULL,
	numero_horas int4 NULL,
	descripcion varchar(256) NULL,
	CONSTRAINT imputaciones_pk PRIMARY KEY (codigo),
	CONSTRAINT imputaciones_empleados_fk FOREIGN KEY (empleado) REFERENCES modeloproyectos.empleados(codigo),
	CONSTRAINT imputaciones_tareas_fk FOREIGN KEY (tarea) REFERENCES modeloproyectos.tareas(codigo)
);


-- modeloproyectos.asignaciones definition

-- Drop table

-- DROP TABLE modeloproyectos.asignaciones;

CREATE TABLE modeloproyectos.asignaciones (
	tarea int4 NOT NULL,
	empleado int4 NOT NULL,
	CONSTRAINT asignaciones_pk PRIMARY KEY (tarea, empleado),
	CONSTRAINT asignaciones_empleados_fk FOREIGN KEY (empleado) REFERENCES modeloproyectos.empleados(codigo),
	CONSTRAINT asignaciones_tareas_fk FOREIGN KEY (tarea) REFERENCES modeloproyectos.tareas(codigo)
);

CREATE TABLE modeloproyectos.usuarios (
    codigo serial4 NOT NULL,
    empleado int4 NOT NULL,
    usuario VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    activo BOOLEAN NOT NULL,
    CONSTRAINT usuarios_pk PRIMARY KEY (codigo),
    CONSTRAINT usuarios_empleados_pk FOREIGN KEY (empleado) REFERENCES modeloproyectos.empleados(codigo)
);

INSERT INTO modeloproyectos.empleados (nif, nombre, apellidos, correo) VALUES ('11111111F', 'Admin', 'Admin', 'admin.admin@foo.com');
INSERT INTO modeloproyectos.usuarios (empleado, usuario, password, activo) VALUES (1, 'admin', '392ac49261ae2ea91e4a446fb1118888ec8ed2797f3d408d8310f0f65c054767', true);
COMMIT;