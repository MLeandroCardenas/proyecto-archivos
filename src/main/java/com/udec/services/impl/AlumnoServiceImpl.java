package com.udec.services.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.udec.dto.AlumnoDto;
import com.udec.exception.ModelNotFoundException;
import com.udec.repository.IAlumnoRepo;
import com.udec.service.IAlumnoService;


@Service
public class AlumnoServiceImpl implements IAlumnoService {
	
	@Autowired
	private IAlumnoRepo conexionDatos;
	
	@Override
	public ResponseEntity<Object> devolverListaVacia() {
		return new ResponseEntity<Object>(null,HttpStatus.NO_CONTENT);
	}

	@Override
	public void escribirFichero(AlumnoDto alumno) throws FileNotFoundException, IOException {
		conexionDatos.leerFichero(alumno);
	}

	@Override
	public List<AlumnoDto> obtenerAlumnosFichero() throws FileNotFoundException, ClassNotFoundException, IOException {	
		List<AlumnoDto> alumnos = conexionDatos.recuperarFichero();
		if(alumnos.isEmpty())
			devolverListaVacia();
		return alumnos;
	}

	@Override
	public void RegistrarAlumnoDB(AlumnoDto alumno) throws SQLException {
		conexionDatos.insertarAlumnoDB(alumno);
	}

	@Override
	public List<AlumnoDto> ObtenerAlumnosDB() throws SQLException {
		List<AlumnoDto> alumnos = conexionDatos.recuperarAlumnosDB();
		return alumnos;
	}

	/*
	
	@Override
	public AlumnoDto obtenerAlumnoId(int id) throws FileNotFoundException, ClassNotFoundException, IOException {
		AlumnoDto alumnoId = conexionFichero.traerAlumnoId(id);
		if (alumnoId.getId() == 0)
			throw new ModelNotFoundException("No existe el alumno");
		else
			return alumnoId;
	}
	
	
	@Override
	public int eliminarAlumno(int id) throws ClassNotFoundException, IOException {
		int a = conexionFichero.eliminarAlumno(id);
		if (a == 0)
			throw new ModelNotFoundException("No existe el alumno");
		else
			return a;		
	}

	@Override
	public int editarAlumno(AlumnoDto alumno) throws FileNotFoundException, IOException, ClassNotFoundException {
		int a =conexionFichero.editarAlumno(alumno);
		if (a == 0)	
			throw new ModelNotFoundException("No existe el alumno");
		else
			return a;
	}
	
	@Override
	public AlumnoDto obtenerAlumnoId(int id) throws FileNotFoundException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int eliminarAlumno(int id) throws FileNotFoundException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int editarAlumno(AlumnoDto alumno) throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return 0;
	}
	*/
}
