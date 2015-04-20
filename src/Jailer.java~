

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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

public class Jailer extends JFrame{
    private String jailerSpeech;
    private JLabel label1,label2;
    private JPanel panel1;
    private JTextArea text1;
    private BufferedImage img1 = null;
    private String tigerFile;
    
     
    public Jailer(){
        
       jailerSpeech="";
       tigerFile="./text/tiger_puzzle.txt";
       readFile(tigerFile);
       panel1= new JPanel();
        try { 
            img1 = ImageIO.read(new File("./images/master_2.gif"));
        }
        catch (Exception e) {
            System.out.println("Image file not found");
        }
         label1 = new JLabel(new ImageIcon(img1));
        
         text1= new JTextArea(jailerSpeech);
         text1.setFont(new Font("Serif", Font.BOLD, 12));
         text1.setEditable(false);
         text1.setForeground(Color.BLACK);
         text1.setBounds(10, 10, 50, 110);
        
         panel1.add(text1);
         panel1.add(label1);
       
       add(panel1,BorderLayout.CENTER);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setSize(900,700);
       setVisible(true);
 
         

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
  
}