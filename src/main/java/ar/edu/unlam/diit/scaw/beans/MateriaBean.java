package ar.edu.unlam.diit.scaw.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import ar.edu.unlam.diit.scaw.entities.Materia;
import ar.edu.unlam.diit.scaw.services.MateriaService;

@ManagedBean(name = "materiaBean", eager = true)
@RequestScoped
public class MateriaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id = null;
	private String nombre = null;
	private Integer idDocenteTitular = null;
	private Integer idEstadoMateria = null;
	
	MateriaService servicioMateria;
	
	public MateriaBean(){
		
	}
	
	public MateriaBean(Integer id, String nombre, Integer idDocenteTitular, Integer idEstadoMateria){
		
		this.id = id;
		this.nombre = nombre;
		this.idDocenteTitular = idDocenteTitular;
		this.idEstadoMateria = idEstadoMateria;
	}
	
	public List<Materia> getAllMaterias(){
		List<Materia> lista = servicioMateria.traerMaterias();
		return lista;	
	}
	
	
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getIdDocenteTitular() {
		return idDocenteTitular;
	}
	public void setIdDocenteTitular(Integer idDocenteTitular) {
		this.idDocenteTitular = idDocenteTitular;
	}
	public Integer getIdEstadoMateria() {
		return idEstadoMateria;
	}
	public void setIdEstadoMateria(Integer idEstadoMateria) {
		this.idEstadoMateria = idEstadoMateria;
	}
	
	
	
}
