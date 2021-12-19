package PS6;
import java.util.*;
import java.io.*;

public class Sprinklers {

    public static void main(String args[]) throws IOException{

        //BufferedReader for reading in fast input
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = in.readLine();
        String[] input2 = input.split(" ");
        int n = Integer.parseInt(input2[0]);    //number of sprinklers
        int l = Integer.parseInt(input2[1]);    //length of the field
        int w = Integer.parseInt(input2[2]);    //width of the field

        //Make a list of all sprinklers
        ArrayList<Pair> sprinklers = new ArrayList<Pair>();
        //Read in each sprinkler's point of origin and radius
        for (int i = 0; i < n; i++){
            String input3 = in.readLine();
            String[] input4 = input3.split(" ");
            int origin = Integer.parseInt(input4[0]);   //Point of origin of the sprinkler
            int radius = Integer.parseInt(input4[1]);   //The sprinkler's radius of area that it covers

            Double center = Double.valueOf(origin);
            Double x = Math.sqrt(Math.pow(radius,2) - Math.pow(w/2,2));
            
            //Create a new Pair(x, y) where x is the first point on the field where the sprinkler
            //reaches and y is the last point
            sprinklers.add(new Pair(center-x, center+x, radius));
        }


        //Call the function to calculate the minimum number of sprinklers and print the return value.
        System.out.println(minimumSprinklers(sprinklers, n, l));

    }

    public static int minimumSprinklers(ArrayList<Pair> sprinklers, int n, int l){

        //Sort the list of sprinklers in ascending order
        Collections.sort(sprinklers, new Comparator<Pair>() {
            @Override public int compare(Pair p1, Pair p2)
            {
                return Double.compare(p1.x, p2.x);
            }
        });

        Pair current = sprinklers.get(0);   //current means the most recent/closest sprinkler we have decided to turn on

        if (current.x > 0){
            return -1;
        }

        if (sprinklers.isEmpty()){
            return -1;
        }

        //if there are several sprinklers that reach past the 0 point,
        //then choose the one that has the rightmost y value
        int i = 0; //counter for the sprinklers
        
        while ((i < n) && (sprinklers.get(i).x <= 0)){
            if (sprinklers.get(i).y > current.y){
                current = sprinklers.get(i);
            }
            i++;
        }

        Pair next = null;   //this variable holds the current next best choice for the next sprinkler we will turn on
        //the next variable may very well be replaced by the prospect variable, if we find another sprinkler that reaches further
        
        Pair prospect; //prospect simply holds the sprinkler we are looking at right now, deciding if we want to turn it on or not

        Double maxRight = current.y;   //variable to hold the current right-most place we can reach on the field
        int onSprinklers = 1;   //We already decided to turn on sprinkler 0 so we start with onSprinklers = 1

        //While sprinkler i actually exists...
        while (i < n){

            //If i is the last sprinkler in the list
            if (i == (n-1)){
                //And i reaches past the length of the field, return onSprinklers + 1 (aka we only need to turn on 1 more sprinkler)
                if (sprinklers.get(i).y > l || maxRight > l){
                    return onSprinklers + 1;
                }
                //TODO: Need to check this step because it could be wrong
                else{
                    return -1;
                }
            }

            prospect = sprinklers.get(i);   //Get the next sprinkler in the list (sprinkler i)

            //If our prospective sprinkler is in the range of the current sprinkler, and it reaches further than maxRight
            if (prospect.x < current.y && prospect.y > maxRight){
                maxRight = prospect.y;  //update maxRight
                next = prospect;    //update next to be sprinkler i

                //if prospect (sprinkler i) goes further than the length of the field, we are done!
                if (prospect.y > l){
                    return onSprinklers + 1;
                }
            }

            //If the prospective sprinkler is NOT in the range of our current sprinkler
            else if (prospect.x >= current.y){
                //And we haven't already found a next sprinkler, return -1. The field can't be covered
                if (next == null){
                    return -1;
                }
                //ELSE
                current = next; //'Turn on' the next sprinkler by updating current
                next = null;    //Set next to null
                prospect = null;    //Set prospect to null
                onSprinklers++; // Since we turned on another sprinkler, increase count accordingly
                continue;   //DO NOT INCREMENT i!!!
            }

            i++;    //increment i

        }

        return onSprinklers;    //return the minimum number of sprinklers that were turned on

    }
    
}

//User-defined class where an object represents an ordered pair
class Pair{

    Double x;
    Double y;
    int radius;

    Pair(Double start, Double end, int radius){
        this.x = start;
        this.y = end;
        this.radius = radius;
    }

}