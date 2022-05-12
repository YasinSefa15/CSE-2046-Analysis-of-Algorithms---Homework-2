import java.util.ArrayList;
import java.util.Collections;

public class Node implements Comparable{

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
        Collections.sort(neighbours);

    }

    void printNeighbours(){
        System.out.println("Node : " + value);
        for (Node n : neighbours){
            System.out.print(n.value + " ");
        }
        System.out.println();
        System.out.println("----------------------");
    }

    void setColoCode(int colorCode){
        this.colorCode = colorCode;
    }

    @Override
    public int compareTo(Object node) {
        int compareValue=((Node)node).value;
        return value - compareValue;
    }
}
