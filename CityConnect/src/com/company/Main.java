package com.company;
import javax.lang.model.element.ModuleElement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
public class Main {

    public static void main(String[] args) {
	// write your code here
        Main app = new Main();
    }
    HashMap<City, HashSet<Edge>> cityMap;
    HashSet<City> cities;
    HashSet<Edge> edges;
    City start;
    City end;

    public Main()
    {
       cityMap = new  HashMap<City, HashSet<Edge>>();
       cities = new HashSet<City>();
       edges = new HashSet<Edge>();
       ArrayList<String> cityList = new ArrayList<String>();
       File name = new File("C:\\Users\\twins\\IdeaProjects\\CityConnect\\src\\com\\company\\City Distances.txt");
       try{
           BufferedReader reader = new BufferedReader(new FileReader(name));
           String text;
           while((text=reader.readLine()) != null)
           {
               String[] pieces = text.split(",");
               City c1, c2;
               c1 = new City(pieces[0]);
               c2 = new City(pieces[1]);
               int distance = Integer.parseInt(pieces[2]);
               if(!cityList.contains(pieces[0]))
                   cityList.add(pieces[0]);
               if(!cityList.contains(pieces[1]))
                   cityList.add(pieces[1]);
               cities.add(c1);
               cities.add(c2);
               edges.add( new Edge(c1, c2, distance));
               edges.add( new Edge(c2, c1, distance));
               if(!cityMap.containsKey(c1))
                   cityMap.put(c1, new HashSet<Edge>());
               if(!cityMap.containsKey(c2))
                   cityMap.put(c2, new HashSet<Edge>());
               cityMap.get(c1).add(new Edge(c1, c2, distance));
               cityMap.get(c2).add(new Edge(c2, c1, distance));


           }
       }
       catch(IOException e){

       }
       System.out.println("Cities: ");
       for(City city: cities)
       {
           System.out.println("\t"+city);

       }

       System.out.println("\nEdges: ");
       for(Edge edge: edges)
       {
           System.out.println("\t"+edge);
       }

       for(int i=0; i<cityList.size(); i++) {
           for (int j = i+1; j < cityList.size(); j++) {
               for(City city: cities)
               {
                  if(city.toString().equals(cityList.get(i)))
                      start = city;
                   if(city.toString().equals(cityList.get(j)))
                       end = city;
               }
               Graph graph = new Graph(cities, edges);
               DijkstrasAlgorithm da = new DijkstrasAlgorithm(graph);
               da.createTravelsPaths(start);
               ArrayList<City> shortestPath = da.getShortestPath(end);
               int distance = 0;
               System.out.println("Shortest path between "+start+" to "+end);
               for(int a=0; a<shortestPath.size()-1; a++)
               {
                   City c1 = shortestPath.get(a);
                   City c2 = shortestPath.get(a+1);
                   System.out.println("\t"+c1+" to "+c2);
                   for(Edge edge: cityMap.get(c1))
                   {
                       if(edge.getStart().equals(c1) && edge.getDestination().equals(c2))
                           distance += edge.getDistance();
                       else if(edge.getStart().equals(c2) && edge.getDestination().equals(c1))
                           distance += edge.getDistance();
                   }

               }
               System.out.println("Distance between: "+distance+" miles\n\n");
           }
       }
    }

    public class City
    {
        String name;
        int uniqueID;

        public City(String n)
        {
            name = n;
            uniqueID = name.hashCode();
        }

        public String toString()
        {
            return name;
        }

        public int hashCode()
        {
            return uniqueID;
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            City other = (City)obj;
            return hashCode() == other.hashCode();
        }
    }

    public class Edge
    {
        City start;
        City destination;
        int uniqueID;
        int distance;
        String name;

        public Edge(City s, City d, int ds)
        {
            start = s;
            destination = d;
            distance = ds;
            uniqueID = start.hashCode()+destination.hashCode();
        }

