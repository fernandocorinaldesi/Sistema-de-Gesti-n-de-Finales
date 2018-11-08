package ar.com.unpaz.taller.vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ar.com.unpaz.modelo.Alumno;
import ar.com.unpaz.modelo.AlumnoTabletModel;
import ar.com.unpaz.taller.db.AlumnoDAO;

public class AlumnoDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	private JTextField dni;
	private JTextField nombre;
	private JTextField apellido;
	private JTextField email;
	private JButton aceptar;
	private JButton cancelar;
	private AlumnoTabletModel tableModel;
	private Alumno b = new Alumno();

	public AlumnoDialog() {
		this.setModal(true);
		this.setSize(300, 250);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.getContentPane().add(this.createCenter0(), BorderLayout.CENTER);
		this.getContentPane().add(this.createSouth0(), BorderLayout.SOUTH);
	}

	public void setTableModel(AlumnoTabletModel tableModel) {
		this.tableModel = tableModel;
	}

	private JPanel createCenter0() {
		JPanel pane = new JPanel(new GridLayout(4, 1));
		pane.add(new JLabel("Dni : "));
		pane.add(this.dni = new JTextField(8));
		pane.add(new JLabel("Nombre : "));
		pane.add(this.nombre = new JTextField(8));
		pane.add(new JLabel("Apellido : "));
		pane.add(this.apellido = new JTextField(8));
		pane.add(new JLabel("Email : "));
		pane.add(this.email = new JTextField(8));
		return pane;
	}

	private JPanel createSouth0() {
		JPanel pane = new JPanel();
		this.aceptar = new JButton("Grabar");
		aceptar.setIcon(new ImageIcon(MateriasDialog.class.getResource("./images/Guardar.png")));
		this.aceptar.addActionListener((e) -> {
			String dni2 = dni.getText();
			String nombre2 = nombre.getText();
			String apellido2 = apellido.getText();
			if (dni2.isEmpty() || nombre2.isEmpty() || apellido2.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Debe completar los campos obligatorios");
			} else if (!esEntero(dni2)) {
				JOptionPane.showMessageDialog(this, "Error en el DNI");
			} else {
				int dnialumno = Integer.parseInt(dni.getText());
				b.setDni(dnialumno);
				b.setNombre(nombre.getText());
				b.setApellido(apellido.getText());
				b.setEmail(email.getText());

				(new AlumnoDAO()).insertarAlumno(b);
				tableModel.update(new AlumnoDAO().getAlumnos());
				dispose();
				limpiarcampos();
			}

		});
		pane.add(aceptar);
		this.cancelar = new JButton("Cancelar");
		cancelar.setIcon(new ImageIcon(MateriasDialog.class.getResource("./images/Cancelar.png")));
		this.cancelar.addActionListener((e) -> {
			this.dispose();
		});
		pane.add(this.cancelar);
		return pane;
	}

	public void limpiarcampos() {
		dni.setText("");
		nombre.setText("");
		apellido.setText("");
		email.setText("");
	}

	public boolean esEntero(String s) {
		try {
			Integer.parseInt(s);
			return true;

		} catch (Exception e) {
			return false;
		}
	}
}
