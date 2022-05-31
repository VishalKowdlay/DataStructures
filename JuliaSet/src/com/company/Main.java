package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main extends JPanel implements AdjustmentListener, ActionListener, MouseListener {

    BufferedImage juliaImage;
    JFrame frame;
    JScrollBar aBar, bBar, zoomBar, hueBar, satBar, eyeBar, brightBar;
    JLabel aLabel, bLabel, zoomLabel, hueLabel, satLabel, eyeLabel, brightLabel;
    JButton reset, save;
    JPanel scrollPanel, buttonPanel, bigPanel, labelPanel, brightPanel;
    JFileChooser fileChooser;

    double zoom = 1;
    double radius = 6;
    double a = 0, b = 0;
    float hue, saturation, eye, bright;
    float numIterations = 100f;
    int pixel = 1;

    public Main()
    {
        frame = new JFrame("Julia Set Program");
        frame.add(this);
        frame.setSize(1980, 1080);
        scrollPanel = new JPanel();

        aBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, -2000,  2000);//orientation, starting value, bar width, lowest value, highest value
        aBar.addAdjustmentListener(this);
        aBar.addMouseListener(this);
        a = aBar.getValue()/1000.0;


        bBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, -2000,  2000);//orientation, starting value, bar width, lowest value, highest value
        bBar.addAdjustmentListener(this);
        bBar.addMouseListener(this);
        b = bBar.getValue()/1000.0;

        zoomBar = new JScrollBar(JScrollBar.HORIZONTAL, 100, 0, 0,  1000);//orientation, starting value, bar width, lowest value, highest value
        zoomBar.addAdjustmentListener(this);
        zoomBar.addMouseListener(this);
        zoom = zoomBar.getValue()/100.0;

        hueBar = new JScrollBar(JScrollBar.HORIZONTAL, 1000, 0, 0,  1000);//orientation, starting value, bar width, lowest value, highest value
        hueBar.addAdjustmentListener(this);
        hueBar.addMouseListener(this);
        hue = hueBar.getValue()/1000.0f;

        eyeBar = new JScrollBar(JScrollBar.HORIZONTAL, 1000, 0, 0,  1000);//orientation, starting value, bar width, lowest value, highest value
        eyeBar.addAdjustmentListener(this);
        eyeBar.addMouseListener(this);
        eye = eyeBar.getValue()/1000.0f;

        satBar = new JScrollBar(JScrollBar.HORIZONTAL, 1000, 0, 0,  1000);//orientation, starting value, bar width, lowest value, highest value
        satBar.addAdjustmentListener(this);
        satBar.addMouseListener(this);
        saturation = satBar.getValue()/1000.0f;

        brightBar = new JScrollBar(JScrollBar.HORIZONTAL, 1000, 0, 0,  1000);//orientation, starting value, bar width, lowest value, highest value
        brightBar.addAdjustmentListener(this);
        brightBar.addMouseListener(this);
        bright = satBar.getValue()/1000.0f;

        scrollPanel.setLayout(new GridLayout(7, 1));
        scrollPanel.add(aBar);
        scrollPanel.add(bBar);
        scrollPanel.add(zoomBar);
        scrollPanel.add(hueBar);
        scrollPanel.add(satBar);
        scrollPanel.add(eyeBar);
        scrollPanel.add(brightBar);

        labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(7, 1));
        aLabel = new JLabel("A: "+a);
        bLabel = new JLabel("B: "+b);
        zoomLabel = new JLabel("Zoom: "+zoom);
        hueLabel = new JLabel("Hue: "+hue);
        satLabel = new JLabel("Sat: "+saturation);
        eyeLabel = new JLabel("Eye: "+eye);
        brightLabel = new JLabel("Bright: "+bright);
        aLabel.setPreferredSize(new Dimension(200, 20));
        bLabel.setPreferredSize(new Dimension(200, 20));
        zoomLabel.setPreferredSize(new Dimension(200, 20));
        hueLabel.setPreferredSize(new Dimension(200, 20));
        satLabel.setPreferredSize(new Dimension(200, 20));
        eyeLabel.setPreferredSize(new Dimension(200, 20));
        brightLabel.setPreferredSize(new Dimension(200, 20));

        labelPanel.add(aLabel);
        labelPanel.add(bLabel);
        labelPanel.add(zoomLabel);
        labelPanel.add(hueLabel);
        labelPanel.add(satLabel);
        labelPanel.add(eyeLabel);
        labelPanel.add(brightLabel);


        reset = new JButton("Reset");
        reset.addActionListener(this);

        save = new JButton("Save");
        save.addActionListener(this);

        String currDir=System.getProperty("user.dir");
        fileChooser=new JFileChooser(currDir);


        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1));
        buttonPanel.add(reset);
        buttonPanel.add(save);

        bigPanel = new JPanel();
        bigPanel.setLayout(new BorderLayout());
        bigPanel.add(labelPanel, BorderLayout.WEST);
        bigPanel.add(scrollPanel, BorderLayout.CENTER);
        bigPanel.add(buttonPanel, BorderLayout.EAST);
        frame.add(bigPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);/*makes frame visible, needs to be the last thing in constructor
        because there will be a lot of items created and they need to be done above setVisible */

    }

    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(drawJulia(g), 0, 0, null);

    }

    public BufferedImage drawJulia(Graphics g){
        int w, h;
        w = frame.getWidth();
        h = frame.getHeight();
        juliaImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for(int i=0; i<w; i+=pixel){
            for(int j=0; j<h; j+=pixel){
                float iter = numIterations;
                double zx = 1.5*(i-0.5*w)/(0.5*zoom*w);
                double zy = (j-0.5*h)/(0.5*zoom*h);
                while(zx*zx + zy*zy < radius && iter>0) {
                    double difference = zx*zx - zy*zy + a;
                    zy = 2*zx*zy + b;
                    zx = difference;
                    iter--;

                }
                int c;
                if(iter>0)
                    c = Color.HSBtoRGB(hue*(numIterations / iter) % 1, saturation, bright);
                else c = Color.HSBtoRGB(eye, saturation, bright);
                juliaImage.setRGB(i,j,c);
            }
        }
        return juliaImage;


    }

    public void saveImage()
    {
        if(juliaImage!=null) //juliaImage is the BufferedImage I declared globally (and used in
        //the drawJulia method)
        {
            FileFilter filter=new FileNameExtensionFilter("*.png","png");
            fileChooser.setFileFilter(filter);
            if(fileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
            {
                File file=fileChooser.getSelectedFile();
                try
                {
                    String st=file.getAbsolutePath();
                    if(st.indexOf(".png")>=0)
                        st=st.substring(0,st.length()-4);
                    ImageIO.write(juliaImage,"png",new File(st+".png"));
                }
                catch(IOException e)
                {
                }

            }
        }
    }


    public static void main(String[] args)
    {
        Main app = new Main();
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        if(e.getSource() == aBar){
            a=aBar.getValue()/1000.0;
        }

        if(e.getSource() == bBar){
            b=bBar.getValue()/1000.0;
        }

        if(e.getSource() == zoomBar){
            zoom=zoomBar.getValue()/100.0;
        }

        if(e.getSource() == hueBar){
            hue=hueBar.getValue()/1000.0f;
        }

        if(e.getSource() == satBar){
            saturation=satBar.getValue()/1000.0f;
        }

        if(e.getSource() == eyeBar){
            eye=eyeBar.getValue()/1000.0f;
        }

        if(e.getSource() == brightBar){
            bright=brightBar.getValue()/1000.0f;
        }

        aLabel.setText("A: "+a);
        bLabel.setText("B: "+b);
        zoomLabel.setText("Zoom: "+zoom);
        hueLabel.setText("Hue: "+hue);
        satLabel.setText("Sat: "+saturation);
        eyeLabel.setText("Eye: "+eye);
        brightLabel.setText("Bright: "+bright);

        repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==reset)
        {
            aBar.setValue(0);
            bBar.setValue(0);
            zoomBar.setValue(100);
            hueBar.setValue(1000);
            satBar.setValue(1000);
            brightBar.setValue(1000);
            eyeBar.setValue(1000);

            a = 0.0f;
            b = 0.0f;
            zoom = 1.0f;
            hue = 1.0f;
            saturation = 1.0f;
            eye = 1.0f;
            bright = 1.0f;

            aLabel.setText("A: "+a);
            bLabel.setText("B: "+b);
            zoomLabel.setText("Zoom: "+zoom);
            hueLabel.setText("Hue: "+hue);
            satLabel.setText("Sat: "+saturation);
            eyeLabel.setText("Eye: "+eye);
            brightLabel.setText("Bright: "+bright);
            repaint();
        }

        if(e.getSource()==save)
        {
            saveImage();
        }



    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        pixel = 3;
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pixel = 1;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
