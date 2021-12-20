import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PassengerRunner
{
    public static void main(String[]args)
    {
       File file = new File("C:\\Users\\twins\\Downloads\\PassengerInfo.txt");
        PriorityQueue<Passenger> flightsQueue = new PriorityQueue<>();

        Scanner reader;
        try
        {
            reader = new Scanner(file);
            String text;
            while(reader.hasNextLine())
            {
                text = reader.nextLine();
                String[] pieces = text.split("\n");
                System.out.println(pieces[0]);
                Passenger currPsg = new Passenger(pieces[0], pieces[1], pieces[2]);
                System.out.println(currPsg.toString());
                flightsQueue.add(currPsg);

                
            }
        }
        catch (Exception e)
        {

        }

    }    
}