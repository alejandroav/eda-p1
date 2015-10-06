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
			partes = linea.split("[ ]");

			lenguas = new char[nlenguas];
			
			for (int i = 0; i < lenguas.length; i++) {
				lenguas[i] = partes[i].charAt(0);
			}
			
			Palabra nueva = null;
			String[] aux = null;
			
			linea = lectura.readLine();

			// recorro las lineas, creando las palabras, agregando acepciones e insertando
			// en diccionario
			
			while (linea != null) {
				partes = linea.split("[ ]*\\*[ ]*");				
				if (!partes[0].equals("")) {
					nueva = new Palabra(partes[0],nlenguas);
					for (int i = 1; i<partes.length && i <= nlenguas; i++) {
						//if (!partes[i].equals("")) {
							aux = partes[i].split("/");
							//if (aux!=null) {
								for (int j = 0; j < aux.length; j++)
									if (!aux[j].equals("")) {
										nueva.agregaAcepcion(aux[j], lenguas[i-1]);
									}
							/*}
							else {
								nueva.agregaAcepcion(partes[i], lenguas[i-1]);
								System.out.println("hola, esta solo tiene una acepcion");
							}*/
						//}
					}
					
					insertaPalabra(nueva);
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
			
			// si llegamos aqui, es que no existe previamente.
			// se aumenta length si necesario
			boolean aumentar = false;
			if (dicc[dicc.length-1]!=null) {
				aumentar = true;
				Palabra[] aux = new Palabra[dicc.length+10];
				for(int i = 0; i < dicc.length; i ++) {
					aux[i] = dicc[i];
				}
				dicc = aux;
			}
			
			if (aumentar) {
				Palabra[] aux = new Palabra[diccord.length+10];
				for(int i = 0; i < diccord.length; i ++)
					aux[i] = diccord[i];
				diccord = aux;
			}
			
			// finalmente se anade la palabra
			boolean insertado = false;
			for (int i = 0; i < dicc.length && !insertado; i++) {
				if (dicc[i]==null) {
					dicc[i]=p;
					insertado = true;
				}
			}
			
			exito=false;

			if (diccord[0]==null) {
				diccord[0] = p;
				exito = true;
			}
			
			else
				for (int i = 0; i < diccord.length && !exito; i++) {
					if (diccord[i]!=null) {
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
					else {
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

	// numero de comparaciones en dicc hasta encontrar origen s
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

	// devuelve la primera traduccion de s a la lengua l
	public String traduce1(String s, char l) {
		if (s!=null) {
			 for (Palabra p : dicc) {
				 if (p!=null && p.getOrigen().equalsIgnoreCase(s)) {
					 		p.getTraduccion(l);
				 }
			 }
		 }
		return null;
	}
	
	// devuelve todas las traducciones a l de s
	public String traduce2(String s, char l) {
		if (s!=null) {
			 for (Palabra p : dicc) {
				 if (p!=null && p.getOrigen().equalsIgnoreCase(s)) {
					 		p.getTraducciones(l);
				 }
			 }
		}
		return null;
	}

	// muestra escribeInfo para 0: dicc o 1: diccord
	public void muestraDiccA(int i) {
		if (i==0) {
			for (Palabra p : dicc) {
				if (p!=null) {
					p.escribeInfo();
				}
			}
		}
		
		if (i==1) {
			for (Palabra p : diccord) {
				if (p!=null)
					p.escribeInfo();
			}
		}
	}

	// como el anterior pero solo para las j primeras palabras
	public void muestraDiccA(int i, int j) {
		if (j>0) {
			if (i==0) {
				for (int n = 0; n < j; n++) {
					if (dicc[n]!=null)
						dicc[n].escribeInfo();
				}
			}
			
			if (i==1) {
				for (int n = 0; n < j; n++) {
					if (diccord[n]!=null)
						diccord[n].escribeInfo();
				}
			}
		}
	}

	// muestra las j primeras lineas en formato origen:traduccionLengua l
	public void muestraDiccA(int i, int j, char l) {
		if (j > 0) {
			if (i == 0) {
				for (int n = 0; n < j; n++) {
					if (dicc[n]!=null) {
						System.out.print(dicc[n].getOrigen());
						System.out.print(":");
						System.out.println(dicc[n].getTraducciones(l));
					}
				}
			}
			if (i == 1) {
				for (int n = 0; n < j; n++) {
					if (diccord[n]!=null) {
						System.out.print(diccord[n].getOrigen());
						System.out.print(":");
						System.out.println(diccord[n].getTraducciones(l));
					}
				}
			}
		}
	}
	
	// devuelve el array de lenguas
	public char[] getLenguas() {
		return lenguas;
	}
} 