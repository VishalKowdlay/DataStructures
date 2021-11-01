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

    public static void main(String[] args) {
        new MazeRunner();
    }

    public MazeRunner()
    {
        frame = new JFrame("Maze");
        frame.add(this);
        setMaze();
        frame.addKeyListener(this);
        frame.setSize(1480,670);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(new Color(100, 255, 255));
        g.fillRect(0,0, frame.getWidth(), frame.getHeight());
        g.setColor(Color.BLACK);
        for(int r = 0; r < maze.length; r++)
        {
            for(int c = 0; c < maze[r].length; c++)
            {
                if(maze[r][c].equals("#"))
                    g.fillRect(c*20,r*20,20,20);
            }

        }
        g.setColor(Color.BLUE);
        g.fillOval(hero.getLocation().getC()*20, hero.getLocation().getR()*20, 20, 20);
    }

    public void setMaze()
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
        hero = new Hero(new Location(2, 0), "East", maze);
    }

    public void createWalls(){
        walls = new ArrayList<>();
        for(int a=0; a<5; a++)
        {
            walls.add(ceiling(a));
        }
    }

    public Wall ceiling(int n){
        int[] x = {100+50*n, 900-50*n, 850-50*n, 150+50*n};
        int[] y = {100+50*n, 100+50*n, 150+50*n, 150+50*n};
        return new Wall(x, y);
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
}
