//NOTE: For the fast output code, I used the advice given by Andy Polizzotto in this Ed post: https://edstem.org/us/courses/8952/discussion/750641
package PS6;
import java.io.*;
import java.util.*;

public class CountSort {

    public static void main(String args[]) throws IOException{

        //BufferedReader for reading in fast input
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        //Integer n which is the number of grades that will be given in the input
        int n = Integer.parseInt(in.readLine());

        //Create new array of size n
        int[] grades = new int[n];

        //Read in all grades and store them in the array of grades
        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++){
            grades[i] = Integer.parseInt(st.nextToken());
        }

        int[] count = countSort(grades);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 101; i++){
            if (count[i] > 0){
                for (int j = 0; j < count[i]; j++){
                    sb.append(i);
                    sb.append(" ");
                }
            }
        }

        int l = sb.length();
        sb.deleteCharAt(l - 1);

		System.out.print(sb + "\n");

    }

    //Method to implement count sort on an array a[]
    public static int[] countSort(int a[]){

        //All possible grades are between 0 and 100, so we create an array with 101 elements
        //where count[0] is the number of grades = 0 that were given via input
        int[] count = new int[101];

        //Set all counts to zero (initially)
        for (int i = 0; i < 101; i++){
            count[i] = 0;
        }

        //For each input number, increase the count by 1 for that grade
        for (int i = 0; i < a.length; i++){
            count[a[i]]++;
        }

        return count;

    }
    
}


