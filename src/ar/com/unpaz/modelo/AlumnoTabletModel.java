package ar.com.unpaz.modelo;


import java.util.List;

import javax.swing.table.AbstractTableModel;

public class AlumnoTabletModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private List<Alumno> alumno;
    private static  String[] COLUMNAS={"Dni", "Nombre", "Apellido","Email"};
    
    
    public AlumnoTabletModel (List<Alumno> alumno){
        super();
        this.alumno = alumno;
    }
    public AlumnoTabletModel() {
    	
    }
    
 @Override
    public int getRowCount() {
        return alumno.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNAS.length;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object retorno  = null;
        Alumno a = this.alumno.get(rowIndex);
        switch (columnIndex){
            case 0: retorno = String.valueOf(a.getDni());break;
            case 1: retorno = a.getNombre(); break;
            case 2: retorno = a.getApellido(); break;
            case 3: retorno = a.getEmail(); break;
           
        }
        return retorno;
        
    }
    
    @Override
    public String getColumnName(int index){
         return COLUMNAS[index];
    }
    public void update(List<Alumno> Alumno) {
	    this.alumno = Alumno;
	    fireTableDataChanged();
    }
    public Alumno getInRow( int row) {
	    return alumno.get(row);


	  }
	    
}
