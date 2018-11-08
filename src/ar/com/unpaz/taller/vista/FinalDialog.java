package ar.com.unpaz.taller.vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ar.com.unpaz.modelo.Alumno;
import ar.com.unpaz.modelo.Final;
import ar.com.unpaz.modelo.FinalTableModel;
import ar.com.unpaz.modelo.Materia;
import ar.com.unpaz.taller.db.AlumnoDAO;
import ar.com.unpaz.taller.db.FinalDAO;
import ar.com.unpaz.taller.db.MateriaDAO;

public class FinalDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	  private JComboBox<Alumno> alumnos;
	  private JComboBox<Materia> materias;
	  private JLabel fechaLabel;
	  private JLabel id;
	  private JTextField campoNota;
	  private JButton aceptar;
	  private JButton cancelar;

	  private FinalTableModel tableModel;
	  private Final finalObj;

	  public enum Operacion {
	    AGREGAR, ELIMINAR, ACTUALIZAR
	  };

	  public Operacion operacion;

	  public FinalDialog() {
	    this.setModal(true);
	    this.setSize(300, 250);
	    this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    this.getContentPane().add(this.createCenter0(), BorderLayout.CENTER);
	    this.getContentPane().add(this.createSouth0(), BorderLayout.SOUTH);
	  }

	  public void setTableModel(FinalTableModel tableModel) {
	    this.tableModel = tableModel;
	  }
	  
	  public void updateAlumnos() {
	    alumnos.removeAllItems();
	    for (Alumno alumno : (new AlumnoDAO().getAlumnos())) {
	      alumnos.addItem(alumno);
	    }
	  }

	  public void updateMaterias() {
	    materias.removeAllItems();
	    for (Materia materia : (new MateriaDAO().getMaterias())) {
	      materias.addItem(materia);
	    }
	  }

	  public void setFinal(Final finalObj) {
	   
	    alumnos.setSelectedItem(finalObj.getAlumno());
	    materias.setSelectedItem(finalObj.getMateria());
	    fechaLabel.setText(finalObj.getFecha().toString());
	    campoNota.setText(String.valueOf(finalObj.getNota()));
	    id.setText(String.valueOf(finalObj.getId()));
	  }

	 public void editableFields(boolean b) {
	    alumnos.setEditable(b);
	    materias.setEditable(b);
	    campoNota.setEditable(b);
	  }

	  private JPanel createCenter0() {
	    JPanel pane = new JPanel(new GridLayout(5, 1));
	    pane.add(new JLabel("Id Finales : "));
	    pane.add(this.id = new JLabel());
	    pane.add(new JLabel("Alumno : "));
	    pane.add(this.alumnos = new JComboBox<>());
	    pane.add(new JLabel("Materia : "));
	    pane.add(this.materias = new JComboBox<>());
	    pane.add(new JLabel("Fecha"));
	    pane.add(this.fechaLabel = new JLabel("--/--/----"));
	    pane.add(new JLabel("Nota : "));
	    pane.add(this.campoNota = new JTextField(8));
	    return pane;
	  }

	  private JPanel createSouth0() {
	    JPanel pane = new JPanel();
	    this.aceptar = new JButton("Grabar");
	    aceptar.setIcon(new ImageIcon(MateriasDialog.class.getResource("./images/Guardar.png")));
	    this.aceptar.addActionListener((e)->{
	      action();
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

	  private void action() {
	    if (this.operacion == Operacion.AGREGAR) {
	      Alumno alumno = (Alumno) alumnos.getSelectedItem();
	      Materia materia = (Materia) materias.getSelectedItem();
	      double nota = Double.parseDouble(campoNota.getText());
	      Final finalObj2 = new Final();
	      System.out.println(nota);
	     
	      Final finalObj = new Final(finalObj2.getId(), alumno, materia, LocalDate.now(), nota);
	      System.out.println(finalObj2.getId());
	      (new FinalDAO()).agregar(finalObj);
	      dispose();
	      limpiarcampos();
	      tableModel.update(new FinalDAO().getFinales());
	    }
	    if (this.operacion == Operacion.ELIMINAR) {
	      (new FinalDAO()).eliminar(finalObj);
	      dispose();
	      tableModel.update(new FinalDAO().getFinales());
	    }
	    if (this.operacion == Operacion.ACTUALIZAR) {
	    	System.out.println("pase por aki");
	    	 Alumno alumno = (Alumno) alumnos.getSelectedItem();
		      Materia materia = (Materia) materias.getSelectedItem();
		      double nota = Double.parseDouble(campoNota.getText());
		      int id2 = Integer.parseInt(id.getText());
		      
		      System.out.println(nota);
		      Final finalObj2 = new Final();
		      Final finalObj = new Final(id2, alumno, materia, LocalDate.now(), nota);
		      System.out.println(finalObj2.getId());
	    	 
	      (new FinalDAO()).actualizar(finalObj);
	      dispose();
	      limpiarcampos();
	      tableModel.update(new FinalDAO().getFinales());
	    }
	  }
	  public void limpiarcampos() {
			campoNota.setText("");
			id.setText("");
			
		}

	}
