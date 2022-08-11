import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class NBody extends JPanel implements ActionListener
{
    Random random = new Random();

    private static final double G = 100;

    public int n;
    public int x;
    public int y;
    public int size;
    public static int[] xArr;
    public static int[] yArr;
    public static int[] dArr;

    public double dt;
    public double maxVel;
    public double minVel;
    public double maxMass;
    public double minMass;

    public static double[][] ax;
    public static double[][] ay;

    public static double[] dx;
    public static double[] dy;


    public static double[] vxArr;
    public static double[] vyArr;

    public static int[] red;
    public static int[] blue;
    public static int[] green;

    public void init(int n)
    {
        // Your initialization code here:
        this.n = n;

        for (int i = 0; i < n; i++)
        {
            //Assigns xCoordinate, yCoordinate, Diameter(mass) of each star
            xArr[i] = random.nextInt(0,801);
            yArr[i] = random.nextInt(0,801); 
            dArr[i] = random.nextInt(1, 11);

            //Assigns random initial velocity of each star
            vxArr[i] = random.nextDouble(minVel, maxVel);
            vyArr[i] = random.nextDouble(minVel, maxVel);
            
            //Assigns random RGB color to each star
            red[i] = random.nextInt(128,256);
            blue[i] = random.nextInt(128,256);
            green[i] = random.nextInt(128,256);
        }
    }

    // Draw a circle centered at (x, y) with radius r
    public void drawCircle(Graphics g, int x, int y, int r)
    {
        int d = 2*r;
        g.fillOval(x - r, y - r, d, d);
    }

    public void paintComponent(Graphics g)
    {
        // Clear the screen
        super.paintComponent(g);

        for (int i = 0; i < n; i++)
        {
        //Drwaing the stars, coloring them to their assigned colors
        g.setColor(new Color(red[i], blue[i], green[i]));
        drawCircle(g, xArr[i], yArr[i], dArr[i]);
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (j != i)
                {   
                    //Delta Velocity = Acceleration * dt
                    vxArr[i] += dt * ax[i][j];
                    vyArr[i] += dt * ay[i][j];
                    
                    //Delta position = Velocity * dt
                    double xMod = dt * vxArr[i];
                    double yMod = dt * vyArr[i];
                    
                    //Applies positional changes to the stars
                    if (xArr[i] < 800)
                    xArr[i] += xMod;
                    else //If it extends outside canvas (800x800), make it come back
                    xArr[i] = (int) -xMod;


                    if (yArr[i] < 800)
                    yArr[i] += yMod;
                    else //If it extends outside canvas (800x800), make it come back
                    yArr[i] = (int) -yMod;
                }
            }
            forceCalc(n);
        }
        // Repaint the screen
        repaint();
        Toolkit.getDefaultToolkit().sync();
    }

    public void forceCalc(int n)
    {
        //2D array to store star n's acceleration compared to every other stars
        ax = new double[n][n];
        ay = new double[n][n];

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (j != i)
                {
                    //Distance between star i and star j
                    dx[i] = xArr[i] - xArr[j];
                    dy[i] = yArr[i] - yArr[j];
                    double distance = Math.sqrt(dx[i] * dx[i] + dy[i] * dy[i]);
                    
                    //Requested by professor, if distance < 5, it is 5.
                    if (distance < 5)
                        distance = 5;
    
                    //The force between two given stars i and j 
                    double force = (G * dArr[i] * dArr[j]) / (distance * distance);
                    //The acceleration of star i based on the force given its interaction with j
                    ax[i][j] = force / dArr[i];
                    ay[i][j] = force / dArr[i];
                }
            }   
        } 
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int n = Integer.parseInt(args[0]);

        xArr = new int[n];
        yArr = new int[n];
        dArr = new int[n];

        dx = new double[n];
        dy = new double[n];

        vxArr = new double[n];
        vyArr = new double[n];

        red = new int[n];
        blue = new int[n];
        green = new int[n];

        NBody nbody = new NBody();
        nbody.setBackground(Color.BLACK);
        nbody.size = 800;
        nbody.maxVel = 10;
        nbody.minVel = -10;
        nbody.maxMass = 10;
        nbody.minMass = 1;
        nbody.dt = 0.1;
        nbody.setPreferredSize(new Dimension(nbody.size, nbody.size));
        nbody.init(n);
        nbody.forceCalc(n);

        frame.add(nbody);
        frame.pack();

        Timer timer = new Timer(16, nbody);
        timer.start();

        frame.setVisible(true);

        System.out.println();
    }
}
