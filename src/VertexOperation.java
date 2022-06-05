import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class VertexOperation {
    static int G; //The smallest number of colors needed to color a graph
    static ArrayList<Vertex> originalVertices; //will keep original read vertexes. First algorithm will use it
    static ArrayList<Vertex> copyVertices; //second algorithm will use it
    static ArrayList<Integer> originalAssignedColorCodes; //color that assigned by the first algorithm
    static int sampleId;

    static void setInıt(int sampleId){
        VertexOperation.sampleId = sampleId;
        originalVertices = new ArrayList<>();
        copyVertices = new ArrayList<>();
        originalAssignedColorCodes = new ArrayList<>();
    }


    //prints the G and color of all nodes starting from first vertex by best result found by algorithm
    static void printOutput(){
        if (Coloring.usedColorCode.size() > originalAssignedColorCodes.size()){
            print(originalVertices, originalAssignedColorCodes.size());
        }else{
            print(Coloring.copyVertices, Coloring.usedColorCode.size());
        }
    }

    static void print(ArrayList<Vertex> nodes, int G){
        try
        {
            String filename= "test_output"+ sampleId +".txt";
            FileWriter fw = new FileWriter(filename);
            fw.write(G + "\n");
            for (Vertex node : nodes){
                fw.write(node.colorCode + " ");
                //System.out.print(node.colorCode  " ");
            }
            fw.close();
        }
        catch(Exception ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
            System.exit(2);
        }
    }

    //updates neighbour. Checks two vertexes whether they're placed in neighbors mutual or not
    static void updateNeighbours(){
        for (Vertex firstNode : originalVertices){
            for (Vertex secondNode : originalVertices){
                if (firstNode.neighbors.contains(secondNode) && !secondNode.neighbors.contains(firstNode)){
                    secondNode.neighbors.add(firstNode);
                }else if (!firstNode.neighbors.contains(secondNode) && secondNode.neighbors.contains(firstNode)){
                    firstNode.neighbors.add(secondNode);
                }
            }
        }

        for (Vertex firstNode : copyVertices){
            for (Vertex secondNode : copyVertices){
                if (firstNode.neighbors.contains(secondNode) && !secondNode.neighbors.contains(firstNode)){
                    secondNode.neighbors.add(firstNode);
                }else if (!firstNode.neighbors.contains(secondNode) && secondNode.neighbors.contains(firstNode)){
                    firstNode.neighbors.add(secondNode);
                }
            }
        }
    }

    //reads input from sample txt files
    static void readInput() {
        String line;
        int firstVertexNumber;
        int secondVertexNumber;
        Vertex originalFirstNode;
        Vertex copyFirstNode;
        Vertex originalSecondNode;
        Vertex copySecondNode;

        try {
            FileInputStream fis = new FileInputStream("test" + sampleId +".txt"); //dynamic input file
            Scanner input = new Scanner(fis);
            //to pass the first line
            if (input.hasNextLine()){
                line = input.nextLine();
            }

            while(input.hasNextLine()) {
                line = input.nextLine();
                //gets the first vertex value
                firstVertexNumber = Integer.parseInt(line.substring(line.indexOf(" ") + 1, line.lastIndexOf(" ")));
                //gets the second vertex value
                secondVertexNumber = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1));

                //totalReadEdges++;
                //findOrCreate will return the node
                originalFirstNode = findOrCreate(firstVertexNumber,"original");
                originalSecondNode = findOrCreate(secondVertexNumber,"original");

                copyFirstNode = findOrCreate(firstVertexNumber,"copy");
                copySecondNode = findOrCreate(secondVertexNumber,"copy");

                originalFirstNode.addNeighbour(originalSecondNode);
                copyFirstNode.addNeighbour(copySecondNode);
            }
            input.close();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    //returns the searched node from nodes list
    static Vertex findOrCreate(int value, String list){
        if (list.equals("original")){
            for (Vertex node : originalVertices){ //searches for the node
                if (node.value == value){
                    return node; //node is found and returned
                }
            }
            Vertex node = new Vertex(value); //creates the node and increments the totalReadNodes variable
            originalVertices.add(node);
            return node;
        }else{
            for (Vertex node : copyVertices){ //searches for the node
                if (node.value == value){
                    return node; //node is found and returned
                }
            }
            Vertex copyNode = new Vertex(value); //copies the currently created node
            copyVertices.add(copyNode);
            return copyNode;
        }
    }

}
