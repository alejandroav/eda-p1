public class p03 {
  public static void main(String[] args){
    DiccA diccio=new DiccA();
    args[0] = "p03.dic";
    if(args.length>=1){
    	diccio.leeDiccA(args[0]);
    	diccio.muestraDiccA(0);
    }
    else
     System.out.println("Forma uso: java p03 p03.dic");
    
  }
}
