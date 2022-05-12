import java.util.ArrayList;
public class Node {

    ArrayList<Node> neighbours;
    int value;
    int colorCode;

    Node(int value){
        this.neighbours = new ArrayList<>();
        this.value = value;

    }

    void addNeighbour(Node neighbour){
        if (!neighbours.contains(neighbour)){
            neighbours.add(neighbour);
        }
    }

    void sortNeighbours(){
        System.out.println("gonna sort later");
    }

    ArrayList<Node> getNeighbours(){
        return this.neighbours;
    }

    int getValue(){
        return this.value;
    }

    void setColoCode(int colorCode){
        this.colorCode = colorCode;
    }


}
