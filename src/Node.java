import java.util.ArrayList;
import java.util.Collections;

public class Node implements Comparable{

    ArrayList<Node> neighbours;
    int value;
    int colorCode;
    boolean done;

    Node(int value){
        this.neighbours = new ArrayList<>();
        this.value = value;
        this.done = false;
    }

    int updateColorCode(){
        for (Node neighbour : neighbours){
            if (neighbour.colorCode == this.colorCode){
                //System.out.println("atama başarılı");
                this.colorCode++;
            }
        }
        return this.colorCode;

    }


    int assignColor(){
        this.colorCode = 0;

        for (int i = 0 ; i < neighbours.size() ; i++){
            if (neighbours.get(i).colorCode == this.colorCode){
                i = -1;
                this.colorCode = this.colorCode + 1;
            }
        }
        return this.colorCode;
    }

    static boolean validateNeighboursColor(Node node ,int colorCode){
        for (Node neighbour : node.neighbours){
            if (neighbour.colorCode == colorCode ){
                return false;
            }
        }
        return true;
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
