import java.util.ArrayList;

public class Coloring {

    //assigns proper colors to each node
    static void assignColors(){
        //can be written recursively or in a while loop
        //need to check neighbours colors
        for (int i = 1; i < VertexOperation.originalVertexes.size() ; i++){
            int assignedColor = VertexOperation.originalVertexes.get(i).assignColor();
            if (!VertexOperation.originalAssignedColorCodes.contains(assignedColor)){
                VertexOperation.originalAssignedColorCodes.add(assignedColor);
            }
        }

    }



    static boolean validateAssignedColor(String vertexType) {
        if (vertexType.equals("original")){
            for (Node node : VertexOperation.originalVertexes){
                for (Node neighbour : node.neighbours){
                    if (node.colorCode == neighbour.colorCode){
                        //System.out.println(node.value + " " + neighbour.value);
                        return false;
                    }
                }
            }
        }

        else{
            for (Node node : VertexOperation.copyVertexes){
                for (Node neighbour : node.neighbours){
                    if (node.colorCode == neighbour.colorCode){
                        //System.out.println("hatalı vertexler :" + node.value + " " + neighbour.value);
                        return false;
                    }
                }
            }
        }

        return true;
    }

}
