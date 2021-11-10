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

    public MazeRunner()
    {
        setJFrame();
        readFromFile();
        hero = new Hero(new Location(2, 0), "East", maze);
        createWalls();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw2dMaze(g);
        drawWalls(g);
    }

    private void createWalls(){
        walls = new ArrayList<>();
        walls.add(new Wall(new int[]{1, 2, 3, 4}, new int[]{1, 2, 3, 4}));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        hero.move(e.getKeyCode());
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
        frame.setSize(1480,670);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void drawWalls(Graphics g)
    {
        for(Wall wall: walls) g.drawPolygon(wall.getPoly());
    }

    private void draw2dMaze(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(0,0, frame.getWidth(), frame.getHeight());
        g.setColor(Color.BLACK);
        for(int r = 0; r < maze.length; r++)
        {
            for(int c = 0; c < maze[r].length; c++)
            {
                if(maze[r][c].equals("#"))
                    g.fillRect(c*5,r*5,5,5);
            }

        }
        g.setColor(Color.BLUE);
        g.fillOval(hero.getLocation().getC()*5, hero.getLocation().getR()*5, 5, 5);
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
    public static void main(String[] args) {
        new MazeRunner();
    }
}
