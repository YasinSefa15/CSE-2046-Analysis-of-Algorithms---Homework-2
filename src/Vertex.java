import java.util.ArrayList;

public class Vertex implements Comparable{

    ArrayList<Vertex> neighbors;
    int value;
    int colorCode;
    boolean done;

    Vertex(int value){
        this.neighbors = new ArrayList<>();
        this.value = value;
        this.done = false;
    }


    //assigns color by checking its neighbor. First algorithm uses it
    int assignColor(){
        this.colorCode = 0;

        for (int i = 0; i < neighbors.size() ; i++){
            if (neighbors.get(i).colorCode == this.colorCode){
                i = -1; //if color is not assignable return to first element and check with incremented value
                this.colorCode = this.colorCode + 1;
            }
        }
        return this.colorCode;
    }


    void addNeighbour(Vertex neighbour){
        if (!neighbors.contains(neighbour)){
            neighbors.add(neighbour);
        }
    }


    @Override
    public int compareTo(Object node) {
        int compareValue=((Vertex)node).value;
        return value - compareValue;
    }
}
