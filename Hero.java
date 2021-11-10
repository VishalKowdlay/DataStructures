public class Hero {
    private Location loc;
    private String dir;
    private String[][] maze;

    public Hero(Location loc, String dir, String[][] maze)
    {
        this.loc = loc;
        this.dir = dir;
        this.maze = maze;
    }

    public Location getLocation()
    {
        return loc;
    }

    public void move(int key)
    {
        if(key==37)
        {
            if(dir.equals("East"))
                dir = "North";
            else if(dir.equals("North"))
                dir = "West";
            else if(dir.equals("West"))
                dir = "South";
            else if(dir.equals("South"))
                dir = "East";

        }

        if(key==39)
        {
            if(dir.equals("East"))
                dir = "South";
            else if(dir.equals("South"))
                dir = "West";
            else if(dir.equals("West"))
                dir = "North";
            else if(dir.equals("North"))
                dir = "East";

        }

        if(key==38)
        {
            int r = loc.getR();
            int c = loc.getC();
            if(dir.equals("East"))
                if(maze[r][c+1].equals(" "))
                    loc.setC(c+1);
            if(dir.equals("North"))
                if(maze[r-1][c].equals(" "))
                    loc.setR(r-1);
            if(dir.equals("West"))
                if(maze[r][c-1].equals(" "))
                    loc.setC(c-1);
            if(dir.equals("South"))
                if(maze[r+1][c].equals(" "))
                    loc.setR(r+1);

        }
    }

}
