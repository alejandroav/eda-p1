// DNI 45928098 ALARCON VILLENA, ALEJANDRO

public class Bilingue {

	/* asd
	 * public static void main(String[] args) { // recibe un fichero de entrada
	 * con la info para el dic // recibe un fichero con un texto que debe
	 * traducir // recibe el idioma al que debe traducir
	 * 
	 * DiccA dic = new DiccA();
	 * 
	 * // abrir fichero 1
	 * 
	 * dic.leeDiccA(args[0]);
	 * 
	 * // abrir fichero 2
	 * 
	 * try { fichero = new FileReader(args[0]); lectura = new
	 * BufferedReader(fichero);
	 * 
	 * String linea = lectura.readLine(); String partes[] = null;
	 * 
	 * while (linea!=null) { partes = linea.split("[ ]*\\*[ ]*"); for(int i=0; i
	 * < partes.length; i++ ) linea = lectura.readLine(); } } catch (IOException
	 * e) { System.err.println(e); System.exit(0); } finally { try { if
	 * (fichero!=null) fichero.close(); if (lectura!=null) lectura.close(); }
	 * catch(IOException ex) { System.err.println(ex); } }
	 * 
	 * // comprobar que arg[2] es un idioma almacenado
	 * 
	 * // traducir
	 * 
	 * // mostrar traduccion y el porcentaje de exito }
	 */

	public static void main(String[] args) {
		Palabra p = new Palabra("house", 8);
		p.setTrad(new Traduccion('E'), 'E');
		p.agregaAcepcion("casa", 'E');
		p.agregaAcepcion("hogar", 'E');
		p.setTrad(new Traduccion('K'), 'K');
		p.setTrad(new Traduccion('N'), 'N');
		p.setTrad(new Traduccion('S'), 'S');
		p.setTrad(new Traduccion('P'), 'P');
		p.agregaAcepcion("bleble", 'K');
		p.agregaAcepcion("bleble", 'S');
		p.agregaAcepcion("bleble", 'P');
		p.agregaAcepcion("hogarcito", 'E');
		p.agregaAcepcion("mikasa", 'E');
		p.agregaAcepcion("jaus", 'E');
		for(Traduccion t : p.getTraducciones())
			if (t!=null)
				for(String s : t.getAcepciones())
					if (s!=null)
						System.out.println(s);
		p.escribeInfo();
	}
}
