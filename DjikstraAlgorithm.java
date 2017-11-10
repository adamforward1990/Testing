public class DjikstraAlgorithm {
	public static final int NUM_OF_VERTICES = 9;

	public static void printSolution(int shortestPathDistances[]){
		String tab = "         ";
		System.out.println();
		System.out.println(tab + " Vertex |  Shortest Distance from Source (vertex 0)");
		System.out.println(tab + "------------------------------------------------");
		for (int i = 0; i < NUM_OF_VERTICES; i++)
			System.out.println(tab + "   " + i+"    |                   "+shortestPathDistances[i]);
	}

	public static void initialize(int shortestPaths[], Boolean[] solvedVertex, int sourceVertex){
		for (int vertex = 0; vertex < NUM_OF_VERTICES; vertex++){
			shortestPaths[vertex] = Integer.MAX_VALUE;
			solvedVertex[vertex] = false;
		}
		shortestPaths[sourceVertex] = 0;
	}

	public static boolean allVerticesSolved(Boolean[] solvedVertex){
		for(Boolean vertexVisited : solvedVertex){
			if(vertexVisited == false){
				return false;
			}
		}

		return true;
	}

	public static int getNextVertexToSolve(int shortestPaths[], Boolean[] solvedVertex){
		int shortestDistanceFromSource = Integer.MAX_VALUE;
		int nextNodeToVisit = -1;

		for (int vertex = 0; vertex < NUM_OF_VERTICES; vertex++){// for every other vertex
			if (!solvedVertex[vertex]){// if vertex is red
				int distanceFromSource = shortestPaths[vertex];//get the shortest path to this vertex
				if(distanceFromSource <= shortestDistanceFromSource){
					shortestDistanceFromSource = distanceFromSource;
					nextNodeToVisit  = vertex;
				}
			}
		}
		return nextNodeToVisit ;
	}
	
	public static void updateNeighborPaths(int solvedVertex, int[][] graph, int[] shortestPaths){
		for (int v = 0; v < NUM_OF_VERTICES; v++){ // for every vertex v
			if(graph[solvedVertex][v] != 0){ //if this vertex is a neighbor
				int alternatePath = shortestPaths[solvedVertex] + graph[v][solvedVertex]; 
				if(alternatePath < shortestPaths[v]){
					shortestPaths[v] = alternatePath;
				}
			}
		}
	}

	public static int[] getAllShortestPaths(int graph[][], int sourceVertex){
		int[] shortestPaths = new int[NUM_OF_VERTICES];
		Boolean[] solvedVertex = new Boolean[NUM_OF_VERTICES];
		initialize(shortestPaths, solvedVertex, sourceVertex);

		int currentVertex;
		while(!allVerticesSolved(solvedVertex)){
			currentVertex = getNextVertexToSolve(shortestPaths, solvedVertex);
			solvedVertex[currentVertex] = true;
			updateNeighborPaths(currentVertex, graph, shortestPaths);
		}
		printSolution(shortestPaths);
		return shortestPaths;
	}
	
	public static int getShortestPath(int graph[][], int sourceVertex, int destinationVertex){
		int[] shortestPaths = new int[NUM_OF_VERTICES];
		Boolean[] verticesVisited = new Boolean[NUM_OF_VERTICES];
		initialize(shortestPaths, verticesVisited, sourceVertex);

		int currentVertex;
		while(!allVerticesSolved(verticesVisited)){
			currentVertex = getNextVertexToSolve(shortestPaths, verticesVisited);
			verticesVisited[currentVertex] = true;
			updateNeighborPaths(currentVertex, graph, shortestPaths);
			if(currentVertex == destinationVertex){
				return shortestPaths[currentVertex];
			}
		}
		return -1;
	}

	
	public static void main (String[] args){
		int graph[][] = new int[][]{
				{0, 4, 0, 0, 0, 0, 0, 8, 0},   //adjacency list for vertex 0
				{4, 0, 8, 0, 0, 0, 0, 11, 0},  //adjacency list for vertex 1
				{0, 8, 0, 7, 0, 4, 0, 0, 2},   //adjacency list for vertex 2
				{0, 0, 7, 0, 9, 14, 0, 0, 0},  //adjacency list for vertex 3
				{0, 0, 0, 9, 0, 10, 0, 0, 0},  //adjacency list for vertex 4
				{0, 0, 4, 14, 10, 0, 2, 0, 0}, //adjacency list for vertex 5
				{0, 0, 0, 0, 0, 2, 0, 1, 6},   //adjacency list for vertex 6
				{8, 11, 0, 0, 0, 0, 1, 0, 7},  //adjacency list for vertex 7
				{0, 0, 2, 0, 0, 0, 6, 7, 0}    //adjacency list for vertex 8
		};
		int sourceVertex = 0;
		//int destinationVertex = 8;
		DjikstraAlgorithm.getAllShortestPaths(graph, sourceVertex);
		//System.out.println(DjikstraAlgorithm.getShortestPath(graph, sourceVertex, destinationVertex));
	}


}
