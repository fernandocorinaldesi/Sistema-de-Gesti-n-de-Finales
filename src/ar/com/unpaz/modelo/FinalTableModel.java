package ar.com.unpaz.modelo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class FinalTableModel extends AbstractTableModel {

	 private static final long serialVersionUID = 1L;

	  private static final String NOMBRES[] = {"id","Alumno","Materia","Fecha" ,"Nota"};
	  
	  private List<Final> finalesList = new ArrayList<>();

	public FinalTableModel(List<Final> finalesList) {
		  this.finalesList = finalesList;
		// TODO Auto-generated constructor stub
	}

	 public FinalTableModel() {
		// TODO Auto-generated constructor stub
	 }

	@Override
	  public int getRowCount() {
	    return finalesList.size();
	  }
	  
	  @Override
	  public String getColumnName(int column) {
	    return NOMBRES[column];
	  }

	  @Override
	  public int getColumnCount() {
	    return NOMBRES.length;
	  }

	  public void update(List<Final> finalesList) {
	    this.finalesList = finalesList;
	    fireTableDataChanged();
	  }
	  
	  public Final getInRow( int row) {
	    return finalesList.get(row);


	  }
	  
	  @Override
	  public Object getValueAt(int rowIndex, int columnIndex) {
	    Final finalObj = finalesList.get(rowIndex);
	    switch (columnIndex){
	        case 0: return finalObj.getId();
	        case 1: return finalObj.getAlumno().getDni();
	        case 2: return finalObj.getMateria().getDescripcion();
	        case 3: return String.valueOf(finalObj.getFecha());
	        case 4: return String.valueOf(finalObj.getNota());
	    }
	    return ":O";
	  }
}

