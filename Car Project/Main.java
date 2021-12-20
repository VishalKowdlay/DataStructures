import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    Stack<Car> carStack;
    Queue<Car> carQueue;
    PriorityQueue<Car> carPQueue;

    public Main()
    {
        
        carStack = new Stack<>();
        carQueue = new LinkedList<>();
        carPQueue = new PriorityQueue<>();
        fillQueue();
        print();


    }

    public void fillQueue()
    {
        File file = new File("CarData.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String text = "";
            reader.readLine();

            while((text = reader.readLine()) != null)
            {
                String[] pieces = text.split("\t");
                carQueue.add(new Car(Integer.parseInt(pieces[0]), Integer.parseInt(pieces[1]), Integer.parseInt(pieces[2]), Integer.parseInt(pieces[3]), 
                Integer.parseInt(pieces[4]), Integer.parseInt(pieces[5]), Integer.parseInt(pieces[6]), Integer.parseInt(pieces[7])));

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }

    }

    public void fillStack()
    {
        

    }

    public void fillPQueue()
    {
        

    }

    public void print()
    {
        
        while(carQueue.peek() != null)
        {
            System.out.println(carQueue.peek());
            
            carStack.push(carQueue.poll());
        }
        System.out.println("Printing Queue");
        System.out.println("**********");
        System.out.println("Printing Stack");
        while(!carStack.empty())
        {
            System.out.println(carStack.peek());
            carPQueue.add(carStack.pop());
        }

        System.out.println("Printing Priority Queue");
        while(!carPQueue.isEmpty())
        {
            System.out.println(carPQueue.poll());
        }
        
        System.out.println("**********");


    }


    public static void main (String[] args) {
        new Main();
    }
    
}
