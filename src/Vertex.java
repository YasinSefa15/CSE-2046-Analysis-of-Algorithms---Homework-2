import java.util.ArrayList;
import java.util.Collections;

public class Vertex {

    ArrayList<Vertex> neighbours;
    int value;
    int colorCode;
    boolean done;

    Vertex(int value){
        this.neighbours = new ArrayList<>();
        this.value = value;
        this.done = false;
    }

    int updateColorCode(){
        for (Vertex neighbour : neighbours){
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

    static boolean validateNeighboursColor(Vertex node ,int colorCode){
        for (Vertex neighbour : node.neighbours){
            if (neighbour.colorCode == colorCode ){
                return false;
            }
        }
        return true;
    }

    void addNeighbour(Vertex neighbour){
        if (!neighbours.contains(neighbour)){
            neighbours.add(neighbour);
        }
    }


    void printNeighbours(){
        System.out.println("Node : " + value);
        for (Vertex n : neighbours){
            System.out.print(n.value + " ");
        }
        System.out.println();
        System.out.println("----------------------");
    }
}
