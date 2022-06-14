import java.awt.*;
import javax.swing.*;

public class DrawTicTacToe extends Canvas
{
    public int xRow, xCol;
    public int oRow, oCol;

    public void drawBoard(Graphics g) 
    {
        //Draw TicTacToe Board
        g.drawLine(150, 50, 150, 350);
        g.drawLine(250, 50, 250, 350);
        g.drawLine(50, 150, 350, 150);
        g.drawLine(50, 250, 350, 250);
    }

    public void drawX(Graphics g)
    {
        //Draw X on Board
        int firstLinePointX = (xCol * 100 + 65);
        int firstLinePointY = (xRow * 100 + 65);
        int firstLineSecondPointX = (xCol * 100 + 135);
        int firstLineSecondPointY = (xRow * 100 + 135);

        int secondLinePointX = (xCol * 100 + 65);
        int secondLinePointY = (xRow * 100 + 135);
        int secondLineSecondPointX = (xCol * 100 + 135);
        int secondLineSecondPointY = (xRow * 100 + 65);

        g.setColor(Color.RED);
        g.drawLine (firstLinePointX, firstLinePointY, firstLineSecondPointX, firstLineSecondPointY);
        g.drawLine (secondLinePointX, secondLinePointY, secondLineSecondPointX, secondLineSecondPointY);
    }

    public void setupCircle(Graphics g, int x, int y, int r)
    {
        //Setup Circle
        g.setColor(Color.BLUE);
        int diameter = 2*r;
        g.drawOval(x - r, y - r, diameter, diameter);
    }


    public void drawO(Graphics g)
    {
        //Draw O on Board
        setupCircle(g, oCol * 100 + 100, oRow * 100 + 100, 35);
    }


    public void paint(Graphics g)
    {
        drawBoard(g);
        drawX(g);
        drawO(g);
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DrawTicTacToe drawing = new DrawTicTacToe();
        drawing.xRow = Integer.parseInt(args[0]);
        drawing.xCol = Integer.parseInt(args[1]);
        drawing.oRow = Integer.parseInt(args[2]);
        drawing.oCol = Integer.parseInt(args[3]);
        drawing.setSize(400, 400);
        frame.add(drawing);
        frame.pack();
        frame.setVisible(true);
    }
}