import java.util.ArrayList;
import java.util.Collections;
public class Main {

    static int G; //The smallest number of colors needed to color a graph
    static ArrayList<Node> originalNodes;
    static ArrayList<Node> updatedNodes;
    static ArrayList<Integer> originalAssignedColorCodes;
    static ArrayList<Integer> updatedAssignedColorCodes;
    static int totalNumberOfNodes;
    static int totalNumberOfEdges;
    //static int totalReadNodes = 0; //to check the reading is successful



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
        originalNodes = new ArrayList<>();
        updatedNodes = new ArrayList<>();
        originalAssignedColorCodes = new ArrayList<>();
        updatedAssignedColorCodes = new ArrayList<>();
        NodeOperation.readInput(); //reads the sample files and creates nodes

        //sortNodes(); //sorts the nodes
        //sortNodesNeighbours();
        NodeOperation.updateNeighbours(); //updates nodes neighbours list

        originalNodes.get(0).colorCode = 0;
        Coloring.assignColors();//assign colors to each node
        System.out.println();
        NodeOperation.updateColors(); //because of the edges between nodes, neighbour nodes can have same color assigning colors first
        System.out.println(Coloring.checkAssigningColor());
        //System.out.println(originalaAssignedColorCodes.size());
        //System.out.println(assignedColorCodes.size());

        NodeOperation.printOutput(); //prints the output

        //printNodeColor();
        //System.out.println("asd");
    }

    static void printNodeColor(){
        System.out.println();
        for (Node node : originalNodes){
            System.out.print(node.value + " color : " + node.colorCode+ " | ");
            for (Node neigh : node.neighbours){
                System.out.print("value : " + neigh.value + " " + neigh.colorCode + " - ");
            }
            System.out.println();
        }
    }


    //sorts each nodes neighbours depending on neighbours values
    static void sortNodesNeighbours(){
        //calls sortNeighbours for each node
        for (Node node : originalNodes){
            node.sortNeighbours();
        }
    }

    //sorts nodes list depending on their values
    static void sortNodes(){
        Collections.sort(originalNodes);
    }


}
