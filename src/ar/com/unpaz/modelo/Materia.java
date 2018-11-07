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
	  public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + anio;
	    result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
	    result = prime * result + idMateria;
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
	    Materia other = (Materia) obj;
	    if (anio != other.anio)
	      return false;
	    if (descripcion == null) {
	      if (other.descripcion != null)
	        return false;
	    } else if (!descripcion.equals(other.descripcion))
	      return false;
	    if (idMateria != other.idMateria)
	      return false;
	    return true;
	  }

	  
	  @Override
	  public String toString() {
	    return "" + idMateria + " - " + descripcion;
	  }

	}
