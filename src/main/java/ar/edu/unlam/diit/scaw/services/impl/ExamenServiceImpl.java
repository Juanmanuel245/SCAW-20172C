package ar.edu.unlam.diit.scaw.services.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ar.edu.unlam.diit.scaw.daos.ExamenDao;
import ar.edu.unlam.diit.scaw.daos.impl.ExamenDaoImpl;
import ar.edu.unlam.diit.scaw.entities.DatosExamenes;
import ar.edu.unlam.diit.scaw.entities.Examenes;
import ar.edu.unlam.diit.scaw.entities.Preguntas;
import ar.edu.unlam.diit.scaw.entities.Respuestas;
import ar.edu.unlam.diit.scaw.services.ExamenService;

public class ExamenServiceImpl implements ExamenService {
	
	ExamenDaoImpl servicioDao;
	
	public ExamenServiceImpl(){
		servicioDao = new ExamenDaoImpl();
	}

	
	@Override
	public List<DatosExamenes> traerExamen(){
		
		return servicioDao.getAllExamenes();
	}

	public ExamenDao getServicioDao() {
		return servicioDao;
	}

	public void setServicioDao(ExamenDaoImpl servicioDao) {
		this.servicioDao = servicioDao;
	}

	@Override
	public void guardarExamen(Examenes examen) throws Exception {
		
		// PARA DETECTAR SI ES GUARDAR O EDITAR EXAMEN
		Boolean flag = true;
		
		if(examen.getId() == null){
			
			flag = false;
			examen.setId(servicioDao.proxIdParaExamen());
		}
		
			Iterator<Preguntas> itpreg = examen.getPreguntas().iterator();
			Iterator<Respuestas> itresp;
			Preguntas preg;
			Respuestas resp;
			int idpreg= servicioDao.proxIdParaPregunta();
			int idresp = servicioDao.ProxIdParaRespuesta();
			while(itpreg.hasNext()){
			
				preg = itpreg.next();	
			
				preg.setId(idpreg);
				idpreg++;
				System.out.println(preg.toString());
				
				itresp = preg.getRespuestas().iterator();
					
				while(itresp.hasNext()){
					
					resp = itresp.next();
	
					resp.setId(idresp);
					
					idresp++;
				}
				
			}

			if(!flag)
				servicioDao.salvarExamen(examen);
			else
				servicioDao.editarExamen(examen);
		
	}
	
	@Override
	public Examenes getExamenById(Integer id) {
		return servicioDao.getExamenById(id);
		
	}

	@Override
	public void editarExamen(Examenes examen) {
		servicioDao.editarExamen(examen);
	}

	@Override
	public void deshabilitarExamen(Integer id) {
		servicioDao.deshabilitarExamen(id);
	}

	@Override
	public List<Examenes> traerExamenActivos() {
		return servicioDao.traerExamenActivos();
	}



	@Override
	public void anotarExamen(DatosExamenes dato) {
		servicioDao.anotarseExamen(dato);
		
	}



	@Override
	public List<DatosExamenes> examenesParaRendir(Integer id) {	
		return servicioDao.examenesArendir(id);
	}



	@Override
	public List<DatosExamenes> traerExamenesParaUsuario(Integer id) {
		return servicioDao.getAllExamenesParaUsuario(id);
	}



	@Override
	public List<DatosExamenes> verNotasExamenes(Integer id) {
		
		return servicioDao.traerNotasExamenes(id);
	}


	@SuppressWarnings("null")
	@Override
	public Map validarExamen(Examenes examen) {
		
		Integer minpreg = 5;
		Integer minresp = 2;
		Integer cantpreg = examen.getPreguntas().size();
		Integer cantresp;
		Integer cantcorrectas;
		Integer cantincorrectas;
		Iterator<Preguntas> itpreg = examen.getPreguntas().iterator();
		Iterator<Respuestas> itresp;
		String error = null;
		Boolean flag = true;
		HashMap<String, String> map = new HashMap<String, String>();
		
		if(examen.getNombre() == null || examen.getNombre().isEmpty()){
			
			error = "Nombre del exámen nulo o inválido";
			flag = false;
			
		} else if(examen.getIdMateria() == null ) {
			
			error ="No se asignó ninguna materia al examen.";
			flag = false;
		}
		//VALIDO SI HAY POR LO MENOS LA CANTIDAD DE PREGUNTAS MINIMAS
		if(cantpreg < minpreg && flag){
			
			flag = false;
			error = "La cantidad de preguntas minimas para un examen es de " + minpreg +
					", usted ingresó " + cantpreg + " pregunta/s.";
			
		} else {
			
			while(itpreg.hasNext()){
				
				Preguntas preg = itpreg.next();
				
				cantcorrectas = 0;
				cantincorrectas = 0;
				cantresp = preg.getRespuestas().size();
				
				//VALIDO SI HAY POR LO MENOS DOS RESPUESTAS POR PREGUNTA
				if(cantresp < minresp){
					
					flag = false;
					error = "La cantidad de respuestas minimas por pregunta es de " + minresp +
							", usted ingresó " + cantresp + " respuesta/s en una de las preguntas.";	
					
				} else {
				
					itresp = preg.getRespuestas().iterator();
					
					while(itresp.hasNext()){
						
						Respuestas resp = itresp.next();
				
						//LA DESCRIPCION DE LA RESPUESTA NO DEBE SER NULL O ESTAR VACIA
						if(resp.getRespuesta() != null && !resp.getRespuesta().isEmpty()){
							
							//VALIDAMOS QUE HAYA ELEGIDO UNA OPCION
							if(resp.getIdTipoRespuesta() == 1 || resp.getIdTipoRespuesta() == 2){
								
								if(resp.getIdTipoRespuesta() == 1){
									
									cantcorrectas++;
								} else {
									
									cantincorrectas++;
								}
								
							} else {
								
								flag = false;
								
								error = "La opción a elegir en la respuesta (" + resp.getRespuesta() + ") no fue marcada, o es inválida.";
								
							}
							
						} else {
							
							flag = false;
							error = "La respuesta no tiene una descripción válida.";
							
						}
					}
					
					if(cantcorrectas == 0 || cantincorrectas == 0){
						
						System.out.println("RESPCORRECTAS: " + cantcorrectas + " Y RESP. INCORRECTAS : " + cantincorrectas);
						error = "Debe haber por lo menos una respuesta correcta, y una incorrecta.";
						flag = false;
					}
				}
				
				
			}
			
		}
		if(flag)
			map.put("flag" , "true");
		else
			map.put("flag", "false");
		
		map.put("error", error);
		
		return map;
	}


	@Override
	public Examenes getExamenCompletoById(Integer id) throws Exception {
		
		return servicioDao.getExamenCompletoById(id);
	}


	@Override
	public List<Examenes> getExamenes() throws Exception {
		
		return servicioDao.getExamenes();
	}


	@Override
	public void guardarExamenRendidoPorAlumno(Integer idusuario, List<Respuestas> respuestas,Examenes examen) throws Exception {
		
		servicioDao.guardarExamenRendidoPorAlumno(idusuario,respuestas,examen);
		
	}
	

}
