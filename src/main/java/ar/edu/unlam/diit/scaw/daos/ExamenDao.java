package ar.edu.unlam.diit.scaw.daos;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.DatosExamenes;
import ar.edu.unlam.diit.scaw.entities.Examenes;
import ar.edu.unlam.diit.scaw.entities.Respuestas;

public interface ExamenDao {
	
	public List<DatosExamenes> getAllExamenes();
	public void salvarExamen(Examenes examen) throws Exception;
	public void editarExamen(Examenes examen);
	public void deshabilitarExamen(Integer id);
	public List<Examenes> traerExamenActivos();
	public void anotarseExamen(DatosExamenes dato);
	public List<DatosExamenes> examenesArendir(Integer id);
	public List<DatosExamenes> getAllExamenesParaUsuario(Integer id);
	public List<DatosExamenes> traerNotasExamenes(Integer id);
	public Integer proxIdParaExamen() throws Exception;
	public Integer proxIdParaPregunta() throws Exception;
	public Integer ProxIdParaRespuesta() throws Exception;
	public Examenes getExamenCompletoById(Integer id) throws Exception;
	public void guardarExamenRendidoPorAlumno(Integer idusuario, List<Respuestas> respuestas, Examenes examen) throws Exception;
}
