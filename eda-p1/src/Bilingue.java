// DNI 45928098 ALARCON VILLENA, ALEJANDRO
import java.io.*;
public class Bilingue {
	public static void main(String[] args) { 
		// recibe un fichero de entrada con la info para el dic
		// recibe un fichero con un texto que debe traducir
		// recibe el idioma al que debe traducir
		
		DiccA dic = new DiccA();
		dic.leeDiccA(args[0]);
		boolean lenguaEncontrada = false;
		for (char c : dic.getLenguas()) {
			if (c == args[3].charAt(0))
				lenguaEncontrada = true;
		}
		
		if (!lenguaEncontrada) {
			System.out.println("No existe traduccion para esta lengua");
		}
		
		else {
			FileReader fichero = null;
			BufferedReader lectura = null;
			
			try { 
				fichero = new FileReader(args[1]);
				lectura = new BufferedReader(fichero);
	
				String linea = lectura.readLine();
				String[] partes = linea.split(" ");
				
				for (String s : partes)
				
			} catch (IOException e) {
				System.err.println(e);
				System.exit(0);
			} finally {
				try { 
					if (fichero!=null)
						fichero.close();
					if (lectura!=null)
						lectura.close();
				} catch(IOException ex) {
					System.err.println(ex);
				}
			}
			// comprobar que arg[2] es un idioma almacenado
			// traducir
		    // mostrar traduccion y el porcentaje de exito
		}
	}
}