package ar.com.unpaz.taller.listener;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import ar.com.unpaz.modelo.MateriaTableModel;

public class TableAlumnoHeaderMouseListener {
	private JTable table;
	
	public TableAlumnoHeaderMouseListener(JTable table) {
	    this.table = table;
	  

}
	public void mouseClicked(MouseEvent event) {
	    Point point = event.getPoint();
	    int column = table.columnAtPoint(point);
	 
	    MateriaTableModel c = null;
	
	        table.setModel(c);
		    table.getTableHeader().setReorderingAllowed(false) ;
		    	
	    }
	
	}


