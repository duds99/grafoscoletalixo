package grafos;

import java.util.ArrayList;
import java.util.Scanner;

public class Grafos{
   static int [][] grafo;
   static int numerolinhas;
   static int numerocolunas;
   static int dias;
   
   //instancia o grafo com todos valores null
   public static void grafoNull(int rotas, int vertices){
       Grafos.dias = 0;
       Grafos.numerolinhas = rotas;
       Grafos.numerocolunas = vertices;
       
        grafo = new int[rotas][vertices];
        for(int x = 0;x<rotas;x++){
           for(int y = 0;y<vertices;y++){
              grafo[x][y] = -1;
           }
        }
   }
   
   public static void geraGrafo(String rotas){
       String [] rotastrue = rotas.split("\t");

       for(int i=0;i<numerolinhas;i++){
           String aux[] = rotastrue[i].split(",");
            for(int j=0;j<numerocolunas;j++){      
                if(j < aux.length){
                    grafo[i][j] = Integer.parseInt(aux[j]);
                }
            }
       }
   }
   
   public static void mostraGrafo(){
       for(int i=0;i<numerolinhas;i++){
           for(int j=0;j<numerocolunas;j++){
               System.out.print(grafo[i][j] + " ");
           }
           System.out.println("\n");
       }
   }
   
    /*
    gera um grafo não digrafo(um grafo normal)
    @param matriz int grafo nulo
    @param string nome do arquivo de texto lido
    */
    public static void ColetaDeLixo(String rotas){
        String array[] = rotas.split("\t");
        ArrayList <Integer>arrayaux = new ArrayList<>();
        ArrayList <String>resposta = new ArrayList<>();

        for(int i=0;i<array.length;i++){
            String aux[] = array[i].split(",");
            int valores[] = new int[aux.length];
            
            for(int j=0;j<aux.length;j++){
                if(!aux[j].equals("FIM")){
                    valores[j] = Integer.parseInt(aux[j]);
                }
            }
            //System.out.println("next line");
            arrayaux = new ArrayList<>();
            
            for(int j=0;j<valores.length;j++){
               //funcionando ate aqui
               
                for(int k=i+1;k<numerolinhas;k++){
                    for(int l=0;l<numerocolunas;l++){
                        if(valores[j] == grafo[k][l]){
                            int cont = 0;
                            for(int m=0;m < arrayaux.size();m++){
                                if(arrayaux.get(m) != k){
                                    cont++;
                                }
                            }
                            if(cont == arrayaux.size()){
                                arrayaux.add(k);
                                //System.out.println(k + "\t" + l + "\tvalor =" + valores[j]);
                                resposta.add(i + "\t" + k);
                                dias++;
                            }
                            l = numerocolunas;
                        }
                    }
                }
            }
        }  
        mostrarResposta(resposta);
    }
   

    public static void mostrarResposta(ArrayList<String> resposta){
        String armazena = "";
        int conta = 1;
        int salva = -1;
        int vez = 0;

        int auxiliando[] = new int[numerolinhas];
        for(int i=0;i<numerolinhas;i++){
            auxiliando[i] = -1;
        }
        
        int repete[] = auxiliando;
        
        for(int i=0;i<resposta.size();i++){

            String bateu[] = resposta.get(i).split("\t");

            if(salva == Integer.parseInt(bateu[0])){
                auxiliando[Integer.parseInt(bateu[1])] = 1;
            }else{
                if(salva != -1 && repete[salva] == -1){
                    String armazenaaux = "" + salva;
                    repete[salva] = salva;
                    for(int j=0;j<numerolinhas;j++){
                        if(auxiliando[j] != 1 && j != salva){
                            if(repete[j] != j){
                                armazenaaux += "," + j;
                                repete[j] = j;
                            }
                        }
                    }
                    
                    if(!armazenaaux.contains(",")){
                        repete[salva] = -1;
                    }else{ 
                        armazena += armazenaaux;
                        armazena += "\t#rotas no "+ conta +"o dia\n";
                        conta++;  
                    }     
                }
                salva = Integer.parseInt(bateu[0]); 
                auxiliando = new int[numerolinhas];
                auxiliando[Integer.parseInt(bateu[1])] = 1;
                if(i == resposta.size()-1 && vez == 0){
                    vez++;
                    i--;
                }
            }
        }
        
        for(int i=0;i<numerolinhas;i++){
            //System.out.println(repete[i]);
            if(repete[i] == -1){
                armazena += i + "\t#rotas no " + conta + "o dia\n";
                conta++;
            }
        }
        
        System.out.println((conta-1) + " #dias mínimos para todas as rotas");
        System.out.print(armazena);
        
        
    }
    
   //Metodo principal,le txt e escreve o grafo
   public static void gerarGrafo(){
        Scanner ler = new Scanner(System.in);

        int grafodigrafo = Integer.parseInt(ler.nextLine());
        int numvertices = Integer.parseInt(ler.nextLine());
        String rotas = ler.nextLine() + "\t";
        String aux = rotas;
        int numrotas = 0;

        while(!aux.equals("FIM")){
           aux = ler.nextLine();
           if(!aux.equals("FIM")){
            rotas += aux + "\t";
           }
           numrotas++;
        }

        grafoNull(numrotas,numvertices); //gera grafo instanciado no global[][]
        geraGrafo(rotas);
        
        //mostraGrafo();
        
        ColetaDeLixo(rotas);
    }

    public static void main(String[]args){
       gerarGrafo();
    }
}


/*
0
8
0,1,5,0
1,3,7,2,4,1
2,6,7,2
3,7,5,0,3
4,7,5,4
FIM


0
10
1,2,5,1
5,6,4,1,5
0,9,7,0
8,6,4,3,8
FIM


0
10
1,2,5,1
5,6,4,1,5
0,9,7,0
8,6,4,3,8
6,5,1,6
9,2,7,9
FIM



String bateu[] = resposta.get(i).split("\t");
            
            if(salva == Integer.parseInt(bateu[0])){
                System.out.println("entrou");
                auxiliando[Integer.parseInt(bateu[1])] = 1;
            }else{
                //ordenar aqui 
                
                if(salva != -1){
                    System.out.print(salva);
                    
                    for(int j=0;j<numerolinhas;j++){
                        if(auxiliando[j] == -1){
                            if(j != salva){
                                System.out.print("," +j);
                            }
                        }   
                    }
                    System.out.print("\t#rotas no 1o dia\n");
                }
                
                auxiliando = new int[numerolinhas];
                salva = Integer.parseInt(bateu[0]);
                auxiliando[Integer.parseInt(bateu[1])] = 1;
            }




                if(vez == 1){
                    if(salva != -1){
                        System.out.print(salva);
                        repete[salva] = salva;
                        for(int j=0;j<numerolinhas;j++){
                            if(auxiliando[j] != 1 && j != salva){
                                if(repete[j] == -1){
                                    System.out.print("," + j);
                                    repete[j] = j;
                                }
                            }
                        }
                        System.out.println("\t#rotas no "+ conta +"o dia");
                        conta++;
                    }
                }
*/