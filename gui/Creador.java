package gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class Creador extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Creador dialog = new Creador();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Creador() {
		setModal(true);
		setTitle("Creador...");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblcreadoPorrafael = new JLabel("<html>\r\n<h1>Creado por: <b>Rafael Delgado</b></h1>\r\n\r\n<h2>Fecha: 11/06/2017</h2>\r\n\r\n\u00A92017 RDP89 All rights reserved\r\n</html>");
		lblcreadoPorrafael.setBounds(0, 0, 434, 261);
		getContentPane().add(lblcreadoPorrafael);

	}
}
