package gui;

import java.awt.BorderLayout;
import java.time.LocalDate;
import java.time.Period;
import funcionalidad.NombreVacioException;
//import java.time.temporal.ChronoUnit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.event.ChangeListener;

import funcionalidad.Gestion;
import funcionalidad.Heroes;
import funcionalidad.Pelis;

import javax.swing.event.ChangeEvent;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Movie extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNombre;
	private JSpinner spinnerFecha;
	private JTextField textFieldEdad;
	private LocalDate fecha_user = LocalDate.of(1995, 1, 1);
	private LocalDate fecha_actual = LocalDate.now();
	private JComboBox<Heroes> comboBoxHeroes;
	private JComboBox<Pelis> comboBoxPelis;

	static {
		Locale.setDefault(new Locale("es", "ES"));// Para Mostrar los dias y
													// meses en formato Español
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Movie dialog = new Movie();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Movie() {
		setTitle("V\u00EDdeoteca de h\u00E9roes");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton btnAddLine = new JButton("A\u00F1adir l\u00EDnea");
			btnAddLine.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						comprobarNombreVacio(textFieldNombre.getText());
					} catch (NombreVacioException g) {
						JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío","Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					String nombre_usuario = textFieldNombre.getText();
					LocalDate fecha_usuario = getFechaSpinner();
					String edad = textFieldEdad.getText();
					Heroes heroe = (Heroes) comboBoxHeroes.getSelectedItem();
					Pelis pelis = (Pelis) comboBoxPelis.getSelectedItem();
					Gestion.aniadirLinea(nombre_usuario, fecha_usuario, edad, heroe, pelis);
					JOptionPane.showMessageDialog(null, "Se ha añadido la línea correctamente");
					//dispose();
				}
			});
			btnAddLine.setBounds(153, 227, 131, 23);
			contentPanel.add(btnAddLine);
		}
		{
			JLabel lblTuNombre = new JLabel("Nombre:");
			lblTuNombre.setBounds(10, 23, 70, 14);
			contentPanel.add(lblTuNombre);
		}
		{
			textFieldNombre = new JTextField();
			textFieldNombre.setBounds(73, 20, 86, 20);
			contentPanel.add(textFieldNombre);
			textFieldNombre.setColumns(10);
		}

		spinnerFecha = new JSpinner();
		spinnerFecha.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				calcularAnios();
			}

		});
		spinnerFecha.setModel(new SpinnerDateModel(java.sql.Date.valueOf(fecha_user), null, null, Calendar.YEAR));
		spinnerFecha.setEditor(new JSpinner.DateEditor(spinnerFecha, "dd'/'MM'/'yyyy"));
		spinnerFecha.setBounds(317, 20, 107, 20);
		contentPanel.add(spinnerFecha);

		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento: ");
		lblFechaDeNacimiento.setBounds(193, 23, 131, 14);
		contentPanel.add(lblFechaDeNacimiento);
		{
			JLabel lblEdad = new JLabel("Edad:");
			lblEdad.setBounds(261, 54, 46, 14);
			contentPanel.add(lblEdad);
		}
		{
			textFieldEdad = new JTextField();
			textFieldEdad.setEditable(false);
			textFieldEdad.setBounds(317, 51, 86, 20);
			textFieldEdad.setText(obtenerAnios(getFechaSpinner(), fecha_actual));
			contentPanel.add(textFieldEdad);
			textFieldEdad.setColumns(10);
		}

		comboBoxHeroes = new JComboBox<Heroes>();
		for (Heroes heroes : Heroes.values()) {
			comboBoxHeroes.addItem(heroes);
		}
		comboBoxHeroes.setBounds(254, 130, 123, 20);
		contentPanel.add(comboBoxHeroes);

		JLabel lblHroes = new JLabel("H\u00E9roes:");
		lblHroes.setBounds(296, 105, 46, 14);
		contentPanel.add(lblHroes);
		{
			JLabel lblPelis = new JLabel("Pelis:");
			lblPelis.setBounds(89, 105, 46, 14);
			contentPanel.add(lblPelis);
		}
		{
			comboBoxPelis = new JComboBox<Pelis>();
			for (Pelis pelis : Pelis.values()) {
				comboBoxPelis.addItem(pelis);
			}
			comboBoxPelis.setBounds(37, 131, 156, 20);
			contentPanel.add(comboBoxPelis);
		}
	}

	private void calcularAnios() {
		textFieldEdad.setText(obtenerAnios(getFechaSpinner(), fecha_actual));
	}

	private String obtenerAnios(LocalDate fechaDelSpinner, LocalDate actual) {
		// Long anios = ChronoUnit.YEARS.between(fecha_user, fecha_actual);
		Period period = Period.between(fechaDelSpinner, actual);
		return period.getYears() + " años";
	}

	private LocalDate getFechaSpinner() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime((Date) spinnerFecha.getModel().getValue());
		LocalDate fecha = LocalDate.of(calendar.get(Calendar.YEAR), (calendar.get(Calendar.MONTH) + 1),
				calendar.get(Calendar.DAY_OF_MONTH));
		return fecha;
	}

	private void comprobarNombreVacio(String text) throws NombreVacioException {
		if (textFieldNombre.getText().isEmpty())
			throw new NombreVacioException("El nombre no puede estar vacío");
	}

}
