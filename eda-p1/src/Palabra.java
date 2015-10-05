// DNI 45928098 ALARCON VILLENA, ALEJANDRO
public class Palabra {

	private String origen;
	private Traduccion[] trad;

	// constructor con palabra origen y cantidad de traducciones
	public Palabra(String p, int nl) {
		if (p != null)
			origen = p;

		if (nl < 1)
			nl = 3;

		trad = new Traduccion[nl];
	}

	// almaceno t en la primera posicion libre si no hay traduccion de esta
	// lengua y hay posicion libre. si ya hay, se anade como nueva. devuelve la posicion o -1
	public int setTrad(Traduccion t, char l) {
		for (int i = 0; i < trad.length; i++)
			if (trad[i] != null)
				if (trad[i].getIdioma() == l) {
					// almacenar en la lengua existente
					for(int j = 0; j < t.acepciones(); j++)
						trad[i].anadir(t.getAcepcion(j));
					
					return i;
				}

		for (int i = 0; i < trad.length; i++)
			if (trad[i] == null) {
				trad[i] = t;
				return i;
			}

		return -1;
	}

	// devuelve el origen
	public String getOrigen() {
		return origen;
	}

	// devuelve la primera acepcion del idioma l
	public String getTraduccion(char l) {
		for (int i = 0; i < trad.length; i++) {
			if (trad[i] != null)
				if (trad[i].getIdioma() == l) {
					return trad[i].getAcepcion(0);
				}
		}

		return null;
	}

	// recorro las traducciones en busca de mi idioma
	// si lo encuentro, recorro cada acepcion y construyo el string
	public String getTraducciones(char l) {
		String aceps = null;
		String[] temp = null;

		// busco el objeto traduccion del idioma l
		for (int i = 0; i < trad.length && temp == null; i++)
			if (trad[i] != null)
				if (trad[i].getIdioma() == l) {
					temp = trad[i].getAcepciones();
				}

		// compruebo cada acepcion. si son la ultima o la siguiente es null, la
		// anado
		// si no, la anado y la sigo con /
		if (temp != null) {
			aceps = "";
			for (int j = 0; j < temp.length; j++)
				if (temp[j] != null)
					if (j == temp.length - 1 || temp[j + 1] == null) {
						aceps += temp[j];
						return aceps;
					} else
						aceps += temp[j] + "/";
		}

		return aceps;
	}

	// busco el idioma en mis traducciones. si esta y la nueva acepcion no
	// existe la anado. si no esta y hay sitio, creo una traduccion nueva
	// y le agrego la acepcion
	// esquema: palabra -> traduccion -> acepcion
	public boolean agregaAcepcion(String s, char l) {
		if (s!=null) {
			for (int i = 0; i < trad.length; i++)
				if (trad[i] != null)
					if (trad[i].getIdioma() == l) {
						return trad[i].anadir(s);
					}
			
			// si llegamos aqui es que no exsite esa lengua, se crea
			for (int i = 0; i < trad.length; i++)
				if (trad[i]==null) {
					trad[i] = new Traduccion(l);
					trad[i].anadir(s);
					return true;
				}
		}
		return false;
	}

	// escribir origen:acepcion/acepcion:acepcion/acepcion...
	public void escribeInfo() {
		String s = origen;

		for (int i = 0; i < trad.length; i++) {
			if (trad[i] != null) {
				s += ":";
				s += getTraducciones(trad[i].getIdioma());
			}
		}

		System.out.println(s);
	}
	
	public Traduccion[] getTraducciones() {
		return trad;
	}
	
	// compara las traducciones de dos palabras iguales y anade las nuevas a la original
	public boolean mezclarAcepciones(Palabra p) {
		boolean exito = false;
		
		if (p!=null) {
			Traduccion[] nuevas = p.getTraducciones();
			if (nuevas!=null)
				for(int i = 0; i < trad.length; i++)
					for(int j = 0; j < nuevas.length; j++)
						if (trad[i]!=null && nuevas[j]!=null) {
							if (setTrad(nuevas[j],nuevas[j].getIdioma()) != -1)
								exito = true;
						}
			
							/*if (trad[i].getIdioma() == nuevas[j].getIdioma())
								/*for(int x = 0; x < nuevas[j].acepciones(); x++)
									if (trad[i].anadir(nuevas[j].getAcepcion(x)))
										exito = true;*/
		}
		
		return exito;
	}
}