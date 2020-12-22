package com.company;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import java.awt.*;
import java.util.ArrayList;

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
            for (String s : formats)
                System.out.print(s + ", ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        ArrayList<Point> Points = new ArrayList<>();

        for(int i=100;i<200;i++){
            Point p = new Point(100+i, 150+i);
            Points.add(i-100,p);
        }
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.BLACK);
        bezierGoGo(Points,g);
    }

    static class ImageFrame extends JFrame {
        public ImageFrame() {
            setTitle("ImageTest");
            setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

            // Добавление компонента к фрейму.

            MyComponent component = new MyComponent();
            add(component);
        }

        public static final int DEFAULT_WIDTH = 600;
        public static final int DEFAULT_HEIGHT = 500;
    }


    private void bezierGoGo(ArrayList<Point> sourcePoints, Graphics painter) {
        // ф-ия расчитывает финальный набор точек, по которым будет строится кривуля, а затем рисует ее
        ArrayList<Point> finalPoints = new ArrayList<>();

        for (double t = 0; t <= 1; t += 0.1){
            finalPoints.add(calculateBezierFunction(t, sourcePoints));
        }
        drawCurve(finalPoints, painter);
    }

    private Point calculateBezierFunction(double t, ArrayList<Point> srcPoints) {   // ф-ия расчитывает очередную точку на кривой исходя из входного набора управляющих точек
        double x = 0;
        double y = 0;

        int n = srcPoints.size() - 1;
        for (int i = 0; i <= n; i++) {
            x += fact(n) / (fact(i) * fact(n - i)) * srcPoints.get(i).getX() * Math.pow(t, i) * Math.pow(1 - t, n - i);
            y += fact(n) / (fact(i) * fact(n - i)) * srcPoints.get(i).getY() * Math.pow(t, i) * Math.pow(1 - t, n - i);
        }
        return new Point((int) x, (int) y);
    }

    private double fact(double arg) {
        if (arg < 0) throw new RuntimeException("negative argument.");
        if (arg == 0) return 1;

        double rezult = 1;
        for (int i = 1; i <= arg; i++)
            rezult *= i;
        return rezult;
    }

    private void drawCurve(ArrayList<Point> points, Graphics painter) {
        for (int i = 1; i < points.size(); i++) {
            int x1 = (int) (points.get(i - 1).getX());
            int y1 = (int) (points.get(i - 1).getY());
            int x2 = (int) (points.get(i).getX());
            int y2 = (int) (points.get(i).getY());
            painter.drawLine(x1, y1, x2, y2);
        }
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                MyComponent.ImageFrame frame = new MyComponent.ImageFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

