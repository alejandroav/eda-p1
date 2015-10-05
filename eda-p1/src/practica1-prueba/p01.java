/**
* @author Alicia Garrido Alenda
*
* Se crea un objeto de tipo Palabra pasando un numero no valido por
* parametro al constructor. Se agregan acepciones para mas idiomas
* de los posibles. Se invoca su metodo escribeInfo.
*/
public class p01 {
  public static void main(String[] args){
    String lexeme=new String("workplace");
    Palabra word=new Palabra(lexeme,0);
    String[] meaning=new String[4];
    char[] language=new char[4];
    meaning[0]=new String("lugar de trabajo");
    language[0]='E';
    meaning[1]=new String("lieu de travail");
    language[1]='F';
    meaning[2]=new String("local de trabalho");
    language[2]='P';
    meaning[3]=new String("sul posto di lavoro");
    language[3]='I';
    boolean flag=false;
    for(int i=0;i<language.length;i++){
     flag=word.agregaAcepcion(meaning[i],language[i]);
     System.out.println("traduccion a "+language[i]+"; acepcion "+meaning[i]+" -> "+flag);
    }
    word.escribeInfo();
  }
}
