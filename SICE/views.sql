CREATE VIEW 
vista_mantenimientoGrupos AS
SELECT grupos.idgrupos,grupos.nombre AS nombreGrupo,grupos.idcurso,grupos.idProfesor,grupos.horaInicio,grupos.estado,grupos.dia,personas.nombre as nombreProfesor,personas.apellido1,personas.apellido2,cursos.nombre as nombreCurso FROM grupos join personas join cursos WHERE grupos.idcurso=cursos.idcurso AND grupos.idProfesor=personas.identificacion;


CREATE VIEW 
vista_mantenimientoCursos AS
SELECT idcurso,cursos.nombre AS nombreCurso,cursos.idIdioma,cursos.estado,idiomas.nombre AS nombreIdioma FROM cursos join idiomas WHERE cursos.idIdioma=idiomas.idIdioma;

CREATE VIEW 
vista_pagos AS
SELECT idEstudiante,personas.nombre,personas.apellido1,personas.apellido2,numeroRecibo,observacion,fechaDelPago,fechaPagada FROM pagos join personas WHERE pagos.idEstudiante=personas.identificacion;

CREATE VIEW 
vista_matriculas AS
SELECT matriculas.idEstudiante, cursos.idcurso, cursos.nombre as nombrecurso, idiomas.nombre as nombreIdioma, grupos.nombre as nombreGrupo, personas.nombre as nombreProfesor, personas.apellido1, personas.apellido2 FROM matriculas join grupos join cursos join idiomas join personas WHERE matriculas.nombreGrupo=grupos.nombre and grupos.idcurso=cursos.idcurso and cursos.idIdioma=idiomas.idIdioma and grupos.idprofesor=personas.identificacion;