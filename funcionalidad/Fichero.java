package funcionalidad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Fichero {

	public static void escribir(File file, ArrayList<String> contenido) throws ErrorAlEscribirException {
		annadirExtension(file);
		try (BufferedWriter bufferSalida = new BufferedWriter(new FileWriter(file))) {
			for (String linea : contenido) {
				bufferSalida.write(linea + "\n");
			}
		} catch (IOException e) {
			throw new ErrorAlEscribirException("Error de escritura");
		}
	}

	public static ArrayList<String> leer(File file) throws ErrorAlLeerException {
		String linea;
		ArrayList<String> contenido = new ArrayList<String>();
		try (BufferedReader bufferEntrada = new BufferedReader(new FileReader(file))) {
			while ((linea = bufferEntrada.readLine()) != null) {
				contenido.add(linea);
			}
			return contenido;
		} catch (IOException e) {
			throw new ErrorAlLeerException("Error de lectura");
		}

	}
	
	private static File annadirExtension(File file) {
		String path = file.getPath();
		if (!path.endsWith(".txt"))
			return new File(path + ".txt");
		return file;
}
}
