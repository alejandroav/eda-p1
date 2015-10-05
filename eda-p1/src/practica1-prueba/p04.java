/**
* @author Alicia Garrido Alenda
*
* Se crea un objeto de tipo DiccA, se invoca su metodo leeDiccA
* con un diccionario de unas 50 palabras, sin repetidas y algunas
* palabras no tienen acepciones en algun idioma, que aparecen 
* posteriormente y se agregan. Se invoca muestraDiccA para que lo
* muestre completo en orden alfabetico.
*/
public class p04 {
  public static void main(String[] args){
    DiccA diccio=new DiccA();
    if(args.length>=1){
      diccio.leeDiccA(args[0]);
      diccio.muestraDiccA(1);
    }
    else
     System.out.println("Forma uso: java p04 p04.dic");
    
  }
}
