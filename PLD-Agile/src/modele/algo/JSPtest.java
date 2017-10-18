package modele.algo;

public class JSPtest {

	public static void main(String[] args){
		test4();
	}
	
	public static void test1(){
		TSP1 tsp = new TSP1();
		
		float[][] cout = {{0,1,2,1,4}, //distances du node 0 vers les nodes i
						{3,0,3,4,3},
						{2,4,0,5,6},
						{1,4,5,0,2},
						{3,3,6,1,0}
						};
		System.out.println("test, de 0 à 1 : "+cout[0][1]);
		int[] duree = {2,1,2,3,1};
		int tpsLimite = 10000;
		int nbSommets = 5;
		tsp.chercheSolution(tpsLimite, nbSommets, cout, duree);
		
		System.out.println("temps de la solution : "+tsp.getCoutMeilleureSolution());
		System.out.print("Chemin de la solution : ");
		for(int i=0;i<nbSommets;i++){
			System.out.print(tsp.getMeilleureSolution(i)+" ");
		}
	}
	
	public static void test2(int nbSommets){
		TSP1 tsp = new TSP1();
		float[][] cout = new float[nbSommets][nbSommets];
		int[] duree = new int[nbSommets];
		for(int i=0;i<nbSommets;i++){
			duree[i] = (int)(Math.random()*5);
			for(int j=0;j<nbSommets;j++){
				cout[i][j] = (float)(Math.random()*5);
			}
		}
		
		int tpsLimite = 30000;
		long delay = System.currentTimeMillis();
		tsp.chercheSolution(tpsLimite, nbSommets, cout, duree);
		delay = System.currentTimeMillis() - delay;
		
		System.out.println("Nombre de sommets : "+nbSommets+"  Temps mis : "+delay+"ms");
		System.out.println("temps de la solution : "+tsp.getCoutMeilleureSolution());
		System.out.print("Chemin de la solution : ");
		for(int i=0;i<nbSommets;i++){
			System.out.print(tsp.getMeilleureSolution(i)+" ");
		}
	}
	
	public static void test3(){
		Dijkstra dijkstra = new Dijkstra();
		Float[][] graph = {{null,(float) 3,null,(float) 1,null,(float) 9,null},
							{null,null,(float) 2,null,(float) 8,null,null},
							{null,null,null,null,null,null,null},
							{null,null,null,null,null,null,(float) 3},
							{null,(float) 4,null,null,null,null,(float) 5},
							{null,null,null,null,(float) 1,null,null},
							{null,null,null,null,null,null,null}
							};
		int source = 0;
		DjkSolution result = dijkstra.PCC(graph, source);
		System.out.println("   dist  prev");
		for(int i =0; i<7;i++){
			System.out.println(i+" : "+result.dist[i]+"     "+result.prev[i]);
		}
	}
	
	public static void test4(){
		TSP1 tsp = new TSP1();
		Dijkstra dijkstra = new Dijkstra();
		Float[][] graph = {{null,(float) 3,null,(float) 1,null,(float) 9,null},
							{null,null,(float) 2,null,(float) 8,null,null},
							{null,null,null,null,(float) 3,null,null},
							{null,null,(float) 2,null,null,null,(float) 3},
							{null,(float) 4,null,null,null,null,(float) 5},
							{null,null,null,null,(float) 1,null,null},
							{(float) 1,null,null,(float) 3,null,null,null}
							};
		int[] duree = {2,1,2,3,1,4,3};
		float[][] cout = new float[7][7];
		
		for(int i=0; i<7; i++){
			DjkSolution result = dijkstra.PCC(graph, i);	
			
			///////////////////////////
			System.out.println("--"+i+"--");
			System.out.println("   dist  prev");
			for(int k =0; k<7;k++){
				System.out.println(k+" : "+result.dist[k]+"     "+result.prev[k]);
			}
			System.out.println();
			///////////////////////////
			
			for(int j=0; j<7; j++){
				cout[i][j]=result.dist[j];
			}
		}
		
		int tpsLimite = 10000;
		tsp.chercheSolution(tpsLimite, 7, cout, duree);
		
		System.out.println("temps de la solution : "+tsp.getCoutMeilleureSolution());
		System.out.print("Chemin de la solution : ");
		for(int i=0;i<7;i++){
			System.out.print(tsp.getMeilleureSolution(i)+" ");
		}
	}
	
}
