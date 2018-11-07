package ar.com.unpaz.taller;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;

import ar.com.unpaz.modelo.AlumnoTabletModel;
import ar.com.unpaz.modelo.FinalTableModel;
import ar.com.unpaz.taller.db.AlumnoDAO;
import ar.com.unpaz.taller.db.FinalDAO;
import ar.com.unpaz.taller.vista.AlumnosDialog;
import ar.com.unpaz.taller.vista.FinalesDialog;
import ar.com.unpaz.taller.vista.JPanelConFondo;
import ar.com.unpaz.taller.vista.MateriasDialog;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionFinales {

	private JFrame frame;
	private final JPanelConFondo contenedor = new JPanelConFondo();
	private JMenuBar menuBar;
	private JMenu mnAMB;
	private JMenuItem mntmMaterias;
	private JMenuItem mntmAlumnos;
	private JMenuItem mntmFinales;
	private JMenu mnSistema;
	private JMenuItem mntmAcercaDe;
	private JMenuItem mntmSalir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionFinales window = new GestionFinales();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GestionFinales() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		//frame.setExtendedState(Frame.MAXIMIZED_BOTH);   SI LO QUIERO ARRANCAR MAXIMIZADO
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        frame.setTitle("Sistema de Gestión de Finales");
        
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        mnAMB = new JMenu("ABM");
        mnAMB.setMnemonic('A');
        mnAMB.setMnemonic(KeyEvent.VK_A);
        menuBar.add(mnAMB);
        
        mntmMaterias = new JMenuItem("Materias");
        mntmMaterias.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		MateriasDialog materiaDialog = new MateriasDialog();
        		materiaDialog.setVisible(true);
        	}
        });
        
        mntmMaterias.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
        mnAMB.add(mntmMaterias);
        
        mntmAlumnos = new JMenuItem("Alumnos");
        mntmAlumnos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        mntmAlumnos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AlumnoTabletModel tableModel = new AlumnoTabletModel();
                tableModel.update(new AlumnoDAO().getAlumnos());
        		AlumnosDialog alumnoDialog = new AlumnosDialog("ABM Alumnos",tableModel);
        		alumnoDialog.setVisible(true);
        	}
        });
        
        
        mnAMB.add(mntmAlumnos);
        
        mntmFinales = new JMenuItem("Finales");
        mntmFinales.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
        mntmFinales.addActionListener((e) -> {
            FinalTableModel tableModel = new FinalTableModel();
            tableModel.update(new FinalDAO().getFinales());
            FinalesDialog dialog = new FinalesDialog("ABM Finales", tableModel);
            dialog.setVisible(true);
          });
        mnAMB.add(mntmFinales);
        
        mnSistema = new JMenu("Sistema");
        mnSistema.setMnemonic('S');
        mnSistema.setMnemonic(KeyEvent.VK_S);
        menuBar.add(mnSistema);
        
        mntmAcercaDe = new JMenuItem("Acerca de");
        mnSistema.add(mntmAcercaDe);
        
        mntmSalir = new JMenuItem("Salir de Sistema");
        mntmSalir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog (null, "¿Seguro desea Salir?"  ,"Salir de Sistema",dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
        	}
        });
        mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        mnSistema.add(mntmSalir);
        contenedor.setImagen("fondo.jpg");
        frame.setContentPane(contenedor);
        frame.setLocationRelativeTo(null);
	}

}
