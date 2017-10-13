package ar.edu.unlam.diit.scaw.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import ar.edu.unlam.diit.scaw.entities.DatosExamenes;
import ar.edu.unlam.diit.scaw.services.ExamenService;
import ar.edu.unlam.diit.scaw.services.impl.ExamenServiceImpl;

@ManagedBean(name = "datosExamenesBean", eager = true)
@RequestScoped
public class DatosExamenesBean {
	
	private static final long serialVersionUID = 1L;
	
	private String idExamen;
	private String nombreExamen;
	private String idMateria;
	private Integer idEstado;
	private Integer idUsuario;
	private String estadoExamen;
	private String nombreMateria;
	private String nota;
	
	ExamenService servicioExamen;
	
	private FacesContext context = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
	
	public DatosExamenesBean(){
		super();
		servicioExamen = (ExamenService) new ExamenServiceImpl();
	}
	
	public String anotarse(){
		String formIdExamen = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idExamen");
		
		
		DatosExamenes dato = new DatosExamenes();
		dato.setIdExamen(formIdExamen);
		Integer sessionIdUsuario = (Integer) session.getAttribute("idUsuario");
		dato.setIdUsuario(sessionIdUsuario);
			
		servicioExamen.anotarExamen(dato);
		return "welcome";
	}
	
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
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	
	
	
	
}
