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
            for (Vertex vertex : VertexOperation.originalVertexes){
                for (Vertex neighbour : vertex.neighbours){
                    if (vertex.colorCode == neighbour.colorCode){
                        //System.out.println(vertex.value + " " + neighbour.value);
                        return false;
                    }
                }
            }
        }

        else{
            for (Vertex vertex : VertexOperation.copyVertexes){
                for (Vertex neighbour : vertex.neighbours){
                    if (vertex.colorCode == neighbour.colorCode){
                        //System.out.println("hatalı vertexler :" + vertex.value + " " + neighbour.value);
                        return false;
                    }
                }
            }
        }

        return true;
    }

}