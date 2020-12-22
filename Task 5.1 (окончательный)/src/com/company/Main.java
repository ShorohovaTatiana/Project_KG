package com.company;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

@SuppressWarnings("serial")
class MovingCircle extends JComponent implements ActionListener{

    private double scale;
    private Color color;
    private Timer timer;
    public double x =250;
    public double y =270;

    public double x1 =250;
    public double y1 =350;

    public double xc1 =0;
    public double yc1 =200;

    public double p =0;

    public MovingCircle(Color color, int delay) {
        scale = 1.0;
        timer = new Timer(delay, this);
        this.color = color;
        setPreferredSize(new Dimension(500, 500));
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        int width = 500;
        int height = 500;
        g.fillRect(0, 0, width, height);
        g.setColor(Color.pink);
        g.fillRect(0, 0, 700, 350);
        g2d.setColor(Color.black);
        g2d.drawRect(0, 0, width - 1, height - 1);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        g2d.scale(scale, scale);
        y1++;
        Ellipse2D el = new Ellipse2D.Double(x-25, y, 80+p/2, 80+p/2);
        g2d.fill(el);
        g.setColor(Color.blue);
        g.fillRect(0, 350, 500, 350);
        g2d.drawRect(0, 0, width - 1, height - 1);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.orange);
        g2d.scale(scale, scale);
        y--;
        Ellipse2D e = new Ellipse2D.Double(x1-25, y1, 80-p, 40-p);
        g2d.fill(e);
        p=p+0.3;

        g2d.setColor(Color.white);
        xc1++;
        int i=-400;
        while (i<10000){
            Ellipse2D e1 = new Ellipse2D.Double(xc1+i, yc1, 20, 20);
            Ellipse2D e2 = new Ellipse2D.Double(xc1 + 10+i, yc1 - 10, 30, 30);
            Ellipse2D e3 = new Ellipse2D.Double(xc1 + 30+i, yc1, 20, 20);
            g2d.fill(e1);
            g2d.fill(e2);
            g2d.fill(e3);
            i=i+190;
        }

        int j=0;
        while (j<8000){
            Ellipse2D e1 = new Ellipse2D.Double(xc1+j, yc1 + 100, 20, 20);
            Ellipse2D e2 = new Ellipse2D.Double(xc1 + 10+j, yc1 + 90, 30, 30);
            Ellipse2D e3 = new Ellipse2D.Double(xc1 + 30+j, yc1+ 100, 20, 20);
            g2d.fill(e1);
            g2d.fill(e2);
            g2d.fill(e3);
            j=j+150;
        }

        int k=-800;
        while (k<12000){
            Ellipse2D e1 = new Ellipse2D.Double(xc1 + k, yc1 - 50, 40, 40);
            Ellipse2D e2 = new Ellipse2D.Double(xc1 + 15 + k, yc1 - 60, 60, 60);
            Ellipse2D e3 = new Ellipse2D.Double(xc1 + 50 + k, yc1 - 50, 40, 40);
            g2d.fill(e1);
            g2d.fill(e2);
            g2d.fill(e3);
            k=k+350;
        }

        int n=-750;
        while (n<8000){
            Ellipse2D e1 = new Ellipse2D.Double(xc1+n, yc1 - 150, 20, 20);
            Ellipse2D e2 = new Ellipse2D.Double(xc1 + 10+n, yc1 - 160, 30, 30);
            Ellipse2D e3 = new Ellipse2D.Double(xc1 + 30+n, yc1 - 150, 20, 20);
            g2d.fill(e1);
            g2d.fill(e2);
            g2d.fill(e3);
            n=n+170;
        }

        g2d.setColor(Color.black);
        int l=-950;
        while (l<8000){
            Ellipse2D e1 = new Ellipse2D.Double(xc1-200+ l, yc1 + 170, 80, 2);
            Ellipse2D e2 = new Ellipse2D.Double(xc1 - 220+l, yc1 + 200, 60, 3);
            Ellipse2D e3 = new Ellipse2D.Double(xc1 - 230+l, yc1 + 250, 20, 2);
            g2d.fill(e1);
            g2d.fill(e2);
            g2d.fill(e3);
            l=l+170;
        }

        g2d.setColor(Color.cyan);
        int m=-700;
        while (m<8000){
            Ellipse2D e1 = new Ellipse2D.Double(xc1-200+ m, yc1 + 190, 80, 1);
            Ellipse2D e2 = new Ellipse2D.Double(xc1 - 220+m, yc1 + 220, 60, 2);
            Ellipse2D e3 = new Ellipse2D.Double(xc1 - 230+m, yc1 + 260, 20, 1);
            g2d.fill(e1);
            g2d.fill(e2);
            g2d.fill(e3);
            m=m+170;
        }
        g.setColor(Color.darkGray);
        g.fillRect(0,480,500,60);

        g.setColor(Color.black);
        g.fillRect(210,270,10,240);
        g.fillRect(190,430,5,100);
        g.fillRect(235,430,5,100);
        g.fillRect(188,440,55,5);
        g.fillRect(188,450,55,3);

        g.fillRect(310,270,10,240);
        g.fillRect(335,430,5,100);
        g.fillRect(290,430,5,100);
        g.fillRect(288,440,55,5);
        g.fillRect(288,450,55,3);

        g.fillRect(200,290,130,10);

        g.fillRect(200,270,130,5);
        g.fillRect(195,260,140,10);

    }

        public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Moving Circle");
                JPanel panel = new JPanel();

                final MovingCircle MovingCircleRed = new MovingCircle(Color.red, 40);
                panel.add(MovingCircleRed);

                frame.getContentPane().add(panel);
                final JButton button = new JButton("Start");
                button.addActionListener(new ActionListener() {
                    private boolean pulsing = false;
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (pulsing) {
                            pulsing = false;
                            MovingCircleRed.stop();
                            button.setText("Start");
                        } else {
                            pulsing = true;
                            MovingCircleRed.start();
                            button.setText("Stop");
                        }
                    }
                });
                panel.add(button);
                 /*
                final MovingLine MovingL = new MovingLine(Color.red, 20);
                panel.add(MovingL);
                frame.getContentPane().add(panel);
                final JButton button1 = new JButton("Start1");
                button1.addActionListener(new ActionListener() {
                    private boolean pulsing = false;
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (pulsing) {
                            pulsing = false;
                            MovingL.stop();
                            button1.setText("Start1");
                        } else {
                            pulsing = true;
                            MovingL.start();
                            button1.setText("Stop1");
                        }
                    }
                });
                panel.add(button1);*/
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(600, 550);
                frame.setVisible(true);
            }
        });
    }
}
