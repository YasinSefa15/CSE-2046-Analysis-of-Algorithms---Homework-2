import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        //file name code passed as parameter
        VertexOperation.setInıt(1);
        VertexOperation.readInput(); //reads the sample files and creates nodes
        //since vertexes added to list by read value, we need to sort to list for demonstrating output
        Collections.sort(VertexOperation.originalVertices);
        Collections.sort(VertexOperation.copyVertices);
        VertexOperation.updateNeighbours(); //updates nodes neighbours list

        Coloring.assignColors();//assign colors to each node in original vertices list

        //second coloring algorithm called
        Coloring.setInit();
        Coloring.paintVertices();

        //prints true/false of two colored vertices
        System.out.println("First algorithm result valid : " + Coloring.validateAssignedColor("original"));
        System.out.println("Second algorithm result valid : " +Coloring.validateAssignedColor("copy"));
        //print the output
        VertexOperation.printOutput();

    }

}
