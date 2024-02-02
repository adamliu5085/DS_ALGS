package assign08;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;


/**
 * Represents a maze using a graph.
 * Contains methods to construct a maze-graph, print a maze-graph, and find paths from start to end in a maze graph.
 *
 * @author Daniel Kopta, Michael Wadley, & Adam Liu
 */
public class Graph {

    // The graph itself is just a 2D array of nodes
    private Node[][] nodes;

    // The node to start the path finding from
    private Node start;

    // The size of the maze
    private int width;
    private int height;

	private int DFScount = 0;
	private int BFScount = 0;

    /**
     * Constructs a maze graph from the given text file.
     *
     * @param filename - the file containing the maze
     * @throws Exception
     */
    public Graph(String filename) throws Exception {
        BufferedReader input;
        input = new BufferedReader(new FileReader(filename));

        if (!input.ready()) {
            input.close();
            throw new FileNotFoundException();
        }

        // read the maze size from the file
        String[] dimensions = input.readLine().split(" ");
        height = Integer.parseInt(dimensions[0]);
        width = Integer.parseInt(dimensions[1]);

        // instantiate and populate the nodes
        nodes = new Node[height][width];
        for (int i = 0; i < height; i++) {
            String row = input.readLine().trim();

            for (int j = 0; j < row.length(); j++)
                switch (row.charAt(j)) {
                    case 'X':
                        nodes[i][j] = new Node(i, j);
                        nodes[i][j].isWall = true;
                        break;
                    case ' ':
                        nodes[i][j] = new Node(i, j);
                        break;
                    case 'S':
                        nodes[i][j] = new Node(i, j);
                        nodes[i][j].isStart = true;
                        start = nodes[i][j];
                        break;
                    case 'G':
                        nodes[i][j] = new Node(i, j);
                        nodes[i][j].isGoal = true;
                        break;
                    default:
                        throw new IllegalArgumentException("maze contains unknown character: \'" + row.charAt(j) + "\'");
                }
        }
        input.close();
    }

