package ar.edu.unlam.diit.scaw.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ar.edu.unlam.diit.scaw.configs.HsqlDataSource;
import ar.edu.unlam.diit.scaw.daos.ExamenDao;
import ar.edu.unlam.diit.scaw.entities.DatosExamenes;
import ar.edu.unlam.diit.scaw.entities.Examenes;
import ar.edu.unlam.diit.scaw.entities.Materia;
import ar.edu.unlam.diit.scaw.entities.Preguntas;
import ar.edu.unlam.diit.scaw.entities.Respuestas;
import ar.edu.unlam.diit.scaw.entities.Usuario;

public class ExamenDaoImpl implements ExamenDao {
	
	HsqlDataSource dataSource = new HsqlDataSource();
	Connection conn;
	
	@Override
	public void anotarseExamen(DatosExamenes dato){
		
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			// Creo la consulta SQL
			PreparedStatement ps = conn.prepareStatement("INSERT INTO AlumnoExamen (idExamen, idAlumno, idEstadoExamen, nota) VALUES(?,?,2,'A')");
			ps.setString(1, dato.getIdExamen());
			ps.setInt(2, dato.getIdUsuario());
			
			// Ejecuto la sentencia
			ps.executeUpdate();
			ps.close();
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}
	
	// Me trae todos los examenes ACTIVOS
	@Override
 	public List<DatosExamenes> getAllExamenes() {
		
		List<DatosExamenes> ll = new ArrayList<DatosExamenes>();
		
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			
			query = conn.createStatement();
			String sql = "SELECT e.id as idExamen, e.nombre NombreExamen, e.IdMateria as idMateria, e.idEstadoExamen as idEstado, m.nombre as nombreMateria, est.descripcion as estadoExamen FROM examenes as e INNER JOIN materias as m ON m.id = e.IdMateria INNER JOIN estadosexamenes as est ON e.idEstadoExamen = est.id WHERE e.idEstadoExamen = 2 AND e.id NOT IN (SELECT idexamen FROM alumnoexamen);";
			
			ResultSet rs = query.executeQuery(sql);
	
			while (rs.next()) {
			  
				DatosExamenes datos = new DatosExamenes();
				datos.setIdExamen(rs.getString("idExamen"));
				datos.setEstadoExamen(rs.getString("estadoExamen"));
				datos.setIdEstado(rs.getInt("idEstado"));
				datos.setIdMateria(rs.getString("idMateria"));
				datos.setNombreExamen(rs.getString("nombreExamen"));
				datos.setNombreMateria(rs.getString("nombreMateria"));
				
				ll.add(datos);
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ll;
	}
	
	// Me trae todos los examenes ACTIVOS y en los que el usuario no se haya anotado con anterioridad
		@Override
	 	public List<DatosExamenes> getAllExamenesParaUsuario(Integer id) {
			
			List<DatosExamenes> ll = new ArrayList<DatosExamenes>();
			
			try {
				conn = (dataSource.dataSource()).getConnection();
			
				Statement query;
				
				
				query = conn.createStatement();
				String sql = "SELECT e.id as idExamen, e.nombre NombreExamen, e.IdMateria as idMateria, e.idEstadoExamen as idEstado, m.nombre as nombreMateria, est.descripcion as estadoExamen FROM examenes as e INNER JOIN materias as m ON m.id = e.IdMateria INNER JOIN estadosexamenes as est ON e.idEstadoExamen = est.id WHERE e.idEstadoExamen = 2 AND e.id NOT IN (SELECT idexamen FROM alumnoexamen WHERE idalumno = " + id +");";
			
				ResultSet rs = query.executeQuery(sql);
		
				while (rs.next()) {
				  
					DatosExamenes datos = new DatosExamenes();
					datos.setIdExamen(rs.getString("idExamen"));
					datos.setEstadoExamen(rs.getString("estadoExamen"));
					datos.setIdEstado(rs.getInt("idEstado"));
					datos.setIdMateria(rs.getString("idMateria"));
					datos.setNombreExamen(rs.getString("nombreExamen"));
					datos.setNombreMateria(rs.getString("nombreMateria"));
					
					ll.add(datos);
				}
				
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return ll;
		}
	
	@Override
	public void salvarExamen(Examenes examen) throws Exception {


		try {
			Iterator<Preguntas> itpreg;
			Iterator<Respuestas> itresp;
			Preguntas preg;
			Respuestas resp;
			conn = (dataSource.dataSource()).getConnection();
	        
	        PreparedStatement ps = conn.prepareStatement("INSERT INTO examenes(Id,nombre,idMateria,IdEstadoExamen)"
	        		+ "  VALUES(?,?,?,?)");
	        ps.setInt(1, examen.getId());
			ps.setString(2,examen.getNombre());			
			ps.setInt(3,examen.getIdMateria());
			ps.setInt(4,examen.getIdEstadoExamen());
			
			ps.executeUpdate();
			ps.close();
			
			itpreg = examen.getPreguntas().iterator();
				
				while(itpreg.hasNext()){
				
					preg = itpreg.next();	
				
					ps = conn.prepareStatement("INSERT INTO preguntas(Id,IdExamen,pregunta)"
			        		+ "  VALUES(?,?,?)");
					
					ps.setInt(1, preg.getId());
					ps.setInt(2, examen.getId());
					ps.setString(3, preg.getPregunta());
					
					ps.executeUpdate();
					ps.close();
					
					itresp = preg.getRespuestas().iterator();
						
					while(itresp.hasNext()){
						
						resp = itresp.next();

					
							ps = conn.prepareStatement("INSERT INTO respuestas(Id,IdPregunta,respuesta,IdTipoRespuesta)"
					        		+ "  VALUES(?,?,?,?)");
							
							ps.setInt(1, resp.getId());
							ps.setInt(2, preg.getId());
							ps.setString(3, resp.getRespuesta());
							ps.setInt(4, resp.getIdTipoRespuesta());
							
							ps.executeUpdate();
							ps.close();
						}
					}
				

				ps.close();
			conn.close();
		} catch (Exception e) {
			conn.close();
			
			e.printStackTrace();
		}		

	}

	@Override
	public void editarExamen(Examenes examen) {
		try {
			Iterator<Preguntas> itpreg;
			Iterator<Respuestas> itresp;
			Preguntas preg;
			Respuestas resp;
			PreparedStatement ps;
			
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			query = conn.createStatement();
			
			String sql = "UPDATE Examenes set nombre='" + examen.getNombre() + "',idMateria="+examen.getIdMateria()+", idEstadoExamen="+examen.getIdEstadoExamen()+" WHERE id="+examen.getId();
			System.out.println(sql);
			query.executeUpdate(sql);  
			
			sql = "DELETE FROM respuestas as r WHERE r.idpregunta IN (SELECT p.id FROM Preguntas as p WHERE p.idexamen="+examen.getId()+")";
			System.out.println(sql);
			query.executeUpdate(sql);  
			
			sql = "DELETE FROM preguntas as p WHERE p.idexamen="+examen.getId();
			System.out.println(sql);
			query.executeUpdate(sql);
			

			itpreg = examen.getPreguntas().iterator();
				
				while(itpreg.hasNext()){
				
					preg = itpreg.next();	
				
					System.out.println(preg.toString());
			
					ps = conn.prepareStatement("INSERT INTO preguntas(Id,IdExamen,pregunta)"
			        		+ "  VALUES(?,?,?)");
					
					ps.setInt(1, preg.getId());
					ps.setInt(2, examen.getId());
					ps.setString(3, preg.getPregunta());
					
					ps.executeUpdate();
					ps.close();
					
					itresp = preg.getRespuestas().iterator();
						
					while(itresp.hasNext()){
						
						resp = itresp.next();
					
							ps = conn.prepareStatement("INSERT INTO respuestas(Id,IdPregunta,respuesta,IdTipoRespuesta)"
					        		+ "  VALUES(?,?,?,?)");
							
							ps.setInt(1, resp.getId());
							ps.setInt(2, preg.getId());
							ps.setString(3, resp.getRespuesta());
							ps.setInt(4, resp.getIdTipoRespuesta());
							
							ps.executeUpdate();
							ps.close();
						}
					}

			
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void deshabilitarExamen(Integer id) {
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			query = conn.createStatement();
			
			String sql = "UPDATE Examenes set idEstadoExamen=3 WHERE id="+id;
			System.out.println(sql);
			query.executeUpdate(sql);  
			
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public List<Examenes> traerExamenActivos() {
		List<Examenes> ll = new ArrayList<Examenes>();
		
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			query = conn.createStatement();
			String sql = "SELECT e.id,e.nombre as examen,m.nombre as materia,ee.descripcion as estado "+
					"FROM examenes as e "+
					"INNER JOIN materias as m ON m.id = e.idmateria "+
					"INNER JOIN estadosexamenes as ee ON e.idestadoexamen=ee.id "+
					"WHERE e.idestadoexamen!=3";
			ResultSet rs = query.executeQuery(sql);
	
			while (rs.next()) {
			  
				Examenes examen = new Examenes();
				examen.setId(rs.getInt("id"));
				examen.setNombre(rs.getString("examen"));
				examen.setMateria(new Materia(rs.getString("materia")));
				examen.setEstadosExamenes(rs.getString("estado"));

	
				ll.add(examen);
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ll;
	}

	@Override
	public List<DatosExamenes> examenesArendir(Integer id) {
			
		List<DatosExamenes> ll = new LinkedList<DatosExamenes>();
		
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			query = conn.createStatement();
			
			ResultSet rs = query.executeQuery("SELECT a.idexamen as idExamen, a.idalumno as idAlumno, e.nombre as nombreExamen, m.nombre as nombreMateria FROM alumnoexamen as a INNER JOIN examenes as e ON  e.id = a.idexamen INNER JOIN materias as m ON e.idmateria = m.id WHERE a.idalumno = " + id + "AND a.idestadoexamen = 2");
	
			while (rs.next()) {
			  
						
				DatosExamenes datos = new DatosExamenes();
				datos.setIdExamen(rs.getString("idExamen"));
				datos.setIdUsuario(rs.getInt("idAlumno"));
				datos.setNombreExamen(rs.getString("nombreExamen"));
				datos.setNombreMateria(rs.getString("nombreMateria"));
						
				ll.add(datos);
				
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ll;
	}

	@Override
	public List<DatosExamenes> traerNotasExamenes(Integer id) {
		List<DatosExamenes> ll = new LinkedList<DatosExamenes>();
		
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			query = conn.createStatement();
			
			ResultSet rs = query.executeQuery("select m.nombre as nombreMateria, e.nombre as nombreExamen, a.nota from alumnoexamen as a INNER JOIN examenes as e ON a.idexamen = e.id INNER JOIN materias as m ON m.id = e.idmateria WHERE a.idalumno = " + id);
	
			while (rs.next()) {
			  
						
				DatosExamenes datos = new DatosExamenes();
				datos.setNombreMateria(rs.getString("nombreMateria"));
				datos.setNombreExamen(rs.getString("nombreExamen"));
				datos.setNota(rs.getString("nota"));
						
				ll.add(datos);
				
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ll;
	}

	@Override
	public Integer proxIdParaExamen() throws Exception {
		
		try {
			Integer lastid = null;
			
			conn = (dataSource.dataSource()).getConnection();
	        Statement stmt = conn.createStatement();
	        
	      //OBTENGO EL ID DEL ULTIMO USUARIO
			ResultSet rs = stmt.executeQuery("select id from examenes order by id desc limit 1"); 
			
			while(rs.next()){
				//AL OBTENER EL ID LE SUMO 1 YA QUE DEBE SER EL PROXIMO USUARIO
				lastid = rs.getInt("id") + 1;
				
			}
			

			rs.close();
			stmt.close();
			conn.close();
		return lastid;
		
		} catch(Exception e){
			
			throw e;
		}
}
	@Override
	public Integer proxIdParaPregunta() throws Exception {

		try {
			Integer lastid = null;
			
			conn = (dataSource.dataSource()).getConnection();
	        Statement stmt = conn.createStatement();
	        
	      //OBTENGO EL ID DEL ULTIMO USUARIO
			ResultSet rs = stmt.executeQuery("select id from preguntas order by id desc limit 1"); 
			
			while(rs.next()){
				//AL OBTENER EL ID LE SUMO 1 YA QUE DEBE SER EL PROXIMO USUARIO
				lastid = rs.getInt("id") + 1;
				
			}
		
			rs.close();
			stmt.close();
			conn.close();
		return lastid;
		
		} catch(Exception e){
			
			throw e;
		}
	}

	@Override
	public Integer ProxIdParaRespuesta() throws Exception {
		try {
			Integer lastid = null;
			
			conn = (dataSource.dataSource()).getConnection();
	        Statement stmt = conn.createStatement();
	        
	      //OBTENGO EL ID DEL ULTIMO USUARIO
			ResultSet rs = stmt.executeQuery("select id from respuestas order by id desc limit 1"); 
			
			while(rs.next()){
				//AL OBTENER EL ID LE SUMO 1 YA QUE DEBE SER EL PROXIMO USUARIO
				lastid = rs.getInt("id") + 1;
				
			}

			rs.close();
			stmt.close();
			conn.close();
			
		return lastid;
		
		} catch(Exception e){
			
			throw e;
		}
	}

	public Examenes getExamenCompletoById(Integer id) throws Exception {
		
		try{
			
			Materia materia = new Materia();
			ResultSet rs;
			ResultSet rsresp;
			PreparedStatement ps;
			Examenes examen = new Examenes();
			Preguntas preg;
			Respuestas resp;
			List<Preguntas> preguntas = new ArrayList<Preguntas>();
			List<Respuestas> respuestas = new ArrayList<Respuestas>();
			
			conn = (dataSource.dataSource()).getConnection();
	        
			Statement stresp = conn.createStatement();
			
	        ps = conn.prepareStatement("Select * from examenes INNER JOIN materias on examenes.idMateria = materias.id where id = ?");
	        
	        ps.setInt(1, id);
	        
	        rs = ps.executeQuery();
			
	        while(rs.next()){
	        	
	        	examen.setId(id);
	        	examen.setNombre(rs.getString("nombre"));
	        	examen.setIdEstadoExamen(rs.getInt("idEstadoExamen"));
	        	materia.setId(rs.getInt("materias.id"));
	        	materia.setNombre(rs.getString("materias.nombre"));
	        	examen.setMateria(materia);
	        }
	        
	        rs.close();
	        ps.close();
	        
	        ps = conn.prepareStatement("Select * from preguntas where idExamen= ?");
	        
	        ps.setInt(1, id);
	        
	        rs = ps.executeQuery();
	        
	        
	        while(rs.next()){
	        	
	        	preg = new Preguntas();
	        	preg.setId(rs.getInt("id"));
	        	preg.setIdExamen(id);
	        	preg.setPregunta(rs.getString("pregunta"));
	      
	        	rsresp = stresp.executeQuery("Select * from respuestas where idPregunta=" + preg.getId());
		        	
	        	while(rsresp.next()){
	        		
	        		resp = new Respuestas();
	        		
	        		resp.setId(rsresp.getInt("id"));
	        		resp.setIdPregunta(preg.getId());
	        		resp.setRespuesta(rsresp.getString("respuesta"));
	        		resp.setIdTipoRespuesta(rsresp.getInt("idTipoRespuesta"));

	        		preg.addRespuestas(resp);
	        	}
	        	
	        	preguntas.add(preg);
		        rsresp.close();
	        }
	        
	        rs.close();
	       ps.close();
	       stresp.close();
	        conn.close();
	       
	        examen.setPreguntas(preguntas);
	      
	         
	        return examen;
			
		} catch(Exception e){
		
			throw e;
		}
		
	}

	public List<Examenes> getExamenes() throws Exception {
		
		try{
			List<Examenes> examenes = new ArrayList<Examenes>();
			Examenes examen;
			PreparedStatement ps;
			Materia materia = new Materia();
			
			conn = (dataSource.dataSource()).getConnection();
	        
	        ps = conn.prepareStatement("Select * from examenes join materias on examenes.idMateria = materias.id");
	        
	        ResultSet rs = ps.executeQuery();
			
	        while(rs.next()){
	        	
	        	examen = new Examenes();
	        	examen.setId(rs.getInt("id"));
	        	examen.setNombre(rs.getString("nombre"));
	        	examen.setIdEstadoExamen(rs.getInt("idEstadoExamen"));
	        	materia.setId(rs.getInt("materias.id"));
	        	materia.setNombre(rs.getString("materias.nombre"));
	        	examen.setMateria(materia);
	        	
	        	examenes.add(examen);
	        }
	        
	        rs.close();
	        ps.close();
	        conn.close();
	        
	        return examenes;
		
		} catch(Exception e){
			
			throw e;
			
			}
		}

	public void guardarExamenRendidoPorAlumno(Integer idusuario, List<Respuestas> respuestas, Examenes examen) throws Exception {
		
		try{
			List<Respuestas> respcorrectas = new ArrayList<Respuestas>();
			PreparedStatement ps;
			Iterator<Respuestas> it = respuestas.iterator();
			Iterator<Preguntas> itpreg = examen.getPreguntas().iterator();
			Iterator<Respuestas> itresp;
			Respuestas resp;
			Respuestas respcor;
			Preguntas preg;
			Double cantcorrectas = 0D;
			Double cantrespuestas = 0D;
			conn = (dataSource.dataSource()).getConnection();
			
			//OBTENGO TODAS LAS RESPUESTAS ORIGINALES DEL EXAMEN
			while(itpreg.hasNext()){
				
				preg = itpreg.next();
				
				respcorrectas.addAll(preg.getRespuestas());
			}
			
			//CANTITAD TOTAL DE RESPUESTAS DEL EXAMEN
			cantrespuestas = (double) respcorrectas.size();
			itresp = respcorrectas.iterator();
			
			//RECORRO RESPUESTAS DEL ALUMNO
			while(it.hasNext()){
				
				resp = it.next();

				ps = conn.prepareStatement("INSERT INTO alumnorespuestas(idalumno,idrespuesta,idtiporesp) values(?,?,?)");
				ps.setInt(1, idusuario);
				ps.setInt(2, resp.getId());
				ps.setInt(3, resp.getIdTipoRespuesta());
				
				ps.executeUpdate();
				ps.close();
				
				//RECORRO LAS RESPUESTAS DEL EXAMEN POR CADA RESPUESTA DEL ALUMNO(PARA EVITAR MALAS COMPARACIONES ENTRE RESPUESTAS)
				while(itresp.hasNext()){
					
					respcor= itresp.next();
				
						
					if(resp.getId() == respcor.getId()){
						
						if(resp.getIdTipoRespuesta() == respcor.getIdTipoRespuesta()){
							cantcorrectas++;

						}
						
					}
				}
				itresp = respcorrectas.iterator();
			}
			
			
			//OBTENGO LA NOTA Y SEGUN LA ESCALA PROVISTA LA CAMBIO
			Double nota = (cantcorrectas * 100) / cantrespuestas;
			
			if(nota == 100){
				
				nota = 10D;
			} else if(nota >= 80 && nota < 100){
				
				nota = 7D;
			} else if(nota >= 60 && nota < 80){
				
				nota = 4D;
			} else{
				
				nota = 2D;	
			}

			//GUARDO LA NOTA
			ps = conn.prepareStatement("UPDATE alumnoexamen SET idEstadoExamen = ? , nota = ? where idExamen = ? and idAlumno = ?");
			
			ps.setInt(1, 3);
			ps.setInt(2, nota.intValue());
			ps.setInt(3, examen.getId());
			ps.setInt(4, idusuario);
			
			ps.executeUpdate();
			
			ps.close();
			conn.close();
			
		}catch(Exception e){
			
			throw e;
		}
	}

	@Override
	public List<DatosExamenes> getAlumnoNotas(Integer id) throws Exception{
		
		List<DatosExamenes> result = new ArrayList<DatosExamenes>();
		try{
			conn = (dataSource.dataSource()).getConnection();
			
			// Creo la consulta SQL
			PreparedStatement ps = conn.prepareStatement("select u.nombre as nombre, u.apellido as ape, m.nombre as nombreMateria, e.nombre as nombreExamen, a.nota from "
														+ " alumnoexamen as a  INNER JOIN examenes as e ON   a.idexamen = e.id "
														+ " INNER JOIN materias  as m ON   m.id = e.idmateria "
														+ " INNER JOIN usuarios as u ON  a.idalumno = u.id where e.id = ? ");
			ps.setInt(1, id);
			
			
			// Ejecuto la sentencia
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				
				String nomape = new StringBuilder()
								.append(rs.getString("nombre"))
								.append(" ")
								.append(rs.getString("apellido"))
								.toString();
				String nMateria = rs.getString("nombreMateria");
				String nExamen = rs.getString("nombreMateria");
				String  nota = rs.getString("nota");
				
				DatosExamenes de = new DatosExamenes();
				
				de.setNombreExamen(nExamen);
				de.setNombreMateria(nMateria);
				de.setNota(nota);
				de.setNombreUsuario(nomape);
				result.add(de);
			}
			ps.close();
			
			conn.close();
		}catch(SQLException e){
			System.out.println("Ha ocurrido un error: " + e.getMessage());
		}
		
		return result;
	}
}

