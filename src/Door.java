//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;


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
    //variable to hold the statment for the user to read
    private String text;
    // text area where the statements are printing 
    private JTextArea txtArea;

   // private JLabel label1,label2;
    private JTextArea text1, trialArea;
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

    private JLabel doorLabel;
    private JButton d1,d2;
     
    private Image img;
    
    private Sound sound;
    /**
     * Constructor for objects of class Ball
     */
    public Door(int x, int y, int width, int heigh)
    {

    }
    
    /**
     * Constructor for objects of class Ball
     */
    public Door()
    {
        try {
            doorImage = new ImageIcon("./images/door.gif");
        }
        catch (Exception e) {
            System.out.println("Door image file not found");
        }
        
        //putting the image in a label
        label = new JLabel(doorImage);
        //adding the label in the panel
        add(label);
        d1 = new JButton(doorImage);
        
        d1.setOpaque(false);
        d1.setContentAreaFilled(false);
        d1.setBorderPainted(false);
        d1.setFocusPainted(false);

        doorLabel = new JLabel("Door No. ");
        doorLabel.setBackground(Color.black);
        doorLabel.setForeground(Color.getHSBColor(50, 54, 54));

        txtArea = new JTextArea(" hola");
        txtArea.setFont(new Font("Serif", Font.BOLD, 10));
        txtArea.setEditable(false);
        txtArea.setOpaque(false);
        txtArea.setBackground(Color.black);
        txtArea.setForeground(Color.getHSBColor(50, 54, 54));
        txtArea.setBounds(10, 10, 50, 110);
        txtArea.setWrapStyleWord(true);
        txtArea.setLineWrap(true);
        txtArea.setSize(30, 100);


        // set Layout and arrange components and transparency of panel
         setLayout(new BorderLayout());
         setOpaque(false);
        //add(label, BorderLayout.NORTH);
        add(d1,BorderLayout.NORTH);
        d1.addActionListener(new doorButton());
        
        add(doorLabel, BorderLayout.CENTER);
        add(txtArea, BorderLayout.SOUTH);
        setBackground(Color.black);
        
        
        
    }

    /**
     * returns the image of the door
     */
    public ImageIcon getImage()
    {
        return doorImage;
    }
    

    /**
     * Draws a different image when the door is open
     */
    
    ///Henrique is still working on this. I can always go back to the opendoor method.
    private class doorButton implements ActionListener{ 
       Sound sound = new Sound();
       boolean hasLover = false;
       Thread t = new Thread();
       
     public void actionPerformed(ActionEvent evt) {
        if(hasLover == false)
        {
       
        loverImage = new ImageIcon ("./images/loverImage.gif");
        label2 = new JLabel(loverImage);
        add(label2,BorderLayout.NORTH);
        d1.setVisible(false);
            try {
               
                sound.playSound("love");
            } catch (LineUnavailableException ex) {
                Logger.getLogger(Door.class.getName()).log(Level.SEVERE, null, ex);
            }
        revalidate();
        repaint();
        }
        else 
        {
        tigerImage = new ImageIcon ("./images/tigerImage.gif");
        label2 = new JLabel(tigerImage);
        add(label2,BorderLayout.NORTH);
         d1.setVisible(false);
        
        
         try {
               
                sound.playSound("tiger");
            } catch (LineUnavailableException ex) {
                Logger.getLogger(Door.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        revalidate();
        repaint();
        }
     
}
    }
    
    /**
     * to pass the text
     */
    public void setText(String doorText)
    {
        add(txtArea);
       
    }
    
    /**
     * main method creates a frame to add the panel and paint the label
     */
    /*public static void main(String args[])
    {
        JFrame frame = new JFrame();
        
        //creatring the text Area zone
        JTextArea txtArea = new JTextArea(5,10);
        //txtArea.append(reader());
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
    }*/
    
    /**
     * opens the file for the clues, and read it, returns a tring with the content of the file
     **/
   /* public static String reader()
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
    }*/
}
