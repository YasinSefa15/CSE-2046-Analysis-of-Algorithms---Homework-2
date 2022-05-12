import java.util.ArrayList;
public class Node {

    ArrayList<Node> neighbours;
    int value;
    String color;

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

    void setColor(String color){
        this.color = color;
    }


}
