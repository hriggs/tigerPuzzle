//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.io.*;
import java.util.*; 
import java.io.FileNotFoundException;
import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import javax.imageio.ImageIO;

/**
 * Door Class is part of the implementation of the Tiger Puzzle application
 * creates a label with a image call door, each object door will be able to draw it self
 * it will be display a text for the user instructions, which is read from a file
 * 
 * @author (Maria, Henrique, Hannah) 
 * @version (2015-04-16)
 */
public class Door extends JPanel
{
   
    //Variable for the position of the door and the size
    /*private int x;
    private int y;*/
    //private int  width;
    //variable to hold the statment for the user to read
    private String text;
    // text area where the statements are printing 
    private JTextArea txtArea;
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
    public Door(int x, int y, int width, int heigh)
    {
        /*this.x = x;
        this.y = y;*/
       // this.width = width;
        //this.doorImage = image;
    }
    
    /**
     * Constructor for objects of class Ball
     */
    public Door()
    {
        /*this.x = 50;
        this.y = 50;*/
        //this.width = 200;
        //drawin the image of the door 
        try { 
            // gave me errors due to the file path and I wasn't sure how to fix it, so I switched to code below
            //doorImage = new ImageIcon (getClass().getResource("door.jpg"));
            doorImage = new ImageIcon("../images/door.jpg");
        }
        catch (Exception e) {
            System.out.println("Door image file not found");
        }
        
        //putting the image in a label
        label = new JLabel(doorImage);
        //adding the label in the panel
        add(label);
    }

    /**
     * returns the x position where the image was draw
     */
    /*public int getX()
    {
        return x;
    }*/
    
    /**
     * returns the x position where the image was draw
     */
    /*public int getY()
    {
        return y;
    }*/
    
    /**
     * returns the width of the image in the label 
     */
    /*public int getWidth()
    {
       return width;
    }*/
    
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
    /*public void setPosition(int x, int y)
    {
       this.x = x; 
       this.y = y; 
    }*/
    
    /**
     * Draws a diferent image when the door is open
     */
    public void openDoor()
    {
        if(hasLover == true)
        {
        loverImage = new ImageIcon (getClass().getResource("loverImage.png"));
        label1 = new JLabel(loverImage);    
        }
        else 
        {
        tigerImage = new ImageIcon (getClass().getResource("tigerImage.png"));
        label2 = new JLabel(tigerImage);       
        }
    }
    
    /**
     * to pass the text
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
        
        //creatring the text Area zone
        JTextArea txtArea = new JTextArea(5,10);
        txtArea.append(reader());
        txtArea.setBackground(Color.blue);
        //txtArea.setFont(new Font("Serif", Font.ITALIC, 20));
        //txtArea.setForeground(new Color(28, 94, 161));
        txtArea.setEditable(false);
        //add(txtArea);
        
        Door door = new Door();
        JPanel panel = new JPanel();
        panel.add(door);
        panel.add(txtArea);
        panel.setBackground(Color.yellow); //para prueba
        frame.add(panel);
   
        frame.pack();
        frame.setSize(800,800);
        frame.setVisible(true); 
    }
    
    /**
     * opens the file for the clues, and read it, returns a tring with the content of the file
     **/
    public static String reader()
    {
        String s = "";
        try {
            //open the file
            File file = new File("trial.txt");
            //reads the file
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            //reads line by line
            while ((line = bufferedReader.readLine()) != null) 
            {
                stringBuffer.append(line);
                stringBuffer.append("\n");
            }
            fileReader.close();
            //string that saves the content from the file
            s = stringBuffer.toString();
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return s;
    }
}
