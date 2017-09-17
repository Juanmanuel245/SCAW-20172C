package ar.edu.unlam.diit.scaw.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import ar.edu.unlam.diit.scaw.entities.DatosMaterias;
import ar.edu.unlam.diit.scaw.services.MateriaService;

@ManagedBean(name = "datosMateriasBean", eager = true)
@RequestScoped
public class DatosMateriasBean {
	
	private static final long serialVersionUID = 1L;
	private Integer idMateria;
	private String nombreMateria;
	private String descripcion;
	private String docente;
	private Integer idEstadoMateria;
	private String idMateriaString;
	
	MateriaService servicioMateria;
	
	public String editar(){
		String valor = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idMateria");
	
		DatosMaterias materia = servicioMateria.traerMateria(valor);
		
		idMateria = materia.getIdMateria();
		nombreMateria = materia.getNombreMateria();
		docente = materia.getDocente();
		
			
		
		return "editarMateria";
	}
	
	
	
	
	public Integer getIdMateria() {
		return idMateria;
	}
	public void setIdMateria(Integer idMateria) {
		this.idMateria = idMateria;
	}
	public String getNombreMateria() {
		return nombreMateria;
	}
	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDocente() {
		return docente;
	}
	public void setDocente(String docente) {
		this.docente = docente;
	}
	public Integer getIdEstadoMateria() {
		return idEstadoMateria;
	}
	public void setIdEstadoMateria(Integer idEstadoMateria) {
		this.idEstadoMateria = idEstadoMateria;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	public String getIdMateriaString() {
		return idMateriaString;
	}




	public void setIdMateriaString(String idMateriaString) {
		this.idMateriaString = idMateriaString;
	}
	
	
	
	
	
	

}
