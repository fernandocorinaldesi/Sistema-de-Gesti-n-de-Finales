package ar.com.unpaz.taller.vista;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ar.com.unpaz.modelo.Anio;
import ar.com.unpaz.modelo.Materia;
import ar.com.unpaz.modelo.MateriaTableModel;
import ar.com.unpaz.taller.db.MateriaDAO;
import ar.com.unpaz.taller.listener.TableMateriaHeaderMouseListener;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class MateriasDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tblMaterias;
	private JComboBox<Anio> cboAnio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MateriasDialog dialog = new MateriasDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MateriasDialog() {
	   initialize();
       MateriaTableModel c = new MateriaTableModel((new MateriaDAO()).getMaterias());
       tblMaterias.setModel(c);
       tblMaterias.getTableHeader().setReorderingAllowed(false) ;
       
       ArrayList<Anio> anios = new ArrayList<>();
       anios.add(new Anio(0,"Seleccione"));
       anios.add(new Anio(1,"Primero"));
       anios.add(new Anio(2,"Segundo"));
       anios.add(new Anio(3,"Tercero"));
       anios.add(new Anio(4,"Cuarto"));
       anios.add(new Anio(5,"Quinto"));
      
       cboAnio.setModel(new DefaultComboBoxModel(anios.toArray()));
       

	}
	
	
	private void initialize() {
		setTitle("ABM de Materias");
		setModal(true);
		setBounds(100, 100, 530, 270);
		
		JLabel lblFiltrarPorAo = new JLabel("Filtrar por a\u00F1o:");
		cboAnio = new JComboBox<Anio>();
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAgregarActionPerformed(e);
			}
		});
		btnAgregar.setIcon(new ImageIcon(MateriasDialog.class.getResource("./images/Agregar.png")));
		btnAgregar.setActionCommand("");
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnBorrarActionPerformed(arg0);
			}
		});
		btnBorrar.setIcon(new ImageIcon(MateriasDialog.class.getResource("./images/Borrar.png")));
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnModificarActionPerformed(e);
			}
		});
		btnModificar.setIcon(new ImageIcon(MateriasDialog.class.getResource("./images/Modificar.png")));
		

		cboAnio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cboAnioActionPerformed(e);
			}
		});
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE))
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(160)
								.addComponent(btnModificar, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnBorrar, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnAgregar, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblFiltrarPorAo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboAnio, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
							.addGap(273)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFiltrarPorAo)
						.addComponent(cboAnio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAgregar, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
						.addComponent(btnBorrar, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
						.addComponent(btnModificar, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		tblMaterias = new JTable();
		tblMaterias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tblMaterias);
		getContentPane().setLayout(groupLayout);
		
        JTableHeader header = tblMaterias.getTableHeader();
        header.addMouseListener(new TableMateriaHeaderMouseListener(tblMaterias, cboAnio));
		
	    pack();
	    setLocationRelativeTo(null);
	}
	
	
    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        int row = tblMaterias.getSelectedRow();
        if (row != -1){
            Materia c = new Materia();
            //COLUMNAS={"id", "Materia"}
            
            c.setIdMateria((int)tblMaterias.getModel().getValueAt(row, 0));
            c.setDescripcion((String)tblMaterias.getModel().getValueAt(row, 1).toString());
            
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (null, "¿Confirma la eliminación de la materia \"" + c.getDescripcion() + "\"? ","Borrar Materia",dialogButton);
            if(dialogResult == JOptionPane.YES_OPTION){
                if ((new MateriaDAO()).BorrarMateria(c)==1) {
                	Anio anio = (Anio) cboAnio.getSelectedItem();
                	MateriaTableModel mtm= null;
           		 	if (anio.getAnio() == 0)
           		 		mtm = new MateriaTableModel((new MateriaDAO()).getMaterias());
        			else
        				mtm = new MateriaTableModel((new MateriaDAO()).getMateriasByAnio(anio));
           		 	
                	tblMaterias.setModel(mtm);
                }
                	
            }            
        }
        else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una Materia");
        } 
    }   
    
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {                                           
        MateriaDialog altaMateria = new MateriaDialog();
        altaMateria.setOperacion("A");
        altaMateria.setLocationRelativeTo(this);
        altaMateria.setVisible(true);
        if (altaMateria.isGrabo()) {
        	Anio anio = (Anio) cboAnio.getSelectedItem();
        	MateriaTableModel mtm = null;
   		 	if (anio.getAnio() == 0)
   		 		mtm = new MateriaTableModel((new MateriaDAO()).getMaterias());
			else
				mtm = new MateriaTableModel((new MateriaDAO()).getMateriasByAnio(anio));
   		 	
        	tblMaterias.setModel(mtm);
        }
    }                                          

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {                                             
    	MateriaDialog materiaDialog ;
        
        int row = tblMaterias.getSelectedRow();
        if (row != -1){
            Materia materia = new Materia();
          
            
            materia.setIdMateria((int)tblMaterias.getModel().getValueAt(row, 0));
            materia.setDescripcion((String)tblMaterias.getModel().getValueAt(row, 1).toString());
            materia.setAnio((int)tblMaterias.getModel().getValueAt(row, 2)); 
            materiaDialog   = new MateriaDialog(materia);
            materiaDialog.setOperacion("M");
            materiaDialog.setLocationRelativeTo(this);
            materiaDialog.setVisible(true);
            if (materiaDialog.isGrabo()) {
            	Anio anio = (Anio) cboAnio.getSelectedItem();
            	MateriaTableModel mtm = null;
       		 	if (anio.getAnio() == 0)
       		 		mtm = new MateriaTableModel((new MateriaDAO()).getMaterias());
    			else
    				mtm = new MateriaTableModel((new MateriaDAO()).getMateriasByAnio(anio));
       		 	
            	tblMaterias.setModel(mtm);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una Materia");
        }

    }         
    
    
    void cboAnioActionPerformed (java.awt.event.ActionEvent evt) {
    	if (cboAnio.getSelectedIndex() == 0) 
    		tblMaterias.setModel(new MateriaTableModel((new MateriaDAO()).getMaterias()));
    	else {
    		Anio anio = (Anio) cboAnio.getSelectedItem();
    		tblMaterias.setModel(new MateriaTableModel((new MateriaDAO()).getMateriasByAnio(anio)));
    	}
    		
    	
    }
}
