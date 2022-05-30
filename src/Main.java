import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Main {
//  errorrrrrr
    static int G; //The smallest number of colors needed to color a graph
    static ArrayList<Node> nodes;
    static int totalNumberOfNodes;
    static int totalNumberOfEdges;
    //static int totalReadNodes = 0; //to check the reading is successful

    public static void main(String[] args) {
        nodes = new ArrayList<>();
        readInput(); //reads the sample files and creates nodes
        sortNodes(); //sorts the nodes
        sortNodesNeighbours();
        //assignColors(); //assign colors to each node
        //printOutput(); //prints the output
    }

    //assigns proper colors to each node
    static void assignColors(Node node, int currentColorCode){
        //can be written recursively or in a while loop
    }

    //prints the G and color of all nodes starting from first vertex
    static void printOutput(){
        System.out.println(G);
        for (Node node : nodes){
            System.out.print(node.colorCode + " ");
        }
    }

    //sorts each nodes neighbours depending on neighbours values
    static void sortNodesNeighbours(){
        //calls sortNeighbours for each node
        for (Node node : nodes){
            node.sortNeighbours();
        }
    }

    //sorts nodes list depending on their values
    static void sortNodes(){
        Collections.sort(nodes);
    }

    //reads input from sample txt files
    static void readInput() {
        int sampleId = 1;
        String line;

        //int totalReadEdges = 0; //to check the reading is successful
        int firstVertexNumber;
        int secondVertexNumber;
        Node firstNode;
        Node secondNode;
        try {
            FileInputStream fis = new FileInputStream("sample" + sampleId +".txt"); //dynamic input file
            Scanner input = new Scanner(fis);
            if (input.hasNextLine()){ //getting total number of nodes and total number of edges
                line = input.nextLine();
                totalNumberOfNodes = Integer.parseInt(line.substring(line.indexOf(" ") + 1, line.lastIndexOf(" ")));
                totalNumberOfEdges = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1));
            }
            while(input.hasNextLine()) {
                line = input.nextLine();
                //gets the first vertex value
                firstVertexNumber = Integer.parseInt(line.substring(line.indexOf(" ") + 1, line.lastIndexOf(" ")));
                //gets the second vertex value
                secondVertexNumber = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1));
                //totalReadEdges++;
                //findOrCreate will return the node
                firstNode = findOrCreate(firstVertexNumber);
                secondNode = findOrCreate(secondVertexNumber);
                firstNode.addNeighbour(secondNode);
            }
            //System.out.println(totalReadNodes);
            //System.out.println(totalReadEdges);
            input.close();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    //returns the searched node from nodes list
    static Node findOrCreate(int value){
        for (Node node : nodes){ //searches for the node
            if (node.value == value){
                return node; //node is found and returned
            }
        }
        Node node = new Node(value); //creates the node and increments the totalReadNodes variable
        //totalReadNodes++;
        nodes.add(node);
        return node;
    }
}
