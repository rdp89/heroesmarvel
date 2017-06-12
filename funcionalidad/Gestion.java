package funcionalidad;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;



//import javax.swing.JFileChooser;

public class Gestion {
	//private static JFileChooser seleccionaFichero = new JFileChooser();
	private static ArrayList<String> la_lista = new ArrayList<String>();
	private static File fichero = null;
	

	public static void aniadirLinea(String nombre_usuario, LocalDate fecha_usuario, String edad, Heroes heroe,
			Pelis peli) {
		String linea_creada = "Nombre: " + nombre_usuario + ", fecha de nacimiento:" + fecha_usuario + ", edad: " + edad
				+ ", héroe elegido:" + heroe + ", peli elegida: " + peli;
		la_lista.add(linea_creada);
	}

	public static void guardar(File file) throws ErrorAlEscribirException {
		Fichero.escribir(file, la_lista);
		setFile(file);
	}
	
	public static void guardar() throws ErrorAlEscribirException{
		Fichero.escribir(fichero, la_lista);
	}

	private static File getFile() {
		return fichero;
	}

	private static void setFile(File file) {
		Gestion.fichero = file;
	}
	public static ArrayList<String> abrir(File file) throws ErrorAlLeerException{
		ArrayList<String> lectura = Fichero.leer(file);
		setFile(file);
		return lectura;
		
		
	}
	
	
	

}
