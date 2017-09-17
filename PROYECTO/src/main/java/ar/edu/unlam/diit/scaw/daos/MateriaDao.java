package ar.edu.unlam.diit.scaw.daos;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.Materia;

public interface MateriaDao {
	
	public List<Materia> getAllMaterias();
	public void salvarMateria(Materia materia);
}
