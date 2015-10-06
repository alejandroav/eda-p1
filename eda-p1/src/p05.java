/**
* @author Alicia Garrido Alenda
*
* Se crea un objeto de tipo DiccA, se invoca su metodo leeDiccA
* con un diccionario de unas 50 palabras, con alguna palabra repetida
* literalmente. Se invoca su metodo insertaPalabra con palabras que ya
* estan en el diccionario pero tienen acepciones nuevas, que no estan 
* en el diccionario o bien que son repetidas sin acepciones nuevas.
* Se invoca muestraDiccA para que lo muestre completo en orden alfabetico.
*/
public class p05 {
  /**
  * Crea un array de objetos de tipo Palabra, sin leerlos de fichero.
  * Las palabras creadas son coherentes (mismos idiomas en el mismo orden) con
  * el diccionario en el que se van a insertar.
  * @return El array creado, con un objeto en cada una de sus posiciones.
  */
  public static Palabra[] creaPalabras(){
   Palabra[] creadas=new Palabra[8];
   // P E F
   creadas[0]=new Palabra("matter",3);
   creadas[0].agregaAcepcion("assunto",'P');
   creadas[0].agregaAcepcion("materia",'E');
   creadas[1]=new Palabra("subway",3);
   creadas[1].agregaAcepcion("metrô",'P');
   creadas[1].agregaAcepcion("metro",'E');
   creadas[1].agregaAcepcion("métro",'F');
   creadas[2]=new Palabra("if",3);
   creadas[2].agregaAcepcion("se",'P');
   creadas[2].agregaAcepcion("si",'E');
   creadas[2].agregaAcepcion("si",'F');
   creadas[3]=new Palabra("basket",3);
   creadas[3].agregaAcepcion("cesto",'P');
   creadas[4]=new Palabra("cord",3);
   creadas[4].agregaAcepcion("fio",'P');
   creadas[5]=new Palabra("cord",3);
   creadas[5].agregaAcepcion("corda",'P');
   creadas[5].agregaAcepcion("cuerda",'E');
   creadas[6]=new Palabra("cord",3);
   creadas[6].agregaAcepcion("cabo",'P');
   creadas[6].agregaAcepcion("cuerda",'E');
   creadas[6].agregaAcepcion("corde",'F');
   creadas[7]=new Palabra("matter",3);
   creadas[7].agregaAcepcion("questão",'P');
   creadas[7].agregaAcepcion("asunto",'E');
   creadas[7].agregaAcepcion("affaire",'F');
   return creadas;
  }  
  public static void main(String[] args){
	args = new String[1];
	args[0] = "p05.dic";
    DiccA diccio=new DiccA();
    if(args.length>=1){
      diccio.leeDiccA(args[0]);
      System.out.println("============ DICCIONARIO LEIDO ===========");
      diccio.muestraDiccA(1);
      Palabra[] agregadas=creaPalabras();
      boolean flag=false;
      for(int i=0;i<agregadas.length;i++){
       flag=diccio.insertaPalabra(agregadas[i]);
       System.out.println(agregadas[i].getOrigen()+" insertada? -> "+flag);
      }
      System.out.println("============ DICCIONARIO MODIFICADO ===========");
      diccio.muestraDiccA(1);
    }
    else
     System.out.println("Forma uso: java p05 p05.dic");
    
  }
}
