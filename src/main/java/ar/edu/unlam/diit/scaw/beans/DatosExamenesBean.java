package ar.edu.unlam.diit.scaw.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "datosExamenesBean", eager = true)
@RequestScoped
public class DatosExamenesBean {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idExamen;
	private String nombreExamen;
	private Integer idMateria;
	private Integer idEstado;
	private String estadoExamen;
	private String nombreMateria;
	
	
	public String anotarse(){
		return "welcome";
	}
	
	public Integer getIdExamen() {
		return idExamen;
	}
	public void setIdExamen(Integer idExamen) {
		this.idExamen = idExamen;
	}
	public String getNombreExamen() {
		return nombreExamen;
	}
	public void setNombreExamen(String nombreExamen) {
		this.nombreExamen = nombreExamen;
	}
	public Integer getIdMateria() {
		return idMateria;
	}
	public void setIdMateria(Integer idMateria) {
		this.idMateria = idMateria;
	}
	public Integer getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	public String getEstadoExamen() {
		return estadoExamen;
	}
	public void setEstadoExamen(String estadoExamen) {
		this.estadoExamen = estadoExamen;
	}
	public String getNombreMateria() {
		return nombreMateria;
	}
	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
