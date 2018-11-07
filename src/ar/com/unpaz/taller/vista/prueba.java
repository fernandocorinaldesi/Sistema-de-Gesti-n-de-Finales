package ar.com.unpaz.taller.vista;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import ar.com.unpaz.modelo.Alumno;
import ar.com.unpaz.modelo.Final;
import ar.com.unpaz.modelo.FinalTableModel;
import ar.com.unpaz.modelo.Materia;

public class prueba {
	public static void main(String[] args) {
	    Alumno alumno = new Alumno();
	    alumno.setNombre("fer");
	    
	    Materia materia = new Materia();
	    materia.setDescripcion("algoritmo");
	    List<Final> finalesList = Arrays.asList(new Final(0, alumno, materia,LocalDate.now() ,10));
	    FinalTableModel tableModel = new FinalTableModel();
	    tableModel.update(finalesList);
	    new FinalesDialog("ABM Finales", tableModel).setVisible(true);
	  }

}
