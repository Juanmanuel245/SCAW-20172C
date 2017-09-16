package ar.edu.unlam.diit.scaw.services.impl;

import java.util.List;

import ar.edu.unlam.diit.scaw.daos.MateriaDao;
import ar.edu.unlam.diit.scaw.daos.impl.MateriaDaoImpl;
import ar.edu.unlam.diit.scaw.entities.Materia;
import ar.edu.unlam.diit.scaw.services.MateriaService;

public class MateriaServiceImpl implements MateriaService{
	
	MateriaDaoImpl servicioDao;
	
	public MateriaServiceImpl(){
		servicioDao = new MateriaDaoImpl();
	}
	
	@Override
	public List<Materia> traerMaterias(){
		
		return servicioDao.getAllMaterias();
	}

	public MateriaDao getServicioDao() {
		return servicioDao;
	}

	public void setServicioDao(MateriaDaoImpl servicioDao) {
		this.servicioDao = servicioDao;
	}

	@Override
	public void guardarMateria(Materia materia) {
		
		servicioDao.salvarMateria(materia);		
	}

}
