package com.udec.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import io.swagger.annotations.Api;

@Api(value = "/alumnosDto", description = "Servicios rest para la clase alumnos")
public class AlumnoDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Campo id es obligatorio")
	@Min(value= 1, message = "Minimo 1")
	private Integer id;
	
	@NotNull(message = "Campo nombre es obligatorio")
	@Size(min = 4, max = 30, message = "Minimo 4 caracteres y maximo 30 caracteres")
	private String nombres;
	
	@NotNull(message = "Campo universidad es obligatorio")
	@Size(min = 3, max = 100, message = "Minimo 3 caracteres y maximo 100 caracteres")
	private String universidad;
	
	@NotNull(message = "Campo edad es obligatorio")
	@Min(value = 18, message = "Minimo 18 años")
	@Max(value = 60, message = "Maximo 60 años")
	private Integer edad;
	
	public AlumnoDto(Integer id, String nombres, String universidad, Integer edad) {
		super();
		this.id = id;
		this.nombres = nombres;
		this.universidad = universidad;
		this.edad = edad;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getUniversidad() {
		return universidad;
	}

	public void setUniversidad(String universidad) {
		this.universidad = universidad;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}
}
