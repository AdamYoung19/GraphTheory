import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;


public class Main {

    public static void main(String[] args) {
        try {
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

      //Test matrix build

    //   System.out.println("Adjacency Matrix:");
    //   System.out.print(" ");
    //   for (int i = 0; i < numVertices; i++) {
    //       System.out.print((char)('a' + i));
    //   }
    //   System.out.println();

    //   for (int i = 0; i < numVertices; i++) {
    //     System.out.print((char)('a' + i) + " ");
    //     for (int j = 0; j < numVertices; j++) {
    //         System.out.print(adjMat[i][j]);
    //     }
    //     System.out.println();
    //   }

    //----------------BFS----------------------------------
    
    System.out.print("BFS: ");
    boolean[] visited = new boolean[numVertices];
    Queue<Integer> queue = new LinkedList<>();

    int startVertex = 0; //start at a
    visited[startVertex] = true;
    queue.add(startVertex);

    while (!queue.isEmpty()) {

        //pop first element from queue
        int current = queue.poll();

        //convert int back to char
        System.out.print((char) (current + 'a'));

        //find unvisited neighbords and add to queue
        for (int i = 0; i < numVertices; i++) {
            if (adjMat[current][i] == 1 && !visited[i]) {
                visited[i] = true;
                queue.add(i);
            }
        }
    }
    System.out.println();


    // -----------------DFS----------------------------------

    System.out.print("DFS: ");
    boolean[] visitedDFS = new boolean[numVertices];
    Stack<Integer> stack = new Stack<>();

    
    visitedDFS[startVertex] = true;
    stack.push(startVertex);

    while (!stack.isEmpty()) {

        //pop first element from stack
        int current = stack.pop();

        //convert int back to char
        System.out.print((char) (current + 'a'));

        //find unvisited neighbords and add to stack
        for (int i = 0; i < numVertices; i++) {
            if (adjMat[current][i] == 1 && !visitedDFS[i]) {
                visitedDFS[i] = true;
                stack.push(i);
            }
        }
    }

    System.out.println();

    }catch (Exception e) {
        e.printStackTrace();
    }



    


    }
    
}
