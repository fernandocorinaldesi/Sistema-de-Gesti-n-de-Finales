package ar.com.unpaz.modelo;

public class Materia {
	  private int idMateria;
	  private String descripcion;
	  private int anio;

	  public int getIdMateria() {
	    return idMateria;
	  }

	  public void setIdMateria(int idMateria) {
	    this.idMateria = idMateria;
	  }

	  public int getAnio() {
	    return anio;
	  }

	  public void setAnio(int anio) {
	    this.anio = anio;
	  }

	  public String getDescripcion() {
	    return descripcion;
	  }

	  public void setDescripcion(String descripcion) {
	    this.descripcion = descripcion;
	  }

	 

	  
	  @Override
	  public String toString() {
	    return "" + idMateria + " - " + descripcion;
	  }

	}
