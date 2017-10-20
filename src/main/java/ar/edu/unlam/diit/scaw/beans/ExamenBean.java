package ar.edu.unlam.diit.scaw.beans;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import ar.edu.unlam.diit.scaw.entities.DatosExamenes;
import ar.edu.unlam.diit.scaw.entities.Examenes;
import ar.edu.unlam.diit.scaw.entities.Materia;
import ar.edu.unlam.diit.scaw.entities.Preguntas;
import ar.edu.unlam.diit.scaw.entities.Respuestas;
import ar.edu.unlam.diit.scaw.services.ExamenService;
import ar.edu.unlam.diit.scaw.services.impl.ExamenServiceImpl;

@ManagedBean(name = "examenBean", eager = true)
@RequestScoped
@SessionScoped
public class ExamenBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Materia materia;
	private Integer estadoExamen = 0;
	private List<Preguntas> preguntas = new ArrayList<>();
	private String json;
	private Examenes examen;
	private List<Examenes> examenes;

	ExamenService servicioExamen;

	private FacesContext context = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) context.getExternalContext().getSession(true);

	public ExamenBean() {
		super();
		servicioExamen = (ExamenService) new ExamenServiceImpl();
	}

	public String editarExamen(Integer id) throws Exception {

		this.examen = servicioExamen.getExamenCompletoById(id);
		
		return "crearExamen";
	}

	public String crearExamen(){
		
		this.examen = new Examenes();
		
		return "crearExamen";
	}
	
	public String verNotas() {

		return "verNotas";
	}
	

	public List<DatosExamenes> verNotasExamen() {
		Integer sessionIdUsuario = (Integer) session.getAttribute("idUsuario");
		return servicioExamen.verNotasExamenes(sessionIdUsuario);
	}
	//METODOS AGREGDO PAR VER LISTADO DE ALUMNO CON SUS NOTAS
	public String verAlumnoNotas(Integer id) {

		session.setAttribute("idExamen", id);
		return "verAlumnoNota";
	}
	//METODOS AGREGDO PAR VER LISTADO DE ALUMNO CON SUS NOTAS	
	public List<DatosExamenes> verAlumnoNotasExamen() throws Exception {
		Integer idExamen = (Integer) session.getAttribute("idExamen");
		
		return servicioExamen.traerAlumnoNotas(idExamen);
	}
	

	public List<DatosExamenes> getAllExamenes() {
		List<DatosExamenes> lista = servicioExamen.traerExamen();
		return lista;
	}

	public List<DatosExamenes> getAllExamenesParaUsuario() {
		Integer sessionIdUsuario = (Integer) session.getAttribute("idUsuario");
		List<DatosExamenes> lista = servicioExamen.traerExamenesParaUsuario(sessionIdUsuario);
		return lista;
	}

	public List<DatosExamenes> getExamenesRendir() {
		Integer sessionIdUsuario = (Integer) session.getAttribute("idUsuario");
		List<DatosExamenes> lista = servicioExamen.examenesParaRendir(sessionIdUsuario);
		return lista;
	}

	public String rendirExamenes() {
		return "examenesRendir";
	}

	public String inicio() {
		return "examenesAlumno";
	}

	public String gestionExamenes(){
			
		return "gestionExamenes";	
			
	}
	
	//ESTE METODO SE UTILIZA PARA GUARDAR Y PARA MODIFICAR
	public String guardarExamen() throws Exception {

		try {
			String json = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("datosj");

			Gson gson = new Gson();
			
			//SE PARSEA EL JSON A LA ENTIDAD EXAMEN
			Examenes examen = gson.fromJson(json, Examenes.class);

			// ES UN MAP PORQUE CONTIENE EL FLAG (TRUE OR FALSE EN FORMA DE
			// STRING) Y EL ERROR QUE ES UN STRING
			Map<String, String> valid = servicioExamen.validarExamen(examen);

			if (valid.get("flag") == "true") {
				// OK, GUARDAMOS EL EXAMEN
				servicioExamen.guardarExamen(examen);

				//SE UTILIZA PARA ENVIAR MENSAJOS AL CLIENTE
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Satisfactorio", "Examén guardado");

				FacesContext.getCurrentInstance().addMessage("info", message);
				return "OK";

			} else {


				//SE UTILIZA PARA ENVIAR MENSAJOS AL CLIENTE
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", valid.get("error"));

				FacesContext.getCurrentInstance().addMessage("errores", message);

				// DEVOLVEMOS EL ERROR ENCONTRADO
				return valid.get("error");

			}

		} catch (Exception e) {

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", e.getCause().toString());

			FacesContext.getCurrentInstance().addMessage("errores", message);

			throw e;
		}
	}


	public String rendirExamen(Integer id) throws Exception {
		
		this.examen = servicioExamen.getExamenCompletoById(id);
		
		return "rendirExamen";
	}

	@SuppressWarnings("unchecked")
	public String examenRendido() throws Exception{
		
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		Integer idusuario = (Integer) session.getAttribute("id");
		List<Respuestas> respuestas = new ArrayList<Respuestas>();
		ec.getRequestParameterMap().get("datosj");
		Gson gson = new Gson();
		
		//NECESARIO PARA NO TENER QUE CREAR OTRA CLASE PARA PARSEAR GSON,SI NO EXPLOTA
		Type fooType = new TypeToken<List<Respuestas>>() {}.getType(); 
		respuestas = gson.fromJson(json, fooType);
		
		servicioExamen.guardarExamenRendidoPorAlumno(idusuario, respuestas, this.examen);
		

	    ec.redirect(ec.getRequestContextPath() + "/faces/verNotas.xhtml");
		return "verNotas";
	}
	
	public String deshabilitarExamen(Integer id) {

		servicioExamen.deshabilitarExamen(id);
		return "";
	}

	public String listarRendirExamen() {
		return "listadoExamenesRendir";
	}

	public Integer getEstadoExamen() {
		return estadoExamen;
	}

	public void setEstadoExamen(Integer estadoExamen) {
		this.estadoExamen = estadoExamen;
	}

	public List<Preguntas> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Preguntas> preguntas) {
		this.preguntas = preguntas;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public Examenes getExamen() {
		return examen;
	}

	public void setExamen(Examenes examen) {
		this.examen = examen;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public void setExamenes(List<Examenes> examenes) {
		this.examenes = examenes;
	}

	public List<Examenes> getExamenes() throws Exception{

		return servicioExamen.getExamenes();
	}
}
