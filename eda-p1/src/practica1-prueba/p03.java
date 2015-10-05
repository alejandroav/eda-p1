/**
* @author Alicia Garrido Alenda
*
* Se crea un objeto de tipo DiccA, se invoca su metodo leeDiccA
* con un diccionario de unas 50 palabras, sin repetidas y con
* acepciones para cada idioma. Se invoca muestraDiccA para que lo
* muestre completo en el orden leido.
*/
public class p03 {
  public static void main(String[] args){
    DiccA diccio=new DiccA();
    if(args.length>=1){
      diccio.leeDiccA(args[0]);
      diccio.muestraDiccA(0);
    }
    else
     System.out.println("Forma uso: java p03 p03.dic");
    
  }
}
