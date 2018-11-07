package ar.com.unpaz.taller.listener;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.JTable;

import ar.com.unpaz.modelo.Anio;
import ar.com.unpaz.modelo.MateriaTableModel;
import ar.com.unpaz.taller.db.MateriaDAO;

public class TableMateriaHeaderMouseListener extends MouseAdapter {
	
	private JTable table;
	private JComboBox<Anio> combo;
	public TableMateriaHeaderMouseListener(JTable table, JComboBox<Anio> combo) {
	    this.table = table;
	    this.combo = combo;
	}
 
	public void mouseClicked(MouseEvent event) {
	    Point point = event.getPoint();
	    int column = table.columnAtPoint(point);
	    Anio anio = (Anio) combo.getSelectedItem();
	    MateriaTableModel c = null;
	    switch (column){
	    	case 0: 
	    		 if( anio.getAnio() == 0 )
	    			 c = new MateriaTableModel((new MateriaDAO()).getMateriasOrderById());
	    		 else
	    			 c = new MateriaTableModel((new MateriaDAO()).getMateriasByAnioOrderById(anio));
	    		 break;
	    	case 1:
	    		 if (anio.getAnio() == 0)
	    			 c = new MateriaTableModel((new MateriaDAO()).getMaterias());
	    		 else
	    			 c = new MateriaTableModel((new MateriaDAO()).getMateriasByAnio(anio));
	    		 break;
	    }
	    if (c!=null) {
	        table.setModel(c);
		    table.getTableHeader().setReorderingAllowed(false) ;
		    	
	    }
	
	}

}
