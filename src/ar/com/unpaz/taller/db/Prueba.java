package ar.com.unpaz.taller.db;

import ar.com.unpaz.modelo.Alumno;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MateriaDAO m = new MateriaDAO();
		AlumnoDAO a = new AlumnoDAO();
		Alumno b = new Alumno();
		b.setDni(1);
		b.setApellido("corinaldesi");
		b.setNombre("fernando");
		b.setEmail("fer@fer.com");
		
		
       // a.insertarAlumno(b);
		
		System.out.println(a.getAlumnoOrderByNombre());
		
	}
	
		

	}


