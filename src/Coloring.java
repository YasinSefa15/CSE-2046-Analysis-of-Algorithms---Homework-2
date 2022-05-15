public class Coloring {

    //assigns proper colors to each node
    static void assignColors(){
        //can be written recursively or in a while loop
        //need to check neighbours colors
        for (int i = 1; i < Main.originalNodes.size() ; i++){
            int assignedColor = Main.originalNodes.get(i).assignColor();
            if (!Main.originalAssignedColorCodes.contains(assignedColor)){
                //System.out.println("assigned color");
                Main.originalAssignedColorCodes.add(assignedColor);
            }
        }
    }

    static boolean checkAssigningColor() {
        for (Node node : Main.originalNodes){
            for (Node neighbour : node.neighbours){
                if (node.colorCode == neighbour.colorCode){
                    System.out.println(node.value + " " + neighbour.value);
                    return false;
                }
            }
        }
        return true;
    }

    static int updateColors(){
        return 0;
    }

    static void copyLastColors(){

    }

    static Node copyNodes(){



        return null;
    }

}
