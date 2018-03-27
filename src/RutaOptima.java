import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RutaOptima {
	public static int input1;
	public static int input2;
	public static InputStreamReader buffer1;
	public static BufferedReader buffer2;
	int nodos = 10;
	public static int matrizOriginal[][]=new int[10][10];
	public static int [][] matrizA = {
			{-1,106,60,32,46,116,60,39,105,100}, 
			{120,-1,76,106,118,31,66,131,108,78},
			{117,52,-1,91,36,54,102,68,99,38},
			{46,122,135,-1,128,129,110,121,37,115},
			{83,48,119,97,-1,103,133,43,122,47},
			{143,100,87,138,150,-1,49,71,98,99},
			{86,94,105,97,60,145,-1,33,138,114},
			{41,42,127,57,47,70,71,-1,45,144},
			{75,140,67,46,61,46,125,148,-1,49},
			{144,38,116,109,102,120,128,64,125,-1},
			};

	  List conjuntoYaVisitados = new ArrayList();
	  List conjuntoSinVisitar = new ArrayList();
	  List resultados = new ArrayList();
	  String tmp;

	  private void resolverMatriz(int nodoorigen,int nododestino){
	    int nod;
	    int minimo;
	    int aux;
	    int nodCambio=0;
	    int intento;
	    String tmp2;
	    for(int i=0;i<nodos;i++){
	      if(i!=nodoorigen)
	        conjuntoSinVisitar.add(""+i);
	      else
	        conjuntoYaVisitados.add(""+i);
	        resultados.add("");
	    }
	  
	    for(int i=0;i<nodos;i++){
	      minimo=-1;
	      for(int j=0;j<conjuntoSinVisitar.size();j++){
	        nod=Integer.valueOf((String)(conjuntoSinVisitar.get(j))).intValue();
	        aux=minimo(nod);
	        if(minimo==-1 || (aux<minimo && aux!=-1)){
	          minimo=aux;
	          nodCambio=j;
	        }
	      }
	      if(minimo!=-1){
	        conjuntoYaVisitados.add(""+(String)(conjuntoSinVisitar.get(nodCambio)));
	        conjuntoSinVisitar.remove(nodCambio);
	      }
	    }
	    System.out.print("\nEl viajero debe pasar por las siguientes ciudades: ");
	    for(int k=0;k<resultados.size();k++)
	      if(k!=nodoorigen){
	        tmp=(String)(resultados.get(k))+(char)(k+65);
	        resultados.set(k,tmp);
	      }
	    for(int j=0;j<resultados.size();j++)
	      if(j!=nodoorigen){
	        intento=0;
	        tmp=(String)(resultados.get(j));
	          while(tmp.charAt(0)!=(char)(nodoorigen+65) && intento<10){
	            aux=tmp.charAt(0)-65;
	            tmp=((String)(resultados.get(aux)))+tmp.substring(1,tmp.length());
	            if(++intento==10)
	              tmp="*"+tmp;
	          };
	          if (j==nododestino){
	        	  imprimirResultado(tmp,j,nodoorigen);
	          }
	      }
	    
	  }

	  private int minimo(int dest){
	    int min=-1;
	    int nodo=0;
	    int nodOrigen=-1;
	    int aux;
	    for(int i=0;i<conjuntoYaVisitados.size();i++){
	      nodo=Integer.valueOf((String)(conjuntoYaVisitados.get(i))).intValue();
	      if(matrizA[nodo][nodo]!=-1 && matrizA[nodo][dest]!=-1)
	        aux=matrizA[nodo][nodo]+matrizA[nodo][dest];
	      else
	        aux=-1;
	      if((aux<min && aux!=-1)||min==-1){
	        min=aux;
	        nodOrigen=nodo;
	      }
	    }
	    if(min!=-1){
	      matrizA[dest][dest]=min;
	      resultados.set(dest,""+(char)(nodOrigen+65));
	    }
	    return min;
	  }

	  private void imprimirResultado(String res, int nod, int o){
		  int ant =0;
		  int act = 0;
		  System.out.print("\n");
	      for(int i=0;i<res.length();i++){
	    	  act=(res.charAt(i)-65);
	    	  System.out.print(""+(res.charAt(i)));
	        if (i>0){
	        	//System.out.print("anterior: "+ant + "actual: " + act);
	        	System.out.print("("+matrizOriginal[ant][act]+")");
	        }
	        System.out.print((i==res.length()-1?"":"->"));
	        ant = act;
	      }
	      System.out.print("\nCosto Total del Recorrido: "+matrizA[nod][nod]+"\n");
	    
	  }
	  public static void main(String args[]){
		  try {
		  RutaOptima ruta = new RutaOptima();
		  buffer1 = new InputStreamReader(System.in);
		  buffer2 = new BufferedReader(buffer1);
		  System.out.print("=========================MATRIZ PRECARGADA=====================\n");
		  ruta.imprimirMatriz(matrizA);
		  System.out.print("\n==============================================================");
	        System.out.print("\nIntroduzca la letra del nodo origen: ");
	        input1=((int)((buffer2.readLine()).toUpperCase()).charAt(0))-65;
	        System.out.print("Introduzca la letra del nodo destino: ");
	        input2=((int)((buffer2.readLine()).toUpperCase()).charAt(0))-65;
	        if (input1==input2){
	        	System.out.print("\nEl viajero no debe moverse, se encuentra en la misma ciudad a la cual se dirige");
	        	
	        }else{
		        ruta.matrizA[input1][input1]=0;
		        //ruta.imprimirMatriz(matrizA);
		        ruta.duplicarMatriz(matrizA,matrizOriginal);
		        ruta.resolverMatriz(input1,input2);
	        }

	       // ruta.imprimirMatriz(matrizOriginal);
		  } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	  public static void duplicarMatriz(int a[][],int b[][]){
		  int x;
		  int y;
		  for (x=0;x<a.length;x++){
			  for (y=0;y<a.length;y++){
				  b[x][y]=a[x][y];
			  } 
		  }
	  }
	  public static void imprimirMatriz(int a[][]){
		  int x;
		  int y;
		  int z;
		  System.out.print("\t");
		  for (z=0;z<a.length;z++){
			  System.out.print((char)(z+65)+"\t");
		  }
		  System.out.print("\n");
		  for (x=0;x<a.length;x++){
			  System.out.print((char)(x+65)+"\t");
			  for (y=0;y<a.length;y++){
				  System.out.print(""+a[x][y]+"\t");
			  } 
			  System.out.print("\n");
		  }
	  }
}
