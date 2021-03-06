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
import javax.imageio.ImageIO; 
import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.awt.event.MouseAdapter; 
import java.awt.event.MouseEvent;

/**
 * The Display class... To do: description  
 * 
 * @author (Hannah, Henrique, Maria) 
 * @version (2015-04-20)
 */

public class Display extends JPanel {//change it to extends Game panel later
  
  // keep track of trials
  private ArrayList<Trial> trials;
  private int currentIndex; 
  private int trialNum;
  private int numCorrect;
  private boolean chosenAnswer;
 
  // components
  private JPanel southPanel, centerPanel;
  private JTextArea directions;
  private Image img1,img2;
  private JPanel centerLower; 
  private JLabel resultsText;
  private JLabel scoreLabel;
  
  private Sound sound;

  /**
   * Constructor for objects of type Trial. 
   * @throws javax.sound.sampled.LineUnavailableException
   */
  public Display() throws LineUnavailableException {
    trials = new ArrayList<Trial>(); 
    currentIndex = 0; 
    numCorrect = 0;
    chosenAnswer = false;
    
    setComponents(); 

    // read file to create trials
    readFile("./text/tigerText.txt");
    
    //plays sound effects
    sound = null;
    sound = new Sound();
    //sound.playSound("cave");
    
    // display center panel
    createCenter(); 
  
    // set size
    // will be set by referring to contasts in frame team class
    setPreferredSize(new Dimension(800, 600));
    
    System.out.println(trialNum);
  }
  
