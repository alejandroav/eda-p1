/**
* @author Alicia Garrido Alenda
*
* Se crea un objeto de tipo Palabra pasando un numero valido por
* parametro al constructor. Se agregan mas acepciones de las posibles,
* alguna repetida, a alguno de los idiomas. Se invoca su metodo escribeInfo.
*/

public class p02 {
  public static void main(String[] args){
    String lexeme=new String("soul");
    Palabra word=new Palabra(lexeme,4);
    String[] meaning=new String[10];
    char[] language=new char[10];
    meaning[0]=new String("coeur");
    language[0]='F';
    meaning[1]=new String("anima");
    language[1]='I';
    meaning[2]=new String("ziel");
    language[2]='H';
    meaning[3]=new String("alma");
    language[3]='E';
    meaning[4]=new String("conciencia");
    language[4]='E';
    meaning[5]=new String("esencia");
    language[5]='E';
    meaning[6]=new String("conciencia");
    language[6]='E';
    meaning[7]=new String("psique");
    language[7]='E';
    meaning[8]=new String("voluntad");
    language[8]='E';
    meaning[9]=new String("psiquis");
    language[9]='E';
    boolean flag=false;
    for(int i=0;i<language.length;i++){
     flag=word.agregaAcepcion(meaning[i],language[i]);
     System.out.println("traduccion a "+language[i]+"; acepcion "+meaning[i]+" -> "+flag);
    }
    word.escribeInfo();
  }
}
