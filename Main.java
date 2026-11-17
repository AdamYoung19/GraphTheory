import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;
import java.io.PrintWriter;


public class Main {

    public static void main(String[] args) {
        try {
            PrintWriter out = new PrintWriter("output.txt");


            Scanner scanner = new Scanner(new File("input.txt"));
            ArrayList<String> lines = new ArrayList<>();

            // Read all lines to see all vertices
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) lines.add(line);
        }
         scanner.close();

         int numVertices = lines.size();

         //simple 2d array for adjacency matrix;
         int[][] adjMat = new int[numVertices][numVertices];

         //populate adjacency matrix
         for (String line : lines) {
            String[] parts = line.split(":");
            char fromVertex = parts[0].charAt(0);
            int u = fromVertex - 'a';


            if (parts.length > 1 && !parts[1].isEmpty()) {
                String[] neighbors = parts[1].split(",");
                for (String n : neighbors) {
                    char toNode = n.charAt(0);
                    int v = toNode - 'a'; //column index

                    adjMat[u][v] = 1; //edge has 1
                }
            }            
      }

      //Output matrix build
      out.println();
      out.println("Adjacency Matrix: (undirected)");
      out.print(" ");
      for (int i = 0; i < numVertices; i++) {
          out.print((char)('a' + i));
      }
      out.println();

      for (int i = 0; i < numVertices; i++) {
        out.print((char)('a' + i) + " ");
        for (int j = 0; j < numVertices; j++) {
            out.print(adjMat[i][j]);
        }
        out.println();
      }

    //----------------BFS----------------------------------
    out.println();
    out.print("BFS: ");
    boolean[] visited = new boolean[numVertices];
    Queue<Integer> queue = new LinkedList<>();

    int startVertex = 0; //start at a
    visited[startVertex] = true;
    queue.add(startVertex);

    while (!queue.isEmpty()) {

        //pop first element from queue
        int current = queue.poll();

        //convert int back to char
        out.print((char) (current + 'a'));

        //find unvisited neighbords and add to queue
        for (int i = 0; i < numVertices; i++) {
            if (adjMat[current][i] == 1 && !visited[i]) {
                visited[i] = true;
                queue.add(i);
            }
        }
    }
        out.println();


    // -----------------DFS----------------------------------
    out.print("DFS: ");
    boolean[] visitedDFS = new boolean[numVertices];
    Stack<Integer> stack = new Stack<>();

    
    visitedDFS[startVertex] = true;
    stack.push(startVertex);

    while (!stack.isEmpty()) {

        //pop first element from stack
        int current = stack.pop();

        //convert int back to char
        out.print((char) (current + 'a'));

        //find unvisited neighbords and add to stack
        for (int i = 0; i < numVertices; i++) {
            if (adjMat[current][i] == 1 && !visitedDFS[i]) {
                visitedDFS[i] = true;
                stack.push(i);
            }
        }
    }

    out.println();

    // ------Weighted Graphs-----------------

    Scanner scannerWeighted = new Scanner(new File("input_weights.txt"));
    ArrayList<String> linesWeighted = new ArrayList<>();

    while (scannerWeighted.hasNextLine()) {
        String line = scannerWeighted.nextLine().trim();
        if (!line.isEmpty()) linesWeighted.add(line);
    }

    scannerWeighted.close();

    int[][] adjMatWeighted = new int[numVertices][numVertices];

    for (String line : linesWeighted) {
        String[] parts = line.split(":");
        char fromNode = parts[0].charAt(0);
        int u = fromNode - 'a'; //row index

        //if theres neighbors, split and get weights

        if (parts.length > 1 && !parts[1].isEmpty()) {
            String[] neighbors = parts[1].split(",");

            for (String n : neighbors) {
                String[] edgeParts = n.split("_");
                char toNode = edgeParts[0].charAt(0);
                int weight = Integer.parseInt(edgeParts[1]);

                int v = toNode - 'a'; // column index
                adjMatWeighted[u][v] = weight; //set weight in adjacency matrix

            }
        }
    }

    // Print for weighted matrix
    out.println();
    out.println("Adjacency Matrix (directed w/weights):");
    out.print(" ");
    for (int i = 0; i < numVertices; i++) out.print((char)('a' + i));
    out.println();

    for (int i = 0; i < numVertices; i++) {
        out.print((char)('a' + i) + " ");
        for (int j = 0; j < numVertices; j++) {
            out.print(adjMatWeighted[i][j]);
        }
        out.println();
    }


    //-----------------Dijkstra's Algorithm-----------------
    

    int[] distances = new int[numVertices];
    boolean[] vistedDijkstra = new boolean[numVertices];

    //Set all distances to infinity
    for (int i = 0; i < numVertices; i++) distances[i] = Integer.MAX_VALUE;
   
    //Set cost of vertex a to 0
    distances[startVertex] = 0; 

    //visit all vertex
    for (int count = 0; count < numVertices; count++) {
        int minDistance = Integer.MAX_VALUE;
        int u = -1;

        for (int i = 0; i < numVertices; i++) {
            if (!vistedDijkstra[i] && distances[i] <= minDistance) {
                minDistance = distances[i];
                u = i;
            }
        }

        //mark picked vertex as visited
            if (u != -1){
                vistedDijkstra[u] = true;
                for (int v = 0; v < numVertices; v++){
                    if (!vistedDijkstra[v] && adjMatWeighted[u][v] > 0
                     && distances[u] != Integer.MAX_VALUE 
                     && distances[u] + adjMatWeighted[u][v] < distances[v]) {
                    distances[v] = distances[u] + adjMatWeighted[u][v];
                }
            }
        }
    }

    // Print Dijkstra

    out.println();
    out.print("Dijkstra's: ");
    for (int i = 0; i < numVertices; i++) {
        out.print((char)('a' + i) + ":" + distances[i]);
        if (i < numVertices - 1) out.print(",");
    }
    out.println();

    out.close();

    System.out.println("Succesfful run, check output.txt");

    }catch (Exception e) {
        e.printStackTrace();
    }
    }
    
}
