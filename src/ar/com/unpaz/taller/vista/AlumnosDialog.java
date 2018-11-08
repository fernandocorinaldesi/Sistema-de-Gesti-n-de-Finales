package ar.com.unpaz.taller.vista;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import ar.com.unpaz.modelo.AlumnoTabletModel;

import ar.com.unpaz.taller.db.AlumnoDAO;


public class AlumnosDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTable table;
	private JButton agregar;
	private JButton borrar;
	private JButton actualizar;
	private AlumnoTabletModel tableModel;
	private AlumnoDialog subDialog = new AlumnoDialog();
	private AlumnoDialogModificar subDialog2 = new AlumnoDialogModificar();
	

	public AlumnosDialog(String titulo, AlumnoTabletModel tableModel) {
		
		this.tableModel = tableModel;
		subDialog.setTableModel(tableModel);
		subDialog2.setTableModel(tableModel);
		setSize(450, 256);
		setModal(true);
		setTitle(titulo);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().add(createCenter(), BorderLayout.CENTER);
		getContentPane().add(createSouth(), BorderLayout.SOUTH);
		TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(tableModel);
		table.setRowSorter(elQueOrdena);
	  
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
			subDialog.setVisible(true);

		});
		borrar = new JButton("Borrar");
		borrar.setIcon(new ImageIcon(MateriasDialog.class.getResource("./images/Borrar.png")));
		borrar.addActionListener((e) -> {
			if (table.getSelectedRow() != -1) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "¿Confirma la eliminación de el Alumno \"");
				if (dialogResult == JOptionPane.YES_OPTION) {
					int row = table.getSelectedRow();
					(new AlumnoDAO()).BorrarAlumno(tableModel.getInRow(row));
					tableModel.update((new AlumnoDAO()).getAlumnos());
				}
			} else {
				JOptionPane.showMessageDialog(null, "Debe seleccionar un Alumno");
			}

		});
		actualizar = new JButton("Modificar");
		actualizar.setIcon(new ImageIcon(MateriasDialog.class.getResource("./images/Modificar.png")));
		actualizar.addActionListener((e) -> {
			if (table.getSelectedRow() != -1) {
			}
			subDialog2.setTitle("Modificar");
			if (table.getSelectedRow() != -1) {
				int row = table.getSelectedRow();

				subDialog2.setFinal(tableModel.getInRow(row));

				subDialog2.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Debe seleccionar un Alumno");
			}
		});
		pane.add(agregar);
		pane.add(borrar);
		pane.add(actualizar);
		return pane;
	}
}
