public class Main {

    public static void main(String[] args) {

        VertexOperation.setInıt();
        VertexOperation.readInput(); //reads the sample files and creates nodes
        VertexOperation.updateNeighbours(); //updates nodes neighbours list

        Coloring.assignColors();//assign colors to each node
        VertexOperation.printOutput();

        System.out.println();

        System.out.println("Original List Correct ? : " + Coloring.validateAssignedColor("original"));
        System.out.println("------------------------------------");

        System.out.println("\nLast List Correct ? : " + Coloring.validateAssignedColor("asd"));

        //VertexOperation.printOutput(); //prints the output

        //printNodeColor();
        //System.out.println("asd");
    }

}
