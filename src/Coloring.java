import java.util.ArrayList;

public class Coloring {

    static void assignColors(){
        //can be written recursively or in a while loop
        //need to check neighbours colors
        int index = 0;
        /*for (Node node : nodes){
            int assignedColor = node.assignColor();
            break;
            //printOutput();
            //System.exit(5);
            //System.exit(1);
            //System.out.println(assignedColor);
    *//*        if (originalaAssignedColorCodes.contains(assignedColor)){
                //System.out.println("assigned color");
            }else{
                //System.out.println("farklı");
                originalaAssignedColorCodes.add(assignedColor);
            }*//*
        }*/

        for (int i = 1 ; i < Main.nodes.size() ; i++){
            /*System.out.println("value " + nodes.get(i).value);
            System.out.println(nodes.get(i).neighbours.size());
            System.exit(8);*/
            int assignedColor = Main.nodes.get(i).assignColor();
            if (Main.originalAssignedColorCodes.contains(assignedColor)){
                //System.out.println("assigned color");
            }else{
                //System.out.println("farklı");
                Main.originalAssignedColorCodes.add(assignedColor);
            }
        }
    }
}
