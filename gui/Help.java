package gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class Help extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Help help;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Help dialog = new Help();
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
	public Help() {
		setTitle("Ayuda...");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>\r\n<h1>Tu v\u00EDdeoteca favorita </h1>\r\n\r\nElige tu pelis de marvel favoritas y a tu h\u00E9roe<br />\r\n\r\nNuevo: Crea un nuevo fichero para crear tus h\u00E9roes y pelis favoritas<br />\r\n\r\nAbrir: Abre tu fichero y parte desde \u00E9l para crear tus pelis favoritas<br />\r\n\r\nGuardar: Guarda tu actual fichero de pelis y h\u00E9roes favoritos<br />\r\n\r\nGuardar como...: Elige otro fichero para guardar el que hayas modificado antes<br />\r\n</html>");
		lblNewLabel.setBounds(0, 0, 434, 261);
		getContentPane().add(lblNewLabel);

	}
	
	/**
	 * Singleton
	 * @return
	 */

	public static Help getInstance(){
			if(help==null)
				help = new Help();
			return help;
		}
	}
