package PS6;
import java.io.*;
import java.util.*;

public class FindSums {

    static List<String> results = new ArrayList<String>();

    public static void main(String args[]) throws IOException{

        //BufferedReader for reading in fast input
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        //Read in the first line of input (target value and number of addends provided)
        String lineOne = in.readLine();
        String[] parseLineOne = lineOne.split(" ");
        int target = Integer.parseInt(parseLineOne[0]);
        int size = Integer.parseInt(parseLineOne[1]);

        //Read in the second line of input (all of the addends provided to reach the target sum)
        String lineTwo = in.readLine();
        String[] parseLineTwo = lineTwo.split(" ");
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (int i = 0; i < parseLineTwo.length; i++){
            nums.add(Integer.parseInt(parseLineTwo[i]));
        }

        Collections.sort(nums); //Sort the addends in ascending order
        Collections.reverse(nums);  //Reverse the addends so that they are in descending order

        System.out.println("Sums of " + target + ":");
        findSums(nums, target, 0, size, 0, ""); //Make the intiial call to the recursive function

        //If no sums are found, print "NONE"
        if (results.isEmpty()){
            System.out.println("NONE");
            return;
        }

    }

    public static void findSums(ArrayList<Integer> nums, int target, int sum, int size, int i, String result){

        //BASE CASE 1: If the sum is greater than the target value, end this line of recursion.
        if (sum > target){
            return;
        }
        /*
        BASE CASE 2: If the sum is equal to the target value,
        if this specific sum hasn't already been used, 
        add the sum to the list of results and print the result
        and return
        */
        else if (sum == target){
            if (results.contains(result)){
                return;
            }
            else{
                results.add(result);
                System.out.println(result);
                return;
            }
        }
        //If the sum is STILL less than the target...
        else if (sum < target){

            //But we have reached the end of our list of numbers, return
            if (i >= size){
                return;
            }

            //Scenario 1: We add the next number, and make a new recursive call.
            int newSum1 = sum + nums.get(i);
            int counter1 = i + 1;
            String result1 = result;
            if (result.isEmpty()){
                result1 += Integer.toString(nums.get(i));
            }
            else{
                result1 += "+";
                result1 += Integer.toString(nums.get(i));
            }
            findSums(nums, target, newSum1, size, counter1, result1);

            //Scenario 2: We skip the next number, and make a new recursive call.
            int newSum2 = sum;
            int counter2 = i + 1;
            String result2 = result;
            findSums(nums, target, newSum2, size, counter2, result2);

        }


    }
    
}
