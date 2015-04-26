import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

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
    JPanel centerPanel = new JPanel();
    centerPanel.add(doorOne);
    centerPanel.add(doorTwo);
    centerPanel.add(jailer);
    add(centerPanel, BorderLayout.CENTER);
    
    // center JLabel in top center
    JPanel topPanel = new JPanel(new BorderLayout());
    topPanel.add(trialLabel, BorderLayout.CENTER);
    
    // add left panel in order to center label
    JPanel lPanel = new JPanel();
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