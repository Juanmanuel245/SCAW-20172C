package ar.edu.unlam.diit.scaw.entities;

public class DatosExamenes {
	
	private String idExamen;
	private String nombreExamen;
	private String idMateria;
	private Integer idEstado;
	private String estadoExamen;
	private String nombreMateria;
	private Integer idUsuario;
	//Atributo para recuperar el nombre del usuario
	private String nombreUsuario;
	private String nota;
	
	public String getIdExamen() {
		return idExamen;
	}
	public void setIdExamen(String idExamen) {
		this.idExamen = idExamen;
	}
	public String getNombreExamen() {
		return nombreExamen;
	}
	public void setNombreExamen(String nombreExamen) {
		this.nombreExamen = nombreExamen;
	}
	public String getIdMateria() {
		return idMateria;
	}
	public void setIdMateria(String idMateria) {
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
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	

}