  /**
   * Read the file at the given path to add puzzle text.
   * 
   * @param  fileString  path of file
   */
  public void readFile(String fileString) {
     File file = new File(fileString);
     Scanner scanner = null;
     //access file
     try {
       scanner = new Scanner(file);
     } catch (FileNotFoundException e) {
       System.out.println("Text file not found.");
     }
     // read line by line
     scanner.useDelimiter(System.getProperty("line.separator"));
     String line ="";
     String directText = "";
     String jStartText = "";
     // find directions start marker
     do {
     // if more lines in file, go to next line
       if (scanner.hasNext()) {
         line = scanner.nextLine();
       } else
       {
         break;
       }
     } while (!line.equals("DSTART"));
     // until end of directions found
     while (scanner.hasNext() && !line.equals("DEND")) {
       line = scanner.nextLine();
       // add more directions text
       if (!line.equals("DEND")) {
         directText += line;
       }
     }
     // set directions
     directions.append(directText);
     // find jailer start speech marker
     do {
     // if more lines in file, go to next line
       if (scanner.hasNext()) {
         line = scanner.nextLine();
       } else
       {
         break;
       }
     } while (!line.equals("JSTART"));
     // until end of jailer speech found
     while (scanner.hasNext() && !line.equals("JEND")) {
       line = scanner.nextLine();
       // add more of jailer's text
       if (!line.equals("JEND")) {
         jStartText += line;
       }
     }
     // find trial start marker
     do {
       // if more lines in file, go to next line
       if (scanner.hasNext()) {
         line = scanner.nextLine();
       } else
       {
         break;
       }
     } while (!line.equals("TSTART"));
     
     // get number of trials
     trialNum = Integer.parseInt(scanner.nextLine());
     System.out.println(trialNum);
     int num = trialNum + 1;
     
     // for every trial
     for (int i = 1; i < num; i++) {
       // create new trial
       Trial trial = new Trial(new Door(this), new Door(this), new Jailer(), i);
       
       // set door text
       trial.setDoorText(scanner.nextLine(), 1);
       trial.setDoorText(scanner.nextLine(), 2);
       
       // set jailer text
       trial.setJailerStartText(jStartText);
       trial.setJailerTrialText(scanner.nextLine());
       
       // set what is behind each door
       trial.setDoorHasLover(Boolean.parseBoolean(scanner.nextLine()), 1);
       trial.setDoorHasLover(Boolean.parseBoolean(scanner.nextLine()), 2);
       
       trial.setDoorNum("One", 1);
       trial.setDoorNum("Two", 2);

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
    
    // create south panel
    ImageIcon img = new ImageIcon("./images/prison.png");
    southPanel = new JPanel();
    southPanel.setBackground(Color.black);
    southPanel.setLayout(new BorderLayout());
    southPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

    // create directions
    createDirections(); 
    
    // create buttons
    createButtons();
    
    // add south panel to main panel
    add(southPanel, BorderLayout.SOUTH);
  }
  
  /**
   * 
   */
  public void createCenter() {
    centerPanel = new JPanel(new BorderLayout());
    
    // display trial
    centerPanel.add(trials.get(currentIndex), BorderLayout.CENTER);
    
    // create components for bottom half of center panel
    resultsText = new JLabel("Will you choose right?");
    scoreLabel = new JLabel("Correct: 0/" + trialNum);
    
    JButton refuseButton = new JButton("I choose Neither!");
    refuseButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          System.out.println("both tigers button chosen");
          
          if (trials.get(currentIndex).bothHaveTigers()) {
            resultsText.setText("Yes-- both doors have tigers!");
            numCorrect++; 
            scoreLabel.setText("Correct: " + numCorrect + "/" + trialNum);
          } else {
            resultsText.setText("No-- a lover is behind a door!");
          }
        }
      });
      
    centerLower = new JPanel();
    centerLower.setOpaque(false);
    centerLower.add(refuseButton);
    centerLower.add(resultsText);
    centerLower.add(scoreLabel);
    
    centerPanel.add(centerLower, BorderLayout.SOUTH);
    
    add(centerPanel, BorderLayout.CENTER);
  }
  
  /**
   * Creates the buttons. 
   */
  private void createButtons() {
    // buttons in own panel in south
    
    final ImageIcon img = new ImageIcon("./images/prison.png");
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
            chosenAnswer = false;
            
            resultsText.setText("Will you choose right?");

            centerPanel.remove(trials.get(currentIndex));
            
            // increment current index
            currentIndex++;
            
            centerPanel.add(trials.get(currentIndex), BorderLayout.CENTER);
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
  startOverButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
      // display first trial
      remove(centerPanel);
      currentIndex = 0;
      centerPanel.add(trials.get(currentIndex), BorderLayout.CENTER);
      add(centerPanel, BorderLayout.CENTER);
      revalidate();
      repaint();
    }
  });
  // add buttons to panels
  btnPanel.add(startOverButton);
  southPanel.add(btnPanel, BorderLayout.SOUTH);
  
  JButton showAnswerButton = new JButton("Show Behind Doors");
  // add event listent to "show answer" button
  showAnswerButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
      System.out.println("show pressed");
      // show what is behind each door
      trials.get(currentIndex).showBehindDoors(); 
      revalidate();
      repaint();
    }
  });

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
    
    // set directions styling
    directions = new JTextArea("");
    directions.setEditable(false);
    directions.setWrapStyleWord(true);
    directions.setLineWrap(true);
    directions.setBackground(Color.black);
    directions.setForeground(Color.getHSBColor(50, 54, 54));
  
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
  
  public void increaseScore(boolean b) {
    
    if (chosenAnswer) {
      return;
    }
    
    if (b) {
      numCorrect++;
      scoreLabel.setText("Score: " + numCorrect + "/" + trialNum);
    } else {
      chosenAnswer = true;
    }
  }
 
  
  /**
   * Main method for testing purposes.
   */
  public static void main(String[] args)  {
    
   JFrame frame = new JFrame();
   
   Display display = null; 
   
   try {
     display = new Display();
   }
   catch (Exception e) {
     System.out.println("Display could not be created.");
   }
   
   frame.add(display); 
   frame.pack();
   frame.setVisible(true); 
 }
}