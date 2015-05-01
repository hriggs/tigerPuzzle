import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Jailer extends JPanel{
    private String jailerSpeech, trialSpeech;
    private JLabel label1,label2;
    private JTextArea text1, trialArea;
    private BufferedImage img1 = null;
    private String tigerFile;
    private int x, y; 
    private Image img;
    
/**
 * The Jailer class... To do: Description here
 * 
 * @author (Henrique, Maria, Hannah) 
 * @version (2015-04-16)
 */
    public Jailer(){
      
      x = 100;
      y = 100; 
        
       trialSpeech = "";
       jailerSpeech="";
       tigerFile="./text/tiger_puzzle.txt";
       setBackground(Color.black);
       readFile(tigerFile);
        try { 
            img1 = ImageIO.read(new File("./images/master_2.gif"));
        }
        catch (Exception e) {
            System.out.println("Image file not found");
        }
        label1 = new JLabel(new ImageIcon(img1));
        
        text1= new JTextArea();
        
        text1.setFont(new Font("Serif", Font.BOLD, 10));
         text1.setForeground(Color.getHSBColor(50, 54, 54));
        text1.setBackground(Color.black);
        text1.append(jailerSpeech);
       
        
        text1.setEditable(false);
        //text1.setForeground(Color.BLACK);
        text1.setBounds(10, 10, 50, 110);
        text1.setWrapStyleWord(true);
        text1.setLineWrap(true);
        text1.setSize(300, 300); 
        
        // set Layout and arrange components
        setLayout(new BorderLayout());
        add(text1, BorderLayout.NORTH);
        add(label1, BorderLayout.CENTER);
        //add(new JTextArea(trialSpeech), BorderLayout.SOUTH);
     }
     public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        ImageIcon img1 = new ImageIcon("./images/prison.png");
         img = img1.getImage();
         g.drawImage(img,0,0,null);
  }
  
    public void readFile(String fileName)
    {
        File file = new File(fileName);
        Scanner scanner = null; 

        //access file
        try {
            scanner = new Scanner(file);
        } 
        catch (FileNotFoundException e) {
            System.out.println("Text file not found.");
        }

        scanner.useDelimiter(System.getProperty("line.separator"));

        String line =""; 

        do 
        {
            // if more lines in file, go to next line
            if (scanner.hasNext())
            {
                line = scanner.nextLine(); 
            }
            // end of file found
            else
            {
                break; 
            }
        }
        while (!line.equals("T1START"));

        // read in first trial text
        while (scanner.hasNext() && !line.equals("T1END"))
        {
            line = scanner.nextLine(); 

            if (!line.equals("T1END"))
            {
                jailerSpeech += line + "\n";
            }
        }
    }
    
    /**
     * Sets the trial text of the jailer.
     * 
     * @param  speech  the trial speech text
     */
    public void setTrialText(String speech) {
        JTextArea speechBox = new JTextArea( BorderLayout.SOUTH);
        speechBox.setBackground(Color.black);
        speechBox.setForeground(Color.getHSBColor(50, 54, 54));
        speechBox.append(speech); 
      //add(new JTextArea(speech), BorderLayout.SOUTH);
    }
  
}