package com.example.android.tarea.domain;

public class Tarea {
	
	private String codigo;
	private String titulo;
	private String descripcion;
	
	public Tarea(String codigo, String titulo, String descripcion) {
		
		this.codigo = codigo;
		this.titulo = titulo;
		this.descripcion = descripcion;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
