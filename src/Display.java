import javax.swing.*;
import java.util.ArrayList; 
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Font;

/**
 * The Display class... To do: description  
 * 
 * @author (Hannah, Henrique, Maria) 
 * @version (2015-04-20)
 */

public class Display extends JPanel {
  
  private ArrayList<Trial> trials;
  private int currentIndex; 

  /**
   * Constructor for objects of type Trial. 
   */
  public Display() {
    trials = new ArrayList<Trial>(); 
    currentIndex = 0; 
    
    // read file to create trials
    readFile("file name goes here");
    
    setComponents();
    
    // set size
    // width must be 800 by 600 (width by height) according to frame team
    // will be set by referring to contasts in frame team class
    setPreferredSize(new Dimension(800, 600));
  }
  
  /**
   * Read the file at the given path to add puzzle text.
   * 
   * @param  text  path of file
   */
  public void readFile(String text) {
    
    // In reality this will read from a file, but for testing purposes text is hardcoded
    // imagining 4 trials in this case
    
    for (int i = 1; i < 5; i++) {
      
      // create trial
      Trial trial = new Trial(new Door(), new Door(), new Jailer(), i);
      
      // set text
      trial.setJailerText("Jailer's text goes here. Trial number: " + i);
      
      // add trial to list
      trials.add(trial);
    }
  }
  
  /**
   * Adds and sets the components in the display panel. 
   */
  public void setComponents() {
    setLayout(new BorderLayout());
    
    createTitle();
    
    // display 1st trial
    add(trials.get(currentIndex), BorderLayout.CENTER);
    
    // create panel of buttons in south
    JPanel southPanel = new JPanel();
    JButton nextButton = new JButton("Next Trial");
    
    // add event listent to "next trial" button
    nextButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          
          // check if another trial
          if (!(currentIndex + 1 >= trials.size()))
          {
            // increment current index
            currentIndex++;

            // display next trial
            remove(trials.get(currentIndex - 1));
            add(trials.get(currentIndex));
            revalidate();
            repaint();
          }
        }
      });
    
    // add button to south panel
    southPanel.add(nextButton);
    add(southPanel, BorderLayout.SOUTH);
  }
  
  /**
   * Adds the title to the puzzle.
   */
  private void createTitle() {
    JPanel northPanel = new JPanel();
    
    JLabel title = new JLabel("The Tiger Puzzle");
    title.setFont(new Font("Sans-serif", Font.BOLD, 30));
    
    
    northPanel.add(title);
    
    add(northPanel, BorderLayout.NORTH); 
    
  }
}