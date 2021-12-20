public class Passenger
{
    private String name;
    String city;
    String time;

    public Passenger(String n, String c, String t)
    {
        name = n;
        city = c;
        time = t;
    }   
    
    public String getLastName()
    {
        return name.substring(name.indexOf(" "));
    }

    public String getFirstName()
    {
        return name.substring(0, name.indexOf(" "));
    }

    public String getFlightCity()
    {
        return city;
    }

    public String getTime()
    {
        return time;
    }

    public String toString()
    {
        return name+", "+city+", "+time;
    }

    public int etdCalc(String currentTime){
        String[] flightTimeSplit = time.split(" |:");
        String[] currentTimeSplit = currentTime.split(" |:");
        int flightTimeMinutes = 0;
        int currentTimeMinutes = 0;

        if(flightTimeSplit[2].equals("PM")){
            flightTimeMinutes = ((Integer.parseInt(flightTimeSplit[0]) + 12) * 60) + Integer.parseInt(flightTimeSplit[1]);
        } else {
            flightTimeMinutes = ((Integer.parseInt(flightTimeSplit[0])) * 60) + Integer.parseInt(flightTimeSplit[1]);
        }
        if(currentTimeSplit[2].equals("PM")){
            currentTimeMinutes = ((Integer.parseInt(currentTimeSplit[0]) + 12) * 60) + Integer.parseInt(currentTimeSplit[1]);
        } else {
            currentTimeMinutes = ((Integer.parseInt(currentTimeSplit[0])) * 60) + Integer.parseInt(currentTimeSplit[1]);
        }
        return flightTimeMinutes - currentTimeMinutes;
    }

    public boolean compareTo(Passenger other)
    {
        int timeDiff = other.etdCalc(other.getTime());
        if(timeDiff > 60)
        {
            return true;
        }
        else return false;


    }






}