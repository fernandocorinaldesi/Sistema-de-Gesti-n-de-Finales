package ar.com.unpaz.taller.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.com.unpaz.modelo.Materia;
import ar.com.unpaz.taller.db.MateriaDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class MateriaDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtDescripcion;
	private JSpinner txtAnio;
    private boolean grabo = false;
    private String operacion;
    private Materia vMateria;

    public Materia getvMateria() {
        return vMateria;
    }

    public void setvMateria(Materia vMateria) {
        this.vMateria = vMateria;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public boolean isGrabo() {
        return grabo;
    }

    public void setGrabo(boolean grabo) {
        this.grabo = grabo;
    }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MateriaDialog dialog = new MateriaDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MateriaDialog() {
		initialize();
	}
	
	public MateriaDialog(Materia materia) {
		initialize();
		this.setvMateria(materia);
		this.txtDescripcion.setText(materia.getDescripcion());
		this.txtDescripcion.selectAll();
		this.txtAnio.setValue((int) materia.getAnio());
	}
	
	private void initialize() {
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 420, 142);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblMateria = new JLabel("Materia:");
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		JLabel lblNewLabel = new JLabel("A\u00F1o:");
		
		txtAnio = new JSpinner();
		txtAnio.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMateria)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtAnio, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtDescripcion, GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMateria)
						.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(txtAnio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Grabar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						btnGrabarActionPerformed(e);
					}
				});
				okButton.setIcon(new ImageIcon(MateriaDialog.class.getResource("./images/Guardar.png")));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 dispose();
					}
				});
				cancelButton.setIcon(new ImageIcon(MateriaDialog.class.getResource("./images/Cancelar.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	  private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {                                          
	        // TODO add your handling code here:

	        // Se Controla que el campo no esté en blanco
	        String descripcion = txtDescripcion.getText();
	        descripcion = descripcion.replaceAll(" ", "");
	        if (descripcion.isEmpty()){
	            JOptionPane.showMessageDialog(this,"Debe completar el campo Materia");
	            this.txtDescripcion.requestFocus();
	            this.txtDescripcion.selectAll();
	        }
	        else {
	            if (this.getOperacion().equals("M")){

	                this.getvMateria().setDescripcion(txtDescripcion.getText().toString());
	                this.getvMateria().setAnio((int)txtAnio.getValue());
	                if ((new MateriaDAO()).ActualizarMateria(this.getvMateria()) == 1) {
	                    this.setGrabo(true);
	                    this.dispose();
	                } else
	                    JOptionPane.showMessageDialog(this,"Error");
	            }


	            if (this.getOperacion().equals("A")) {

	                    Materia c = new Materia();
	                    c.setDescripcion(txtDescripcion.getText().toString());
	                    c.setAnio((int)txtAnio.getValue());
	                    if ((new MateriaDAO()).insertarMateria(c) == 1) {
	                        this.setGrabo(true);
	                        this.dispose();
	                    } else
	                        JOptionPane.showMessageDialog(this,"Error");
	            }
	        }
	    }  
}
