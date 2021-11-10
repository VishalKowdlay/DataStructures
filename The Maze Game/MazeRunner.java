import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;

public class MazeRunner extends JPanel implements KeyListener, MouseListener {

    JFrame frame;
    String[][] maze;
    Hero hero;
    ArrayList<Wall> walls;
    int score;

    public MazeRunner()
    {
        setJFrame();
        readFromFile();
        hero = new Hero(new Location(2, 0), "East", maze);
        createWalls();
        score = 0;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
    
        draw2dMaze(g);
        draw3dMaze(g);
        drawDirections(g);  
        drawScore(g);      
    }
  

    @Override
    public void keyPressed(KeyEvent e) {
        hero.move(e.getKeyCode());
        score++;
        createWalls();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}


    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    private void setJFrame()
    {
        frame = new JFrame("Maze");
        frame.add(this);
        frame.addKeyListener(this);
        frame.setSize(1500,1500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void drawWalls(Graphics g)
    {
        
        for(Wall wall: walls) {
            if(wall.getIfEmpty()){
                g.setColor(Color.BLACK);
                g.drawPolygon(wall.getPoly());
                g.setColor(Color.DARK_GRAY);
                g.fillPolygon(wall.getPoly());
            }
        }
    }

    private void draw2dMaze(Graphics g)
    {
        g.setColor(Color.black);
        g.fillRect(0,0, frame.getWidth(), frame.getHeight());
           
        g.setColor(Color.lightGray);
        g.fillRect(50, 0, 850, 1500);
        
        g.setColor(Color.black);
        for(int r = 0; r < maze.length; r++)
        {
            for(int c = 0; c < maze[r].length; c++)
            {
                if(maze[r][c].equals("#"))
                    g.fillRect(300+c*5,100+r*5,5,5);
            }

        }
        g.setColor(Color.BLUE);
        g.fillOval(300+hero.getLocation().getC()*5, 100+hero.getLocation().getR()*5, 5, 5);
    }

    private void readFromFile()
    {
        maze = new String[33][73];
        File file = new File("Mazes", "maze1.txt");
        try{
            BufferedReader input = new BufferedReader(new FileReader(file));
            String text;
            int r = 0;
            while((text = input.readLine()) != null){
                maze[r] = text.split("");
                r++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Wall makeRightWall(int dist){
		switch(dist){
			case 0:
				return (new Wall(new int[] {900, 750, 750, 900}, new int[] {300, 350, 750, 800}));
			case 1:
				return (new Wall(new int[] {750, 650, 650, 750}, new int[] {350, 400, 700, 750}));
			case 2:
				return (new Wall(new int[] {650, 600, 600, 650}, new int[] {400, 450, 650, 700}));
			default:
				return new Wall();
		}
	}
	public Wall makeLeftWall(int dist){
		switch(dist){
			case 0:
				return new Wall(new int[] {50,200,200,50}, new int[] {300,350,750,800});
			case 1:
				return new Wall(new int[] {200, 300, 300, 200}, new int[] {350, 400, 700,750});
			case 2:
				return new Wall(new int[] {300, 350, 350, 300}, new int[] {400, 450,650,700});
			default:
				return new Wall();
		}
	}

	public Wall makeStandardWall(int dist){
		switch(dist){
			case 1:
				return new Wall(new int[] {200,750,750,200}, new int[]{350,350,750,750});
			case 2:
				return (new Wall(new int[] {300,700,700,300}, new int[] {400,400,700,700}));
			case 3:
				return (new Wall(new int[] {300,650,650,300}, new int[] {450,450,650,650}));
		}
		return new Wall();
	}

    private void draw3dMaze(Graphics g)
    {
        draw3DBase(g);
        drawWalls(g);
    }

    
    private void createWalls(){
        walls = new ArrayList<>();
        int playerRow = hero.getLocation().getR();
        int playerCol = hero.getLocation().getC();
        String wall = "#";
        
        if(hero.getDir().equals("South"))
        {
            for(int row = hero.getLocation().getR() + hero.SIGHT; row >= hero.getLocation().getR(); row--)
            {
                if(row < maze.length)
                {
                    if(playerCol+1 < maze[row].length){
						if(maze[row][playerCol+1].equals(wall)){
							walls.add(makeLeftWall(Math.abs(row-playerRow)));
						}
					}
                    if(playerCol - 1 >= 0){
						if(maze[row][playerCol-1].equals(wall)){
							walls.add(makeRightWall(Math.abs(row-playerRow)));
						}
					}
					if(maze[row][playerCol].equals(wall)){
						walls.add(makeStandardWall(Math.abs(row-playerRow)));
					}
                }
            }
        }

        if(hero.getDir().equals("North"))
        {
            for(int row = hero.getLocation().getR() - hero.SIGHT; row <= hero.getLocation().getR(); row++)
            {
                if(row >= 0)
                {
                    if(playerCol+1 < maze[row].length){
						if(maze[row][playerCol+1].equals(wall)){
							walls.add(makeRightWall(Math.abs(row-playerRow)));
						}
					}
                    if(playerCol - 1 >= 0){
						if(maze[row][playerCol-1].equals(wall)){
							walls.add(makeLeftWall(Math.abs(row-playerRow)));
						}
					}
					if(maze[row][playerCol].equals(wall)){
						walls.add(makeStandardWall(Math.abs(row-playerRow)));
					}
                }
            }
        }

        if(hero.getDir().equals("East"))
        {
            for(int col = playerCol+hero.SIGHT; col >= playerCol; col--){
				if(col < maze[playerRow].length){
					if(playerRow+1 < maze.length){
						if(maze[playerRow+1][col].equals(wall)){
							walls.add(makeRightWall(Math.abs(col-playerCol)));
						}
					}
					if(playerRow-1 >= 0){
						if(maze[playerRow-1][col].equals(wall)){
							walls.add(makeLeftWall(Math.abs(col-playerCol)));
						}
					}
					if(maze[playerRow][col].equals(wall)){
						walls.add(makeStandardWall(Math.abs(col-playerCol)));
					}
				}
			}
        }

        if(hero.getDir().equals("West"))
        {
            for(int col = playerCol-hero.SIGHT; col <= playerCol; col++){
				if(col >= 0){
					if(playerRow+1 < maze.length){
						if(maze[playerRow+1][col].equals(wall)){
							walls.add(makeLeftWall(Math.abs(col-playerCol)));
						}
					}

					if(playerRow-1 >= 0){
						if(maze[playerRow-1][col].equals(wall)){
							walls.add(makeRightWall(Math.abs(col-playerCol)));
						}
					}

					if(maze[playerRow][col].equals(wall)){
						walls.add(makeStandardWall(Math.abs(col-playerCol)));
					}
				}
			}
        }
    }

    private void drawScore(Graphics g)
    {
        Font font = g.getFont().deriveFont( 50.0f );
        g.setFont( font );
        g.setColor(Color.WHITE);
        
        g.drawString("Walked " + score + " steps!", 1000, 600);
           
    } 

    private void drawDirections(Graphics g)
    {
        Font font = g.getFont().deriveFont( 50.0f );
        g.setFont( font );
        g.setColor(Color.WHITE);
        
        switch(hero.getDir())
        {
            case "North": g.drawString("Direction: NORTH", 1000, 300);
            break;

            case "South": g.drawString("Direction: SOUTH", 1000, 300);
            break;

            case "East": g.drawString("Direction: EAST", 1000, 300);
            break;

            case "West": g.drawString("Direction: WEST", 1000, 300);
        }
    }



    
    private void draw3DBase(Graphics g)
    {
        Color wallColor = Color.DARK_GRAY;
        g.setColor(new Color(77, 39, 25));
        g.fillRect(50,650,850, 150);

        int xPoint = 50;
        int yPoint = 350;
        int width = 150;
        int length = 400;
        for(int i = 0; i < 3; i++){
            g.setColor(wallColor);
            g.fillRect(xPoint, yPoint, width, length);
            xPoint+=width;
            yPoint+=50;
            width-=50;
            length-=100;
        }

        xPoint = 600;
        yPoint = 450;
        width = 50;
        length = 200;
        for(int i = 0; i < 3; i++){
            g.setColor(wallColor);
            g.fillRect(xPoint, yPoint, width, length);
            xPoint+=width;
            yPoint-=50;
            width+=50;
            length+=100;
        }
    }

    public static void main(String[] args) {
        new MazeRunner();
    }
}
