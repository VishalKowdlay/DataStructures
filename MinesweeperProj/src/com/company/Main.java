package com.company;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class Main extends JPanel implements ActionListener, MouseListener{

    //Pogius Champious
    JPanel boardPanel;
    JButton reset;
    JMenuBar menuBar;
    JMenu diff;
    JMenuItem beginner, intermediate, expert;
    JToggleButton[][] board;
    Timer timer;
    int timePassed = 0;
    JTextField timeField;
    GraphicsEnvironment ge;
    Font clockFont;
    int numNotClicked;
    int dimR = 9, dimC = 9;
    boolean firstClick = true;
    int numMines=10;
    boolean gameOn=true;
    JFrame frame;
    int buttonSize = 35;
    ImageIcon mineIcon, flag, smile, wow, lose, win;
    ImageIcon[] numbers;

    public Main()
    {
        timeField=new JTextField("   "+timePassed);
        timeField.setText(" "+timePassed);
        frame = new JFrame();
        frame.add(this);
        String st = "C:\\Users\\twins\\IdeaProjects\\MinesweeperProj\\src\\com\\company\\";
        mineIcon = new ImageIcon(st+"mine.png");
        mineIcon = new ImageIcon(mineIcon.getImage().getScaledInstance(buttonSize, buttonSize, Image.SCALE_SMOOTH));
        flag = new ImageIcon(st+"flag.png");
        flag = new ImageIcon(flag.getImage().getScaledInstance(buttonSize, buttonSize, Image.SCALE_SMOOTH));
        smile = new ImageIcon(st+"smile1.png");
        smile = new ImageIcon(smile.getImage().getScaledInstance(buttonSize, buttonSize, Image.SCALE_SMOOTH));
        lose = new ImageIcon(st+"dead1.png");
        lose = new ImageIcon(lose.getImage().getScaledInstance(buttonSize, buttonSize, Image.SCALE_SMOOTH));
        wow = new ImageIcon(st+"wait1.png");
        wow = new ImageIcon(wow.getImage().getScaledInstance(buttonSize, buttonSize, Image.SCALE_SMOOTH));
        win = new ImageIcon(st+"win1.png");
        win = new ImageIcon(win.getImage().getScaledInstance(buttonSize, buttonSize, Image.SCALE_SMOOTH));
        numbers = new ImageIcon[8];
        for(int i=0; i<numbers.length; i++)
        {
            numbers[i] = new ImageIcon(st+(i+1)+".png");
            numbers[i] = new ImageIcon(numbers[i].getImage().getScaledInstance(buttonSize, buttonSize, Image.SCALE_SMOOTH));

        }

        try {
            ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            clockFont=Font.createFont(Font.TRUETYPE_FONT,
                    new File(st+"digital-7.ttf"));
            ge.registerFont(clockFont);
        } catch (IOException|FontFormatException e) {
        }
        createBoard(dimR, dimC);
        menuBar = new JMenuBar();
        diff = new JMenu("Difficulty");
        beginner = new JMenuItem("Beginner");
        intermediate = new JMenuItem("Intermediate");
        expert = new JMenuItem("Expert");
        beginner.addActionListener(this);
        intermediate.addActionListener(this);
        expert.addActionListener(this);
        diff.add(beginner);
        diff.add(intermediate);
        diff.add(expert);
        menuBar.add(diff);
        reset = new JButton();
        reset.setIcon(smile);
        reset.addActionListener(this);
        menuBar.add(reset);
        menuBar.setLayout(new GridLayout(1,3));
        menuBar.add(timeField);
        timeField.setFont(clockFont.deriveFont(18f));
        timeField.setBackground(Color.BLACK);
        timeField.setForeground(Color.GREEN);
        frame.add(menuBar, BorderLayout.NORTH);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        Main app = new Main();

    }

    public void createBoard(int row, int col)
    {
        if(boardPanel != null)
            frame.remove(boardPanel);

        board = new JToggleButton[row][col];
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(row, col));

        for(int r=0; r<row; r++)
        {
            for(int c=0; c<col; c++)
            {
                board[r][c] = new JToggleButton();
                board[r][c].putClientProperty("row", r);
                board[r][c].putClientProperty("col", c);
                board[r][c].putClientProperty("state", 0);
                board[r][c].addMouseListener(this);
                boardPanel.add(board[r][c]);

            }
        }
        numNotClicked = row*col;
        frame.add(boardPanel, BorderLayout.CENTER);
        frame.setSize(board[0].length*buttonSize, board.length*buttonSize);
        frame.revalidate();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
        if(e.getSource() == beginner){
            dimR = 9;
            dimC = 9;
            numMines = 10;
        }
        if(e.getSource() == intermediate){
            dimR = 16;
            dimC =16;
            numMines = 40;
        }
        if(e.getSource() == expert){
            dimR = 16;
            dimC = 40;
            numMines = 99;
        }
        if(e.getSource() == reset){
            reset.setIcon(smile);
        }
        createBoard(dimR, dimC);
        firstClick = true;
        gameOn = true;
        if(timer!=null)
            timer.cancel();

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(gameOn)
            reset.setIcon(wow);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int row = (int)((JToggleButton)e.getComponent()).getClientProperty("row");
        int col = (int)((JToggleButton)e.getComponent()).getClientProperty("col");

        if(gameOn)
        {
            if(e.getButton() == MouseEvent.BUTTON1 && board[row][col].isEnabled())
            {
                if(firstClick)
                {
                    timer=new Timer();
                    timePassed=0;
                    timer.schedule(new UpdateTimer(),0,1000);
                    setBombsAndNums(row, col);
                    firstClick = false;
                }
                board[row][col].setSelected(true);
                board[row][col].setEnabled(false);
                numNotClicked -=1;
                reset.setIcon(smile);
                int state = (int)board[row][col].getClientProperty("state");
                if(state == -1)
                {
                    board[row][col].setContentAreaFilled(false);
                    gameOn = false;
                    board[row][col].setOpaque(true);
                    board[row][col].setBackground(Color.RED);
                    reset.setIcon(lose);
                    timer.cancel();
                    displayMines();
                    //you lose!
                }
                else
                {
                    expand(row, col);
                    checkWin();
                }
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                if(!board[row][col].isSelected())
                {
                    if(board[row][col].getIcon() == null)
                    {
                        board[row][col].setIcon(flag);
                        board[row][col].setDisabledIcon(flag);
                        board[row][col].setEnabled(false);
                    }
                    else
                    {
                        board[row][col].setIcon(null);
                        board[row][col].setDisabledIcon(null);
                        board[row][col].setEnabled(true);
                    }
                }
            }
        }

    }

    public void displayMines(){
        for(int row=0; row<board.length; row++)
        {
            for(int col=0; col<board[0].length; col++)
            {
                int state = (int)board[row][col].getClientProperty("state");
                if(state==-1){
                    board[row][col].setIcon(mineIcon);
                    board[row][col].setDisabledIcon(mineIcon);
                }
                board[row][col].setEnabled(false);
            }
        }
    }
    public void checkWin()
    {
        if(numNotClicked == numMines)
        {
            for(int r=0; r<board.length; r++)
            {
                for(int c=0; c<board[0].length; c++)
                {
                    board[r][c].setEnabled(false);
                }
            }
            reset.setIcon(win);
            timer.cancel();
        }

    }

    public void expand(int row, int col) {
        if(!board[row][col].isSelected())
        {
            board[row][col].setSelected(true);
            //board[row][col].setContentAreaFilled(false);
            board[row][col].setEnabled(false);
            numNotClicked -=1;
        }
        int stateButton = (int)board[row][col].getClientProperty("state");
        if(stateButton>0){
            board[row][col].setIcon(numbers[stateButton-1]);
            board[row][col].setDisabledIcon(numbers[stateButton-1]);

        }
        else{
            for(int relR=row-1; relR<=row+1; relR++){
                for(int relC=col-1; relC<=col+1; relC++){
                    try{
                        if(!board[relR][relC].isSelected())
                            expand(relR, relC);

                    }catch(ArrayIndexOutOfBoundsException e){

                    }
                }
            }
        }
    }

    public void setBombsAndNums(int selectedRow, int selectedCol)
    {
        int count = numMines;

        while(count>0)
        {
            int row = (int)(Math.random()*dimR);
            int col = (int)(Math.random()*dimC);    
            int state = Integer.parseInt(""+board[row][col].getClientProperty("state"));

            //if(state==0 && (row != selectedRow || col != selectedCol)) //Allows for mine to be within 1 step of where you clicked but not where you clicked
            if(state==0 && Math.abs(selectedRow-row)>1 && Math.abs(selectedCol-col)>1) //Allows for mines to not spawn within 1 unit around first click
            {
                board[row][col].putClientProperty("state", -1);
                count--;
            }

        }
        for(int r=0; r<board.length; r++){
            for(int c=0; c<board[0].length; c++){
                int state = (int)board[r][c].getClientProperty("state");
                if(state==-1){
                    for(int relR=r-1; relR<=r+1; relR++){
                        for(int relC=c-1; relC<=c+1; relC++){
                            try{
                                int relCount = (int)board[relR][relC].getClientProperty("state");
                                if(relCount!=-1){
                                    board[relR][relC].putClientProperty("state", relCount+1);
                                }
                            }catch(ArrayIndexOutOfBoundsException e){

                            }

                        }
                    }
                }
            }
        }
//        for(int r=0; r<board.length; r++){
//            for(int c=0; c<board[0].length; c++) {
//                int state = (int)board[r][c].getClientProperty("state");
//                board[r][c].setText(""+state);
//            }
//        }
    }
    public class UpdateTimer extends TimerTask {
        @Override
        public void run() {
            if(gameOn)
            {
                timePassed++;
                timeField.setText(timePassed+"");
                System.out.println(timePassed);

            }
        }
    }


}


