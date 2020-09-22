package com.udec.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.udec.dto.AlumnoDto;

public interface IAlumnoService {
	
	void escribirFichero(AlumnoDto alumno) throws FileNotFoundException, IOException;
	
	List<AlumnoDto> obtenerAlumnosFichero() throws FileNotFoundException, ClassNotFoundException, IOException;
	
	ResponseEntity<Object> devolverListaVacia(); 
	
	void RegistrarAlumnoDB(AlumnoDto alumno) throws SQLException;
	
	List<AlumnoDto> ObtenerAlumnosDB() throws SQLException;
	
	/*
	AlumnoDto obtenerAlumnoId(int id) throws FileNotFoundException, ClassNotFoundException, IOException;
	
	int eliminarAlumno(int id) throws FileNotFoundException, ClassNotFoundException, IOException;
	
	int editarAlumno(AlumnoDto alumno) throws FileNotFoundException, IOException, ClassNotFoundException;
	*/
	
	
	
}
