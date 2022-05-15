import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
public class NodeOperation {

    //burada
    static void updateColors(){
        formatColors();
        Main.updatedAssignedColorCodes = new ArrayList<>();

        //burada asıl renk kodlarını koşul koyup tüm olasılıklar denenebilir
    }

    static void formatColors(){
        for (Node node : Main.updatedNodes){
            node.setColoCode(0);
        }
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
                Main.totalNumberOfNodes = Integer.parseInt(line.substring(line.indexOf(" ") + 1, line.lastIndexOf(" ")));
                Main.totalNumberOfEdges = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1));
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
        for (Node node : Main.originalNodes){ //searches for the node
            if (node.value == value){
                return node; //node is found and returned
            }
        }
        Node node = new Node(value); //creates the node and increments the totalReadNodes variable

        node.colorCode = -1; //-1 says us no color assigned to node
        //totalReadNodes++;
        Main.originalNodes.add(node);
        return node;
    }

    static void updateNeighbours(){
        for (Node firstNode : Main.originalNodes){
            for (Node secondNode : Main.originalNodes){
                if (firstNode.neighbours.contains(secondNode) && !secondNode.neighbours.contains(firstNode)){
                    secondNode.neighbours.add(firstNode);
                }else if (!firstNode.neighbours.contains(secondNode) && secondNode.neighbours.contains(firstNode)){
                    firstNode.neighbours.add(secondNode);
                }
            }
        }
    }

    //prints the G and color of all nodes starting from first vertex
    static void printOutput(){
        String list = getCorrectNodeList();
        ArrayList<Node> nodes = null;
        nodes = list.equals("updated") ? Main.updatedNodes : Main.originalNodes;

        System.out.println("G = " + (list.equals("updated") ? Main.updatedAssignedColorCodes.size() : Main.originalAssignedColorCodes.size()));
        System.out.println("vertex " + nodes.size());
        for (Node node : nodes){
            System.out.print( node.colorCode + " ");//node.value + ":" +
        }
    }

    static String getCorrectNodeList(){
        if (Main.originalNodes.size() == 0 && Main.updatedNodes.size() != 0 ){
            return "updated";
        }else if(Main.originalNodes.size() != 0 && Main.updatedNodes.size() == 0){
            return "original";
        }else if(Main.originalAssignedColorCodes.size() > Main.updatedAssignedColorCodes.size()){
            return "original";
        }
        System.out.println("hata line 98");
        System.exit(9);
        return null;
    }
}
