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
        fillStack();
        print();
        fillPQueue();
        print();


    }

    public void fillQueue()
    {
        

    }

    public void fillStack()
    {
        

    }

    public void fillPQueue()
    {
        

    }

    public void print()
    {
        System.out.println("**********");
        System.out.println("Printing Stack");

        for(int i=0; i<carStack.size(); i++)
        {
            System.out.print(carStack.get(i));
        }

        System.out.println("Printing Queue");
        Car currentCar = carQueue.peek();
        while(currentCar != null)
        {
            System.out.print(currentCar.toString());
            carQueue.add(currentCar);
            currentCar = carQueue.poll();
        }

        System.out.println("Printing Priority Queue");
        Car currentPCar = carPQueue.peek();
        while(currentPCar != null)
        {
            System.out.print(currentPCar.toString());
            carPQueue.add(currentPCar);
            currentPCar = carPQueue.poll();
        }
        
        System.out.println("**********");


    }


    public static void main (String[] args) {
        new Main();
    }
    
}
