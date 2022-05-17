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

        VertexOperation.updateColors(); //because of the edges between nodes, neighbour nodes can have same color assigning colors first

        //System.out.println(originalaAssignedColorCodes.size());
        //System.out.println(assignedColorCodes.size());



        System.out.println("\nLast List Correct ? : " + Coloring.validateAssignedColor("asd"));
        System.out.println(VertexOperation.originalVertexes.get(0).neighbours.size());
        System.out.println(VertexOperation.copyVertexes.get(0).neighbours.size());

        for (Node n : VertexOperation.copyVertexes){
            System.out.print(n.colorCode + " ");
        }

        //VertexOperation.printOutput(); //prints the output
        System.out.println("\nLast List Correct ? : " + Coloring.validateAssignedColor("asd"));

        //printNodeColor();
        //System.out.println("asd");
    }

}
