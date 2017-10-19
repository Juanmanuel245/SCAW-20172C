package ar.edu.unlam.diit.scaw.services;

import java.util.List;
import java.util.Map;

import ar.edu.unlam.diit.scaw.entities.DatosExamenes;
import ar.edu.unlam.diit.scaw.entities.Examenes;
import ar.edu.unlam.diit.scaw.entities.Respuestas;

public interface ExamenService {
	
	public List<DatosExamenes> traerExamen();
	public void guardarExamen(Examenes examen) throws Exception;
	public void editarExamen(Examenes examen);
	public Examenes getExamenById(Integer id);
	public void deshabilitarExamen(Integer id);
	public List<Examenes> traerExamenActivos();
	public void anotarExamen(DatosExamenes dato);
	public List<DatosExamenes> examenesParaRendir(Integer id);
	public List<DatosExamenes> traerExamenesParaUsuario(Integer id);
	public List<DatosExamenes> verNotasExamenes(Integer id);
	public Map<String,String> validarExamen(Examenes examen);
	public Examenes getExamenCompletoById(Integer id) throws Exception;
	public List<Examenes> getExamenes() throws Exception;
	public void guardarExamenRendidoPorAlumno(Integer idusuario, List<Respuestas> respuestas, Examenes examen) throws Exception;
	
}
