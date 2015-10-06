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
		char l = args[2].charAt(0);
		for (char c : dic.getLenguas()) {
			if (c == l)
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
				String[] partes1 = linea.split("[,:;?!\\.() ]+");
				String[] partes2 = linea.split("[ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz]+");
				int exito = 0;
				int exitop = 100/partes1.length;
				
				for (int i = 0; i < partes1.length; i++) {
					if (partes1[i]!=null) {
						if (dic.traduce1(partes1[i], l)!=null) {
							System.out.print(dic.traduce1(partes1[i], l));
							exito+=exitop;
						}
						else
							System.out.print("-");
					}
					System.out.print(partes2[i]);
				}
				System.out.println("");
				System.out.println(exito + "%");
				
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
		}
	}
}