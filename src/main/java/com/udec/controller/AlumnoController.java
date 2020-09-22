package com.udec.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udec.dto.AlumnoDto;
import com.udec.service.IAlumnoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/alumnos")
@Api(value = "/alumnos", description = "Servicios rest para la clase alumno")
public class AlumnoController {
	
	@Autowired
	private IAlumnoService servicioAlumno;
	
	@PostMapping("/guardar")
	@ApiOperation(value = "Guardar registro en base de datos",
    notes = "Guardar registros en una base de datos") 	
	@ApiResponses(value = { @ApiResponse (code = 200, message = "Registro Creado") })
	public ResponseEntity<Object> guardarAlumnoBD(@Valid @RequestBody AlumnoDto alumno) throws SQLException {
		servicioAlumno.RegistrarAlumnoDB(alumno);
		return new ResponseEntity<Object>("Creado correctamente", HttpStatus.CREATED);
	}
	
	@GetMapping("/info/obtener")
	@ApiOperation(value = "Obtener todos los registros de base de datos",
    notes = "Obtener todos los registros almacenados en base de datos") 	
	@ApiResponses(value = { @ApiResponse (code = 204, message = "Lista vacia") })
	public ResponseEntity<List<AlumnoDto>> obtenerAlumnosDB() throws SQLException {
		List<AlumnoDto> alumno = servicioAlumno.ObtenerAlumnosDB();
		return new ResponseEntity<List<AlumnoDto>>(alumno,HttpStatus.OK);
	}

	
	@PostMapping("/guardarFichero")
	@ApiOperation(value = "Guardar registro",
    notes = "Guardar registros en los archivos txt") 	
	@ApiResponses(value = { @ApiResponse (code = 200, message = "Registro Creado") })
	public ResponseEntity<Object> guardarAlumnoFichero(@Valid 	@RequestBody AlumnoDto alumno) throws FileNotFoundException, ClassNotFoundException, IOException {
		servicioAlumno.escribirFichero(alumno);
		return new ResponseEntity<Object>("Creado correctamente", HttpStatus.CREATED);
	}

	@GetMapping("/info/obtenerFichero")
	@ApiOperation(value = "Obtener todos los registros",
    notes = "Obtener todos los registros almacenados en los archivos") 	
	@ApiResponses(value = { @ApiResponse (code = 204, message = "Lista vacia") })
	public ResponseEntity<List<AlumnoDto>> obtenerAlumnosFichero() throws FileNotFoundException, ClassNotFoundException, IOException {
		List<AlumnoDto> alumno = servicioAlumno.obtenerAlumnosFichero();
		return new ResponseEntity<List<AlumnoDto>>(alumno,HttpStatus.OK);
	}
	
	/*
	@GetMapping("/info/obtenerPorId/{id}")
	@ApiOperation(value = "Obtener registros por id",
    notes = "Obtener el registro almacenado que coincida con el id ingresado") 	
	@ApiResponses(value = { @ApiResponse (code = 404, message = "Registro no encontrado") })
	public ResponseEntity<Object> obtenerAlumnosId(
		@ApiParam(value = "id es necesario para buscar registro", required = true) @PathVariable int id) throws FileNotFoundException, ClassNotFoundException, IOException {		
		AlumnoDto alumnoId = servicioAlumno.obtenerAlumnoId(id);
		return new ResponseEntity<Object>(alumnoId,HttpStatus.OK);	
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ApiOperation(value = "Eliminar registro por id",
    notes = "Elimiar el registro almacenado que coincida con el id ingresado") 	
	@ApiResponses(value = { @ApiResponse (code = 404, message = "Registro no encontrado"),
			 @ApiResponse (code = 204, message = "Eliminado correctamente")})
	public ResponseEntity<String> eliminarAlumnos(
		@ApiParam(value = "id es necesario para eliminar registro", required = true)@PathVariable int id) throws ClassNotFoundException, IOException {		
		int eliminarAl = servicioAlumno.eliminarAlumno(id);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);	
	}
	
	@PutMapping("/editar")
	@ApiOperation(value = "Editar registro",
    notes = "Editar el registro almacenado que coincida con el id ingresado") 	
	@ApiResponses(value = { @ApiResponse (code = 404, message = "Registro no encontrado"),
			 @ApiResponse (code = 200, message = "Editado correctamente")})
	public ResponseEntity<Object> editarAlumno(@Valid @ApiParam(value = "Objeto Alumno es necesario para editar el registro", required = true) @RequestBody AlumnoDto alumno) throws Exception {
		int editarAl = servicioAlumno.editarAlumno(alumno);
		return new ResponseEntity<Object>(HttpStatus.OK);	
	}
	*/
}
