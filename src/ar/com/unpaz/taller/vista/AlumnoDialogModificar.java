package ar.com.unpaz.taller.vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ar.com.unpaz.modelo.Alumno;
import ar.com.unpaz.modelo.AlumnoTabletModel;
import ar.com.unpaz.taller.db.AlumnoDAO;

public class AlumnoDialogModificar extends JDialog {
	private static final long serialVersionUID = 1L;

	private JLabel dni;
	private JTextField nombre;
	private JTextField apellido;
	private JTextField email;
	private JButton aceptar;
	private JButton cancelar;
	private AlumnoTabletModel tableModel;
	private Alumno finalObj;

	public AlumnoDialogModificar() {
		this.setModal(true);
		
		this.setSize(300, 250);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.getContentPane().add(this.createCenter0(), BorderLayout.CENTER);
		this.getContentPane().add(this.createSouth0(), BorderLayout.SOUTH);
	}

	public void setTableModel(AlumnoTabletModel tableModel) {
		this.tableModel = tableModel;
	}

	public void setFinal(Alumno finalObj) {
		this.finalObj = finalObj;

		nombre.setText(finalObj.getNombre().toString());
		apellido.setText(finalObj.getApellido().toString());
		email.setText(finalObj.getEmail().toString());
		dni.setText(String.valueOf(finalObj.getDni()));
	}

	private JPanel createCenter0() {
		JPanel pane = new JPanel(new GridLayout(4, 1));
		pane.add(new JLabel("Dni : "));
		pane.add(this.dni = new JLabel(""));
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
			Alumno b = new Alumno();
			b.setDni(b.getDni());
			b.setNombre(nombre.getText());
			b.setApellido(apellido.getText());
			b.setEmail(email.getText());

			(new AlumnoDAO()).ActualizarAlumno(b);
			tableModel.update(new AlumnoDAO().getAlumnos());
			System.out.println("pase por aki");
			dispose();
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

}
