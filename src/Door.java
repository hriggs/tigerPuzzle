import java.awt.Graphics2D;
import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Color;

/**
 * Door Class is part of the implementation of the Tiger Puzzle application
 * creates a label with a image call door, each object door will be able to draw it self
 * it will be display a text for the user instructions, wich is read from a file
 * 
 * @author (Hannah, Henrique, Maria) 
 * @version (2015-04-16)
 */
public class Door extends JPanel
{
    // instance variables
    //Variable for the position of the door and the size
    private int x;
    private int y;
    private int  width;
    //variable to hold the statment for the user to read
    private String text;
    //variable to check if the lover is behind of the door
    private boolean hasLover;
    //variable to hold the images;
    private ImageIcon doorImage;
    private ImageIcon tigerImage;
    private ImageIcon loverImage;
    //label for each of the images
    private JLabel label;
    private JLabel label1;
    private JLabel label2;
    
    /**
     * Constructor for objects of class Ball
     */
    public Door(int x, int y, int width, int heigh, ImageIcon image)
    {
        this.x = x;
        this.y = y;
        this.width = width;// width for the image
        this.doorImage = image;
    }
    
    /**
     * Constructor for objects of class Ball
     */
    public Door()
    {
        this.x = 50;
        this.y = 50;
        this.width = 200;
        //drawin the image of the door 
        doorImage = new ImageIcon (getClass().getResource("images/door.jpg"));
        //putting the image in a label
        label = new JLabel(doorImage);
        //adding the label in the panel
        add(label);
    }

    /**
     * returns the x position where the image was draw
     */
    public int getX()
    {
        return x;
    }
    
    /**
     * returns the x position where the image was draw
     */
    public int getY()
    {
        return y;
    }
    
    /**
     * returns the width of the image in the label 
     */
    public int getWidth()
    {
       return width;
    }
    
    /**
     * returns the image of the door
     */
    public ImageIcon getImage()
    {
        return doorImage;
    }
    
    /**
     * sets the x and y position where the image is being draw
     */
    public void setPosition(int x, int y)
    {
       this.x = x; 
       this.y = y; 
    }
    
    /**
     * Draws a diferent image when the door is open
     */
    public void openDoor()
    {
        if(hasLover == true)
        {
        loverImage = new ImageIcon (getClass().getResource("images/loverImage.png"));
        label1 = new JLabel(loverImage);    
        }
        else 
        {
        tigerImage = new ImageIcon (getClass().getResource("images/tigerImage.png"));
        label2 = new JLabel(tigerImage);       
        }
    }
    
    /**
     * to pass the test
     */
    public void setText()
    {
       
    }
    
    /**
     * main method creates a frame to add the panel and paint the label
     */
    public static void main(String args[]) 
    {
        JFrame frame = new JFrame();
        
        Door door = new Door();
        JPanel panel = new JPanel();
        panel.add(door);
        panel.setBackground(Color.yellow); //para prueba
        frame.add(panel);
   
        frame.pack();
        frame.setSize(800,1000);
        frame.setVisible(true); 
    }
}
