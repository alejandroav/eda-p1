// DNI 45928098 ALARCON VILLENA, ALEJANDRO
import java.io.*;

public class DiccA {
	private int nlenguas;
	private char[] lenguas;
	private Palabra[] dicc;
	private Palabra[] diccord;

	public DiccA() {
		nlenguas = -1;
		lenguas = null;
		dicc = new Palabra[10];
		diccord = new Palabra[10];
	}

	// recibe y almacena el diccionario en dicc y diccord
	// explicar diccord: diccord anade las nuevas palabras mediante un metodo
	// de ordenacion lineal
	public void leeDiccA(String f) {
		FileReader fichero = null;
		BufferedReader lectura = null;
		try {
			// flujos
			fichero = new FileReader(f);
			lectura = new BufferedReader(fichero);

			String linea = lectura.readLine();
			nlenguas = Integer.parseInt(linea);

			// obtengo las partes
			linea = lectura.readLine();
			String partes[] = null;
			partes = linea.split("[ ]*");

			int i = 0;
			lenguas = new char[nlenguas];

			for (i = 0; i < nlenguas; i++)
				lenguas[i] = partes[i].charAt(0);
			
			Palabra nueva = null;

			// recorro las lineas, creando las palabras, agregando acepciones e insertando
			// en diccionario
			while (linea != null) {
				partes = linea.split("[ ]*\\*[ ]*");
				if (partes[0]!="") {
					nueva = new Palabra(partes[0],nlenguas);
					for (i = 1; i < nlenguas; i++) { // cambiar a cantidad de lenguas?
						if (partes[i]!="")
							nueva.agregaAcepcion(partes[i], lenguas[i]);
					}
					insertaPalabra(new Palabra(partes[0],partes.length-1));
				}
				linea = lectura.readLine();
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (fichero != null)
					fichero.close();
				if (lectura != null)
					lectura.close();
			} catch (IOException ex) {
				System.out.println(ex);
			}
		}
	}
	
	// inserta la palabra en dicc y diccord, redimensionar si necesario
	// si la lengua ya existe, anadir a acepciones
	public boolean insertaPalabra(Palabra p) {
		boolean exito = false;
		if (p!=null) {
			// comprobar si existe
			for(int i = 0; i < dicc.length; i++) {
				if (dicc[i]!=null)
					if (dicc[i].getOrigen().equalsIgnoreCase(p.getOrigen())) {
						// existe, anadir las acepciones
						if (dicc[i].mezclarAcepciones(p))
							exito = true;
					}
			}
			
			if (exito) { // mezclar en diccord
				for(int i = 0; i < diccord.length; i++) {
					if (diccord[i]!=null)
						if (diccord[i].getOrigen().equalsIgnoreCase(p.getOrigen())) {
							diccord[i].mezclarAcepciones(p);
						}
				}
				return exito;
			}
			
			// si llegamos aqui, es que no existe previamente. se aumenta length si necesario
			if (dicc[dicc.length-1]!=null) {
				Palabra[] aux = new Palabra[dicc.length+10];
				for(int i = 0; i < dicc.length; i ++)
					aux[i] = dicc[i];
				dicc = aux;
			}
			
			if (diccord[diccord.length-1]!=null) {
				Palabra[] aux = new Palabra[diccord.length+10];
				for(int i = 0; i < diccord.length; i ++)
					aux[i] = diccord[i];
				diccord = aux;
			}
			
			// finalmente se anade la palabra
			for (int i = 0; i < dicc.length; i++) {
				if (dicc[i]==null) {
					dicc[i]=p;
				}
			}
			for (int i = 0; i < diccord.length && !exito; i++) {
				if (diccord[i]!=null)
					// si la nueva palabra va antes de la actual
					// debo insertarla aqui
					if (p.getOrigen().compareToIgnoreCase(diccord[i].getOrigen()) < 0) {
						for (int j = diccord.length-1; j>=i; j--) {
							if (diccord[j]!=null) {
								diccord[j+1] = diccord[j];
							}
						}
						diccord[i] = p;
						exito = true;
					}
			}
		}
		return exito;
	}
	
	// elimina s de ambos diccionarios, y retrocede las demas palabras una posicion
	public boolean borraPalabra(String s) {
		if (s!=null) {
			int posDicc = -1, posDiccOrd = -1;
			for(int i = 0; i < dicc.length; i++)
				if (dicc[i]!=null && dicc[i].getOrigen().equalsIgnoreCase(s)) {
					// encontrada en dicc
					posDicc = i;
				}
				
			for(int i = 0; i < diccord.length; i++)
				if (diccord[i]!=null && diccord[i].getOrigen().equalsIgnoreCase(s)) {
					// encontrada en diccord
					posDiccOrd = i;
				}
				
			if (posDiccOrd > -1 && posDicc > -1) {
				// esta en ambas, eliminar y retroceder
				dicc[posDicc] = null;
				diccord[posDiccOrd] = null;
				
				for(int i = posDicc; i < dicc.length; i++)
					if (dicc[i] == null && dicc[i+1]!=null) {
						dicc[i] = dicc[i+1];
						dicc[i+1] = null;
					}
				
				for(int i = posDiccOrd; i < diccord.length; i++)
					if (diccord[i] == null && diccord[i+1]!=null) {
						diccord[i] = diccord[i+1];
						diccord[i+1] = null;
					}
			}
		}
		
		return false;
	}

	// numero de comparaciones en dicchasta encontrar origen s
	public int busqueda(String s) {
		if (s!=null) {
			int n = 0;
			
			for (Palabra p : dicc) {
				n++;
				if (p!=null)
					if (p.getOrigen().equalsIgnoreCase(s))
						return n;
			}
		}
		
		return -1;
	}

	// definir algoritmo de busqueda:
	// utilizo el sistema de la busqueda binaria. como se que el array ya esta
	// ordenado, puedo comparar el valor que busco a partir del centro del array.
	// si es menor, voy acotando hacia abajo, si es mayor hacia arriba, hasta encontrar
	// la posicion que busco
	public int busquedaOptima(String s) {
		int n = diccord.length;
		int comp = 0;
		int centro,inf=0,sup=n-1;
		while(inf<=sup){
			comp++;
			centro=(sup+inf)/2;
			if(diccord[centro].getOrigen().compareToIgnoreCase(s)==0) return comp;
			else if(s.compareToIgnoreCase(diccord[centro].getOrigen()) < 0 ){
				sup=centro-1;
			}
			else inf=centro+1;
	   }
	   return -1;
	}

	public String traduce1(String s, char l) {
		return null;
	}

	public String traduce2(String s, char l) {
		return null;
	}

	public void muestraDiccA(int i) {

	}

	public void muestraDiccA(int i, int j) {

	}

	public void muestraDiccA(int i, int j, char l) {

	}
}
