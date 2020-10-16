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
        g.setColor(Color.CYAN);
        g2.drawImage(img, 0, 0, null);
        g.drawLine(380, 2, 100, 300);
        g.setColor(Color.BLACK);
        g.drawOval(30, 50, 200, 200);
        g.setColor(Color.blue);
        g.fillOval(200, 200, 200, 200);
        g.setColor(Color.GRAY);
        g.drawOval(150, 350, 200, 100);
        g.setColor(Color.red);
        g.drawRect(100, 70, 200, 200);
        g.setColor(Color.orange);
        g.fillRect(500, 280, 70, 110);

        g.setColor(Color.BLACK);
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
