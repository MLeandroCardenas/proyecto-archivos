package com.udec.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.udec.dto.AlumnoDto;

public interface IAlumnoRepo {
	
	void leerFichero(AlumnoDto alumno) throws FileNotFoundException, IOException;
	
	List<AlumnoDto> recuperarFichero() throws FileNotFoundException, ClassNotFoundException, IOException;
	
	Connection conexionDB() throws SQLException;
	
	void insertarAlumnoDB(AlumnoDto alumno) throws SQLException;
	
	List<AlumnoDto> recuperarAlumnosDB() throws SQLException;
	
	
	
	/*
	AlumnoDto traerAlumnoId(int id) throws FileNotFoundException, ClassNotFoundException, IOException;
	
	int eliminarAlumno(int id) throws FileNotFoundException, ClassNotFoundException, IOException;
	
	int editarAlumno(AlumnoDto alumno) throws FileNotFoundException, IOException, ClassNotFoundException;
	*/
}
