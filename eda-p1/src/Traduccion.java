// DNI 45928098 ALARCON VILLENA, ALEJANDRO
public class Traduccion {

	private char idioma; // el idioma de las acepciones
	private String[] trad; // las acepciones

	// creo el objeto con su idioma y la primera acepcion
	public Traduccion(char i) {
		idioma = i;
		trad = new String[5];
	}

	// este metodo sirve para modificar la traduccion si se anaden
	// nuevas para el mismo idioma, maximo 5. si ya existe se ignora
	public boolean anadir(String s) {
		if (s != null) {
			for (int i = 0; i < trad.length; i++)
				if (trad[i] != null)
					if (trad[i].equalsIgnoreCase(s)) // compruebo si la palabra ya esta, ignorando case
						return false;
			
			for (int i = 0; i < trad.length; i++) // la anado en el primer hueco vacio si lo hay
				if (trad[i] == null) {
					trad[i] = s;
					return true;
				}
		}
		return false;
	}

	// devuelve el idioma de las acepciones
	public char getIdioma() {
		return idioma;
	}

	// devuelve una de las acepciones
	public String getAcepcion(int i) {
		if (i >= trad.length || i < 0)
			return null;
		else
			return trad[i];
	}

	// devuelve el array de acepciones
	public String[] getAcepciones() {
		return trad;
	}

	// establece el idioma de la traduccion
	public void setIdioma(char l) {
		idioma = l;
	}

	// devuelve la cantidad de acepciones
	public int acepciones() {
		int n = 0;
		for (int i = 0; i < trad.length; i++)
			if (trad[i] != null)
				n++;
		return n;
	}
	
	// devuelve la cantidad maxima de acepciones
	public int length() {
		return trad.length;
	}
}