        public String toString()
        {
            return start+" to " +destination+": "+distance;
        }

        public int hashCode()
        {
            return uniqueID;
        }

        public City getStart()
        {
            return start;
        }

        public City getDestination()
        {
            return destination;
        }

        public int getDistance()
        {
            return distance;
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            Edge other = (Edge)obj;
            return hashCode() == other.hashCode();
        }
    }

    public class Graph
    {
        HashSet<City> cities;
        HashSet<Edge> edges;

        public Graph(HashSet<City> c, HashSet<Edge> e)
        {
            cities = c;
            edges = e;
        }

        public HashSet<City> getCities()
        {
            return cities;
        }

        public HashSet<Edge> getEdges()
        {
            return edges;
        }
    }

    public class DijkstrasAlgorithm
    {
        ArrayList<City> cities;
        ArrayList<Edge> edges;
        HashSet<City> visitedCities;
        HashSet<City> unvisitedCities;
        HashMap<City, City> predecessors;
        HashMap<City, Integer> distances;

        public DijkstrasAlgorithm(Graph graph)
        {
            cities = new ArrayList<City>(graph.getCities());
            edges = new ArrayList<Edge>(graph.getEdges());

        }

        public void createTravelsPaths(City source)
        {
            visitedCities = new HashSet<City>();
            unvisitedCities = new HashSet<City>();
            predecessors = new HashMap<City, City>();
            distances = new HashMap<City, Integer>();
            distances.put(source, 0);
            unvisitedCities.add(source);

            while(unvisitedCities.size()>0)
            {
                City city = getMinimum(unvisitedCities);
                visitedCities.add(city);
                unvisitedCities.remove(city);
                findMinimalDistances(city);
            }
        }

        public City getMinimum(HashSet<City> cities)
        {
            City minimum = null;//change later
            for(City city: cities)
            {
                if(minimum == null)
                    minimum = city;
                else if(getShortestDistance(city)<getShortestDistance(minimum))
                    minimum = city;
            }
            return minimum;
        }

        public void findMinimalDistances(City temp)
        {
            ArrayList<City> adjacentNodes = getNeighbors(temp);
            for(City target: adjacentNodes)
            {
                if(getShortestDistance(target)>getShortestDistance(temp)+getDistance(temp, target))
                {

                    distances.put(target, getShortestDistance(temp)+getDistance(temp, target));
                    predecessors.put(target, temp);
                    unvisitedCities.add(target);

                }
            }
        }

        public ArrayList<City> getNeighbors(City temp)
        {
            ArrayList<City> neighbors = new ArrayList<City>();
            for(Edge edge: edges)
            {
                if(edge.getStart().equals(temp) && !wasVisited(edge.getDestination()))
                    neighbors.add(edge.getDestination());

                if(edge.getDestination().equals(temp) && !wasVisited(edge.getStart()))
                    neighbors.add(edge.getStart());
            }
            return neighbors;//fill
        }

        public boolean wasVisited(City city)
        {
            return visitedCities.contains(city);
        }

        public int getShortestDistance(City destination)
        {
            Integer dist = distances.get(destination);
            if(dist == null)
                return Integer.MAX_VALUE;
            return dist;
        }

        public int getDistance(City temp, City target)
        {
            for(Edge e: edges)
            {
                if((e.getStart().equals(temp) && e.getDestination().equals(target)) || (e.getStart().equals(target) && e.getDestination().equals(temp))){
                    return e.getDistance();
                }
            }
            throw new RuntimeException();
        }

        public ArrayList<City> getShortestPath(City target)
        {
            ArrayList<City> connectingCities = new ArrayList<City>();
            City step = target;

            if(predecessors.get(step) == null)
                return null;
            connectingCities.add(step);

            while(predecessors.get(step)!=null)
            {
                step = predecessors.get(step);
                connectingCities.add(step);
            }
            Collections.reverse(connectingCities);
            return connectingCities;
        }

    }
}
