package ar.edu.unlam.diit.scaw.services;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.Materia;

public interface MateriaService {
	
	public List<Materia> traerMaterias();
	public void guardarMateria(Materia materia);
}
