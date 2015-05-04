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
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.*;

public class Jailer extends JPanel{
    private JLabel label1;
    private JTextArea startArea, trialArea;
    private BufferedImage img1 = null;
    
/**
 * The Jailer class... To do: Description here
 * 
 * @author (Henrique, Maria, Hannah) 
 * @version (2015-04-16)
 */
    public Jailer(){
      setBackground(Color.black);
      
       try {
         img1 = ImageIO.read(new File("./images/master_2.gif"));
       }
       catch (Exception e) {
         System.out.println("Image file not found");
       }
      label1 = new JLabel(new ImageIcon(img1));
        
      startArea = new JTextArea("");
      trialArea = new JTextArea(""); 
        
      startArea.setFont(new Font("Sans-Serif", Font.PLAIN, 10));
      startArea.setForeground(Color.getHSBColor(50, 54, 54));
      startArea.setBackground(Color.black);
      startArea.setEditable(false);
      startArea.setBounds(10, 10, 50, 110);
      startArea.setWrapStyleWord(true);
      startArea.setLineWrap(true);
      startArea.setSize(300, 300); 
      
      trialArea.setFont(new Font("Sans-Serif", Font.PLAIN, 10));
      trialArea.setBackground(Color.black);
      trialArea.setForeground(Color.getHSBColor(50, 54, 54));
      trialArea.setBounds(10, 10, 50, 110);
      trialArea.setEditable(false);
      trialArea.setWrapStyleWord(true);
      trialArea.setLineWrap(true);
      trialArea.setSize(300, 300); 
        
      // set Layout and arrange components
      setLayout(new BorderLayout());
      add(startArea, BorderLayout.NORTH);
      add(label1, BorderLayout.CENTER);
      setOpaque(false);
      add(trialArea, BorderLayout.SOUTH);
     }
    
    /**
     * Sets the trial text of the jailer.
     * 
     * @param  speech  the trial speech text
     */
    public void setTrialText(String speech) {
      trialArea.append(speech); 
    }
    
    /**
     * 
     */
    public void setStartText(String speech) {
      startArea.append(speech);
    }
}