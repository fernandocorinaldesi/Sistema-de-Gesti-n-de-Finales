package ar.com.unpaz.taller.vista;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ar.com.unpaz.modelo.FinalTableModel;
import ar.com.unpaz.taller.db.FinalDAO;
import ar.com.unpaz.taller.vista.FinalDialog.Operacion;

public class FinalesDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	  private JTable table;
	  private JButton agregar;
	  private JButton borrar;
	  private JButton actualizar;
	  private FinalTableModel tableModel;
	  private FinalDialog subDialog = new FinalDialog();

	  public FinalesDialog(String titulo, FinalTableModel tableModel) {
	    this.tableModel = tableModel;
	    subDialog.setTableModel(tableModel);
	    setSize(450, 256);
	    setModal(true);
	    setTitle(titulo);
	    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    getContentPane().add(createCenter(), BorderLayout.CENTER);
	    getContentPane().add(createSouth(), BorderLayout.SOUTH);
	  }

	  private JScrollPane createCenter() {
	    table = new JTable(tableModel);
	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    return scrollPane;
	  }

	  private JPanel createSouth() {
	    JPanel pane = new JPanel();
	    agregar = new JButton("Agregar");
	    agregar.setIcon(new ImageIcon(MateriasDialog.class.getResource("./images/Agregar.png")));
	    agregar.addActionListener((e) -> {
	      subDialog.setTitle("Agregar");
	      subDialog.updateAlumnos();
	      subDialog.updateMaterias();
	      subDialog.editableFields(true);
	      subDialog.setVisible(true);
	    });
	    borrar = new JButton("Borrar");
	    borrar.setIcon(new ImageIcon(MateriasDialog.class.getResource("./images/Borrar.png")));
	    borrar.addActionListener((e) -> {
	      if (table.getSelectedRow() != -1) {
	    	  int dialogResult = JOptionPane.showConfirmDialog (null, "¿Confirma la eliminación de el alumno \"");
	            if(dialogResult == JOptionPane.YES_OPTION){
	    	   int row = table.getSelectedRow();
               (new FinalDAO()).eliminar(tableModel.getInRow(row));
               tableModel.update( (new FinalDAO()).getFinales());}
	       /* subDialog.setTitle("Borrar");
	        subDialog.updateAlumnos();
	        subDialog.updateMaterias();
	        int row = table.getSelectedRow();
	        subDialog.setFinal(tableModel.getInRow(row));
	        subDialog.operacion = Operacion.ELIMINAR;
	        // subDialog.editableFields(false);
	        subDialog.setVisible(true);*/
	      }
	    });
	    actualizar = new JButton("Modificar");
	    actualizar.setIcon(new ImageIcon(MateriasDialog.class.getResource("./images/Modificar.png")));
	    actualizar.addActionListener((e) -> {
	      subDialog.setTitle("Modificar");
	      if (table.getSelectedRow() != -1) {
	        int row = table.getSelectedRow();
              subDialog.updateAlumnos();
	        subDialog.updateMaterias();

	        subDialog.setFinal(tableModel.getInRow(row));
	        subDialog.operacion = Operacion.ACTUALIZAR;
	        subDialog.editableFields(true);
	        subDialog.setVisible(true);
	      }
	    });
	    pane.add(agregar);
	    pane.add(borrar);
	    pane.add(actualizar);
	    return pane;
	  }
	}