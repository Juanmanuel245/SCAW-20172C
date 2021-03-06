package ar.edu.unlam.diit.scaw.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Preguntas implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer idExamen;
	private String pregunta;
	
	private List<Respuestas> respuestas = new ArrayList<>();
	
	public Preguntas() {
		
	}
	
	public Preguntas(Integer id,Integer idExamen, String pregunta,Integer respuestas) {
		this.id 		= id;
		this.idExamen	= idExamen;
		this.pregunta	= pregunta;
		this.respuestas.add(new Respuestas(0,0,"",null));
		this.respuestas.add(new Respuestas(0,0,"",null));
		this.respuestas.add(new Respuestas(0,0,"",null));
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdExamen() {
		return idExamen;
	}
	public void setIdExamen(Integer idExamen) {
		this.idExamen = idExamen;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public List<Respuestas> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<Respuestas> respuestas) {
		this.respuestas = respuestas;
	}

	public void addRespuestas(Respuestas resp){
		
		this.respuestas.add(resp);
	}
	
	@Override
	public String toString() {
		return "Preguntas [id=" + id + ", idExamen=" + idExamen + ", pregunta=" + pregunta + ", respuestas="
				+ respuestas + "]";
	}
	
	

}
