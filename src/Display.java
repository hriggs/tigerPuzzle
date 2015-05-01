import javax.swing.*;
import java.util.ArrayList; 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.swing.border.EmptyBorder;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * The Display class... To do: description  
 * 
 * @author (Hannah, Henrique, Maria) 
 * @version (2015-04-20)
 */

public class Display extends JPanel {//change it to extends Game panel later
  
  private ArrayList<Trial> trials;
  private int currentIndex; 
 
  
  // components
  private JPanel southPanel;
  private JTextArea directions;
  private Image img1,img2;
  
  private Sound sound;
  
    


  /**
   * Constructor for objects of type Trial. 
     * @throws javax.sound.sampled.LineUnavailableException
   */
  public Display() throws LineUnavailableException {
    trials = new ArrayList<>(); 
    currentIndex = 0; 
    
    setComponents(); 
    
    
    // read file to create trials
    readFile("file name goes here");
    sound = new Sound();
    sound.playSound("cave");
    // display 1st trial
    add(trials.get(currentIndex), BorderLayout.CENTER);
    //southPanel.setBackground(Color.darkGray);
   
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
    
    // set directions from file
    directions.setBackground(Color.black);
    directions.setForeground(Color.getHSBColor(50, 54, 54));
    directions.append("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
  }
  
  /**
   * Adds and sets the components in the display panel. 
   */
  public void setComponents() {
    setLayout(new BorderLayout());
    
    createTitle();
    
    // create panel south panel
   // southPanel = new JPanel();
    ImageIcon img = new ImageIcon("./images/prison.png");
     southPanel = new JPanel();
     southPanel.setBackground(Color.black);
     southPanel.setLayout(new BorderLayout());
     southPanel.setBorder(new EmptyBorder(10, 10, 10, 10) );

    // create directions
    createDirections(); 
    
    // create buttons
    createButtons();
    
    // add south panel to main panel
    add(southPanel, BorderLayout.SOUTH);
  }
  
  /**
   * Creates the buttons. 
   */
  private void createButtons() {
    // buttons in own panel in south
    
    ImageIcon img = new ImageIcon("./images/prison.png");
    JPanel btnPanel = new JPanel(){
       
    protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            img1 = img.getImage();
           g.drawImage(img1, 0,0, null);
          
        };
    };
    btnPanel.setLayout(new FlowLayout()); 
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
    
    // add buttons to panels
    btnPanel.add(nextButton);
  
  
    southPanel.add(btnPanel, BorderLayout.SOUTH);

  JButton startOverButton = new JButton("Start Over");
  // add event listent to "start Over" button
//  startOverButton.addActionListener(new ActionListener() {
//    public void actionPerformed(ActionEvent evt) {
//
//    }
//  });
  // add buttons to panels
  btnPanel.add(startOverButton);
  southPanel.add(btnPanel, BorderLayout.SOUTH);


  JButton showAnswerButton = new JButton("Show Answer");
  // add event listent to "show answer" button
//  showAnswerButton.addActionListener(new ActionListener() {
//    public void actionPerformed(ActionEvent evt) {
//
//    }
//  });
//
  // add buttons to panels
  btnPanel.add(showAnswerButton);
  southPanel.add(btnPanel, BorderLayout.SOUTH);
  }

  /**
   * Adds the directions to the puzzle.
   */
  private void createDirections() {
    
    // add directions label
    JLabel directTitle = new JLabel("Directions");
    directTitle.setFont(new Font("Sans-serif", Font.BOLD, 15));
    directTitle.setForeground(Color.getHSBColor(50, 54, 54));
    southPanel.add(directTitle, BorderLayout.NORTH);
    
    // set direction text 
    directions = new JTextArea("");
    directions.setEditable(false);
    directions.setWrapStyleWord(true);
    directions.setLineWrap(true);
  
    // add directions to south panel
   southPanel.add(directions, BorderLayout.CENTER); 
  }
  
  /**
   * Adds the title to the puzzle.
   */
  private void createTitle() {
    //JPanel northPanel = new JPanel();
     final ImageIcon img4 = new ImageIcon("./images/prison.png");
    JPanel northPanel = new JPanel(){ 
       
    protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            img2 = img4.getImage();
            g.drawImage(img2, 0,0, null);
          
        };
    };
    
        
    
    // create title
    JLabel title = new JLabel("The Tiger Puzzle");
    title.setFont(new Font("Sans-serif", Font.BOLD, 30));
    title.setForeground(Color.getHSBColor(50, 54, 54));
   
    
    
    // add to north panel
    northPanel.add(title);
    add(northPanel, BorderLayout.NORTH); 
    
    
  }

  

  
}
