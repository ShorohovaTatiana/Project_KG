package com.company;

import com.sun.javafx.iio.ImageFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;



class MyComponent extends JComponent {
    BufferedImage img;

    int x =0;
    private void DDA(int x1, int y1, int x2, int y2, Graphics g) {

        float L = Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1));
        float dx = (x2 - x1) / L;
        float dy = (y2 - y1) / L;
        float x = x1;
        float y = y1;

        for (int i = 0; i <= L; i++) {
            x += dx;
            y += dy;
            g.drawRect((int) x, (int) y, 1, 1);
        }
    }

    private void Bresenham(int x1,int y1, int x2, int y2, Graphics g) {

        int x = x1;
        int y = y1;

        int Dx = x2 - x1;
        int Dy = y2 - y1;

        int e = 2 * Dy - Dx;

        for (int i = 1; i <= Dx; i++) {
            g.drawRect(x, y, 1, 1);
            if (e >= 0) {
                y++;
                e += -2 * Dx + 2 * Dy;
            } else
                e += 2 * Dy;
            x++;
        }
    }
    private void Wu(int x1, int y1, int x2, int y2, Graphics g) {

        int x = x1;
        int y = y1;
        int Dx = x2 - x1;
        int Dy = y2 - y1;
        int e = 2 * Dy - Dx;

        for (int i = 1; i <= Dx; i++) {
            if (e >= 0){
                g.fillRect( x, y, 1, 1);
                g.fillRect( x, y + 1, 1, 1);
                y++;
                e += -2 * Dx + 2 * Dy;

            } else {

                g.fillRect(x, y, 1, 1);
                g.fillRect(x, y - 1, 1, 1);
                e += 2 * Dy;
            }
            x++;
        }
    }

    private void Dr_Circle(int center_x, int center_y, int radius, Graphics g){
         int x = 0, y = radius, sigma = 0, delta = 2 - 2 * radius;
         while (y >= 0){
             //считаем для одной четверти, и симметрично заполняем остальные
             g.drawLine(center_x + x, center_y - y, center_x + x, center_y - y);     // 1 четверть
             g.drawLine(center_x - x, center_y - y, center_x - x, center_y - y);     // 2 четверть
             g.drawLine(center_x - x, center_y + y, center_x - x, center_y + y);     // 3 четверть
             g.drawLine(center_x + x, center_y + y, center_x + x, center_y + y);     // 4 четверть
             sigma = 2 * (delta + y) - 1;
             if (delta < 0 && sigma <= 0) {          //перемещение по горизонтали
                 x++;
                 delta += x + 1;
             } else if (delta > 0 && sigma > 0) {    //перемещение по вертикали
                 y--;
                 delta -= y + 1;
             } else {                                //перемещение по диагонали
                 x++;
                 delta += x - y;
                 y--;
             }
         }
     }

    public MyComponent() {
        try {
            // чтение
            img = ImageIO.read(new File("tree.png"));
            // запись
            ImageIO.write(img, "gif", new File("/tree.gif"));
            // выводим в консоль список поддерживаемых форматов
            String formats[] = ImageIO.getWriterFormatNames();
            for(String s : formats)
                System.out.print(s+", ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g.setColor(Color.BLACK);
        Dr_Circle(150, 170, 110, g); //координаты центра и радиус
        DDA(0,100,200,200, g);
        Bresenham(200,30,300,300, g);
        Wu(10,40,400,400, g);
    }
    static class ImageFrame extends JFrame
    {
        public ImageFrame()
        {
            setTitle("ImageTest");
            setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

            // Добавление компонента к фрейму.

            MyComponent component = new MyComponent();
            add(component);
        }
        public static final int DEFAULT_WIDTH = 600;
        public static final int DEFAULT_HEIGHT = 500;
    }
    public void start() {
        javax.swing.Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextStep();
            }
        } );
        timer.start();
    }

    private void nextStep() {
        x += 25;
        repaint();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run()
            {
                ImageFrame frame = new ImageFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });

    }
}
