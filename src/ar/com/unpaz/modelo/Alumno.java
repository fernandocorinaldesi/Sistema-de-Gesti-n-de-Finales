package ar.com.unpaz.modelo;

public class Alumno {


	  private int dni;
	  private String nombre;
	  private String apellido;
	  public String email;
	  private double porcentaje;

	  public int getDni() {
	    return dni;
	  }
	  public double getPorcentaje() {
		    return porcentaje;
		  }
	  public void setPorcentaje(double porcentaje) {
		    this.porcentaje= porcentaje;
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


	  public String toString() {
	    return "" + dni + " - " + nombre + " - " + apellido + " - " + email+" - "+porcentaje;

	  }
}

	
