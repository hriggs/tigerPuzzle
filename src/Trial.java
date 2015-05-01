import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

/**
 * The Trial class is responsible for an individual trial in the puzzle.
 * A Trial has two Door objects and one Jailer object. 
 * It extends JPanel, so that it can be added to the main JPanel of the game. 
 * 
 * @author (Hannah, Henrique, Maria) 
 * @version (2015-04-20)
 */

public class Trial extends JPanel
{
  
  private JLabel trialLabel; 
  private Door doorOne, doorTwo;
  private Jailer jailer;
  private Image img1,img2;
  
  /**
   * Constructor for objects of type Trial. 
   */
  public Trial(Door d1, Door d2, Jailer j, int trialNum) {
    setLayout(new BorderLayout()); 
    trialLabel = new JLabel("Trial #" + trialNum);
    trialLabel.setFont(new Font("Sans-serif", Font.BOLD, 15));
    
    doorOne = d1;
    doorTwo = d2;
    jailer = j; 
    
    // add objects to panel
    addComponents(); 
  }
  
  /**
   * Add components to trial panel.
   */
  public void addComponents() {
    
    // add items to center panel
   // JPanel centerPanel = new JPanel();
    ImageIcon img = new ImageIcon("./images/prison.png");
   JPanel centerPanel = new JPanel(){ 
       
   protected void paintComponent(Graphics g) {
           super.paintComponent(g);
           img1 = img.getImage();
           g.drawImage(img1, 0,0, null);
          
       };
   };
    
    centerPanel.add(doorOne);
    centerPanel.add(doorTwo);
    centerPanel.add(jailer);
    //centerPanel.setBackground(Color.black);
    add(centerPanel, BorderLayout.CENTER);
    
    // center JLabel in top center
    //JPanel topPanel = new JPanel(new BorderLayout());
    ImageIcon img3 = new ImageIcon("./images/prison.png");
    JPanel topPanel = new JPanel(){ 
       
    protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            img2 = img3.getImage();
            g.drawImage(img2, 0,0, null);
          
        };
    };
    topPanel.add(trialLabel, BorderLayout.CENTER);
    
    // add left panel in order to center label
    JPanel lPanel = new JPanel(){
     protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            img2 = img3.getImage();
            g.drawImage(img2, 0,0, null);
          
        };
    };
    
    // change 800 to constant
    int westSize = (int) ((800 - trialLabel.getPreferredSize().width)/2); 
    lPanel.setPreferredSize(new Dimension(westSize, 5));
    topPanel.add(lPanel, BorderLayout.WEST);
    
    // add top panel in north
    add(topPanel, BorderLayout.NORTH);
  }
  
  
  /**
   * Set text of the jailer.
   * 
   * @param  text  new text to be given to jailer
   */
  public void setJailerText(String text) {
    jailer.setTrialText(text);
  }
}