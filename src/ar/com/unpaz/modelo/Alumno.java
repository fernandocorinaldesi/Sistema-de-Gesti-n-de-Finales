package ar.com.unpaz.modelo;

public class Alumno {


	  private int dni;
	  private String nombre;
	  private String apellido;
	  public String email;

	  public int getDni() {
	    return dni;
	  }

	  public void setDni(int dni) {
	    this.dni = dni;
	  }

	  public String getNombre() {
	    return nombre;
	  }

	  public void setNombre(String nombre) {
	    this.nombre = nombre;
	  }

	  public String getApellido() {
	    return apellido;
	  }

	  public void setApellido(String apellido) {
	    this.apellido = apellido;
	  }

	  public String getEmail() {
	    return email;
	  }

	  public void setEmail(String email) {
	    this.email = email;
	  }

	  @Override
	  public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
	    result = prime * result + dni;
	    result = prime * result + ((email == null) ? 0 : email.hashCode());
	    result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
	    Alumno other = (Alumno) obj;
	    if (apellido == null) {
	      if (other.apellido != null)
	        return false;
	    } else if (!apellido.equals(other.apellido))
	      return false;
	    if (dni != other.dni)
	      return false;
	    if (email == null) {
	      if (other.email != null)
	        return false;
	    } else if (!email.equals(other.email))
	      return false;
	    if (nombre == null) {
	      if (other.nombre != null)
	        return false;
	    } else if (!nombre.equals(other.nombre))
	      return false;
	    return true;
	  }

	  @Override
	  public String toString() {
	    return "" + dni + " - " + nombre + " - " + apellido + " - " + email;

	  }

	}
