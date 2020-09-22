package com.udec.repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.udec.dto.AlumnoDto;

@Repository
public class AlumnoRepoImpl implements IAlumnoRepo {

	// bd remota
	
	private final String rutaFichero = "alumno.txt";
	private final String url = "jdbc:postgresql://postgres://uffwdowxltlinq:b440c1e2b86259a6f742f6d9722a7ad7a1021d47f56848d0a18f820ffe88710b@ec2-34-234-185-150.compute-1.amazonaws.com:5432/d8rqmiutr18q6i";
	private final String user = "uffwdowxltlinq";
	private final String password = "b440c1e2b86259a6f742f6d9722a7ad7a1021d47f56848d0a18f820ffe88710b";
    
	
	/*
	private final String rutaFichero = "C:\\Users\\michl\\OneDrive\\Documentos\\springBoot\\serviciosArchivoDoc\\src\\main\\resources\\static\\alumno.txt";
	private final String url = "jdbc:postgresql://localhost/testing";
    private final String user = "postgres";
    private final String password = "1234";
    */
    
    
	private List<AlumnoDto> listaAlumno;
	private List<AlumnoDto> serializar;
	private List<AlumnoDto> deserializar;
	
	AlumnoDto al;
	
	
	@Autowired
	public AlumnoRepoImpl(List<AlumnoDto> listaAlumno, List<AlumnoDto> serializar, List<AlumnoDto> deserializar) {
		this.listaAlumno = listaAlumno;
		this.serializar = serializar;
		this.deserializar = deserializar;
	}
	
	@Override
	public Connection conexionDB() throws SQLException {
		 Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(url, user, password);
	        } catch (SQLException e) {
	        	throw new SQLException("No se pudo conectar a la base de datos");
	        }
	        return conn;
	}
	
	@Override
	public void insertarAlumnoDB(AlumnoDto alumno) throws SQLException {
		try {
			Connection conn = conexionDB();
			PreparedStatement query = conn.prepareStatement("insert into alumnos(id,nombres,universidad,edad) values(?, ?, ?, ?)");
			query.setInt(1, alumno.getId());
			query.setString(2, alumno.getNombres());
			query.setString(3, alumno.getUniversidad());
			query.setInt(4, alumno.getEdad());
			query.executeUpdate();
			query.close();
		} catch (SQLException e) {
			throw new SQLException("No se pudo conectar a la base de datos");
		}
	}
	
	@Override
	public List<AlumnoDto> recuperarAlumnosDB() throws SQLException {
		try {
			Connection conn = conexionDB();
			PreparedStatement preparedStatement = conn.prepareStatement("select * from alumnos");
			ResultSet resulSet = preparedStatement.executeQuery();
			while(resulSet.next()) {
				Integer id = resulSet.getInt("id");
				String nombres = resulSet.getString("nombres");
				String universidad = resulSet.getString("universidad");
				Integer edad = resulSet.getInt("edad");
				AlumnoDto objAlumno = new AlumnoDto(id, nombres, universidad, edad);
				listaAlumno.add(objAlumno);
			}
			resulSet.close();
			conn.close();
		} catch (SQLException e) {
			throw new SQLException("No se pudo conectar a la base de datos");
		}
		return listaAlumno;
	}
	

	@Override
	public void leerFichero(AlumnoDto alumno) throws IOException, FileNotFoundException{
		serializar.add(alumno);
		try {
			ObjectOutputStream escribiendoFichero = new ObjectOutputStream(new FileOutputStream(rutaFichero));
			escribiendoFichero.writeObject(serializar);
			escribiendoFichero.close();
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("No se pudo encontrar el archivo");
		} catch (IOException ex) {
			throw new IOException("Error leyendo el fichero");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AlumnoDto> recuperarFichero() throws FileNotFoundException, ClassNotFoundException, IOException{

		try {
			ObjectInputStream leyendoFichero = new ObjectInputStream( new FileInputStream(rutaFichero));
		    deserializar = (List<AlumnoDto>)leyendoFichero.readObject();
		    leyendoFichero.close();

		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("No se pudo encontrar el archivo");

		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("La definicion de la clase no fue encontrada");

		} catch (IOException e) {
			throw new IOException("Error leyendo el fichero");

		}
		return deserializar;
	}

	/*
	@Override
	public AlumnoDto traerAlumnoId(int id) throws FileNotFoundException, ClassNotFoundException, IOException {

		List<AlumnoDto> listaAlumnoId = recuperarFichero();

		for (int i = 0; i < listaAlumnoId.size(); i++) {
			
			if (listaAlumno.get(i).getId() == id)
				return listaAlumno.get(i);
		}
		return new AlumnoDto(0,"","",0);
	}
	*/
	
	/*
	@Override
	public int eliminarAlumno(int id) throws ClassNotFoundException, IOException, FileNotFoundException {
		
		List<AlumnoDto> listaAlumnoId = recuperarFichero();

		for (int i = 0; i < listaAlumnoId.size(); i++) {
			
			if (listaAlumno.get(i).getId() == id) {
				listaAlumno.remove(i);
				return 1;
			}			
		}
		return 0;
	}
	*/
	
	/*

	@Override
	public int editarAlumno(AlumnoDto alumno) throws ClassNotFoundException, FileNotFoundException, IOException{
		
		List<AlumnoDto> listaAlumnoId = recuperarFichero();

		for (int i = 0; i < listaAlumnoId.size(); i++) {
			
			if (listaAlumno.get(i).getId().equals(alumno.getId())) {
				listaAlumno.remove(i);
				leerFichero(alumno);
				return 1;
			}			
		}
		return 0;
	}
	*/
}








