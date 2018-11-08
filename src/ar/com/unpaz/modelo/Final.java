package ar.com.unpaz.modelo;

import java.time.LocalDate;

public class Final {

	  private int id;
	  private Alumno alumno;
	  private Materia materia;
	  private LocalDate fecha;
	  private double nota;

	  public Final(int id, Alumno alumno, Materia materia, LocalDate fecha, double nota) {
	    this.id = id;
	    this.alumno = alumno;
	    this.fecha = fecha;
	    this.materia = materia;
	    this.nota = nota;
	  }
	 

	
	  public Final() {
		// TODO Auto-generated constructor stub
	  }



	public int getId() {
	    return this.id;
	  }

	  public Alumno getAlumno() {
	    return alumno;
	  }

	  public Materia getMateria() {
	    return materia;
	  }

	  public LocalDate getFecha() {
	    return fecha;
	  }

	  public double getNota() {
	    return nota;
	  }

	 
	  
	}