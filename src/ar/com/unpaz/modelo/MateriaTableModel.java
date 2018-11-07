package ar.com.unpaz.modelo;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class MateriaTableModel extends AbstractTableModel {
    
   	private static final long serialVersionUID = 1L;
	private List<Materia> materia;
    private static  String[] COLUMNAS={"ID", "Materia", "Año"};
    
    
    public MateriaTableModel (List<Materia> materia){
        super();
        this.materia = materia;
    }
    
    @Override
    public int getRowCount() {
        return materia.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNAS.length;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object retorno  = null;
        Materia c = this.materia.get(rowIndex);
        switch (columnIndex){
            case 0: retorno = c.getIdMateria(); break;
            case 1: retorno = c.getDescripcion(); break;
            case 2: retorno = c.getAnio(); break;
        }
        return retorno;
        
    }
    
    @Override
    public String getColumnName(int index){
         return COLUMNAS[index];
    }
    
}
