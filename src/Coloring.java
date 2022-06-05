import java.util.ArrayList;

public class Coloring {
    static ArrayList<Vertex> copyVertices;
    static ArrayList<Integer> usedColorCode;
    static ArrayList<Vertex> finishedVertices;
    static int copiedVertexCount=0;
    static ArrayList<Integer> tempColorCodes;

    static void setInit(){
        copyVertices=VertexOperation.copyVertices;
        usedColorCode=new ArrayList<>();
        tempColorCodes=new ArrayList<>();
        finishedVertices=new ArrayList<>();
        tempColorCodes.add(0);
        usedColorCode.add(0);
        copiedVertexCount=copyVertices.size();
    }

    static void paintVertices(){
        int temp;
        for (int i=0;i<copyVertices.size();i++){
            copyVertices.get(i).done=true;
            finishedVertices.add(copyVertices.get(i));
            for(int j = 0; j<copyVertices.get(i).neighbors.size(); j++){
                if (copyVertices.get(i).neighbors.get(j).done!=true){
                    tempColorCodes.clear();
                    tempColorCodes.addAll(usedColorCode);
                    if(copyVertices.get(i).neighbors.get(j)!=null){
                        tempColorCodes.remove(Integer.valueOf(copyVertices.get(i).colorCode));
                        temp=findMinColorCode(tempColorCodes);
                        if(temp!=-1){
                            copyVertices.get(i).neighbors.get(j).colorCode=(usedColorCode.get(usedColorCode.size()-1)+1>temp) ? temp : usedColorCode.get(usedColorCode.size()-1)+1;
                        }else{
                            copyVertices.get(i).neighbors.get(j).colorCode=usedColorCode.get(usedColorCode.size()-1)+1;
                        }
                    }
                }
                for (int k=0;k<finishedVertices.size()-1;k++){
                    if (copyVertices.get(i).colorCode==finishedVertices.get(k).colorCode && copyVertices.get(i).neighbors.contains(finishedVertices.get(k))){
                        copyVertices.get(i).colorCode++;
                    }
                }
                boolean control=false;
                for (int c: usedColorCode){
                    if (c==copyVertices.get(i).neighbors.get(j).colorCode)
                        control=true;
                }
                if(!control){
                    usedColorCode.add(copyVertices.get(i).neighbors.get(j).colorCode);
                }
            }
        }
        controlUsedColorSize();
    }

    private static void controlUsedColorSize() {
        for (Vertex vertex : copyVertices){
            int vCC=vertex.colorCode;
            if(usedColorCode.size()==vCC){
                usedColorCode.add(vCC);
            }
        }
    }

    static int findMinColorCode(ArrayList<Integer> colors){
        if (colors.size()!=0){
            int temp=colors.get(0);
            for (int i=0;i<colors.size();i++){
                if (temp>colors.get(i)){
                    temp=colors.get(i);
                }
            }
            return temp;
        }
        return -1;
    }

    //first coloring algorithm. Assign color to each node and checks whether its assignable or not
    //otherwise increments the color code
    static void assignColors(){
        //can be written recursively or in a while loop
        //need to check neighbours colors
        for (int i = 1; i < VertexOperation.originalVertices.size() ; i++){
            int assignedColor = VertexOperation.originalVertices.get(i).assignColor();
            if (!VertexOperation.originalAssignedColorCodes.contains(assignedColor)){
                VertexOperation.originalAssignedColorCodes.add(assignedColor);
            }
        }

    }

    //Checks whether colors assigned correctly
    static boolean validateAssignedColor(String vertexType) {
        if (vertexType.equals("original")){
            for (Vertex vertex : VertexOperation.originalVertices){
                for (Vertex neighbour : vertex.neighbors){
                    if (vertex.colorCode == neighbour.colorCode){
                        //System.out.println(vertex.value + " " + neighbour.value);
                        return false;
                    }
                }
            }
        }

        else{
            for (Vertex vertex : VertexOperation.copyVertices){
                for (Vertex neighbour : vertex.neighbors){
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