    /**
     * Outputs this graph to the specified file.
     * Use this method after you have found a path to one of the goals.
     * Before using this method, for the nodes on the path, you will need
     * to set their isOnPath value to true.
     *
     * @param filename - the file to write to
     */
    public void printGraph(String filename) {
        try {
            PrintWriter output = new PrintWriter(new FileWriter(filename));
            output.println(height + " " + width);
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    output.print(nodes[i][j]);
                }
                output.println();
            }
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Traverse the graph with BFS (shortest path to closest goal)
     * A side-effect of this method should be that the nodes on the path
     * have had their isOnPath member set to true.
     *
     * @return - the length of the path
     */
    public int CalculateShortestPath() {
      //  BFScount++;
        LinkedList<Node> queue = new LinkedList<>();
        start.isVisited = true;
        queue.offer(start);
        Node lastSeenNode = null;
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            BFScount++;
            // If our current node is the goal, we have completed the path.
            if (current.isGoal) {
                lastSeenNode = current;
                break;
            }

            // Populate the neighbors list for the current node
            if (current.col + 1 < width) {
                if (!nodes[current.row][current.col + 1].isWall)
                    current.neighbors.add(nodes[current.row][current.col + 1]); // Right neighbor node added to neighbors list.
            }
            if (current.col - 1 < width) {
                if (!nodes[current.row][current.col - 1].isWall)
                    current.neighbors.add(nodes[current.row][current.col - 1]); // Left neighbor node added to neighbors list.
            }
            if (current.row - 1 < height) {
                if (!nodes[current.row - 1][current.col].isWall)
                    current.neighbors.add(nodes[current.row - 1][current.col]); // Upper neighbor node added to neighbors list.
            }
            if (current.row + 1 < height) {
                if (!nodes[current.row + 1][current.col].isWall)
                    current.neighbors.add(nodes[current.row + 1][current.col]); // Lower neighbor node added to neighbors list.
            }

            // Look at each neighbor, if it hasn't been visited, we add it to the queue and drop a 'breadcrumb' to mark the path we took to get to the neighbor.
            for (Node neighbor : current.neighbors) {
                if (!neighbor.isVisited) {
					//BFScount++;
                    neighbor.isVisited = true;
                    neighbor.cameFrom = current;
                    queue.offer(neighbor);
                }
            }
        }
        Node current = lastSeenNode;
        int count = 0;
        // Starting with the goal, follow the 'breadcrumb' path back to the beginning, and set each path member as being on the path.
        // Count each path member as we loop to find the total path count.
        while (current != null) {
            current.isOnPath = true;
            count++;
            current = current.cameFrom;
        }
		System.out.println(BFScount);
        return count;
    }


    /**
     * Traverse the graph with DFS (any path to any goal)
     * A side-effect of this method should be that the nodes on the path
     * have had their isOnPath member set to true.
     *
     * @return - the length of the path
     */
    public int CalculateAPath() {
		//DFScount++;
        LinkedList<Node> stack = new LinkedList<>();
        start.isVisited = true;
        stack.push(start);
        Node lastSeenNode = null;
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            DFScount++;
            // If our current node is the goal, we have completed the path.
            if (current.isGoal) {
                lastSeenNode = current;
                break;
            }

            // Populate the neighbors list for the current node
            if (current.col + 1 < width) {
                if (!nodes[current.row][current.col + 1].isWall)
                    current.neighbors.add(nodes[current.row][current.col + 1]); // Right neighbor node added to neighbors list.
            }
            if (current.col - 1 < width) {
                if (!nodes[current.row][current.col - 1].isWall)
                    current.neighbors.add(nodes[current.row][current.col - 1]); // Left neighbor node added to neighbors list.
            }
            if (current.row - 1 < height) {
                if (!nodes[current.row - 1][current.col].isWall)
                    current.neighbors.add(nodes[current.row - 1][current.col]); // Upper neighbor node added to neighbors list.
            }
            if (current.row + 1 < height) {
                if (!nodes[current.row + 1][current.col].isWall)
                    current.neighbors.add(nodes[current.row + 1][current.col]); // Lower neighbor node added to neighbors list.
            }
            // Look at each neighbor, if it hasn't been visited, we add it to the stack and drop a 'breadcrumb' to mark the path we took to get to the neighbor.
            for (Node neighbor : current.neighbors) {
                if (!neighbor.isVisited) {
                    //DFScount++;
                    neighbor.isVisited = true;
                    neighbor.cameFrom = current;
                    stack.push(neighbor);
                }
            }
        }
        // Starting with the goal, follow the 'breadcrumb' path back to the beginning, and set each path member as being on the path.
        // Count each path member as we loop to find the total path count.
        Node current = lastSeenNode;
        int count = 0;
        while (current != null) {
            current.isOnPath = true;
            count++;
            current = current.cameFrom;
        }
        System.out.println(DFScount);
        return count;
    }


    /**
     * A node class to assist in the implementation of the graph.
     *
     * @author Daniel Kopta, Adam Liu, & Michael Wadley.
     */
    private static class Node {
        // The node's position in the maze
        private int row, col;

        // The type of the node
        private boolean isStart;
        private boolean isGoal;
        private boolean isOnPath; // Set to true when visited in methods above.
        private boolean isWall;
        private boolean isVisited; //Set to true when visited in methods above.

        // An arrayList of Nodes being the top, bottom, left, and right positional neighbors.
        private ArrayList<Node> neighbors;

        // For each node, cameFrom holds the previously visited node.
        private Node cameFrom;

        // Constructs each Node.
        public Node(int r, int c) {
            isStart = false;
            isGoal = false;
            isOnPath = false;
            isVisited = false;
            // Each node begins with no previously visited node.
            cameFrom = null;
            row = r;
            col = c;
            // Each node begins with an empty neighbor list.
            neighbors = new ArrayList<>();
        }

        /**
         * Returns node properties as a string value.
         */
        @Override
        public String toString() {
            if (isWall)
                return "X";
            if (isStart)
                return "S";
            if (isGoal)
                return "G";
            if (isOnPath)
                return ".";
            return " ";
        }
    }

}