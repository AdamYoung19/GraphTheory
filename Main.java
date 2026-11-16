import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;



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


            if (part.length > 1 && !parts[1].isEmpty()) {
                String[] neighbors = parts[1].split(",");
                for (String n : neighbors) {
                    char toNode = n.charAt(0);
                    int v = toNode - 'a'; //column index
                    
                    adjMat[u][v] = 1; //edge has 1
                }
            }
            
            
      }
    }

       
    
}
