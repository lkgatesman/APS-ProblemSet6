package PS6;
import java.util.*;

public class AyusCandies{

    public static void main(String args[]){

        Scanner in = new Scanner(System.in);

        //Create the first CandyBox
        CandyBox box1 = new CandyBox(in.nextLong(), in.nextLong(), in.nextLong());

        //Create the second CandyBox
        CandyBox box2 = new CandyBox(in.nextLong(), in.nextLong(), in.nextLong());

        //Create the third CandyBox
        CandyBox box3 = new CandyBox(in.nextLong(), in.nextLong(), in.nextLong());
        
        //Generate all numbers of possible movements that could be made
        Long[] movements = possibleMovements(box1, box2, box3);

        //Each combination of movements results in a specific order of designated boxes, stored here
        String[] results = {"BCG", "BGC", "CBG", "CGB", "GBC", "GCB"};

        Long x = movements[0];  //Holds the minimum number of movemets
        int minMovementIndex = 0;   //Holds the index where the minimum number of movements was

        //Find the minimum number of moves possible, and its respective index
        for (int i = 1; i < 6; i++){
            if (movements[i] < x){
                x = movements[i];
                minMovementIndex = i;
            }
        }

        //Print out the string associated with that number of moves and the number of moves itself (x)
        System.out.println(results[minMovementIndex] + " " + x);

        //Close the scanner
        in.close();

    }

    public static Long[] possibleMovements(CandyBox box1, CandyBox box2, CandyBox box3){

        Long[] movements = new Long[6];

        //Determine all possible combinations of moves

        //BCG
        movements[0] = box1.grape + box1.cherry + box2.banana + box2.grape + box3.banana + box3.cherry;

        //BGC
        movements[1] = box1.grape + box1.cherry + box2.banana + box2.cherry + box3.banana + box3.grape;

        //CBG
        movements[2] = box1.banana + box1.grape + box2.grape + box2.cherry + box3.banana + box3.cherry;

        //CGB
        movements[3] = box1.banana + box1.grape + box2.banana + box2.cherry + box3.grape + box3.cherry;

        //GBC
        movements[4] = box1.banana + box1.cherry + box2.grape + box2.cherry + box3.banana + box3.grape;

        //GCB
        movements[5] = box1.banana + box1.cherry + box2.banana + box2.grape + box3.grape + box3.cherry;

        return movements;

    }

}

   

//User-defined class where an object represents one of Ayu's candy boxes
class CandyBox{

    Long banana;
    Long grape;
    Long cherry;

    CandyBox(Long banana, Long grape, Long cherry){

        this.banana = banana;
        this.grape = grape;
        this.cherry = cherry;

    }


}