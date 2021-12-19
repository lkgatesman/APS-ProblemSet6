package PS6;
import java.util.*;
import java.io.*;

public class VirusMonks {

    public static void main(String args[]) throws IOException{

        //Buffered reader for reading input
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        //Read the first line of input: the number of monks and the number of offices
        String input = in.readLine();
        String[] inputArr = input.split(" ");
        int monks = Integer.parseInt(inputArr[0]);
        int offices = Integer.parseInt(inputArr[1]);

        //Create a list that will hold all the monks
        List<Monk> allMonks = new ArrayList<Monk>();
        int time;   //Input to hold a monk's time that he arrives at the clinic
        int total;  //Total number of clinics that a monk will visit

        //Read in information about each monk
        for (int i = 0; i < monks; i++){
            input = in.readLine();
            inputArr = input.split(" ");

            time = Integer.parseInt(inputArr[0]);   //the monk's arrival time
            total = Integer.parseInt(inputArr[1]);  //the monk's total number of offices to visit
            
            //Create the monk's schedule queue and populate it
            Queue<Integer> schedule = new LinkedList<Integer>();
            for (int j = 2; j < total + 2; j++){
                //I am decrementing the office's index by 1 since in the list it will start at 0, not 1
                schedule.add(Integer.parseInt(inputArr[j])-1);
            }
            //System.out.println("Monk " + i + " :" + schedule);
            //Add a new monk object to the list of monks
            allMonks.add(new Monk(i, time, total, schedule));
        }

        System.out.println(iosefkaClinic(allMonks, monks, offices));

    }

    public static int iosefkaClinic(List<Monk> allMonks, int numMonks, int numOffices){

        int timer = -1; //Timer starts at -1 because it will be incremented to 0 on the first iteration

        //Create the list of the offices and populate it with a queue for each office
        List<Queue<Monk>> offices = new ArrayList<Queue<Monk>>();
        for (int i = 0; i < numOffices; i++){
            Queue<Monk> newOffice = new LinkedList<Monk>(); //Create new office
            offices.add(newOffice); //Add office to the list of offices
        }

        while (true){

            timer++;    //increment timer by 1

            Monk current;   //variable to hold the current monk that we are evaluating
            
            //Iterator to loop through the list of monks that haven't completed ALL of their visits yet
            Iterator<Monk> iterator = allMonks.listIterator();   

            //Look at each monk
            while (iterator.hasNext()){

                current = iterator.next();  //Current monk we are evaluating

                //If the current monk has already passed its arrival time (i.e. is active)
                if (current.active){
                    //If the current monk has no more offices to visit, remove it from the list of monks
                    if (current.order.isEmpty()){
                        iterator.remove();
                        continue;
                    }

                    //If the current monk is not in an office (aka it was just removed)
                    if (current.currentOffice == -1){
                        int nextOffice = current.order.poll();  //Get the monk's next office from its schedule/order of offices

                        //Add the monk to the queue of its next office
                        offices.get(nextOffice).add(current);
                        current.currentOffice = nextOffice; //Update which office the monk is in / in queue for
                    }
                }

                //If the monk is NOT active (i.e. it has not reached its arrival time so far)
                else{

                    //If the monk's arrival time is NOW
                    if (current.time == timer){
                        current.active = true;  //change status to active
                        int nextOffice = current.order.poll();  //Get the monk's next office from its schedule/order of offices

                        //Add the monk to the queue of its next office
                        offices.get(nextOffice).add(current);
                        current.currentOffice = nextOffice; //Update which office the monk is in / in queue for
                    }

                }

            }

            int emptyOffices = 0;   //variable to count how many offices are currently empty

            Queue<Monk> office; //variable to hold the current office that we are evaluating

            //Iterate through each office and empty it if need be
            for (int officeCounter = 0; officeCounter < numOffices; officeCounter++){
                office = offices.get(officeCounter);

                //If the office is not empty, we remove it's current patient (monk)
                if (!office.isEmpty()){
                    Monk removed = office.remove();
                    removed.currentOffice = -1; //Set the removed monk's current office to -1 (that is, the monk is not in an office)
                }

                //If the office is empty, increment the number of empty offices by 1
                else{
                    emptyOffices++;
                }
            }

            //If all of the offices are empty AND all monks are done (i.e. all monks have been removed from the list of monks),
            //then we are done.
            if (emptyOffices == numOffices){
                if (allMonks.isEmpty()){
                    break;
                }
            }

        }

        return timer;

    }
    
}

class Monk{

    int id;
    int time;
    int total;
    boolean active;
    int currentOffice;
    Queue<Integer> order = new LinkedList<Integer>();

    Monk(int id, int time, int total, Queue<Integer> order){
        this.id = id;
        this.time = time;
        this.total = total;
        this.order = order;
        this.active = false;
        currentOffice = -1;
    }

}
