package ar.edu.unlam.diit.scaw.services.impl;

import java.util.List;

import ar.edu.unlam.diit.scaw.daos.MateriaDao;
import ar.edu.unlam.diit.scaw.entities.Materia;
import ar.edu.unlam.diit.scaw.services.MateriaService;

public class MateriaServiceImpl implements MateriaService{
	
	MateriaDao servicioDao;
	
	public List<Materia> traerMaterias(){
		
		return servicioDao.traerMaterias();
	}

}
