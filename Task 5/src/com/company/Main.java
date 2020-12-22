package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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
        g.fillRect(0, 350,600,150);
        g.setColor(Color.orange);
        g.fillRect(0, 0,600,350);
        g.setColor(Color.red);
        g.fillOval(50,50, 25,25);

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
