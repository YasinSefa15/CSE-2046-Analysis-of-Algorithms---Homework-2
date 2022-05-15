import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Main {

    static int G; //The smallest number of colors needed to color a graph
    static ArrayList<Node> nodes;
    static ArrayList<Integer> originalAssignedColorCodes;
    static ArrayList<Integer> assignedColorCodes;
    static int totalNumberOfNodes;
    static int totalNumberOfEdges;
    //static int totalReadNodes = 0; //to check the reading is successful

    static boolean checkAssigningColor() {
        for (Node node : nodes){
            for (Node neighbour : node.neighbours){
                if (node.colorCode == neighbour.colorCode){
                    System.out.println(node.value + " " + neighbour.value);
                    return false;
                }
            }
        }
        return true;
    }

    /*static void updateColors(){
        //renkleri optimize etmeliyiz.
        boolean isAssignable = true;
        boolean isFinished = true;
        int tempCode = 0;
        for (Node node : nodes){
            tempCode= 0;
            while (isFinished){
                for (Node neighbour : node.neighbours){
                    if (neighbour.colorCode == tempCode){
                        isAssignable = false;
                    }

                }
                if (isAssignable){
                    node.colorCode = colorCode;
                }
            }

        }
            originalaAssignedColorCodes = new ArrayList<>();
        System.out.println(originalaAssignedColorCodes.size());
        for (Node node : nodes){
            if (!originalaAssignedColorCodes.contains(node.colorCode)){
                originalaAssignedColorCodes.add(node.colorCode);
            }
        }
    }*/

    //node yerine vertex yazılabilir

    public static void main(String[] args) {
        nodes = new ArrayList<>();
        originalAssignedColorCodes = new ArrayList<>();
        assignedColorCodes = new ArrayList<>();
        readInput(); //reads the sample files and creates nodes

        //sortNodes(); //sorts the nodes
        //sortNodesNeighbours();
        updateNeighbours(); //updates nodes neighbours list

        nodes.get(0).colorCode = 0;
        Coloring.assignColors();//assign colors to each node
        System.out.println();
        //updateColors(); //because of the edges between nodes, neighbour nodes can have same color assigning colors first
        System.out.println(checkAssigningColor());
        //System.out.println(originalaAssignedColorCodes.size());
        printOutput(); //prints the output

        //printNodeColor();
        //System.out.println("asd");
    }

    static void printNodeColor(){
        System.out.println();
        for (Node node : nodes){
            System.out.print(node.value + " color : " + node.colorCode+ " | ");
            for (Node neigh : node.neighbours){
                System.out.print("value : " + neigh.value + " " + neigh.colorCode + " - ");
            }
            System.out.println();
        }
    }

    //hatalı sanırım .d
    static void updateNeighbours(){
        for (Node firstNode : nodes){
            for (Node secondNode : nodes){
                if (firstNode.neighbours.contains(secondNode) && !secondNode.neighbours.contains(firstNode)){
                    secondNode.neighbours.add(firstNode);
                }else if (!firstNode.neighbours.contains(secondNode) && secondNode.neighbours.contains(firstNode)){
                    firstNode.neighbours.add(secondNode);
                }
            }
        }
    }

    //2 1 in komşusu ama 1 2 nin değil. oldu


    //assigns proper colors to each node


    //prints the G and color of all nodes starting from first vertex
    static void printOutput(){
        System.out.println("G = " + originalAssignedColorCodes.size());
       // System.out.println("vertex " + nodes.size());
        for (Node node : nodes){
            System.out.print( node.colorCode + " ");//node.value + ":" +
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

        int asd = 0;
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

                //System.out.println(firstVertexNumber + " " + secondVertexNumber);
                //System.exit(99);
                if (secondVertexNumber == 2)
                    asd++;
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

        node.colorCode = -1; //-1 says us no color assigned to node
        //totalReadNodes++;
        nodes.add(node);
        return node;
    }
}
