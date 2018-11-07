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
	    return id;
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

	  @Override
	  public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((alumno == null) ? 0 : alumno.hashCode());
	    result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
	    result = prime * result + id;
	    result = prime * result + ((materia == null) ? 0 : materia.hashCode());
	    long temp;
	    temp = Double.doubleToLongBits(nota);
	    result = prime * result + (int) (temp ^ (temp >>> 32));
	    return result;
	  }

	  @Override
	  public boolean equals(Object obj) {
	    if (this == obj)
	      return true;
	    if (obj == null)
	      return false;
	    if (getClass() != obj.getClass())
	      return false;
	    Final other = (Final) obj;
	    if (alumno == null) {
	      if (other.alumno != null)
	        return false;
	    } else if (!alumno.equals(other.alumno))
	      return false;
	    if (fecha == null) {
	      if (other.fecha != null)
	        return false;
	    } else if (!fecha.equals(other.fecha))
	      return false;
	    if (id != other.id)
	      return false;
	    if (materia == null) {
	      if (other.materia != null)
	        return false;
	    } else if (!materia.equals(other.materia))
	      return false;
	    if (Double.doubleToLongBits(nota) != Double.doubleToLongBits(other.nota))
	      return false;
	    return true;
	  }
	  
	}