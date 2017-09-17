package ar.edu.unlam.diit.scaw.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;


import ar.edu.unlam.diit.scaw.services.ExamenService;
import ar.edu.unlam.diit.scaw.services.MateriaService;
import ar.edu.unlam.diit.scaw.services.impl.ExamenServiceImpl;


@ManagedBean(name = "examenBean", eager = true)
@RequestScoped
public class ExamenBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id = null;
	private String nombre = null;
	private Integer idMateria = null;
	private Integer idEstadoExamen = null;
	
	ExamenService servicioExamen;
	
	public ExamenBean(){
		super();
		servicioExamen = (ExamenService) new ExamenServiceImpl(); 
	
	}
}
