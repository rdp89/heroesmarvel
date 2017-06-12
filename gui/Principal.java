package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import funcionalidad.ErrorAlEscribirException;
import funcionalidad.ErrorAlLeerException;
import funcionalidad.Gestion;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class Principal extends JFrame {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Help help;
	private static JFileChooser ficheroSeleccionado = new JFileChooser();
	private JTextField textFieldDatos;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setTitle("MARVEL H\u00C9ROES");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		mnArchivo.setMnemonic('a');
		menuBar.add(mnArchivo);
		
		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Movie movie = new Movie();
				movie.setVisible(true);
			}
		});
		mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mntmNuevo.setMnemonic('n');
		mnArchivo.add(mntmNuevo);
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ficheroSeleccionado.showOpenDialog(null);
				 try {
					Gestion.abrir(ficheroSeleccionado.getSelectedFile());
				} catch (ErrorAlLeerException e1) {
					JOptionPane.showMessageDialog(null, "Error al leer el fichero","Error", JOptionPane.ERROR_MESSAGE);
				}
				ArrayList<String> datos = null;
				try {
					datos = Gestion.abrir(ficheroSeleccionado.getSelectedFile());
				} catch (ErrorAlLeerException e1) {
					System.out.println("Error al abrir el archivo");
				}
				 mostrarDatos(datos);
				 
				
			}
		});
		mntmAbrir.setMnemonic('a');
		mntmAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmAbrir);
		
		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ficheroSeleccionado.showSaveDialog(null);
				try {
					Gestion.guardar(ficheroSeleccionado.getSelectedFile());
				} catch (ErrorAlEscribirException e1) {
					JOptionPane.showMessageDialog(null, "Error al escribir el fichero","Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mntmGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmGuardar.setMnemonic('s');
		mnArchivo.add(mntmGuardar);
		
		JMenuItem mntmGuardarComo = new JMenuItem("Guardar como...");
		mntmGuardarComo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		mntmGuardarComo.setMnemonic('g');
		mnArchivo.add(mntmGuardarComo);
		
		JSeparator separator = new JSeparator();
		mnArchivo.add(separator);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		mntmSalir.setMnemonic('e');
		mnArchivo.add(mntmSalir);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmInfo = new JMenuItem("Info");
		mntmInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				help = Help.getInstance();
				help.setVisible(true);
			}
		});
		mntmInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mnAyuda.add(mntmInfo);
		
		JMenuItem mntmCreador = new JMenuItem("Creador...");
		mntmCreador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Creador creador = new Creador();
				creador.setVisible(true);
			}
		});
		mnAyuda.add(mntmCreador);
		contentPane = new JPanel();
		contentPane.setBackground(Color.RED);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(177, 28, 224, 168);
		contentPane.add(scrollPane);
		
		textFieldDatos = new JTextField();
		scrollPane.setViewportView(textFieldDatos);
		textFieldDatos.setEditable(false);
		textFieldDatos.setColumns(10);
		
		
	}

	private void mostrarDatos(ArrayList<String> lista) {
		String datos = "";
		for (String string : lista) {
			datos += string+"\n";
		}
		textFieldDatos.setText(datos);
	}
}
