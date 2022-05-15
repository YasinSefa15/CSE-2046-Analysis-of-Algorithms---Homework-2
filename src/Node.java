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
       //System.out.println(colorCode + " " + index);
        //for (Node neighbour : neighbours){
        //system.out.println(neighbours.size());
        //System.out.println(index < neighbours.size());

        this.colorCode = 0;

        for (int i = 0 ; i < neighbours.size() ; i++){
            //if (neighbours.get(index).colorCode == this.colorCode){
                //System.out.println("aynı renk");
            if (neighbours.get(i).colorCode == this.colorCode){
                i = -1;
                this.colorCode = this.colorCode + 1;
            }

        }

            //this.colorCode = colorCode;




        return this.colorCode;
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
