package ar.edu.unlam.diit.scaw.services;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.DatosExamenes;
import ar.edu.unlam.diit.scaw.entities.Examenes;

public interface ExamenService {
	
	public List<DatosExamenes> traerExamen();
	public void guardarExamen(Examenes examen);
	public void editarExamen(Examenes examen);
	public Examenes getExamenById(Integer id);
	public void deshabilitarExamen(Integer id);
	public List<Examenes> traerExamenActivos();
	public void anotarExamen(DatosExamenes dato);
}
