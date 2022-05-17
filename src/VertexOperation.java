import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
public class VertexOperation {
    static int G; //The smallest number of colors needed to color a graph
    static ArrayList<Node> originalVertexes;
    static ArrayList<Node> copyVertexes;
    static ArrayList<Integer> originalAssignedColorCodes;
    static ArrayList<Integer> updatedAssignedColorCodes;
    static ArrayList<Integer> assignableColorCodes;
    static int totalNumberOfNodes;
    static int totalNumberOfEdges;


    static void setInıt(){
        originalVertexes = new ArrayList<>();
        copyVertexes = new ArrayList<>();
        originalAssignedColorCodes = new ArrayList<>();
        updatedAssignedColorCodes = new ArrayList<>();
        assignableColorCodes = new ArrayList<>();
    }

    static void updateColors(){
        formatColors();

        assignableColorCodes = new ArrayList<>();
        for (int color = 0 ; color < originalAssignedColorCodes.size() ; color++){
            assignableColorCodes.add(color);
        }

        System.out.println("o : " + originalAssignedColorCodes);
        System.out.println("assi : " + assignableColorCodes);

        int i = 0; //for debugging
        do {
            //i != 1
            if (true){
                recursive(copyVertexes.get(1),1);
                assignableColorCodes.remove(assignableColorCodes.size()-1);
                System.out.println("::::::::::::::::::::::::." + assignableColorCodes);
                i++;
            }
            else {
                break;
            }
        }
        while ( assignableColorCodes.size() > 0);



        System.out.println("upda : " + updatedAssignedColorCodes);

        System.out.println(Coloring.validateAssignedColor("nau nau"));



        if (!Coloring.validateAssignedColor("updated")){
            updatedAssignedColorCodes.clear();
            //copyVertexes = new ArrayList<>();
        }

    }

    static void recursive(Node currentNode, int index){
        //int currentColorCode = 0
        //int index = 0;
        System.out.println("index : " + index);

        for (int currentColorCode : assignableColorCodes){
            if (index >= copyVertexes.size() - 1){

                //System.exit(2);
                break;
            }
            //nodeların rengi updatelenmiyor
            System.out.println("Current color : " + currentColorCode + " node : " + currentNode.value);
            //updatedAssignedColorCodes
            if (Node.validateNeighboursColor(currentNode,currentColorCode)){
                System.out.println("komşular farklı ");
                currentNode.colorCode = currentColorCode;
                if (Coloring.validateAssignedColor("copy") && originalAssignedColorCodes.size() > updatedAssignedColorCodes.size()){
                    //renkler tutarlı demek oluyor.
                    setUpdatedAssignedColorCodes(currentColorCode);
                    System.out.println("updated value :Ç " + updatedAssignedColorCodes.size());
                }
                //®System.out.println("next node");
                recursive(copyVertexes.get(index + 1),index + 1);
                System.out.println("callback " + index);
            }

        }

        //listeden atamaya başla
        //renk atanabilirse ata (neighbour check)
        //değilse renk kodunu artırıp atama yap
        //diğer node a geç
        //her seferinde renkler tutarlı mı kontrol edilebilir. anlık G den küçükse güncellenir
        //renkler tutarlı olmadığı sürece ileri gidip ilerideki tüm seçenekleri denerl

        //recursive(copyVertexes.get(++index),currentColorCode,++index);
    }

    static void setUpdatedAssignedColorCodes(int currentColorCode){

        if (!updatedAssignedColorCodes.contains(currentColorCode)){
            updatedAssignedColorCodes.add(currentColorCode);
        }
    }





    //prints the G and color of all nodes starting from first vertex
    static void printOutput(){
        String list = getCorrectNodeList();
        System.out.println("LİST : " + list);
        //System.exit(88);
        ArrayList<Node> nodes = null;
        nodes = list.equals("updated") ? copyVertexes : originalVertexes;
        //System.out.println("color of " + originalVertexes.get(1).colorCode);
        //System.out.println(copyVertexes.get(1).colorCode);
        System.out.println("G = " + (list.equals("updated") ? updatedAssignedColorCodes.size() : originalAssignedColorCodes.size()));
        //System.out.println("vertex " + nodes.size());
        for (Node node : nodes){
            System.out.print(node.value + ":" + node.colorCode + " ");//
        }
        System.out.println();
    }

    static String getCorrectNodeList(){
        if(updatedAssignedColorCodes.size() != 0 && originalAssignedColorCodes.size() > updatedAssignedColorCodes.size()){
            return "updated";
        }else {
            return "original";
        }
    }

    static void formatColors(){
        for (Node node : copyVertexes){
            node.setColoCode(0);
        }
    }

    static void updateNeighbours(){
        for (Node firstNode : originalVertexes){
            for (Node secondNode : originalVertexes){
                if (firstNode.neighbours.contains(secondNode) && !secondNode.neighbours.contains(firstNode)){
                    secondNode.neighbours.add(firstNode);
                }else if (!firstNode.neighbours.contains(secondNode) && secondNode.neighbours.contains(firstNode)){
                    firstNode.neighbours.add(secondNode);
                }
            }
        }


        //copy nodes neighbours didnt update earlier. can check later
        for (Node firstNode : copyVertexes){
            for (Node secondNode : copyVertexes){
                if (firstNode.neighbours.contains(secondNode) && !secondNode.neighbours.contains(firstNode)){
                    secondNode.neighbours.add(firstNode);
                }else if (!firstNode.neighbours.contains(secondNode) && secondNode.neighbours.contains(firstNode)){
                    firstNode.neighbours.add(secondNode);
                }
            }
        }

        //originalVertexes.get(0).colorCode = 0;
    }

    //reads input from sample txt files
    static void readInput() {
        int sampleId = 0;
        String line;

        //int totalReadEdges = 0; //to check the reading is successful
        int firstVertexNumber;
        int secondVertexNumber;
        Node originalFirstNode;
        Node copyFirstNode;
        Node originalSecondNode;
        Node copySecondNode;

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
    static Node findOrCreate(int value, String list){
        if (list.equals("original")){
            for (Node node : originalVertexes){ //searches for the node
                if (node.value == value){
                    return node; //node is found and returned
                }
            }
            Node node = new Node(value); //creates the node and increments the totalReadNodes variable
            originalVertexes.add(node);
            return node;
        }else{
            for (Node node : copyVertexes){ //searches for the node
                if (node.value == value){
                    return node; //node is found and returned
                }
            }
            Node copyNode = new Node(value); //copies the currently created node
            copyVertexes.add(copyNode);
            return copyNode;
        }


        //totalReadNodes++;

    }

}
