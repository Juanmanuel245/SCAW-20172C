package ar.edu.unlam.diit.scaw.daos.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import ar.edu.unlam.diit.scaw.configs.HsqlDataSource;
import ar.edu.unlam.diit.scaw.daos.MateriaDao;
import ar.edu.unlam.diit.scaw.entities.Materia;

public class MateriaDaoImpl implements MateriaDao{
	
	HsqlDataSource dataSource = new HsqlDataSource();
	Connection conn;
	
	@Override
	public List<Materia> getAllMaterias() {
		
		List<Materia> ll = new LinkedList<Materia>();
		
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			query = conn.createStatement();
			
			ResultSet rs = query.executeQuery("SELECT * FROM Materias");
	
			while (rs.next()) {
			  
				Integer id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				Integer idDocenteTitular = rs.getInt("idDocenteTitular");
				Integer idEstadoMateria = rs.getInt("idEstadoMateria");
								
				Materia materia = new Materia();
				materia.setId(id);
				materia.setNombre(nombre);
				materia.setIdDocenteTitular(idDocenteTitular);
				materia.setIdEstadoMateria(idEstadoMateria);
	
				ll.add(materia);
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ll;
	}
	
}
